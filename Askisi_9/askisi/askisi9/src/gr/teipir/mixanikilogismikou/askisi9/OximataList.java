package gr.teipir.mixanikilogismikou.askisi9;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class OximataList extends JFrame {

	private	Mysql con;
	private DefaultListModel<String> listModel;
	private ArrayList<String> oximataId=new ArrayList<String>();
	private JList<String> oximataList;

	
	public OximataList() {
		/*
		 * parathiro me thn lista twn oximatwn
		 */
		con = new Mysql();
		setTitle("Lista Oximatwn");
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 350x400 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-175, dim.height/2-200, 350, 400);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		listModel = new DefaultListModel<String>();
		oximataList = new JList<String>(listModel);
		getContentPane().add(oximataList);
		
		JScrollPane scrollPane = new JScrollPane(oximataList);
		scrollPane.setBounds(0, 0, 350, 340);
		getContentPane().add(scrollPane);
		
		JButton editButton = new JButton("Epe3ergasia");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					/*
					 * anoigei to parathiro epe3ergasias gia to epilegmeno oxima
					 */
					OximataAdd frame = new OximataAdd(Integer.parseInt(oximataList.getSelectedValue().toString()));
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		editButton.setBounds(0, 338, 115, 34);
		getContentPane().add(editButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * diagrafei to epilegmeno oxima epeita apo erwthsh
				 */
				int dialogResult = JOptionPane.showConfirmDialog (null, "Thelete sigoura na diagrapsete to sigkekrimeno oxima?","Delete",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					delete(Integer.parseInt(listModel.get(oximataList.getSelectedIndex())),oximataList.getSelectedIndex());
				}
			}
		});
		deleteButton.setBounds(115, 338, 115, 34);
		getContentPane().add(deleteButton);
		
		JButton mapButton = new JButton("Map");
		mapButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * anoigei ton xarti me thn topothesia tou oximatos
				 */
				seeOnMap(Integer.parseInt(listModel.get(oximataList.getSelectedIndex())));
			}
		});
		mapButton.setBounds(230, 338, 115, 34);
		getContentPane().add(mapButton);
		
		addOximata();
		
		
	}
	
	private void addOximata(){
		/*
		 * vazei ta oximata tis vashs sthn lista
		 */
		ArrayList<String> oximata=con.getOximata();
		for(int i=0;i<oximata.size();i++){
			listModel.addElement(oximata.get(i));
		}
	}
	
	private void delete(int oximaId,int index){
		/*
		 * diagrafei to epilegmeno oxima
		 */
		String query="delete from oximata where id=?";
		try {
			con.pS=con.conn.prepareStatement(query);
			con.pS.setInt(1, oximaId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(con.makeQuery(query)){
			JOptionPane.showMessageDialog(null, "Deleted!");
			listModel.remove(index);
			oximataId.remove(index);
			
		}else{
			JOptionPane.showMessageDialog(null, "Something went wrong please try later!");
		}
	}
	
	private void seeOnMap(int id){
		/*
		 * emfanizei ton xarth me kedro thn topothesia tou oximatos
		 */
		JFrame mapFrame = new JFrame("Topothesia Oxhmatos");
		String[] latLon=con.getLatLon(id);
		String lat,lon;
		if(latLon!=null){
			lat=latLon[0];
			lon=latLon[1];
	        try {
	            String imageUrl = "http://maps.google.com/staticmap?center="+lat+","+lon+"&zoom=16&size=630x600&maptype=roadmap&markers=color:blue%7Clabel:S%7A"+lat+","+lon+"&key=ABQIAAAAgb5KEVTm54vkPcAkU9xOvBR30EG5jFWfUzfYJTWEkWk2p04CHxTGDNV791-cU95kOnweeZ0SsURYSA&format=jpg&sensor=false";
	            String destinationFile = "image.jpg";
	            URL url = new URL(imageUrl);
	            InputStream is = url.openStream();
	            OutputStream os = new FileOutputStream(destinationFile);
	
	            byte[] b = new byte[2048];
	            int length;
	
	            while ((length = is.read(b)) != -1) {
	                os.write(b, 0, length);
	            }
	
	            is.close();
	            os.close();
	            mapFrame.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
		                java.awt.Image.SCALE_SMOOTH))));
		
		        mapFrame.setVisible(true);
		        mapFrame.pack();
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.exit(1);
	        }catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }

	        
			}
	}
}
