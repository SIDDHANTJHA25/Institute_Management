/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

/**
 *
 * @author sid
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

	private static Connection con;
	public static Connection getMyConnection() {
		
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
                          con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc1","don");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return con;
	}
}
