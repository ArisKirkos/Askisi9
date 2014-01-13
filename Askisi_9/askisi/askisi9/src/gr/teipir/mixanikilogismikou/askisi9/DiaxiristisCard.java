package gr.teipir.mixanikilogismikou.askisi9;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class DiaxiristisCard extends JFrame {
	
	private DefaultListModel<String> listModel;
	private JList<String> oximataList;
	private ArrayList<HashMap<String, String>> oximata;
	private HashMap<String, String> oximataInfo;
	private	Mysql con;
	private JLabel odigoiLabel,oximataLabel;
	

	public DiaxiristisCard() {
		/*
		 * kartela diaxiristi
		 */
		con = new Mysql();
		setTitle("Lista Anaforwn");
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 700x500 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-350, dim.height/2-250, 700, 500);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		odigoiLabel = new JLabel("");
		odigoiLabel.setBounds(15, 11, 100, 14);
		getContentPane().add(odigoiLabel);
		
		oximataLabel = new JLabel("");
		oximataLabel.setBounds(120, 11, 100, 14);
		getContentPane().add(oximataLabel);
		
		listModel = new DefaultListModel<String>();
		oximataList = new JList<String>(listModel);
		getContentPane().add(oximataList);
		
		JScrollPane scrollPane = new JScrollPane(oximataList);
		scrollPane.setBounds(0, 30, 700, 500);
		getContentPane().add(scrollPane);
		
		addStatistika();// function i opoia pairnei ta statistika apo tin vasi kai ta emfanizei
	}
	
	private void addStatistika(){
		/*
		 * pairnei ola ta aparaithta stoixia apo thn vash kai ta emfanizei ston diaxiristi
		 */
		String oximataNum=con.getNumOfOximata();  //pairnei ton arithmo twn oximatwn ths vashs
		String odigoiNum=con.getNumOfOdigoi(); //pairnei ton arithmo twn odigwn thw vashs
		oximataLabel.setText("Oximata: "+oximataNum); //emfanizei ton arithmo ton oximatwn
		odigoiLabel.setText("Odigoi: "+odigoiNum); //emfanizei ton arithmo ton odigwn
		
		oximata=con.getOximataStatistika(); //pairnei ta aparaitita stoixia apo thn vash
		oximataInfo = new HashMap<String, String>(); 
		for(int i=0;i<oximata.size();i++){
			oximataInfo=oximata.get(i);
			listModel.addElement((i+1)+
					") Oxima: "+oximataInfo.get("id")+" Odigos: "+oximataInfo.get("odigosOnoma")+" ("+oximataInfo.get("odigosId")+") Anafores: "+oximataInfo.get("anaforesNum")+" Anefodiasmoi: "+oximataInfo.get("anefodiasmoiNum"));
			//vazei ta dedomena sthn lista
		}
	}
}
