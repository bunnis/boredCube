package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BDTest {

	public static void main(String[] args) {
		
		DB ola = new DB();
		System.out.println("criei");
		 try {
			 System.out.println("criei2");
			ResultSet a =  ola.runSql("select * from users");
			System.out.println("criei3");
			a.toString();
			System.out.println("criei4");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
