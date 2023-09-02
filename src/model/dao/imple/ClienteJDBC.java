package model.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbConexao;
import db.DbExecessao;
import model.dao.ClienteDAO;
import model.entidades.Cliente;
import model.entidades.Vendedora;
import model.entities.enums.Genero;
import model.entities.enums.Status;

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
					+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

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

			if (linhasAfetadas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					cli.setId(id);
				}
				DbConexao.closeResultSet(rs);
			} else {
				throw new DbExecessao("Erro!!! , não houve linhas afetadas.");
			}

		} catch (SQLException e) {
			throw new DbExecessao(e.getMessage());
		} finally {
			DbConexao.closeStatent(ps);
		}

	}

	@Override
	public Cliente buscaClienteNome(String nome) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement("SELECT c.idcliente, c.cpf, c.codTotvs, c.codCliente, c.nome, c.genero, "
					+ "c.aniversario, c.dtCadastro, c.stcli, v.nome AS nome_vendedora "
					+ "FROM cliente AS c "
					+ "INNER JOIN vendedora AS v ON c.id_vendedora = v.idvendedora "
					+ "WHERE c.nome = ? ");
			
			ps.setString(1, nome);
			rs = ps.executeQuery();

			if (rs.next()) {
				Vendedora ved = dadosVendedoraDB(rs);
				Cliente cli = dadosClienteDB(rs, ved);
				return cli;
			}
			return null;

		} catch (Exception e) {
			throw new DbExecessao(e.getMessage());
		}
		finally {
			DbConexao.closeStatent(ps);
			DbConexao.closeResultSet(rs);
		}
	}

	@Override
	public Cliente buscaClienteCPF(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscaClinteCodTotvs(String codTtv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscaClienteCodigo(Integer cod) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Cliente> buscaNomeVendedora(Vendedora ved) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement("SELECT cliente.*,vendedora.nome as NomeVendedora "
					+ "FROM cliente INNER JOIN vendedora "
					+ "ON cliente.id_vendedora = vendedora.idvendedora "
					+ "WHERE nome = ? "
					+ "ORDER BY nome");
			
			ps.setString(1, ved.getNome());
			rs = ps.executeQuery();
			
			List<Cliente> listaCliente = new ArrayList<>();
			Map<String, Vendedora> map = new HashMap<>();
			
			while(rs.next()) {
				Vendedora vendedora = map.get(rs.getString("nome"));
				  if(vendedora == null) {
					  vendedora = dadosVendedoraDB(rs);
					  map.put(rs.getString("nome"), vendedora);
				  }
				  
				 Cliente cli = dadosClienteDB(rs, vendedora);
				 listaCliente.add(cli); 
			}
			return listaCliente;
			
		} catch (SQLException e) {
			throw new DbExecessao(e.getMessage());
		}
		finally {
			DbConexao.closeStatent(ps);
			DbConexao.closeResultSet(rs);
		}
		
	}


	
	private Cliente dadosClienteDB (ResultSet rs, Vendedora ved) throws SQLException {
		Cliente cli = new Cliente();
		cli.setId(rs.getInt("c.idcliente"));
		cli.setNome(rs.getString("c.nome"));
		cli.setCpf(rs.getString("c.cpf"));
		cli.setCodTotvs(rs.getString("c.codTotvs"));
		cli.setCodigo(rs.getInt("c.codCliente"));
		cli.setNascimento(new java.util.Date(rs.getTimestamp("c.aniversario").getTime()));
		cli.setCadastro(new java.util.Date(rs.getDate("c.dtCadastro").getTime()));
		String genString = rs.getString("c.genero");
		Genero gen = Genero.valueOf(genString);
		cli.setGenero(gen);
		String stString = rs.getString("c.stcli");
		Status st = Status.valueOf(stString);
		cli.setStatus(st);
		cli.setVendedora(dadosVendedoraDB(rs));
		return cli;
	}
	
	
	private Vendedora dadosVendedoraDB (ResultSet rs) throws SQLException {
		Vendedora ved = new Vendedora();
		ved.setNome(rs.getString("nome_vendedora"));
		return ved;
	}

	
	
}
