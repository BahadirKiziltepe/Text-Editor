package markov_method;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.FileHandler;

public class MarkovGenerator {

	public static void markovAlert(MarkovSource ms) {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.APPLICATION_MODAL);
		dialogStage.initOwner(new Stage());

		Button setFile = new Button("Choose a Markov File");
		Button setText = new Button("Set the Markov File Null");
		Button setLimits = new Button("Set Limits");
		Label showFile;
		TextField keyWord = new TextField("");
		TextField numberOfWords = new TextField("");
		VBox dialogBox = new VBox();
		HBox setFileBox = new HBox();
		HBox setTextBox = new HBox();
		HBox setLimitsBox = new HBox();

		if (ms.getMarkovFile() == null) {
			showFile = new Label("null");
		} else {
			showFile = new Label(ms.getMarkovFile().getName());
		}
		if (!keyWord.getText().equals("")) {
			; // nop
		} else {
			keyWord.setText(ms.getKeyWord());
		}
		if (ms.getLimit() == 0) {
			; // nop
		} else {
			numberOfWords.setText(Integer.toString(ms.getLimit()));
		}

		setFile.setOnAction(e -> {
			ms.setMarkovFile(FileHandler.fileChooser());
			if(ms.getMarkovFile() != null) {
				showFile.setText(ms.getMarkovFile().getName());
			}
		});
		setText.setOnAction(e -> {
			ms.setMarkovFile(null);
			showFile.setText("null");
		});
		setLimits.setOnAction(e -> {
			try {
				ms.markovMethod();
				ms.setLimit(Integer.parseInt(numberOfWords.getText()));
				ms.setKeyWord(keyWord.getText());
				ms.markovChain(ms.getKeyWord());
			} catch (NullPointerException e1) {
				e1.getStackTrace();
				Alert warning = new Alert(Alert.AlertType.WARNING);
				warning.setHeaderText(null);
				warning.setGraphic(null);
				warning.setTitle("Warning!");
				warning.setContentText("Choose a file or type something before setting up the markov method");
				warning.showAndWait();
			}
		});

		setFileBox.getChildren().addAll(setFile, showFile);
		setTextBox.getChildren().addAll(setText);
		setLimitsBox.getChildren().addAll(keyWord, numberOfWords, setLimits);
		dialogBox.getChildren().addAll(setFileBox, setTextBox, setLimitsBox);
		Scene dialogScene = new Scene(dialogBox, 400, 200);
		dialogStage.setScene(dialogScene);
		dialogStage.show();
	}
	
}
