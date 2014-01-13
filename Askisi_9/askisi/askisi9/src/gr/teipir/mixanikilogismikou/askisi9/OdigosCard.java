package gr.teipir.mixanikilogismikou.askisi9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class OdigosCard extends JFrame {

	private JTextPane sxolia;
	private JComboBox<String> provlima;
	private String[] provlimata={"Lastixo","Xreiazetai Service","Den pairnei Mprosta","Trakarisma","Allo"};
	private Mysql con;
	private int odigosId;


	/**
	 * Create the frame.
	 */
	public OdigosCard(int id) {
		/*
		 * kartela odigou gia anafora provlimatos
		 */
		con = new Mysql();
		odigosId=id;
		this.setTitle("Anafora Provlimatos");
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 350x250 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-150, dim.height/2-125, 350, 250);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel provlimaLabel = new JLabel("Provlima:");
		provlimaLabel.setBounds(19, 11, 67, 14);
		getContentPane().add(provlimaLabel);
		
		JLabel sxoliaLabel = new JLabel("Sxolia:");
		sxoliaLabel.setBounds(19, 36, 67, 14);
		getContentPane().add(sxoliaLabel);
		
		sxolia = new JTextPane();
		sxolia.setBounds(107, 36, 189, 131);
		getContentPane().add(sxolia);
		
		provlima = new JComboBox<String>();
		provlima.setBounds(107, 8, 189, 20);
		getContentPane().add(provlima);
		for(int i=0;i<provlimata.length;i++)
			provlima.addItem(provlimata[i]);
		
		JButton sendButton = new JButton("Send");
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * elegxei ta stoixia kai epeita stelnei tin anafora apothikeuontas thn sthn vash
				 */
				if(checkData(sxolia.getText()))
					send(provlima.getSelectedItem().toString(),sxolia.getText());
			}
		});
		sendButton.setBounds(107, 188, 100, 23);
		getContentPane().add(sendButton);
	}
	
	private void send(String pro,String sxo){
		/*
		 * apothikeuei sthn vash thn anafora
		 */
		String query="insert into anafores(provlima,sxolia,odigos_id) values(?,?,?)";
		try {
			con.pS=con.conn.prepareStatement(query);
			con.pS.setString(1, pro);
			con.pS.setString(2, sxo);
			con.pS.setInt(3, odigosId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(con.makeQuery(query)){
			JOptionPane.showMessageDialog(null, "Stalthike!");
			sxolia.setText("");
		}else{
			JOptionPane.showMessageDialog(null, "Something went wrong please try later!");
		}
	}
	
	private Boolean checkData(String sx){
		/*
		 * elegxei an exei dothei sxolio
		 */
		if(sx.length()<1){
			JOptionPane.showMessageDialog(null, "Grapse kapoio sxolio!");
			return false;
		}else{
			return true;
		}
		
		
	}
}
