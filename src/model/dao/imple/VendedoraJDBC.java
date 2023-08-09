package model.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DbConexao;
import db.DbExecessao;
import model.dao.VendedoraDAO;
import model.entidades.Vendedora;

public class VendedoraJDBC implements VendedoraDAO {

	private Connection conexao;
	
	public VendedoraJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void cadVendedora(Vendedora ved) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement("INSERT INTO vendedora "
					+ "(codVendedora, cpf, nome)"
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, ved.getCodigo());
			ps.setString(2, ved.getCpf());
			ps.setString(3, ved.getNome());
			int linhasAfetadas = ps.executeUpdate();
			if(linhasAfetadas > 0) {
				rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					ved.setId(id);
				}
			}
			else {
				throw new DbExecessao(" Vendedor(a) não pode ser cadastrada.");
			}
		} catch (Exception e) {
			throw new DbExecessao(e.getMessage());
		}
		finally {
			DbConexao.closeResultSet(rs);
			DbConexao.closeStatent(ps);	
		}
	}

	@Override
	public Vendedora buscaVendedorCodigo(Integer cod) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement("SELECT * FROM vendedora WHERE codVendedora = ?");
			ps.setInt(1, cod);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Vendedora ved = new Vendedora();
				ved.setId(rs.getInt("idvendedora"));
				ved.setCodigo(rs.getInt("codVendedora"));
				ved.setCpf(rs.getString("cpf"));
				ved.setNome(rs.getString("nome"));
				return ved;
			}
			return null;
		} catch (SQLException e) {
			throw new DbExecessao(e.getMessage());
		}	
	}
	
	@Override
	public Vendedora buscaVendedorCPF(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement("SELECT * FROM vendedora WHERE cpf = ?");
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Vendedora ved = new Vendedora();
				ved.setId(rs.getInt("idvendedora"));
				ved.setCodigo(rs.getInt("codVendedora"));
				ved.setCpf(rs.getString("cpf"));
				ved.setNome(rs.getString("nome"));
				return ved;
			}
			return null;
		} catch (SQLException e) {
			throw new DbExecessao(e.getMessage());
		}	
	}

	@Override
	public Vendedora buscaVendedornome(String nome) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement("SELECT * FROM vendedora WHERE nome = ?");
			ps.setString(1, nome);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Vendedora ved = new Vendedora();
				ved.setId(rs.getInt("idvendedora"));
				ved.setCodigo(rs.getInt("codVendedora"));
				ved.setCpf(rs.getString("cpf"));
				ved.setNome(rs.getString("nome"));
				return ved;
			}
			return null;
		} catch (SQLException e) {
			throw new DbExecessao(e.getMessage());
		}
	}

	@Override
	public void atualizaVendedora(Vendedora ved) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(
					"UPDATE vendedora "
					+ "SET codVendedora=?, cpf=?, nome=? "
					+ "WHERE idvendedora=?");
			st.setInt(1, ved.getCodigo());
			st.setString(2, ved.getCpf());
			st.setString(3, ved.getNome());
			st.setInt(4, ved.getId());

			st.executeUpdate();
			
		}catch (SQLException e){
			throw new DbExecessao(e.getMessage());
		}
		finally {
			DbConexao.closeStatent(st);
		}
	}

	@Override
	public void removeVendedora(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conexao.prepareStatement("DELETE FROM vendedora WHERE idvendedora=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbExecessao(e.getMessage());
		}
		finally {
			DbConexao.closeStatent(ps);
		}
		
	}

	
	
	
}
