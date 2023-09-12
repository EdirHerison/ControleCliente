package model.dao;

import db.DbConexao;
import model.dao.imple.ClienteJDBC;
import model.dao.imple.VendaJDBC;
import model.dao.imple.VendedoraJDBC;

public class FabricaDAO {

	public static VendedoraDAO cadVendedor() {
		return new VendedoraJDBC(DbConexao.getConnection());
	}
	
	public static ClienteDAO cadCliente() {
		return new ClienteJDBC(DbConexao.getConnection());
	}
	
	public static VendaDAO insereVenda() {
		return new VendaJDBC(DbConexao.getConnection());
	}
}
