package gui;

/**
 * The AoCHelper is a program, that simplifies the complex Arch/Class-Design of the game Ashes of Creation.
 * It uses the User-Inputs to determine the resulting arch/class. The resulting arch/class is then used to create
 * Hyperlinks to the offical Ashes of Creation Community Wiki for an easy access to all availbe informations to
 * the regarding class/arch.
 *
 * Disclaimer: This project was for demonstration/learning purposes only
 *
 * @author Stefan Procik
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("Ashes of Creation Arch Helper");
        primaryStage.setMinWidth(1100);
        primaryStage.setMinHeight(770);
        primaryStage.setScene(new Scene(root, 1082, 753));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
