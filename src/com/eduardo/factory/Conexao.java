package com.eduardo.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <img width="100" src="http://burrissamps.com/blog/userfiles/1.jpg">
 * <br>
 * Classe respons�vel pela conex�o com banco de dados
 * <br>
 * <b>Conex�o com localhost porta 3306</b>
 * @version 1.0
 * @see 
 * <h4>Veja o tutorial</h4>
 * <a href="http://www.google.com">Exemplo na web</a>
 * @author Renato Paranagu�
 */
public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/bancousuario";
	private static final String USER = "root";
	private static final String PASS = "";
	private static Connection conexao;
	
	/**
	 * Chamada singlethon da conex�o
	 * @return
	 */
	public static Connection getConexao(){
		if(conexao==null){
			try {
				conexao = DriverManager.getConnection(URL, USER, PASS);
				System.out.println("Conectou...");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conexao;
	}
	
}






