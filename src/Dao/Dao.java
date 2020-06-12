
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
                          con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","username","password");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return con;
	}
}
