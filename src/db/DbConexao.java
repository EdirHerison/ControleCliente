package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DbConexao {

	private static Connection conexao = null;
	
	public static Connection getConnection() {
		if(conexao == null) {
			try {
				Properties propriedades = carregaPropriedades();
				String url = propriedades.getProperty("dburl");
				conexao = DriverManager.getConnection(url, propriedades);
				
			} catch (Exception e) {
				throw new DbExecessao(e.getMessage());
			}
		}
		
		return conexao;
	}

	private static Properties carregaPropriedades() {
		try (FileInputStream arq = new FileInputStream("db.properties")){
			Properties propriedades = new Properties();
			propriedades.load(arq);
			return propriedades;
		} catch (Exception e) {
			throw new DbExecessao(e.getMessage());
		}
	}
	
	public static void fechaConexao() {
		if(conexao != null) {
			try {
				conexao.close();
			} catch (Exception e) {
				throw new DbExecessao(e.getMessage());
			}
		}
	}
	
	public static void closeStatent (Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (Exception e) {
				throw new DbExecessao(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				throw new DbExecessao(e.getMessage());
			}
		}
	}
	
}
