package co.edu.unbosque.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View {
    private Pane root;
    private Rectangle base;
    private Rectangle canon;
    private Line canonLinea;
    private Circle proyectil;
    private Slider angleSlider;
    private Slider powerSlider;
    private Button shootButton;
    private Label angleLabel;
    private Label powerLabel;
    private Scene scene;
    private ImageView collisionImageView;

    public View(Stage primaryStage) {
        root = new Pane();
        root.setStyle("-fx-background-color: blue;");

        base = new Rectangle(600, 20, Color.GREEN);
        base.setX(0);
        base.setY(270);

        canon = new Rectangle(40, 20, Color.GRAY);
        canon.setX(0);
        canon.setY(250);

        canonLinea = new Line();
        canonLinea.setStartX(canon.getX() + canon.getWidth());
        canonLinea.setStartY(canon.getY() + canon.getHeight() - 20);
        canonLinea.setEndY(245);
        canonLinea.setEndX(65);

        proyectil = new Circle(10, Color.RED);
        proyectil.setCenterX(canon.getX() + canon.getWidth() - 10);
        proyectil.setCenterY(getCanon().getY() - 10);

        angleSlider = new Slider(0, 90, 45);
        angleSlider.setShowTickLabels(true);
        angleSlider.setShowTickMarks(true);

        angleLabel = new Label("Ángulo: 45°");

        powerSlider = new Slider(10, 100, 50);
        powerSlider.setShowTickLabels(true);
        powerSlider.setShowTickMarks(true);

        powerLabel = new Label("Potencia: 50");

        shootButton = new Button("Disparar");

        HBox controls = new HBox(10, angleLabel, angleSlider, shootButton);
        controls.setPadding(new javafx.geometry.Insets(10));

        VBox layout = new VBox(10, root, controls);

        scene = new Scene(layout, 600, 400);

        primaryStage.setTitle("Simulación de Tiro Parabólico (Proyecto Nucleo 1)");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Image collisionImage = new Image(getClass().getResourceAsStream("MyIcon.png"));
        collisionImageView = new ImageView(collisionImage);
        collisionImageView.setFitWidth(100);
        collisionImageView.setFitHeight(100); 
        collisionImageView.setVisible(false); 

        // Centrar la imagen en la pantalla
        collisionImageView.setX((scene.getWidth() - collisionImageView.getFitWidth()) / 2);
        collisionImageView.setY((scene.getHeight() - collisionImageView.getFitHeight()) / 2);

        root.getChildren().add(collisionImageView);
        
    }

    public Pane getRoot() {
        return root;
    }

    public Rectangle getBase() {
        return base;
    }

    public Rectangle getCanon() {
        return canon;
    }

    public Line getCanonLinea() {
        return canonLinea;
    }

    public Circle getProyectil() {
        return proyectil;
    }

    public Slider getAngleSlider() {
        return angleSlider;
    }

    public Slider getPowerSlider() {
        return powerSlider;
    }

    public Button getShootButton() {
        return shootButton;
    }

    public Label getAngleLabel() {
        return angleLabel;
    }

    public Label getPowerLabel() {
        return powerLabel;
    }

    public Scene getScene() {
        return scene;
    }
    
    public ImageView getCollisionImageView() {
        return collisionImageView;
    }
}

