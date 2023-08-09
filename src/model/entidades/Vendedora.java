package model.entidades;

import java.io.Serializable;

public class Vendedora implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer codigo;
	private String cpf;
	private String nome;
	
	public Vendedora() {
		
	}

	public Vendedora(Integer id, Integer codigo, String cpf, String nome) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.cpf = cpf;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

