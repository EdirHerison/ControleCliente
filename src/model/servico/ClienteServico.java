package model.servico;

import model.dao.ClienteDAO;
import model.dao.FabricaDAO;
import model.entidades.Cliente;

public class ClienteServico {
	
	private ClienteDAO dao = FabricaDAO.cadCliente();

	public void cadastraCliente(Cliente cli) {
		dao.cadCliente(cli);
	}
	public Cliente consultaPorNome (String nome) {
		return dao.buscaClienteNome(nome);
	}
	public Cliente consultaPorCPF (String cpf) {
		return dao.buscaClienteCPF(cpf);
	}
	public Cliente consultaPorCodTtv (String codTtv) {
		return dao.buscaClinteCodTotvs(codTtv);
	}
	public Cliente consultaPorCod (Integer cod) {
		return dao.buscaClienteCodigo(cod);
	}
}
