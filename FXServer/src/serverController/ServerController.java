package serverController;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Loaninfo;

public class ServerController implements Initializable{
	
	
	@FXML
	TextArea loanField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		 ServerSocket ss;
		try {
			
			ss = new ServerSocket(7000);
			
			  System.out.println("ServerSocket awaiting connections...");
		        Socket socket = ss.accept();
		        System.out.println("Connection from " + socket);

		        //Deserialization
		        InputStream is = socket.getInputStream();
		        OutputStream os = socket.getOutputStream();
		        
		        ObjectInput ois = new ObjectInputStream(is);
		        ObjectOutput oos = new ObjectOutputStream(os);
		        
		        //System.out.println(ois.readObject());
		        
		        Loaninfo loanObj=  (Loaninfo) ois.readObject();
		       
		        
		        //System.out.println("Values received from Client are:-" + loanObj);
		     // System.out.println("Annual Interest Rate:"+loanObj.getAir());
		      //System.out.println("Number of Years:"+loanObj.getNoy());
		      //System.out.println("Loan Amount:"+loanObj.getLa());

		    
		      
		     // System.out.println("Loan for month is :" +   calculateLoan(loanObj.getLa(), loanObj.getNoy(), loanObj.getAir()));
		        float monthPayment = calculateLoan(loanObj.getLa(), loanObj.getNoy(), loanObj.getAir());
		        float total = calculateLoan(loanObj.getLa(), loanObj.getNoy(), loanObj.getAir()) * loanObj.getNoy() * 12;
		        
		        loanField.setText( " Loan Amount : " + loanObj.getLa() + "\n Annual interest Rate : " + loanObj.getAir() + "\n Years : "  +loanObj.getNoy()+"\n Monthly : "+ monthPayment + "\n total : " + total );
		        loanField.setEditable(false);
		        
		        oos.writeFloat(monthPayment);
		        oos.flush();
		        System.out.println("Closing sockets.");
		        ss.close();
		        socket.close();
		        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		
	}
	
	
	float calculateLoan(float p,float year,float rate) {
		
		float r = rate/1200;
		
		year = year * 12;
		
		float D = (float) (((Math.pow(1 + r,year)) - 1) / (r*Math.pow(1 + r,year)));
		
		float loan = p /D;
		
		
		
		
		return loan;
	}

}
