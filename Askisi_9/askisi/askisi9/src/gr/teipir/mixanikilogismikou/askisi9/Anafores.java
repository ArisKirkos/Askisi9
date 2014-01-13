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

public class Anafores extends JFrame {

	private DefaultListModel<String> listModel; //lista pou tha mpoun oi anafores gia na emfanistou
	private JList<String> anaforesList; //lista pou tha emfanizontai oi anafores
	private ArrayList<HashMap<String, String>> anafores; //lista pou tha mpoun oi anafores me ta onomata twn sthlwn pou exoun sthn bash
	private HashMap<String, String> anaforesInfo;
	private	Mysql con; //antikeimeno ths Mysql
	
	public Anafores() {
		/*kartela anaforwn*/
		
		con = new Mysql();
		setTitle("Lista Anaforwn"); //titlos para8yrou
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 500x400 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-250, dim.height/2-200, 500, 400);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		// lista me anafores
		listModel = new DefaultListModel<String>();
		anaforesList = new JList<String>(listModel);
		getContentPane().add(anaforesList);
		
		//vazei thn idiotita scroll sthn lista
		JScrollPane scrollPane = new JScrollPane(anaforesList);
		scrollPane.setBounds(0, 0, 500, 400);
		getContentPane().add(scrollPane); 
		
		addAnafores(); //pairnei tis anafores apo thn vash kai ths vazei sthn lista
	
	}
	
	private void addAnafores(){
		anafores=con.getAnafores(); //pairnei tis anafores apo thn vash
		anaforesInfo = new HashMap<String, String>(); //dimiourgia HashMap
		for(int i=0;i<anafores.size();i++){
			anaforesInfo=anafores.get(i); //pairnei ta stoixia mias seiras
			listModel.addElement((i+1)+
					") "+anaforesInfo.get("time")+" Oxima: "+anaforesInfo.get("oxima")+" "+anaforesInfo.get("provlima")+" ("+anaforesInfo.get("sxolia")+")");
			//ta vazei sthn lista pros emfanish
		}
	}

}
