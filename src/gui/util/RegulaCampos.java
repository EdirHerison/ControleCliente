package gui.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class RegulaCampos {

	public static void setCampoInteiro(TextField txtInt) {
		txtInt.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			if (valorNovo != null && !valorNovo.matches("\\d*")) {
				txtInt.setText(valorAntigo);
			}
		});
	}

	public static void setLimitaCaracteres(TextField txtMax, int max) {
		txtMax.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			if (valorNovo != null && valorNovo.length() > max) {
				txtMax.setText(valorAntigo);
			}
		});
	}

	public static void setCampoDouble(TextField txtDouble) {
		txtDouble.textProperty().addListener((old, valorAntigo, valorNovo) -> {
			if (valorNovo != null && valorNovo.matches("\\d*([\\.]\\d*)?")) {
				txtDouble.setText(valorAntigo);
			}
		});
	}

	public static void setCampoNumerico(TextField textField) {
	    textField.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null && !newValue.isEmpty()) {
	            if (!newValue.matches("\\d*(\\.\\d*)?")) {
	                textField.setText(newValue.replaceAll("[^\\d.]", ""));
	            }
	        }
	    });
	}

	public static void setCampoCPF(TextField txtCPF) {
		txtCPF.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String valorAntigo, String valorNovo) {

				String cpf = valorNovo.replaceAll("[^0-9]", "");
				cpf = formataCPF(cpf);
				txtCPF.setText(cpf);
			}
		});
	}

	public static String formataCPF(String cpf) {
		int length = cpf.length();
		if (length > 11) {
			cpf = cpf.substring(0, 11);
		}

		StringBuilder formatandoCPF = new StringBuilder();
		int count = 0;
		for (int i = 0; i < length; i++) {
			if (count == 3 || count == 6) {
				formatandoCPF.append('.');
			} else if (count == 9) {
				formatandoCPF.append('-');
			}
			formatandoCPF.append(cpf.charAt(i));
			count++;
		}
		return formatandoCPF.toString();
	}

}
