package gui;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entidades.Cliente;
import model.entidades.Vendedora;
import model.entities.enums.Genero;
import model.entities.enums.Status;
import model.servico.ClienteServico;
import model.servico.VendedoraServico;

public class CadClienteViewController implements Initializable {
	
	private Cliente cli;
	private ClienteServico cls;
	private VendedoraServico vds;

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtCodTOVS;
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
	private Button btCadastrar;

	private ObservableList<Genero> listGen;
	private ObservableList<Status> listStatus;
	private ObservableList<Vendedora> listVed;

	public void setCliente(Cliente cli) {
		this.cli = cli;
	}

	public void setServicos(ClienteServico cls, VendedoraServico vds) {
		this.cls = cls;
		this.vds = vds;
	}

	@FXML
	public void onCadastrarCliente() {
		if (vds == null) {
			throw new IllegalStateException("VedServ was null");
		}
		if (cli == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (cls == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			Cliente cliente = dadosFor();
            cls.cadastraCliente(cliente);
            Alertas.exibeAlerta("Sucesso!!!!!!", null, "Cadastro realizado", AlertType.INFORMATION);
            limpaForm();
		} catch (Exception e) {
			Alertas.exibeAlerta("Erro ao salvar no banco. ", null, "Causa: " + e.getMessage(), AlertType.ERROR);
		}
	}

	public Cliente dadosFor() {
		Cliente cli = new Cliente();
		cli.setNome(txtNome.getText());
		cli.setCpf(txtCpf.getText());
		cli.setCodTotvs(txtCodTOVS.getText());
		cli.setCodigo(Util.valorInteiro(txtCod.getText()));
		cli.setGenero(cbGenero.getValue());
		cli.setStatus(cbStatus.getValue());
		Instant dtNas = Instant.from(dpNascimento.getValue().atStartOfDay(ZoneId.systemDefault()));
		cli.setNascimento(Date.from(dtNas));
		Instant dtCad = Instant.from(dpCadastro.getValue().atStartOfDay(ZoneId.systemDefault()));
		cli.setCadastro(Date.from(dtCad));
		cli.setVendedora(cbVendedora.getValue());

		return cli;
	}

	private void limpaForm() {
		txtNome.setText("");
		txtCpf.setText("");
		txtCod.setText("");
		txtCodTOVS.setText("");
		cbGenero.getSelectionModel().clearSelection();
		cbStatus.getSelectionModel().clearSelection();
		cbVendedora.getSelectionModel().clearSelection();
	}

	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		configInicial();

	}

	private void configInicial() {
		try {
			RegulaCampos.setCampoInteiro(txtCod);
			RegulaCampos.setCampoCPF(txtCpf);
			RegulaCampos.setLimitaCaracteres(txtCpf, 14);
			RegulaCampos.setLimitaCaracteres(txtNome, 70);
			carregarComboBoxGenero();
			cargaComboBoxStatus();
			
			
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
		Callback<ListView<Vendedora>, ListCell<Vendedora>> factory = lv -> 
		    new ListCell<Vendedora>() {
			@Override
			protected void updateItem(Vendedora item, boolean empyt) {
				super.updateItem(item, empyt);
				setText(empyt ? "" : item.getNome());
			}
		};
		cbVendedora.setCellFactory(factory);
		cbVendedora.setButtonCell(factory.call(null));
	}


	public void cargaComboBoxStatus() {
		listStatus = FXCollections.observableArrayList(Arrays.asList(Status.Ativo));
		cbStatus.setItems(listStatus);
	}
	
	private void carregarComboBoxGenero() {
        listGen = FXCollections.observableArrayList(Arrays.asList(Genero.values()));
        cbGenero.setItems(listGen);
    }

}
