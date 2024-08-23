package co.edu.unbosque.controller;

import co.edu.unbosque.model.operacionesTiro;
import co.edu.unbosque.view.View;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Controller {
    private operacionesTiro model;
    private View view;

    public Controller(operacionesTiro model, View view) {
        this.model = model;
        this.view = view;

        setupListeners();
    }

    private void setupListeners() {
        view.getAngleSlider().valueProperty().addListener((obs, oldVal, newVal) -> {
            model.setAngulo(newVal.doubleValue());
            view.getAngleLabel().setText("Ángulo: " + String.format("%.0f", model.getAngulo()) + "°");
            updateCannonLine();
        });

        view.getPowerSlider().valueProperty().addListener((obs, oldVal, newVal) -> {
            model.setVelocidad(newVal.doubleValue());
            view.getPowerLabel().setText("Potencia: " + String.format("%.0f", model.getVelocidad()));
        });

        view.getShootButton().setOnAction(this::handleShootButton);
    }

    private void handleShootButton(ActionEvent event) {
        model.resetTiempo();
        shootProjectile();
    }

    private void shootProjectile() {
        double projectileVelocityX = model.getProjectileVelocityX();
        double projectileVelocityY = model.getProjectileVelocityY();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            model.incrementTiempo(0.1);

            double x = projectileVelocityX * model.getTiempo();
            double y = projectileVelocityY * model.getTiempo() - (0.5 * model.getGravedad() * model.getTiempo() * model.getTiempo());

            view.getProyectil().setCenterX(view.getCanon().getX() + x);
            view.getProyectil().setCenterY(view.getCanon().getY() - y);

            if (view.getProyectil().getCenterX() <= 0 || view.getProyectil().getCenterX() >= 600 ||
                view.getProyectil().getCenterY() >= 265 || view.getProyectil().getCenterY() <= 0) {
            	showCollisionImage();
                resetProjectilePosition();
            }
        }));

        timeline.setCycleCount(127);
        timeline.play();
    }
    
    private void showCollisionImage() {
        view.getCollisionImageView().setVisible(true);

        // Centrar la imagen en la pantalla
        view.getCollisionImageView().setX((view.getScene().getWidth() - view.getCollisionImageView().getFitWidth()) / 2);
        view.getCollisionImageView().setY((view.getScene().getHeight() - view.getCollisionImageView().getFitHeight()) / 2);

        // Ocultar la imagen después de 1 segundo
        Timeline hideImageTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            view.getCollisionImageView().setVisible(false);
        }));
        hideImageTimeline.play();
    }
    private void updateCannonLine() {
        double radianAngle = Math.toRadians(model.getAngulo());
        double length = 50;
        view.getCanonLinea().setEndX(view.getCanonLinea().getStartX() + length * Math.cos(radianAngle));
        view.getCanonLinea().setEndY(view.getCanonLinea().getStartY() - length * Math.sin(radianAngle));
    }

    private void resetProjectilePosition() {
        view.getProyectil().setCenterX(view.getCanon().getX() + view.getCanon().getWidth());
        view.getProyectil().setCenterY(view.getCanon().getY());
    }
}

