/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evenement;
import Entities.Region;
import Services.EvenementService;
import Services.RegionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AfficherAllController implements Initializable {
@FXML
    private Pane firstpane;
@FXML
private TextField rechercher ;
    @FXML
    private TableView<Evenement> Evenement;
    
    @FXML
    private TableColumn<Evenement, String> nomevent;
    @FXML
    private TableColumn<Evenement, String> Duree;
    
    @FXML
    private TableColumn<Evenement, String> Date_d;
    @FXML
    private TableColumn<Evenement, String> Date_f;
    @FXML
    private TableColumn<Evenement, String> Gagnant;
    @FXML
    private TableColumn<Evenement, Integer> Etat;
    @FXML
    private TableColumn<Evenement, Integer> MaxParticipants;
    @FXML
    private TableColumn<Evenement, Integer> region_id;
    @FXML
    private TableColumn<Evenement, String> image;
    public ObservableList<Evenement> data = FXCollections.observableArrayList();
EvenementService s = new EvenementService () ;

    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button bloquer;
    @FXML
    private TableColumn<?,?> nomsearch ;
    @FXML
    private TableColumn<?,?> etatsearch ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomevent.setCellValueFactory(new PropertyValueFactory<>("nomevent"));
         Duree.setCellValueFactory(new PropertyValueFactory<>("Duree"));
         Date_d.setCellValueFactory(new PropertyValueFactory<>("Date_d"));

         Date_f.setCellValueFactory(new PropertyValueFactory<>("Date_f"));
         Gagnant.setCellValueFactory(new PropertyValueFactory<>("Gagnant"));
                  Etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
               MaxParticipants.setCellValueFactory(new PropertyValueFactory<>("MaxParticipants"));
                  region_id.setCellValueFactory(new PropertyValueFactory<>("region_id"));
                  image.setCellValueFactory(new PropertyValueFactory<>("image"));

            
        
    
       
  
       
        
        try {
            
            data = s.indexAction();
            
            Evenement.setItems(data);
           
            
           // System.out.println(data.get(1));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }   
    
    @FXML
    private void ajouterevent(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("Ajout.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
    }
    @FXML
    private void modifierevent(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("Modifier.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 1024, 768));
        
    }
    @FXML
    public void rechercherevent(ActionEvent event) throws IOException,SQLException{
      EvenementService s = new EvenementService ();
      
     
      Evenement e = new Evenement () ;
      e =s.rechercheEvenement(rechercher.getText()) ;
      if (e!=null)
      {
          new Alert(Alert.AlertType.INFORMATION, "Evénement Trouvé !").show();
          nomsearch.setText(e.getNomevent());
          etatsearch.setText(String.valueOf(e.getEtat()));
      }
      else
      {
          nomsearch.setText("No result");
          etatsearch.setText("No result");
          new Alert(Alert.AlertType.INFORMATION, "Evénement Introuvable ! :(((").show();
          
      }
      
        /*if(s.rechercheEvenement(rechercher.getText()) ){
            
            new Alert(Alert.AlertType.INFORMATION, "Trouvé !").show();
            System.out.println(e.getNomevent());
        }
        else{
      
      new Alert(Alert.AlertType.INFORMATION, "Pas trouvé ! :(((").show();
        }*/
    }
    @FXML
    private void deleteevent(ActionEvent event) throws SQLException {
        
        if(Evenement.getSelectionModel().getSelectedItems().size()!=0) {
           
           s.supprimerEvenement(Evenement.getSelectionModel().getSelectedItems().get(0).getId());
           new Alert(Alert.AlertType.INFORMATION, "Evénement supprimé !").show();
             try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("afficherAll.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            
            
        }
        
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       }
    }
    @FXML
    private void bloquerevent(ActionEvent event) throws SQLException {
        if(Evenement.getSelectionModel().getSelectedItems().size()!=0) {
           
           s.BloquerEvenement(Evenement.getSelectionModel().getSelectedItems().get(0).getId());
           new Alert(Alert.AlertType.INFORMATION, "Evénement bloqué !").show();
             try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("afficherAll.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            
            
        }
        
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       }
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/UI/MainInterface.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
    }
    
    
    
}
