package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHandler {
	public static File mainFile;
	public static boolean isSavedToFile = false;
	public static boolean isEdited = false;

	public static void saveWithFileChooser(String text) {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(new Stage());
		mainFile = file;

		if (file != null) {
			saveToNewFile(text, mainFile);
		}
	}

	public static void saveToNewFile(String text, File file) {
		try {
			FileWriter writerF = new FileWriter(file);
			BufferedWriter writerB = new BufferedWriter(writerF);
			writerB.write(text);
			writerB.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void saveToExistingFile(String mainText) {
		try {
			FileWriter writerF = new FileWriter(mainFile);
			BufferedWriter writerB = new BufferedWriter(writerF);
			writerB.write(mainText);
			writerB.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void saveAlert(String mainText) {
		if (isEdited) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setGraphic(null);
			alert.setTitle("Warning!");
			alert.setContentText("Do you want to save?");
			ButtonType yes = new ButtonType("Yes", ButtonData.YES);
			ButtonType no = new ButtonType("No", ButtonData.NO);
			alert.getButtonTypes().setAll(yes, no);
			alert.showAndWait().ifPresent(e -> {
				if (e == yes) {
					if (isSavedToFile) {
						saveToExistingFile(mainText);
					} else {
						saveWithFileChooser(mainText);
					}
				} else if (e == no) {
					; //nop
				}
			});
		}
	}

	public static File fileChooser() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(new Stage());
		return file;
	}

	public static String readChosenFile() {
		StringBuilder builder = new StringBuilder();
		String finalMain;

		try {
			FileReader reader = new FileReader(mainFile);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			if (mainFile != null) {
				e.printStackTrace();
			}
		}
		finalMain = builder.toString();
		return finalMain;
	}

	public static String readExtensionFile(File file) {
		StringBuilder builder = new StringBuilder();
		String finalMain;

		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			if (file != null) {
				e.printStackTrace();
			}
		}
		finalMain = builder.toString();
		return finalMain;
	}

}
