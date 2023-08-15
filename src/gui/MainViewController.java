package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alertas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.entidades.Cliente;
import model.entidades.Vendedora;
import model.servico.ClienteServico;
import model.servico.VendedoraServico;

public class MainViewController implements Initializable{

	//sugestão GPT
	private VendedoraServico vds;
	//
	
	@FXML
	private MenuItem menuItemCadVendedor;
	@FXML
	private MenuItem menuItemCadCliente;
	@FXML
	private MenuItem menuControleVendedora;
	
	
	@FXML
	public void onMenuCadVendedoraAction() {
		exibeTela("/gui/CadVendedoraView.fxml", (CadVendedoraViewController cadVendedora) -> {
			cadVendedora.setVendedoraEntidade(new Vendedora());
			cadVendedora.setVendedoraServico(new VendedoraServico());
		});
	}

	@FXML
	public void onMenuCadClienteAction() {
		exibeTela("/gui/CadClienteView.fxml", (CadClienteViewController cadCliente) ->{
			cadCliente.setCliente(new Cliente());
			cadCliente.setServicos(new ClienteServico(), new VendedoraServico());
			cadCliente.cargaComboBoxVendedora();
		});
	}
	
	@FXML
	public void onMenuControleVendedoraAction() {
		exibeTela("/gui/ControleVendedoraView.fxml", (ControleVendedoraViewController consultVendedora) ->{
			consultVendedora.setVendedoraService(new VendedoraServico()); 
	        consultVendedora.setVendedoraEntidade(new Vendedora());
		});
	}
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rs) {
			
	}
	
	private synchronized <T> void exibeTela(String caminhoTela, Consumer<T> acaoDeInicializacao) {
		try {
			FXMLLoader carregaTela = new FXMLLoader(getClass().getResource(caminhoTela));
			VBox novaVBox = carregaTela.load();
			Scene cenaPrincipal = Main.getCenaPrincipal();
			VBox vbPrincipal = (VBox) ((ScrollPane) cenaPrincipal.getRoot()).getContent();
			Node menuPrincipal = vbPrincipal.getChildren().get(0);
			vbPrincipal.getChildren().clear();
			vbPrincipal.getChildren().add(menuPrincipal);
			vbPrincipal.getChildren().addAll(novaVBox.getChildren());
			T controller = carregaTela.getController();
			acaoDeInicializacao.accept(controller);
		} catch (Exception e) {
			Alertas.exibeAlerta("IO Exception", "Erro ao carregar tela", e.getMessage(), null);
		}
	}

}
