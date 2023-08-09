package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbExecessao;
import gui.util.Alertas;
import gui.util.RegulaCampos;
import gui.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entidades.Vendedora;
import model.servico.VendedoraServico;

public class CadVendedoraViewController implements Initializable {

	private Vendedora ved;
	private VendedoraServico vds;
	
	@FXML
	private TextField txtCod;
	@FXML
	private TextField txtCPF;
	@FXML
	private TextField txtNome;
	@FXML
	private Button btCadastrar;
	
	public void setVendedoraEntidade(Vendedora ved) {
		this.ved = ved;
	}
	public void setVendedoraServico(VendedoraServico vds) {
		this.vds = vds;
	}
	
	@FXML
	public void acaoBotaoCadastrar() {
		if(ved == null ) {
			throw new IllegalStateException(" Erro!!! Dependencia da Entidade Vendedora não foi injetada. ");
		}
		if(vds == null) {
			throw new IllegalStateException(" Erro!!! Dependencia da Serviço Vendedora não foi injetada. ");
		}
		try {
			ved = dadosDoForm();
			vds.cadastraVendedor(ved);
			Alertas.exibeAlerta("Sucesso!!!!!!", null, "Cadastro realizado", AlertType.INFORMATION);
			limpaForm();
		} catch (DbExecessao e) {
			Alertas.exibeAlerta("Erro ao salvar no banco. ", null,"Causa: " + e.getMessage() , AlertType.ERROR);
		}
	}
	
	public Vendedora dadosDoForm() {
		Vendedora ved = new Vendedora();
		ved.setCodigo(Util.valorInteiro(txtCod.getText()));
		ved.setCpf(txtCPF.getText());
		ved.setNome(txtNome.getText());
		return ved;
	}
	
	private void limpaForm() {
		txtCod.setText("");
	    txtCPF.setText("");
	    txtNome.setText("");
	}

	@Override
	public void initialize(URL url, ResourceBundle rs) {
		configInicial();
	}

	private void configInicial() {
       RegulaCampos.setCampoInteiro(txtCod);
       RegulaCampos.setLimitaCaracteres(txtCPF, 14);
       RegulaCampos.setCampoCPF(txtCPF);
       RegulaCampos.setLimitaCaracteres(txtNome, 70);
	}

}
