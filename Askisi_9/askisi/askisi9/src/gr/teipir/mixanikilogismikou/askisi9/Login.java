package gr.teipir.mixanikilogismikou.askisi9;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Mysql con;

	/**
	 * Dimiourgei to frame gia to login
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login() {
		/*
		 * eisagontai username kai password ginetai elegxos an einai swsta kai anoigei thn katallhlh kartela analoga me ton xrhsth pou ekane login
		 */
		con = new Mysql();
		setTitle("Login");
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 300x250 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-150, dim.height/2-125, 300, 250);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * pairnei ta stoixia kanei elegxo kai emfanizei munhma lathous h anoigei thn katalhlh kartela analoga thn orthothta autwn
				 */
				String username,password;
				String[] isDataCorrect=new String[2];
				String odigosId=null;
				
				username=usernameField.getText(); //pairnei thn timi
				password=passwordField.getText();
				
				isDataCorrect=checkData(username,password); //elegxei an einai apodexta ta stoixia
				
				if(isDataCorrect[0].equals("0")){ //an einai lathos ta stoixia tote emfanizei minima lathous
					JOptionPane.showMessageDialog(null, "Ta stoixeia pou dwsate einai lathos!");
				}else{
					if(isDataCorrect[0].equals("odigos")) //an o xristis einai odigos pernaei to id tou sthn metavlhth
						odigosId=isDataCorrect[1]; 
					openCard(isDataCorrect[0],odigosId); //anoigei thn katallhlh kartela analoga me ton xristi pou ekane login
				}
				
			}
		});
		
		btnLogin.setFont(new Font("Aharoni", Font.PLAIN, 17));
		btnLogin.setBounds(100, 171, 100, 40);
		getContentPane().add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(38, 64, 70, 14);
		getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(118, 61, 100, 20);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(38, 108, 70, 14);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 105, 100, 20);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
	}
	
	private String[] checkData(String username,String password){ // NOPMD by Kalom on 11/1/2014 10:34 μμ
		/*
		 * elegxei an einai swsta ta stoixia pou dothikan
		 */
		return con.checkLogin(username,password);
	}
	
	private void openCard(String priv,String odigosId){
		/*
		 * anoigei thn katalili kartela analoga me ton xristi pou ekane login
		 */
		if(priv.equals("odigos")){
			try {
				OdigosCard frame = new OdigosCard(Integer.parseInt(odigosId));
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(priv.equals("diaxiristis")){
			try {
				DiaxiristisCard frame = new DiaxiristisCard();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				IpalilosCard frame = new IpalilosCard();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dispose(); //kleinei to parathiro tou login meta to anoigma tis neas kartelas
	}
}
