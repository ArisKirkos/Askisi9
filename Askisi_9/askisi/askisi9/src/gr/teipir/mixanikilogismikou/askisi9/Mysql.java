package gr.teipir.mixanikilogismikou.askisi9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Mysql {
	public Connection conn=null;
	public PreparedStatement pS=null;
	public Statement stmt;
	private ResultSet rs;
	private String user="root";
	private String pass="123456";

	public Mysql(){
		/*
		 * kanei thn sundesh me thn vash sthn dimiourgia tou antikeimenou
		 */
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/askisi9?useUnicode=true&characterEncoding=UTF-8&";
			conn = DriverManager
			          .getConnection(url
			              + "user="+user+"&password="+pass);
			stmt=conn.createStatement();
			stmt.execute("set names utf8");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Apotixia Epikoinwnias me tin vash!");
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getOximata(){
		/*
		 * epistefei ta oxhmata ths vashs
		 */
		ArrayList<String> oximata=new ArrayList<String>();
		try {
			pS = conn.prepareStatement("SELECT * FROM oximata");
		
		rs=pS.executeQuery();
		while(rs.next())
			oximata.add(rs.getString("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return oximata;
	}
	
	public ArrayList<HashMap<String, String>> getOxima(int id){
		/*
		 * epistefei ta stoixia enos sugkekrimenou oximatos
		 */
		ArrayList<HashMap<String, String>> oxima=new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			pS = conn.prepareStatement("SELECT * FROM oximata WHERE id=?");
			pS.setInt(1, id);
			rs=pS.executeQuery();
			if(rs.next()){
				map.put("id", id+"");
				map.put("diadromi", rs.getString("diadromi"));
				oxima.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return oxima;
	}
	
	public String getNumOfOximata(){
		/*
		 * epistefei ton arithmo twn oximatwn ths vashs
		 */
		String num=null;
		try {
			pS = conn.prepareStatement("SELECT COUNT(*) AS num FROM oximata");
			rs=pS.executeQuery();
			while(rs.next()){
				num=rs.getString("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return num;
	}
	
	public String getNumOfOdigoi(){
		/*
		 * epistefei ton arithmo twn odigwn ths vashs
		 */
		String num=null;
		try {
			pS = conn.prepareStatement("SELECT COUNT(*) AS num FROM odigoi");
			rs=pS.executeQuery();
			while(rs.next()){
				num=rs.getString("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return num;
	}
	
	public ArrayList<HashMap<String, String>> getAnafores(){
		/*
		 * epistrefei tis anafores ths vashs se mia lista opou pairnei to onoma tou column kai thn timi tou
		 */
		ArrayList<HashMap<String, String>> anafores=new ArrayList<HashMap<String, String>>();
		
		try {
			pS = conn.prepareStatement("SELECT * FROM anafores INNER JOIN odigoi ON odigos_id=odigoi.id");
			rs=pS.executeQuery();
			while(rs.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("provlima", rs.getString("provlima"));
				map.put("sxolia", rs.getString("sxolia"));
				map.put("oxima", rs.getString("oxima"));
				map.put("time", rs.getString("time"));
				anafores.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return anafores;
	}
	
	public ArrayList<HashMap<String, String>> getOximataStatistika(){
		/*
		 * epistrefei ta statistika gia ton diaxiristi 
		 */
		 
		ArrayList<HashMap<String, String>> anafores=new ArrayList<HashMap<String, String>>();
		PreparedStatement newPs;
		ResultSet newRs;
		try {
			pS = conn.prepareStatement("SELECT * FROM oximata INNER JOIN odigoi ON oximata.id=oxima");
			rs=pS.executeQuery();
			while(rs.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("oximata.id"));
				map.put("odigosId", rs.getString("odigoi.id"));
				map.put("odigosOnoma", rs.getString("onoma"));
				
				newPs= conn.prepareStatement("SELECT COUNT(*) AS num FROM anafores INNER JOIN odigoi ON odigos_id=odigoi.id WHERE" +
						" odigoi.id =?");
				newPs.setString(1, rs.getString("odigoi.id"));
				newRs=newPs.executeQuery();
				if(newRs.next())
					map.put("anaforesNum", newRs.getString("num"));
				
				newPs= conn.prepareStatement("SELECT COUNT(*) AS num FROM anefodiasmoi INNER JOIN oximata ON oxima_id=oximata.id WHERE" +
						" oximata.id =?");
				newPs.setString(1, rs.getString("oximata.id"));
				newRs=newPs.executeQuery();
				if(newRs.next())
					map.put("anefodiasmoiNum", newRs.getString("num"));
				anafores.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return anafores;
	}
	
	public ArrayList<HashMap<String, String>> getOdigous(){
		/*
		 * epistrefei tous odigous ths vashs
		 */
		ArrayList<HashMap<String, String>> odigoi=new ArrayList<HashMap<String, String>>();
		
		try {
			pS = conn.prepareStatement("SELECT * FROM odigoi");
			rs=pS.executeQuery();
			while(rs.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("onoma", rs.getString("onoma"));
				map.put("eponimo", rs.getString("eponimo"));
				map.put("username", rs.getString("username"));
				map.put("password", rs.getString("password"));
				map.put("oxima", rs.getString("oxima"));
				odigoi.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return odigoi;
	}
	
	public ArrayList<HashMap<String, String>> getOdigo(int id){
		/*
		 * epistrefei ta stoixia enos sugkekrimenou odigou
		 */
		ArrayList<HashMap<String, String>> odigoi=new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			pS = conn.prepareStatement("SELECT * FROM odigoi WHERE id=?");
			pS.setInt(1, id);
			rs=pS.executeQuery();
			if(rs.next()){
				map.put("id", rs.getString("id"));
				map.put("onoma", rs.getString("onoma"));
				map.put("eponimo", rs.getString("eponimo"));
				map.put("username", rs.getString("username"));
				map.put("password", rs.getString("password"));
				map.put("oxima", rs.getString("oxima"));
				odigoi.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return odigoi;
	}
	
	public String[] getLatLon(int id){
		/*
		 * epistrefei tis suntetagmenes enos oximatos
		 */
		String[] oxima=new String[2];
		try {
			pS = conn.prepareStatement("SELECT * FROM oximata WHERE id=?");
			pS.setInt(1, id);
			rs=pS.executeQuery();
			if(rs.next()){
				oxima[0]=rs.getString("lat");
				oxima[1]=rs.getString("lon");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return oxima;
	}
	
	public Boolean makeQuery(String query){
		/*
		 * ektelei to query pou tou dinetai kai epistrefei an ektelesthke kai an oxi
		 */
		try {
			if(pS.executeUpdate()==0)
				return false;
			else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String[] checkLogin(String us,String pass){
		/*
		 * elegxei an einai swsta ta stoixia tou login kai epistrefei to epipedo tou user px(ipalilos)
		 */
		String[] priv= new String[2];
		priv[0]="0";
		try {
			pS = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
			pS.setString(1, us);
			pS.setString(2, pass);
			rs=pS.executeQuery();
			while(rs.next())
				priv[0]=rs.getString("privilages");
			if(priv[0].equals("0")){
				pS = conn.prepareStatement("SELECT * FROM odigoi WHERE username=? AND password=?");
				pS.setString(1, us);
				pS.setString(2, pass);
				rs=pS.executeQuery();
				while(rs.next()){
					priv[0]="odigos";
					priv[1]=rs.getString("id");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			priv[0]="0";
		} catch (Exception e) {
			e.printStackTrace();
			priv[0]="0";
		}
		
		
		return priv;
	}
}
