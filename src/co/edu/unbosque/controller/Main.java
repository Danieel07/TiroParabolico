package co.edu.unbosque.controller;
import co.edu.unbosque.model.operacionesTiro;
import co.edu.unbosque.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        operacionesTiro model = new operacionesTiro();
        View view = new View(primaryStage);
        new Controller(model, view);
        view.getRoot().getChildren().addAll(
            view.getBase(),
            view.getCanon(),
            view.getCanonLinea(),
            view.getProyectil()
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}


