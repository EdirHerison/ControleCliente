package model.dao;

import java.util.List;

import model.entidades.Cliente;
import model.entidades.Vendedora;

public interface ClienteDAO {

	void cadCliente (Cliente cli);
	Cliente buscaClienteNome (String nome);
	Cliente buscaClienteCPF (String cpf);
	Cliente buscaClinteCodTotvs (String codTtv);
	Cliente buscaClienteCodigo (Integer cod);
	List<Cliente> buscaNomeVendedora (Vendedora ved);
}
