package model.entidades;

import java.io.Serializable;
import java.util.Date;

import model.entidades.Vendedora;
import model.entities.enums.Genero;
import model.entities.enums.Status;


public class Cliente implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cpf;
	private String codTotvs;
	private Integer codigo;
	private String nome;
	private Genero genero;
	private Date nascimento;
	private Date cadastro;
	private Status stcli;
	
	private Vendedora vendedora;
	
	public Cliente() {
		
	}

	public Cliente(Integer id, String cpf, String codTotvs, Integer codigo, String nome, Genero genero, Date nascimento,
			Date cadastro, Status status, Vendedora vendedora) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.codTotvs = codTotvs;
		this.codigo = codigo;
		this.nome = nome;
		this.genero = genero;
		this.nascimento = nascimento;
		this.cadastro = cadastro;
		this.stcli = status;
		this.vendedora = vendedora;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCodTotvs() {
		return codTotvs;
	}

	public void setCodTotvs(String codTotvs) {
		this.codTotvs = codTotvs;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public Status getStatus() {
		return stcli;
	}

	public void setStatus(Status status) {
		this.stcli = status;
	}

	public Vendedora getVendedora() {
		return vendedora;
	}

	public void setVendedora(Vendedora vendedora) {
		this.vendedora = vendedora;
	}
	
	

}
