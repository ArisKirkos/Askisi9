package gr.teipir.mixanikilogismikou.askisi9;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OximataAdd extends JFrame {

	private JTextField kodikos,diadromi;
	private	Mysql con;
	private int id;

	/**
	 * Create the frame.
	 */
	public OximataAdd(int editId) {
		/*
		 * parathiro gia prosthiki oximatos an to editId einai 0 i epe3ergasia an einai megaluterao tou 0
		 */
		con = new Mysql();
		id=editId;
		if(id==0) //emfanizei ton katalilo titlo analoga thn leitourgia pou exei epilex8ei
			setTitle("Prosthiki Oxhmatos");
		else
			setTitle("Epe3ergasia Oxhmatos");
		
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 300x220 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-150, dim.height/2-110, 300, 220);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel kodikosLabel = new JLabel("Kwdikos:");
		kodikosLabel.setBounds(10, 11, 70, 14);
		getContentPane().add(kodikosLabel);
		
		JLabel diadromiLabel = new JLabel("Diadromi:");
		diadromiLabel.setBounds(10, 36, 70, 14);
		getContentPane().add(diadromiLabel);
		
		kodikos = new JTextField();
		kodikos.setBounds(101, 8, 125, 20);
		getContentPane().add(kodikos);
		kodikos.setColumns(10);
		
		diadromi = new JTextField();
		diadromi.setBounds(101, 33, 125, 20);
		getContentPane().add(diadromi);
		diadromi.setColumns(10);
		
		JButton saveButton = new JButton("Save");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * apothikeuei sthn vash ta stoixia tou oximatos afou kanei elegxo se auta prwta
				 */
				String kodikosValue=kodikos.getText();
				String diadromiValue=diadromi.getText();
				if(checkData(kodikosValue,diadromiValue))
					save(kodikosValue,diadromiValue);
			}
		});
		saveButton.setBounds(101, 141, 125, 40);
		getContentPane().add(saveButton);
		
		if(id!=0){
			getOximaForEdit(id);
		}
	}
	
	private void getOximaForEdit(int id){
		/*
		 * pairnei ta stoixia tou oximatos pou einai pros epe3ergasia apo thn vash
		 */
		ArrayList<HashMap<String, String>> oxima=con.getOxima(id);
		HashMap<String, String> oximaInfo = new HashMap<String, String>();
		oximaInfo=oxima.get(0);
		kodikos.setText(oximaInfo.get("id"));
		diadromi.setText(oximaInfo.get("diadromi"));	
	}
	
	private Boolean checkData(String kod,String diad){
		/*
		 * elegxei an einai swsta ta dedomena pou exoun do8ei
		 */
		if(kod.length()<2){
			JOptionPane.showMessageDialog(null, "Dwste egkuro kwdiko!");
			return false;
		}else if(diad.length()<3){
			JOptionPane.showMessageDialog(null, "Dwste egkiri diadromh!");
			return false;
		}else{
			return true;
		}
		
		
	}
	
	private void save(String kodikosV,String daidromiV){
		/*
		 * apothikeuei ta stoixia sthn vash kai emfanizei to katalilo minima ston xristi 
		 */
		String query="insert into oximata(id,diadromi,lat,lon) values(?,?,'37.973263','23.740768') on duplicate key update diadromi=?";
		try {
			con.pS=con.conn.prepareStatement(query);
			con.pS.setInt(1, Integer.parseInt(kodikosV));
			con.pS.setString(2, daidromiV);
			con.pS.setString(3, daidromiV);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(con.makeQuery(query)){
			JOptionPane.showMessageDialog(null, "Saved!");
			if(id!=0)
				dispose();
			else{
				kodikos.setText("");
				diadromi.setText("");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Something went wrong please try later!");
		}
			
		
	}

}
