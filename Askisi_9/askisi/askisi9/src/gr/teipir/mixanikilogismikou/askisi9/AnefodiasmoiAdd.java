package gr.teipir.mixanikilogismikou.askisi9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AnefodiasmoiAdd extends JFrame {
	
	private JTextField litra; //pedio litrwn
	private	Mysql con; //antikeimeno ths Mysql
	private JComboBox<String> oximataBox; //dropdown list me ta oximata

	
	public AnefodiasmoiAdd() {
		/* kartela prosthikis anefodiasmwn*/
		con = new Mysql();
		setTitle("Prosthiki Anefodiasmou"); //titlos para8yrou
		/* vgazei to para8uro sto kedro tis othonis me diastaseis 300x220 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(dim.width/2-150, dim.height/2-110, 300, 220);
		/*--------------*/
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel litraLabel = new JLabel("Litra:");
		litraLabel.setBounds(10, 11, 70, 14);
		getContentPane().add(litraLabel);
		
		
		litra = new JTextField();
		litra.setBounds(101, 8, 125, 20);
		getContentPane().add(litra);
		litra.setColumns(10);
		
		JLabel oximaL = new JLabel("Oxima:");
		oximaL.setBounds(10, 48, 46, 14);
		getContentPane().add(oximaL);
		
		oximataBox = new JComboBox<String>();
		oximataBox.setBounds(100, 48, 126, 20);
		getContentPane().add(oximataBox);
		addOximata(); //pairnei ta oximata apo thn vash kai ta vazei sthn dropdown lista oximataBox
		
		
		JButton saveButton = new JButton("Save");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/* otan path8ei to koumpi pairnei tin timi pou exei mpei sta litra kanei elegxo an einai swsth
				 * kai epeita kalei thn function save(String litraV,String oximaV) kai apothikeuei ta stoixia sthn vash
				 */
				String litraValue=litra.getText(); //pairnei thn timi tou pedioi
				String oximaValue=oximataBox.getSelectedItem().toString(); //pairnei tin timi tis dropdown list
				if(checkData(litraValue)) //sunarthsh pou elegxei an einai egkurh h timi
					save(litraValue,oximaValue); //apothikeuei ta stoixia sthn vash
			}
		});
		saveButton.setBounds(101, 141, 125, 40);
		getContentPane().add(saveButton);
		
		
	}
	
private void addOximata(){
	/*
	 * vazei ta oximata apo thn vash sthn dropdown lista
	 */
	ArrayList<String> oximata=con.getOximata(); //pairnei ta oximata apo tin vasi kai ta apothikeui sthn ArrayList<String>
	for(int i=0;i<oximata.size();i++){
		oximataBox.addItem(oximata.get(i)); //prostheti to oxima sthn dropdown lista
	}
}
	
private Boolean checkData(String litra){
	/*
	 * elegxei an einai swsti i timi kai an oxi tote emfanizei minima lathous
	 */
	if(litra.length()<1){
		JOptionPane.showMessageDialog(null, "Den Dwsate arithmo litrwn");
		return false;
	}
	try{  
	    double d = Double.parseDouble(litra);  
	}catch(NumberFormatException nfe){
		JOptionPane.showMessageDialog(null, "Dwste Egkiro arithmo!");
	    return false;  
	}  
	
	return true;
}

private void save(String litraV,String oximaV){
	/*
	 * apothikeuei ta stoixia sthn vash elegxei an apothikeutikan kai emfanizei minima epituxia i apotuxias antistoixa
	 */
	String query="insert into anefodiasmoi(litra,oxima_id) values(?,?)"; //dhmiourgia query
	try {
		con.pS=con.conn.prepareStatement(query);
		con.pS.setString(1, litraV);
		con.pS.setInt(2, Integer.parseInt(oximaV));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	if(con.makeQuery(query)){ //ektelei to query me thn vohthia tiw function makeQuery(String query)
		JOptionPane.showMessageDialog(null, "Saved!"); //an den upardxei lathos emfanizei to minima epituxias
		litra.setText("");
	}else{
		JOptionPane.showMessageDialog(null, "Something went wrong Please Try later!"); //an uparxei lathos tote emfanizei minima lathous
	}
		
	
}
}
