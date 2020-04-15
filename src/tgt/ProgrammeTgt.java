/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgt;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author gogo-
 */
public class ProgrammeTgt extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
           
            Parent root = FXMLLoader.load(getClass().getResource("/tgt/Views/main.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("/Achat/Views/AdminCommandes.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("/Achat/Views/CommandePayment.fxml"));

            Scene scene = new Scene(root);
            
            primaryStage.setTitle("TGT!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProgrammeTgt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
