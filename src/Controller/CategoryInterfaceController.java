/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import BusinessLogicLayer.categoryBLL;
import Controllers.MainHomeController;
import Controllers.SubHomeController;
import DataAccessLayer.category;
import DataGateway.categoryDataGateway;
import DataList.categoryList;
import databaseConfig.DBConnection;
import databaseConfig.SQL;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author Nuwan
 */
public class CategoryInterfaceController implements Initializable {

    @FXML
    private Button btnRefresh;
    @FXML
    private TableView<categoryList> tblViewCategory;
    @FXML
    private TableColumn<Object, Object> tblClmCategoryBrand;
    @FXML
    private TableColumn<Object, Object> tblClmCategorySupplier;
    @FXML
    private TableColumn<Object, Object> tblClmCategoryCreator;
    @FXML
    private TableColumn<Object, Object> tblClmCategoryDate;
    @FXML
    private TableColumn<Object, Object> tblClmCategoryDescription;
    @FXML
    private TableColumn<Object, Object> tblClmCategoryId;
    @FXML
    private TableColumn<Object, Object> tblClmCategoryName;

    
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();
    categoryDataGateway cdataGateway = new categoryDataGateway();
    category category = new category();
    categoryBLL categoryBLL =new categoryBLL();
    @FXML
    private TextField tfsearch;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Supplier & Stocks - Categories");
        subHomeC.backBtn.setVisible(true);//set back button
        SubHomeController.backUrl = "/GUIs/SupplierHome.fxml";
    }    

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        category.categoryList.clear();
        tblClmCategoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClmCategoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tblClmCategoryBrand.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        tblClmCategorySupplier.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblClmCategoryDescription.setCellValueFactory(new PropertyValueFactory<>("categoryDescription"));
        tblClmCategoryCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        tblClmCategoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cdataGateway.view(category);
        
    }

    @FXML
    private void btnAddCategoryOnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Interfaces/addCategory.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if(tblViewCategory.getSelectionModel().getSelectedItem() != null){
            viewDetails();
        }else {
                Notifications.create().title("Select a row ")
              .text("Please select a row!")
               .position(Pos.CENTER)
              .showWarning();
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
            categoryList selectedCatagory = tblViewCategory.getSelectionModel().getSelectedItem();
            category.id = selectedCatagory.getId();
            System.out.println(category.id+ "On hear");
            categoryBLL.delete(category);
            tblViewCategory.getItems().clear();
            showDetails();
    }
   
  public void showDetails(){
        tblViewCategory.setItems(category.categoryList);
        tblClmCategoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClmCategoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tblClmCategoryBrand.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        tblClmCategorySupplier.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblClmCategoryDescription.setCellValueFactory(new PropertyValueFactory<>("categoryDescription"));
        tblClmCategoryCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        tblClmCategoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cdataGateway.view(category);
   

    }
   private void viewDetails() {
        if(!tblViewCategory.getSelectionModel().isEmpty()){
            categoryList selectedCatagory = tblViewCategory.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedCatagory.getCreatorId());
            String items = selectedCatagory.getId();
            if (!items.equals(null)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Interfaces/updateCategory.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    UpdateCategoryController UpdateCategoryController = fxmlLoader.getController();
                    UpdateCategoryController.categoryId = selectedCatagory.id;
                    UpdateCategoryController.showDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("empty Selection");
        }
   }

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
        category.categoryList.clear();
        category.categoryName = tfsearch.getText().trim();
        tblViewCategory.setItems(category.categoryList);
        tblClmCategoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClmCategoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tblClmCategoryBrand.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        tblClmCategorySupplier.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblClmCategoryDescription.setCellValueFactory(new PropertyValueFactory<>("categoryDescription"));
        tblClmCategoryCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        tblClmCategoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cdataGateway.searchView(category);

    }
}
