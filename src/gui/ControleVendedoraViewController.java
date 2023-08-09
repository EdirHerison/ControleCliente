package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbExcessaoIntegracao;
import db.DbExecessao;
import gui.util.Alertas;
import gui.util.RegulaCampos;
import gui.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entidades.Vendedora;
import model.servico.VendedoraServico;

public class ControleVendedoraViewController implements Initializable {

	private Vendedora ved;
	private VendedoraServico vds;

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtCod;
	@FXML
	private TextField txtCPF;
	@FXML
	private TextField txtNome;
	@FXML
	private Button btConsultar;
	@FXML
	private Button btAtualizar;
	@FXML
	private Button btExcluir;

	public void setVendedoraEntidade(Vendedora ved) {
		this.ved = ved;
	}

	public void setVendedoraService(VendedoraServico vds) {
		this.vds = vds;
	}

	@FXML
	public void onBtConsultaVed() {
		try {
			String cod = txtCod.getText();
			String cpf = txtCPF.getText();
			String nome = txtNome.getText();
			ved = null;
			if (!cod.isEmpty()) {
				int codigo = Util.valorInteiro(cod);
				ved = vds.consultaPorCodigo(codigo);
			} else if (!cpf.isEmpty()) {
				ved = vds.consultaPorCPF(cpf);
			} else if (!nome.isEmpty()) {
				ved = vds.consultaPorNomen(nome);
			}

			if (ved != null) {
				txtId.setText(String.valueOf(ved.getId()));
				txtCod.setText(String.valueOf(ved.getCodigo()));
				txtCPF.setText(ved.getCpf());
				txtNome.setText(ved.getNome());
			} else {
				Alertas.exibeAlerta("Erro!!!!", null, "Vendedora não encontrado e/ou não cadastrado na base de dados",
						AlertType.ERROR);
			}

		} catch (DbExecessao e) {
			throw new IllegalStateException("Erro ao consultar vendedora no banco de dados: " + e.getMessage(), e);
		}
	}

	public void onBtAtualiza() {
		try {
			if (ved.getId() == null) {
				Alertas.exibeAlerta("Não há dados", null,
						"Não há dados para serem alterados" + " favor pesquise por uma vendedora (CONSULTAR)",
						AlertType.ERROR);
			} else {
				ved = dadosVendedora();
				vds.atualizaVendedora(ved);
				Alertas.exibeAlerta("Sucesso !!!", null, "Atalizado com Sucesso", AlertType.CONFIRMATION);
				limpaForm();
			}
		} catch (DbExecessao e) {
			throw new IllegalStateException("Erro ao tentar atualizar cadastro: " + e.getMessage());
		}
	}

	public void onBtExclui() {
		try {
			if (ved.getId() == null) {
				Alertas.exibeAlerta("Não há dados", null,
						"Não há dados para serem excluidos" + " favor pesquise por uma vendedora (CONSULTAR)",
						AlertType.ERROR);
			}
			vds.removeVendedora(ved);
			Alertas.exibeAlerta("Sucesso !!!", null, "Cadastro removido com Sucesso", AlertType.CONFIRMATION);
			limpaForm();
		} catch (DbExcessaoIntegracao e) {
			Alertas.exibeAlerta("Erro !!! ", null, e.getMessage(), AlertType.ERROR);
		}
	}

	public Vendedora dadosVendedora() {
		if (ved == null) {
			throw new IllegalStateException("Vendedora esta vazio");
		}
		ved.setCodigo(Util.valorInteiro(txtId.getText()));
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
		configCampos();
	}

	public void configCampos() {
		RegulaCampos.setCampoInteiro(txtCod);
		RegulaCampos.setCampoCPF(txtCPF);
		RegulaCampos.setLimitaCaracteres(txtCPF, 14);
	}
}
