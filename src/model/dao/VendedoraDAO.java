package model.dao;

import model.entidades.Vendedora;

public interface VendedoraDAO {

	void cadVendedora(Vendedora ved);

	Vendedora buscaVendedorCPF(String cpf);

	Vendedora buscaVendedorCodigo(Integer cod);

	Vendedora buscaVendedornome(String nome);

	void atualizaVendedora(Vendedora ved);
	
	void removeVendedora (Integer id);

}