package model.dao;

import db.DbConexao;
import model.dao.imple.VendedoraJDBC;

public class FabricaDAO {

	public static VendedoraDAO cadVendedor() {
		return new VendedoraJDBC(DbConexao.getConnection());
	}
}
