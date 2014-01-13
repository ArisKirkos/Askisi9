package gr.teipir.mixanikilogismikou.askisi9;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;

public class OdigoiAdd extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField name,surname,username;
	private JPasswordField pass;
	private JPasswordField pass2;
	private JComboBox<String> oximataBox;
	private Mysql con;
	private int id;
	private JLabel oximaLabel;

	
	public OdigoiAdd(int editId) {
		/*
		 * parathiro gia thn prosthiki i tin epe3ergasia odigou, an to editId einai 0 tote kanei prosthiki alliws epexergasia
		 */
		con = new Mysql();
		id=editId;
		if(id==0) // vazei ton katallhlo titlo analoga thn leitourgia pou exei epilex8ei
			setTitle("Prosthiki Odigou");
		else
			setTitle("Epe3ergasia Odigou");
		
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 300x220 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-150, dim.height/2-125, 300, 245);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel onomaLabel = new JLabel("Onoma:");
		onomaLabel.setBounds(10, 11, 70, 14);
		getContentPane().add(onomaLabel);
		
		JLabel eponimoLabel = new JLabel("Epwnimo:");
		eponimoLabel.setBounds(10, 36, 70, 14);
		getContentPane().add(eponimoLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(10, 61, 70, 14);
		getContentPane().add(usernameLabel);
		
		JLabel kodikosLabel = new JLabel("Password:");
		kodikosLabel.setBounds(10, 86, 70, 14);
		getContentPane().add(kodikosLabel);
		
		JLabel kodikosELabel = new JLabel("Epal. Kwdikou:");
		kodikosELabel.setBounds(10, 111, 70, 14);
		getContentPane().add(kodikosELabel);
		
		oximaLabel = new JLabel("Oxima:");
		oximaLabel.setBounds(10, 136, 90, 14);
		getContentPane().add(oximaLabel);
		
		name = new JTextField();
		name.setBounds(101, 8, 125, 20);
		getContentPane().add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(101, 33, 125, 20);
		getContentPane().add(surname);
		surname.setColumns(10);
		
		username = new JTextField();
		username.setBounds(101, 58, 125, 20);
		getContentPane().add(username);
		username.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(101, 83, 125, 20);
		getContentPane().add(pass);
		
		
		
		pass2 = new JPasswordField();
		pass2.setBounds(101, 108, 125, 17);
		getContentPane().add(pass2);
		
		
		
		oximataBox = new JComboBox<String>();
		oximataBox.setBounds(101, 133, 125, 20);
		getContentPane().add(oximataBox);
		addOximata();
		
		JButton saveButton = new JButton("Save");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * apothikeuei tis times sthn vash afou tis elen3ei prwta
				 */
				String nameValue=name.getText();
				String surnameValue=surname.getText();
				String usernameValue=username.getText();
				String passValue=pass.getText();
				String pass2Value=pass2.getText();
				String oximaValue=oximataBox.getSelectedItem().toString();
				String action;
				if(checkData(nameValue,surnameValue,passValue,pass2Value)){
					if(id==0)
						action="insert";
					else
						action="update";
					save(nameValue,surnameValue,usernameValue,passValue,oximaValue,action);
				}
			}
		});
		saveButton.setBounds(101, 167, 125, 40);
		getContentPane().add(saveButton);
		
		
		if(id!=0){ // an exei epilexthei i leitourgia tiw epe3ergasias tote pairnei ta stoixia tou odigou pros epe3ergasia
			getOdigoForEdit(id);
		}
		
	}
	
	private void addOximata(){
		/*
		 * vazei ta oximata tis vashs sthn lista
		 */
		ArrayList<String> oximata=con.getOximata();
		for(int i=0;i<oximata.size();i++){
			oximataBox.addItem(oximata.get(i));
		}
	}
	
	private void getOdigoForEdit(int id){
		/*
		 * pairnei ta stoixia tou odigou pou einai pros epe3ergasia
		 */
		ArrayList<HashMap<String, String>> odigos=con.getOdigo(id);
		HashMap<String, String> odigosInfo = new HashMap<String, String>();
		odigosInfo=odigos.get(0);
		name.setText(odigosInfo.get("onoma"));
		surname.setText(odigosInfo.get("eponimo"));
	}
	
	private Boolean checkData(String name,String surname,String pass,String pass2){
		/*
		 * elegxei an einai swsta ta dedomena pou dothikan kai epistrefei to katalilo mnm an den einai
		 */
		if(name.length()<3){
			JOptionPane.showMessageDialog(null, "Dwste egkiro onoma!");
			return false;
		}else if(surname.length()<3){
			JOptionPane.showMessageDialog(null, "Dwste egkiro epwnimo!");
			return false;
		}else if(pass.length()<5){
			JOptionPane.showMessageDialog(null, "O kwdikos prepei na einai megaliteros apo 4 xaraktires!");
			return false;
		}else if(!pass.equals(pass2)){
			JOptionPane.showMessageDialog(null, "Oi kwdikoi den teriazoun!");
			return false;
		}else{
			return true;
		}
		
		
	}
	
	private void save(String nameV,String surnameV,String usernameV,String passV,String oximaV,String action){
		/*
		 * apothikeuei ta stoixia i tis allages sthn vash kai emfanizei ta katallhla minimata
		 */
		String query;
		if(action.equals("insert"))
			query="insert into odigoi(onoma,eponimo,username,password,oxima) values(?,?,?,?,?)";
		else
			query="update odigoi set onoma=?, eponimo=? , username=?,password=?,oxima=? WHERE id=?";
		try {
			con.pS=con.conn.prepareStatement(query);
			con.pS.setString(1, nameV);
			con.pS.setString(2, surnameV);
			con.pS.setString(3, usernameV);
			con.pS.setString(4, passV);
			con.pS.setString(5, oximaV);
			if(action.equals("update"))
				con.pS.setInt(6, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(con.makeQuery(query)){
			JOptionPane.showMessageDialog(null, "Saved!");
			if(id!=0)
				dispose();
			else{
				name.setText("");
				surname.setText("");
				username.setText("");
				pass.setText("");
				pass2.setText("");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Something went wrong please try later!");
		}
			
		
	}
}
