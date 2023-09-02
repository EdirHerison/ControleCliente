package model.entidades;

import java.io.Serializable;
import java.util.Objects;

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
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo, cpf, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedora other = (Vendedora) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return this.getNome() ;
	}
}
