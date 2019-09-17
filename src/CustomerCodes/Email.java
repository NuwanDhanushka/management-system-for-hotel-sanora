/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerCodes;

import static Controllers.CustHomeController.custSendNotification;
import CustomerControllers.CustSendNotificationController;
import Main.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author THARINDU
 */
public class Email {
    public static int emailStatus=0;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;
    Queries DBqry;

    public void sendPromotionEmail(String imgUrl,String txt,int cusType) {
        String tt="";
        System.out.println(tt);
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hotelsanora@gmail.com", "123Qwerty");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hotelsanora@gmail.com"));//my email
            // Adding multiple Recipients
            if( addingRecipients(cusType) != null && ( addingRecipients(cusType).length > 0 ) ) {
                InternetAddress[] address = new InternetAddress[addingRecipients(cusType).length ];
                 
                for( int i = 0; i < addingRecipients(cusType).length; i++ ) {
                    address[ i ] = new InternetAddress( addingRecipients(cusType)[ i ] ) ;
                }
                 
                message.setRecipients( Message.RecipientType.TO, address ) ;
            }
            
    
            message.setSubject("Sanora Promotions");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(txt);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            if(!imgUrl.isEmpty()){
                //attached 1 --------------------------------------------
                String file = imgUrl;
                String fileName = "Offer";
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName);
                multipart.addBodyPart(messageBodyPart);
                //------------------------------------------------------ 
            }
               

            //>>>>>>>>
            message.setContent(multipart);

            System.out.println("sending");
            Transport.send(message);
            System.out.println("Done");
            //successful msg
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Messages Were Sent Successfully!");
            alert.setContentText("Thank you!");

            alert.showAndWait();
            //changing progress icons
            CustSendNotificationController csnc = custSendNotification.getController();
            csnc.Bicon.setVisible(false);
            csnc.Blbl.setVisible(false);
            csnc.Prioicon.setVisible(false);
            csnc.Priolbl.setVisible(false);
            csnc.Platicon.setVisible(false);
            csnc.Platlbl.setVisible(false);
        } catch (MessagingException e) {
            emailStatus=1;
            e.printStackTrace();
            //unsuccessful msg
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Messages Failed to Send!");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
            //changing progress icons
            CustSendNotificationController csnc = custSendNotification.getController();
            csnc.Bicon.setVisible(false);
            csnc.Blbl.setVisible(false);
            csnc.Prioicon.setVisible(false);
            csnc.Priolbl.setVisible(false);
            csnc.Platicon.setVisible(false);
            csnc.Platlbl.setVisible(false);
        }
    }
    //getting recipients for each catagory
    public  String[] addingRecipients(int type){
        int size=0;
        con = DBconnect.dbconnect(); 
        String custType="";
        if(type==1){
            custType="Beginner";
        }
        if(type==3){
            custType="Platinum";
        }
        String qry;
            if(type==1||type==3){
                qry = "Select email from customer where custType ='"+custType+"'";
            }
            else{
                qry = "Select email from customer where custType NOT IN ('Beginner','Platinum')";
            }
        try { 
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()){
                size++;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        String reci[]=new String[size];
        try { 
            int x=0;
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()){
                reci[x]=rs.getString("email");
                x++;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return reci;
    }
}
