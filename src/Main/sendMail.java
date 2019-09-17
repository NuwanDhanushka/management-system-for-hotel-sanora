/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import databaseConfig.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nuwan
 */
public class sendMail {
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    String email="";
    
    public void sendReorderMail(String supplierid,String priority,String qty) throws SQLException{
            connection = dbConnection.getConnection();
            pst = connection.prepareStatement("select supplierAddress from supplier where Id='"+supplierid+"'");
            rs = pst.executeQuery(); 
            while (rs.next()) {
               email=rs.getString("supplierAddress");
            }
            rs.close();
            connection.close();
        System.out.println(email);
        System.out.println(priority);
        System.out.println(qty);
        
		final String username = "hotelsanora@gmail.com";
		final String password = "123Qwerty";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("hotelsanora@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(email));//to custommer 
			message.setSubject("Reorder Products");
			message.setText("Dear Supplier,"
			+ "\n\n We need '"+qty+"' new products and the priority is '"+priority+"'!");
 
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
