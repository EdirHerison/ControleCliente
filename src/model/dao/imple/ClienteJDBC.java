package model.dao.imple;

import java.sql.Connection;

import model.dao.ClienteDAO;
import model.entidades.Cliente;

public class ClienteJDBC implements ClienteDAO {

	private Connection conexao;

	public ClienteJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void cadCliente(Cliente cli) {
		// TODO Auto-generated method stub

	}

}
