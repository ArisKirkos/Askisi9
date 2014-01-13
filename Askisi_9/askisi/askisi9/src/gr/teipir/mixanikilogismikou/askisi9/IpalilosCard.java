package gr.teipir.mixanikilogismikou.askisi9;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IpalilosCard extends JFrame {

	
	public IpalilosCard() {
		/*
		 * kartela ipalilou apo thn opoioa epilegei ti energeia thelei na kanei
		 */
		this.setTitle("Kartela Ipallilou"); //titlos parathirou
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 250x238 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-125, dim.height/2-119, 250, 238);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JButton odigoi = new JButton("Odigoi");
		odigoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					/*
					 * anoigei thn kartela me thn lista twn odhgwn
					 */
					OdigoiList frame = new OdigoiList();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		odigoi.setBounds(0, 0, 244, 35);
		getContentPane().add(odigoi);
		
		JButton oximata = new JButton("Oximata");
		oximata.setBounds(0, 35, 244, 35);
		getContentPane().add(oximata);
		oximata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					/*
					 * anoigei thn kartela me thn lista twn oximatwn
					 */
					OximataList frame = new OximataList();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		JButton odigoiAdd = new JButton("Prosthiki Odigou");
		odigoiAdd.setBounds(0, 70, 244, 35);
		getContentPane().add(odigoiAdd);
		odigoiAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					/*
					 * anoigei to parathiro prosthikis twn odhgwn
					 */
					OdigoiAdd frame = new OdigoiAdd(0);
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		JButton oximataAdd = new JButton("Prosthiki Oximatos");
		oximataAdd.setBounds(0, 105, 244, 35);
		getContentPane().add(oximataAdd);
		oximataAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					/*
					 * anoigei to parathiro prosthikis twn oximatwn
					 */
					OximataAdd frame = new OximataAdd(0);
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		JButton anefodiasmoiAdd = new JButton("Prosthiki Anefodiasmou");
		anefodiasmoiAdd.setBounds(0, 140, 244, 35);
		getContentPane().add(anefodiasmoiAdd);
		anefodiasmoiAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					/*
					 * anoigei to parathiro me thn lista twn anefodiasmwn
					 */
					AnefodiasmoiAdd frame = new AnefodiasmoiAdd();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton anafores = new JButton("Anafores");
		anafores.setBounds(0, 175, 244, 35);
		getContentPane().add(anafores);
		anafores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					/*
					 * anoigei to parathiro me thn lista twn anaforwn
					 */
					Anafores frame = new Anafores();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}
}
