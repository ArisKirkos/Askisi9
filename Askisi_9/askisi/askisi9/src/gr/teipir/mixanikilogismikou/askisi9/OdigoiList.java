package gr.teipir.mixanikilogismikou.askisi9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OdigoiList extends JFrame {

	private	Mysql con;
	private DefaultListModel<String> listModel;
	private JList<String> odigoiList;
	private ArrayList<String> odigoiId=new ArrayList<String>();
	private ArrayList<HashMap<String, String>> odigoi;
	private HashMap<String, String> odigoiInfo;

	
	public OdigoiList() {
		con = new Mysql();
		setTitle("Lista Odigwn");
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 355x40 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-150, dim.height/2-125, 355, 400);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		listModel = new DefaultListModel<String>();
		odigoiList = new JList<String>(listModel);
		getContentPane().add(odigoiList);
		
		JScrollPane scrollPane = new JScrollPane(odigoiList);
		scrollPane.setBounds(0, 0, 355, 340);
		getContentPane().add(scrollPane);
		
		JButton editButton = new JButton("Epe3ergasia");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					/*
					 * anoigei to parathiro epexergasias gia ton epilegmeno odigo
					 */
					odigoiInfo=odigoi.get(odigoiList.getSelectedIndex());
					OdigoiAdd frame = new OdigoiAdd(Integer.parseInt(odigoiInfo.get("id")));
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		editButton.setBounds(0, 338, 175, 34);
		getContentPane().add(editButton);
		
		JButton deleteButton = new JButton("Diagrafh");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * diagrafi ton epilegmeno odigo epeita apo erwthsh
				 */
				int dialogResult = JOptionPane.showConfirmDialog (null, "Eiste sigouri oti thelete na diagrapsete ton sigkekrimeno odigo?","Diagrafh",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					delete(Integer.parseInt(odigoiId.get(odigoiList.getSelectedIndex())),odigoiList.getSelectedIndex());
				}
			}
		});
		deleteButton.setBounds(175, 338, 175, 34);
		getContentPane().add(deleteButton);
		
		addOdigous();
		
		
	}
	
	private void addOdigous(){
		/*
		 * vazei tous odigous ths vashs sthn lista
		 */
		odigoi=con.getOdigous();
		odigoiInfo = new HashMap<String, String>();
		for(int i=0;i<odigoi.size();i++){
			odigoiInfo=odigoi.get(i);
			odigoiId.add(odigoiInfo.get("id"));
			listModel.addElement(odigoiInfo.get("onoma")+
					" "+odigoiInfo.get("eponimo")+" ("+odigoiInfo.get("oxima")+")");
		}
	}
	
	private void delete(int odigosId,int index){
		/*
		 * diagrafei ton epilegmeno odigo apo thn vash kai epistrefei to katallilo minima
		 */
		String query="delete from odigoi where id=?";
		try {
			con.pS=con.conn.prepareStatement(query);
			con.pS.setInt(1, odigosId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(con.makeQuery(query)){
			JOptionPane.showMessageDialog(null, "Diagraftike!");
			listModel.remove(index);
			odigoiId.remove(index);
			
		}else{
			JOptionPane.showMessageDialog(null, "Something went wrong please try later!");
		}
	}

}
