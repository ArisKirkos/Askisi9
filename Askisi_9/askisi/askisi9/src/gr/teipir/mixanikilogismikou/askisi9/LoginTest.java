package gr.teipir.mixanikilogismikou.askisi9;

import static org.junit.Assert.*;

import java.awt.EventQueue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest {
	
	private Mysql con;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() {
		con = new Mysql();
		Login frame = new Login();
		frame.setVisible(true);
		
	}
	
	@Test
	public void testLogin(){
		Login login = new Login();
	}
	

}
