package main;

import java.io.FileInputStream;

import gui_setup.GUI;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.FileHandler;

public class Main extends Application {

	private GUI gui;
	private Image icon;

	@Override
	public void start(Stage stg) throws Exception {

		FileInputStream iconInput = new FileInputStream("icon.png");
		icon = new Image(iconInput);

		gui = new GUI();

		stg.setScene(gui.setProperties());
		stg.getIcons().add(icon);
		stg.setTitle("Text Editor");
		stg.show();
		stg.setOnCloseRequest(e -> {
			FileHandler.saveAlert(gui.getMainTextString());
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
