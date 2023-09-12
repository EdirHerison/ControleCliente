package gui;

import java.net.URL;
import java.nio.channels.IllegalSelectorException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import model.entidades.Cliente;
import model.entidades.Venda;
import model.entidades.Vendedora;
import model.entities.enums.FormPag;
import model.servico.ClienteServico;
import model.servico.VendaServico;
import model.servico.VendedoraServico;

public class VendaViewController implements Initializable {

	private Venda nvd;
	private VendaServico veds;
	private VendedoraServico vds;
	private ClienteServico cls;

	@FXML
	private TextField txtVl1;
	@FXML
	private TextField txtVl2;
	@FXML
	private TextField txtVl3;
	@FXML
	private TextField txtVl4;
	@FXML
	private TextField txtVl5;
	@FXML
	private TextField txtVl6;
	@FXML
	private TextField txtVlTotal;
	@FXML
	private DatePicker dpDtCompra;
	@FXML
	private ComboBox<FormPag> cbFinalizadora;
	@FXML
	private ComboBox<Vendedora> cbVendedora;
	@FXML
	private ComboBox<Cliente> cbCliente;
	@FXML
	private Button soma;
	@FXML
	private Button salvar;

	private ObservableList<FormPag> listFin;
	private ObservableList<Vendedora> listVed;
	private ObservableList<Cliente> listCli;
	
	public void setVenda (Venda vd) {
		this.nvd = vd;
	}
	public void setServicos (VendaServico veds, VendedoraServico vds, ClienteServico cls) {
		this.veds = veds;
		this.vds = vds;
		this.cls = cls;
	}
	
	@FXML
	public void onButtonSalvar() {
		if(nvd == null) {
			throw new IllegalStateException("Entidade venda não foi injetada");
		}
		if(veds == null) {
			throw new IllegalStateException("Serviço venda não foi injetada");
		}
		if(vds == null) {
			throw new IllegalStateException("Serviço vendedora não foi injetada");
		}
		if(cls == null) {
			throw new IllegalStateException("Serviço cliente não foi injetada");
		}
		
		Venda vend = dadosForm();
		veds.insereVenda(vend);
		Alertas.exibeAlerta("SUCESSO", null, "Nova venda inserida com sucesso", AlertType.CONFIRMATION);
		limpaForm();
	}

	@FXML
	public void onButtonSomar() {
		somaValores();
	}

	public void somaValores() {
		double resultado = 0.0;
		try {
			if (!txtVl1.getText().isEmpty()) {
				resultado += Double.parseDouble(txtVl1.getText());
			}
			if (!txtVl1.getText().isEmpty()) {
				resultado += Double.parseDouble(txtVl2.getText());
			}
			if (!txtVl3.getText().isEmpty()) {
				resultado += Double.parseDouble(txtVl3.getText());
			}
			if (!txtVl4.getText().isEmpty()) {
				resultado += Double.parseDouble(txtVl4.getText());
			}
			if (!txtVl5.getText().isEmpty()) {
				resultado += Double.parseDouble(txtVl5.getText());
			}
			if (!txtVl6.getText().isEmpty()) {
				resultado += Double.parseDouble(txtVl6.getText());
			}

			txtVlTotal.setText(String.valueOf(resultado));
		} catch (NumberFormatException e) {
			e.getMessage();
		}

	}
	
	public Venda dadosForm() {
		Venda vd = new Venda();
		vd.setValor(Util.valorDouble(txtVlTotal.getText()));
		vd.setFinalizadora(cbFinalizadora.getValue());
		if(dpDtCompra.getValue() == null) {
			Alertas.exibeAlerta("Erro !!", null, "Data da venda Vazia", null);
		}else {
			Instant dtcmp = Instant.from(dpDtCompra.getValue().atStartOfDay(ZoneId.systemDefault()));
			vd.setDt_compr(Date.from(dtcmp));
		}
		vd.setVendedora(cbVendedora.getValue());
		vd.setCli(cbCliente.getValue());
		return vd;
	}

	public void configInicial() {
		RegulaCampos.setCampoNumerico(txtVl1);
		RegulaCampos.setCampoNumerico(txtVl2);
		RegulaCampos.setCampoNumerico(txtVl3);
		RegulaCampos.setCampoNumerico(txtVl4);
		RegulaCampos.setCampoNumerico(txtVl5);
		RegulaCampos.setCampoNumerico(txtVl6);
		RegulaCampos.setCampoNumerico(txtVlTotal);
		Util.formatDatePicker(dpDtCompra, "dd/MM/yyyy");
		carregarComboBoxFinalizadora();
		iniciaCBVendedora();
		iniciaCBCliente();
	}

	private void carregarComboBoxFinalizadora() {
		listFin = FXCollections.observableArrayList(Arrays.asList(FormPag.values()));
		cbFinalizadora.setItems(listFin);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configInicial();

	}

	public void cargaComboBoxVendedora() {
		if (vds == null) {
			throw new IllegalStateException("Vendedora não foi Injetada");
		}
		List<Vendedora> list = vds.buscanome();
		listVed = FXCollections.observableArrayList(list);
		cbVendedora.setItems(listVed);
	}

	private void iniciaCBVendedora() {
		Callback<ListView<Vendedora>, ListCell<Vendedora>> factory = lv -> new ListCell<Vendedora>() {
			@Override
			protected void updateItem(Vendedora item, boolean empyt) {
				super.updateItem(item, empyt);
				if (item == null || empyt) {
					setText(null);
				} else {
					setText(item.getNome());
				}
			}
		};
		cbVendedora.setCellFactory(factory);
		cbVendedora.setButtonCell(factory.call(null));
	}

	public void cargaComboBoxCliente() {
		if (cls == null) {
			throw new IllegalStateException("Cleinte não foi injetada");
		}
		List<Cliente> list = cls.buscaTodos(); 
		listCli = FXCollections.observableArrayList(list);
		cbCliente.setItems(listCli);
	}

	public void iniciaCBCliente() {
		Callback<ListView<Cliente>, ListCell<Cliente>> factory = lv -> new ListCell<Cliente>() {
		@Override
		protected void updateItem(Cliente item, boolean empyt) {
			super.updateItem(item, empyt);
			if(item == null || empyt) {
				setText(null);
			} else {
				setText(item.getNome());
			}
		}
		};
		cbCliente.setCellFactory(factory);
		cbCliente.setButtonCell(factory.call(null));
	}
	
	private void limpaForm() {
		txtVl1.setText("");
		txtVl2.setText("");
		txtVl3.setText("");
		txtVl4.setText("");
		txtVl5.setText("");
		txtVl6.setText("");
		txtVlTotal.setText("");
		dpDtCompra.setValue(null);
		cbFinalizadora.getSelectionModel().clearSelection();
		cbCliente.getSelectionModel().clearSelection();
		cbVendedora.getSelectionModel().clearSelection();
	}

}
