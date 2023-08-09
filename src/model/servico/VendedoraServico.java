package model.servico;

import model.dao.FabricaDAO;
import model.dao.VendedoraDAO;
import model.entidades.Vendedora;

public class VendedoraServico {

	private VendedoraDAO dao = FabricaDAO.cadVendedor();

	public void cadastraVendedor(Vendedora ved) {
		dao.cadVendedora(ved);
	}

	public Vendedora consultaPorCodigo(Integer cod) {
		return dao.buscaVendedorCodigo(cod);
	}

	public Vendedora consultaPorCPF(String cpf) {
		return dao.buscaVendedorCPF(cpf);
	}

	public Vendedora consultaPorNomen(String nome) {
		return dao.buscaVendedornome(nome);
	}
	
	public void atualizaVendedora(Vendedora ved) {
		 dao.atualizaVendedora(ved);
	}
	
	public void removeVendedora(Vendedora ved) {
		dao.removeVendedora(ved.getId());
	}
}
