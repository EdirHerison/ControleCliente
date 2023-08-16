package model.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DbConexao;
import db.DbExecessao;
import model.dao.ClienteDAO;
import model.entidades.Cliente;

public class ClienteJDBC implements ClienteDAO {

	private Connection conexao;

	public ClienteJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void cadCliente(Cliente cli) {
	    PreparedStatement ps = null;
	    try {
			ps = conexao.prepareStatement("INSERT INTO cliente "
					+ "(cpf, codTotvs, codCliente, nome, genero, aniversario, dtCadastro, stcli, id_vendedora) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, cli.getCpf());
			ps.setString(2, cli.getCodTotvs());
			ps.setInt(3, cli.getCodigo());
			ps.setString(4, cli.getNome());
			ps.setString(5, cli.getGenero().toString());
			ps.setDate(6, new java.sql.Date(cli.getNascimento().getTime()));
			ps.setDate(7, new java.sql.Date(cli.getCadastro().getTime()));
			ps.setString(8, cli.getStatus().toString());
			ps.setInt(9, cli.getVendedora().getId());
			
			int linhasAfetadas = ps.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					cli.setId(id);
				}
				DbConexao.closeResultSet(rs);
			}else {
				throw new DbExecessao("Erro!!! , não houve linhas afetadas.");
			}
			
		} catch (SQLException e) {
			throw new DbExecessao(e.getMessage());
		}
	    finally {
			DbConexao.closeStatent(ps);
		}

	}

}
