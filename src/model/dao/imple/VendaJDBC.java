package model.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DbConexao;
import db.DbExecessao;
import model.dao.VendaDAO;
import model.entidades.Venda;

public class VendaJDBC implements VendaDAO{
	
	private Connection conexao;

	public VendaJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insereVenda(Venda vd) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conexao.prepareStatement("INSERT INTO venda "
					+ "(valvenda, formpag, dtcompra, id_vendedora, id_cliente)"
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setDouble(1, vd.getValor());
			ps.setString(2, vd.getFinalizadora().toString());
			ps.setDate(3, new java.sql.Date(vd.getDt_compr().getTime()));
			ps.setInt(4, vd.getVendedora().getId());
			ps.setInt(5, vd.getCli().getId());
			
			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					vd.setId(id);
				}
				DbConexao.closeResultSet(rs);
			}else {
				throw new DbExecessao("Erro!!! , não houve linhas afetadas.");
			}
			
		} catch (SQLException e) {
			throw new DbExecessao(e.getMessage());
		}
		
	}
	
}
