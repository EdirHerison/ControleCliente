package model.servico;

import model.dao.ClienteDAO;
import model.dao.FabricaDAO;
import model.entidades.Cliente;
import model.entidades.Vendedora;

public class ClienteServico {
	
	private ClienteDAO dao = FabricaDAO.cadCliente();

	public void cadastraCliente(Cliente cli) {
		dao.cadCliente(cli);
	}
}
