package model.servico;

import model.dao.FabricaDAO;
import model.dao.VendaDAO;
import model.entidades.Venda;

public class VendaServico {

	private VendaDAO dao = FabricaDAO.insereVenda();
	
	public void insereVenda (Venda vd) {
		dao.insereVenda(vd);
	}
}
