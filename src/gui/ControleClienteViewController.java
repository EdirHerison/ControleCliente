package gui;

import java.net.URL;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alertas;
import gui.util.RegulaCampos;
import gui.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.util.Callback;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.entidades.Cliente;
import model.entidades.Vendedora;
import model.entities.enums.Genero;
import model.entities.enums.Status;
import model.servico.ClienteServico;
import model.servico.VendedoraServico;

public class ControleClienteViewController implements Initializable {
	
	private Cliente cli;
	private ClienteServico cls;
	private VendedoraServico vds;

	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCPF;
	@FXML
	private TextField txtCodTotvs;
	@FXML
	private TextField txtCod;
	@FXML
	private DatePicker dpNascimento;
	@FXML
	private DatePicker dpCadastro;
	@FXML
	private ComboBox<Genero> cbGenero;
	@FXML
	private ComboBox<Status> cbStatus;
	@FXML
	private ComboBox<Vendedora> cbVendedora;
	@FXML
	private Button btConsultar;
	
	private ObservableList<Genero> listGen;
	private ObservableList<Status> listSt;
	private ObservableList<Vendedora> listVed;
	
	public void setCliente (Cliente cli) {
		this.cli = cli;
	}
	public void setServicos (ClienteServico cls, VendedoraServico vds) {
		this.cls = cls;
		this.vds = vds;
	}
	
	public void onConsultaClienteButton() {
		try {
			String nome = txtNome.getText();
			String cpf = txtCPF.getText();
			String codTtv = txtCodTotvs.getText();
			String cod = txtCod.getText();
			cli = null;
			if(!nome.isEmpty()) {
				cli = cls.consultaPorNome(nome);
			}
			if(!cpf.isEmpty()) {
				cli = cls.consultaPorCPF(cpf);
			}
			if(!codTtv.isEmpty()) {
				cli = cls.consultaPorCodTtv(codTtv);
			}
			if(!cod.isEmpty()) {
				int codigo = Util.valorInteiro(cod);
				cli = cls.consultaPorCod(codigo);
			}
			
			if(cli != null) {
				txtId.setText(String.valueOf(cli.getId()));
				txtNome.setText(cli.getNome());
				txtCPF.setText(cli.getCpf());
				txtCodTotvs.setText(cli.getCodTotvs());
				txtCod.setText(String.valueOf(cli.getCodigo()));
				dpNascimento.setValue(cli.getNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				dpCadastro.setValue(cli.getCadastro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				cbGenero.setValue(cli.getGenero());
				cbStatus.setValue(cli.getStatus());
				cbVendedora.setValue(cli.getVendedora());
			}
			else {
				Alertas.exibeAlerta("Erro !!!", null, "Cliente não foi encontrado na base de dados", AlertType.ERROR);
			}
		} catch (Exception e) {
			 Alertas.exibeAlerta("Erro !!!", null, "Ocorreu um erro ao consultar o cliente: " + e.getMessage(), AlertType.ERROR);
		}
	}
	private void limpaForm() {
		txtNome.setText("");
		txtCPF.setText("");
		txtCod.setText("");
		txtCodTotvs.setText("");
		cbGenero.getSelectionModel().clearSelection();
		cbStatus.getSelectionModel().clearSelection();
		cbVendedora.getSelectionModel().clearSelection();
		dpNascimento.setValue(null);
		dpCadastro.setValue(null);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		configInicial();
		
	}
	
	private void configInicial() {
		try {
			RegulaCampos.setCampoInteiro(txtCod);
			RegulaCampos.setCampoCPF(txtCPF);
			RegulaCampos.setLimitaCaracteres(txtCPF, 14);
			RegulaCampos.setLimitaCaracteres(txtNome, 70);
			Util.formatDatePicker(dpNascimento, "dd/MM/yyyy");
			Util.formatDatePicker(dpCadastro, "dd/MM/yyyy");
			carregarComboBoxGenero();
			cargaComboBoxStatus();
			iniciaCBVendedora();

		} catch (Exception e) {
			Alertas.exibeAlerta("Erro !!!", null, e.getMessage(), null);
		}

	}
	
	public void cargaComboBoxVendedora() {
		if (vds == null) {
			throw new IllegalStateException("Vendedora não foi Injetada");
		}
		List<Vendedora> list = vds.buscaTodos();
		listVed = FXCollections.observableArrayList(list);
		cbVendedora.setItems(listVed);
	}

	private void iniciaCBVendedora() {
		Callback<ListView<Vendedora>, ListCell<Vendedora>> factory = lv -> new ListCell<Vendedora>() {
			@Override
			protected void updateItem(Vendedora item, boolean empyt) {
				super.updateItem(item, empyt);
				if(item == null || empyt) {
					setText(null);
				}else {
					setText(item.getNome());
				}
			}
		};
		cbVendedora.setCellFactory(factory);
		cbVendedora.setButtonCell(factory.call(null));
	}

	public void cargaComboBoxStatus() {
		listSt = FXCollections.observableArrayList(Arrays.asList(Status.ATIVO));
		cbStatus.setItems(listSt);
	}

	private void carregarComboBoxGenero() {
		listGen = FXCollections.observableArrayList(Arrays.asList(Genero.values()));
		cbGenero.setItems(listGen);
	}

}
