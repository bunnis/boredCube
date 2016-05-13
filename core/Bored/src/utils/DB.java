package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import data.Evento;
import scrappers.Yeaaaah;

public class DB {

	public Connection conn = null;
	//aws
	//bunny senhas24
	//endpoint bored.ckqpppo0rrxf.us-west-2.rds.amazonaws.com:3306

	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Scanner s;
			String url="";
			String user="";
			String pass="";
			try {
				s = new Scanner(new File("db.txt"));
				 url = s.nextLine();
				 user = s.nextLine();
				 pass = s.nextLine();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			String url_db = "jdbc:mysql://"+url;
			conn = DriverManager.getConnection(url_db, user, pass);
			System.out.println("conn built");
			
			//check some countries exist
			checkIfCountriesExist("PORTUGAL");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}



	//	
	//	  public int performInsert(String columns, String values, String table) throws SQLException {
	//	        String sqlInsert = "INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")"; 
	//	        Connection conn = DriverManager.getConnection(connectionString, username, password);
	//	        PreparedStatement pst = conn.prepareStatement(sqlInsert);
	//	        int toReturn = 0;
	//	        try {
	//	            toReturn = pst.executeUpdate();
	//	        }
	//	        catch(Exception e){
	//	            e.printStackTrace();
	//	            pst.close();
	//	            conn.close();
	//	            return toReturn;
	//	        }
	//	        pst.close();
	//	        conn.close();
	//	        return toReturn;
	//	    }





	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}

	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}

	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}


	//TODO scrappers need to have a parent class
	public void populateWith(Yeaaaah yh) {
		// TODO Auto-generated method stub
		for(Evento e: yh.getEventList()) {
			try {
				insertEvento(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("Failed inserting event for yeah");
				e1.printStackTrace();
				
			}
		}
	}



	private void insertEvento(Evento e) throws SQLException {
		// TODO Auto-generated method stub
		// create the mysql insert preparedstatement

		//insert actividade
		//INSERT INTO actividade (actividadenome,actividademorada) VALUES ()

		//create horario
		//INSERT INTO `bored`.`horario` (`idhorario`, `horario2f`, `horario3f`, `horario4f`, `horario5f`, `horario6f`, `horariosabado`, `horariodomingo`, `horarioferiado`, `horariodiadefecho`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
		
		String query_horario = "INSERT INTO `bored`.`horario` (`idhorario`, `horario2f`, `horario3f`, `horario4f`, `horario5f`, `horario6f`, `horariosabado`, `horariodomingo`, `horarioferiado`, `horariodiadefecho`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt_horario = conn.prepareStatement(query_horario);
		//preparedStmt_horario.setString (1, e.getNomeActividade());
		preparedStmt_horario.setInt (1, 1);
	    preparedStmt_horario.setString (2, "8h-19h");
	    preparedStmt_horario.setString (3, "8h-19h");
	    preparedStmt_horario.setString (4, "8h-19h");
	    preparedStmt_horario.setString (5, "8h-19h");
	    preparedStmt_horario.setString (6, "8h-19h");
	    preparedStmt_horario.setString (7, "8h-22h");
	    preparedStmt_horario.setString (8, "CLOSED");
	    preparedStmt_horario.setString (9, "8h-13h");
	    preparedStmt_horario.setString (10, "8h-19h");
	    preparedStmt_horario.execute();
		
		
		String query = "INSERT INTO `bored`.`actividade` (`idactividade`, `actividadenome`, `actividademorada`, `actividadegps`, `actividadepais`, `actividadefoto`, `horario_idhorario`, `Pais_idPais`, `Pais_PaisNome`, `actividaderating`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt (1, e.getID());
		preparedStmt.setString (2, e.getNomeActividade());
	    preparedStmt.setString (3, e.getMoradaActividade());
	    preparedStmt.setString (4, "PORTUGAL");
	    preparedStmt.setInt (7, 1);
	    preparedStmt.execute();
	}
	
	private void checkIfCountriesExist(String string) {
		// TODO Auto-generated method stub
		String query = "select * from Pais";
		try {
			ResultSet countries = runSql(query);
			
			int a = 0;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}