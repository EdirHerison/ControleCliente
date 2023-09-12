package model.entidades;

import java.io.Serializable;
import java.util.Date;

import model.entities.enums.FormPag;

public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Double valor;
	private FormPag finalizadora;
	private Date dt_compr;
	
	private Vendedora vendedora;
	private Cliente cli;
	
	public Venda() {
		
	}

	public Venda(Integer id, Double valor, FormPag finalizadora, Date dt_compr, Vendedora vendedora, Cliente cli) {
		super();
		this.id = id;
		this.valor = valor;
		this.finalizadora = finalizadora;
		this.dt_compr = dt_compr;
		this.vendedora = vendedora;
		this.cli = cli;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public FormPag getFinalizadora() {
		return finalizadora;
	}

	public void setFinalizadora(FormPag finalizadora) {
		this.finalizadora = finalizadora;
	}

	public Date getDt_compr() {
		return dt_compr;
	}

	public void setDt_compr(Date dt_compr) {
		this.dt_compr = dt_compr;
	}

	public Vendedora getVendedora() {
		return vendedora;
	}

	public void setVendedora(Vendedora vendedora) {
		this.vendedora = vendedora;
	}

	public Cliente getCli() {
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}
	
	
}
