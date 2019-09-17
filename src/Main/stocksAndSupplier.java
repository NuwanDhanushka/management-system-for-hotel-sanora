/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Nuwan
 */
public class stocksAndSupplier extends Application {
    Image cursor = new Image("/Images/cursor.png");
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/mainInterFace.fxml"));
        Scene scene = new Scene(root);
        scene.setCursor(new ImageCursor(cursor));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stocks and Supplier Management");
        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.setMaximized(false);
        primaryStage.setMinHeight(500.0);
        primaryStage.setMinWidth(850.0);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
