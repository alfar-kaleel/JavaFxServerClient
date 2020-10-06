package clientController;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Loaninfo;

public class ClientController implements Initializable{
	
	
	@FXML
	private TextField airField;
	
	@FXML
	private TextField noyField;
	
	@FXML
	private TextField laField;
	
	@FXML
	private TextArea loanField;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
	}
	
	
	@FXML
	private void submitButton() {
		
		Loaninfo loanObj = new Loaninfo();
		
		
		loanObj.setAir(Float.valueOf(airField.getText()).floatValue());
		loanObj.setNoy(Float.valueOf(noyField.getText()).floatValue());
		loanObj.setLa(Float.valueOf(laField.getText()).floatValue());
		
		
		try {
			Socket socket = new Socket("localhost",7000);
			System.out.println("Connected");
			
			
			//serialization
			
			 OutputStream os = socket.getOutputStream();
			 InputStream is = socket.getInputStream();
			 
		        ObjectOutput oos = new ObjectOutputStream(os);
		        ObjectInput ois = new ObjectInputStream(is);
		        
		      
		        
		        
		        
		        //Sending values to the ServerSocket
		        oos.writeObject(loanObj);
		        oos.flush();
		       
		        //getting monthly payment from serversocket
		        float monthlyPayment = ois.readFloat();
		        
		        //Closing socket and terminating program.
		        socket.close();
		        
		        loanField.setText( " Loan Amount : " + loanObj.getLa() + "\n Annual interest Rate : " + loanObj.getAir() + "\n Years : "  +loanObj.getNoy()+"\n Monthly : "+ monthlyPayment + "\n total : " + monthlyPayment * 12 * loanObj.getNoy() );
		        loanField.setEditable(false);
			
			
		} catch (UnknownHostException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
	
	

}
