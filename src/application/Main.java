package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class Main extends Application {

	private static Scene cenaPrincipal;
	
	@Override
	public void start(Stage inicialEstagio) {
		try {
			FXMLLoader carregaTel = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane painelpricipal = carregaTel.load();
			painelpricipal.setFitToHeight(true);
			painelpricipal.setFitToWidth(true);
			cenaPrincipal = new Scene(painelpricipal);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			inicialEstagio.setScene(cenaPrincipal);
			inicialEstagio.setTitle("Controle de Clientes");
			inicialEstagio.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getCenaPrincipal() {
		return cenaPrincipal;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
