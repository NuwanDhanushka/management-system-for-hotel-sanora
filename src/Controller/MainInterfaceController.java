/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.StocksInterfaceController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class MainInterfaceController implements Initializable {

    @FXML
    private Button btnSupplier;
    @FXML
    private ImageView imgSupplierBtn;
    @FXML
    private ToggleButton menuToogleBtn;
    @FXML
    private ImageView toggleMenuImg;
    Image toggleOffImage = new Image("/Images/toggleMenuOff.png");
    Image toggleOnImage = new Image("/Images/toggleMenuOn.png");
    @FXML
    private AnchorPane Menu;
    @FXML
    private ScrollPane menuPane;
    @FXML
    private StackPane content;
    @FXML
    private Button btnStocks;
    @FXML
    private Button btnBrand;
    @FXML
    private Button btnCategory;
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSupplierOnClick(ActionEvent event) throws IOException {
     FXMLLoader fxmlLoader = new FXMLLoader();
     fxmlLoader.load(getClass().getResource("/Interfaces/supplierInterface.fxml").openStream());
     SupplierInterfaceController supplierController = fxmlLoader.getController();;
     supplierController.showDetails();
     AnchorPane root = fxmlLoader.getRoot();//take content to root anchorpane of fxml that is loaded
     content.getChildren().clear();//remove all items
     content.getChildren().add(root);//set root objects to content 
    }
    @FXML
    private void menuToogleOnCLick(ActionEvent event) {
            if (menuToogleBtn.isSelected()) {
            toggleMenuImg.setImage(toggleOnImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), Menu);
            sideMenu.setByX(-130);
            sideMenu.play();
            Menu.getChildren().clear();
        } else {
            toggleMenuImg.setImage(toggleOffImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), Menu);
            sideMenu.setByX(130);
            sideMenu.play();
            Menu.getChildren().add(menuPane);
        }
    }

    @FXML
    private void btnStocksOnClick(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader();
           fxmlLoader.load(getClass().getResource("/Interfaces/stocksInterface.fxml").openStream());
           StocksInterfaceController stockController = fxmlLoader.getController();
           stockController.viewDetails();
           AnchorPane root = fxmlLoader.getRoot();
           content.getChildren().clear();
           content.getChildren().add(root);
    }

    @FXML
    private void btnBrandOnClick(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader();
           fxmlLoader.load(getClass().getResource("/Interfaces/brandInterface.fxml").openStream());
           BrandInterfaceController brandController = fxmlLoader.getController();
           brandController.showDetails();
           AnchorPane root = fxmlLoader.getRoot();
           content.getChildren().clear();
           content.getChildren().add(root);
    }

    @FXML
    private void btnCategoryOnClick(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader();
          fxmlLoader.load(getClass().getResource("/Interfaces/categoryInterface.fxml").openStream());
          CategoryInterfaceController categoryController = fxmlLoader.getController();
           categoryController.showDetails();
           AnchorPane root = fxmlLoader.getRoot();
           content.getChildren().clear();
           content.getChildren().add(root);
    }
    
}
