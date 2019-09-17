/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerControllers;

import Controllers.CustHomeController;
import Controllers.MainHomeController;
import static Controllers.MainHomeController.subHome;
import Controllers.SubHomeController;
import CustomerCodes.Queries;
import CustomerCodes.Validations;
import Main.DBconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author THARINDU
 */
public class CustPointsController implements Initializable {

    @FXML
    private TextField beginnerMinPiontField;
    @FXML
    private TextField prioMinPiontField;
    @FXML
    private TextField platMinPiontField;
    @FXML
    private Label bigCount;
    @FXML
    private Label prioCount;
    @FXML
    private Label platCount;
    @FXML
    private Button editPrioMP;
    @FXML
    private Button editPlatMP;

    public boolean prioEditMode;
    public boolean platEditMode;

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;
    Queries DBqry;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBqry = new Queries();
        prioEditMode = false;
        platEditMode = false;
        //loading data
        loadMinPoints(beginnerMinPiontField, prioMinPiontField, platMinPiontField, bigCount, prioCount, platCount);
        SubHomeController subHomeC = MainHomeController.subHome.getController();
        subHomeC.funcNamelbl.setText("Customer & Campaigns - Customer Points");
        subHomeC.backBtn.setVisible(true);//set back button
        try {
            subHomeC.subMenuList.getChildren().clear();
            subHomeC.subMenuList.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustSubMenuList.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CustHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubHomeController.backUrl = "/GUIs/CustHome.fxml";
    }

    @FXML
    public void integersOnlyKeyEvent(KeyEvent event) {
        Validations.integersOnly(event);
    }

    public void loadMinPoints(TextField big, TextField prio, TextField plat, Label bigl, Label priol, Label platl) {
        con = DBconnect.dbconnect();

        int begPoint = 0;
        int begCount = 0;
        int prioPoint = 0;
        int prioCount = 0;
        int platPoint = 0;
        int platCount = 0;
        //getting biginner`s minimumPoint value
        try {
            String qry = "SELECT * FROM cust_point_sys where custType = 'Beginner'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            rs.next();
            begPoint = rs.getInt("minPoint");

        } catch (Exception e) {
            System.out.print(e);
        }
        //getting priority`s minimumPoint value
        try {
            String qry = "SELECT * FROM cust_point_sys where custType NOT IN ('Beginner','Platinum')";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            rs.next();
            prioPoint = rs.getInt("minPoint");

        } catch (Exception e) {
            System.out.print(e);
        }
        //getting platimun`s minimumPoint value
        try {
            String qry = "SELECT * FROM cust_point_sys where custType = 'Platinum'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            rs.next();
            platPoint = rs.getInt("minPoint");

        } catch (Exception e) {
            System.out.print(e);
        }
        //getting biginner`s count
        try {
            String qry = "SELECT * FROM customer where custPoints between '" + begPoint + "' and '" + prioPoint + "'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                begCount++;
                DBqry.updateCustType(rs.getString("custID"), "Beginner");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        //getting priority`s count
        try {
            String qry = "SELECT * FROM customer where custPoints between '" + prioPoint + "' and '" + platPoint + "'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                prioCount++;
                DBqry.updateCustType(rs.getString("custID"), "Priority");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        //getting platinum`s count
        try {
            String qry = "SELECT * FROM customer where custPoints >= '" + platPoint + "'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                platCount++;
                DBqry.updateCustType(rs.getString("custID"), "Platinum");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        //setting biginner`s value
        big.setText(Integer.toString(begPoint));
        bigl.setText(Integer.toString(begCount));
        //setting priority`s value
        prio.setText(Integer.toString(prioPoint));
        priol.setText(Integer.toString(prioCount));
        //setting platinum`s value
        plat.setText(Integer.toString(platPoint));
        platl.setText(Integer.toString(platCount));
    }

    @FXML
    public void editPrioPointActionButton(ActionEvent event) throws IOException {
        if (prioEditMode) {
            boolean validation = true;
            int prioPointValue = Integer.parseInt(prioMinPiontField.getText());
            if (prioPointValue >= Integer.parseInt(platMinPiontField.getText())) {
                validation = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Priority level points should be lesser than Platinum level points !");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            }
            if (validation) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Confirmation Required");
                confirm.setHeaderText("Do you really want to update this Value?");
                confirm.setContentText("Are you ok with this values?");

                Optional<ButtonType> result = confirm.showAndWait();
                if (result.get() == ButtonType.OK) {
                    DBqry.updateMinPoints("Priority", prioPointValue);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("UPDATED");
                    msg.setHeaderText("Minimum required poins for priority level has been updated");
                    msg.setContentText("Updating is completed!");

                    msg.showAndWait();

                    //reload page
                    SubHomeController subHomeC = subHome.getController();
                    subHomeC.subContent.getChildren().clear();
                    subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustPoints.fxml")));

                } else {
                    //reload page
                    SubHomeController subHomeC = subHome.getController();
                    subHomeC.subContent.getChildren().clear();
                    subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustPoints.fxml")));
                }
            }
        } else {
            editPlatMP.setDisable(true);
            prioEditMode = true;
            prioMinPiontField.setEditable(true);
            editPrioMP.setText("SAVE");
        }
    }

    @FXML
    public void editPlatPointActionButton(ActionEvent event) throws IOException {
        if (platEditMode) {
            boolean validation = true;
            int platPointValue = Integer.parseInt(platMinPiontField.getText());
            if (platPointValue <= Integer.parseInt(prioMinPiontField.getText())) {
                validation = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Platinum level points should be greater than Priority level points !");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            }
            if (validation) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Confirmation Required");
                confirm.setHeaderText("Do you really want to update this Value?");
                confirm.setContentText("Are you ok with this values?");

                Optional<ButtonType> result = confirm.showAndWait();
                if (result.get() == ButtonType.OK) {
                    DBqry.updateMinPoints("Platinum", platPointValue);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("UPDATED");
                    msg.setHeaderText("Minimum required poins for platinum level has been updated");
                    msg.setContentText("Updating is completed!");

                    msg.showAndWait();

                    //reload page
                    SubHomeController subHomeC = subHome.getController();
                    subHomeC.subContent.getChildren().clear();
                    subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustPoints.fxml")));
                } else {
                    //reload page
                    SubHomeController subHomeC = subHome.getController();
                    subHomeC.subContent.getChildren().clear();
                    subHomeC.subContent.getChildren().add(FXMLLoader.load(getClass().getResource("/CustomerGUIs/CustPoints.fxml")));
                }
            }
        } else {
            editPrioMP.setDisable(true);
            platEditMode = true;
            platMinPiontField.setEditable(true);
            editPlatMP.setText("SAVE");
        }
    }

}
