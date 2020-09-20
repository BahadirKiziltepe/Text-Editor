package gui_properties;

import graph.Graph;
import gui_setup.GUI;
import javafx.scene.control.MenuItem;
import markov_method.MarkovGenerator;
import markov_method.MarkovSource;
import util.FileHandler;
import util.SentenceAnalyzer;

public class MenuItemProperties extends MenuItem {

	public MenuItemProperties(String name) {
		this.setText(name);
	}

	public void newFile(GUI gui) {
		FileHandler.saveAlert(gui.getMainText().getText());
		gui.getMainText().clear();
		FileHandler.isSavedToFile = false;
		FileHandler.isEdited = false;
	}

	public void openFile(GUI gui) {
		FileHandler.saveAlert(gui.getMainText().getText());
		try {
			FileHandler.mainFile = FileHandler.fileChooser();
		} catch (NullPointerException e) {
			; // nop
		}
		if (FileHandler.mainFile != null) {
			gui.setMainText(FileHandler.readChosenFile());
			FileHandler.isSavedToFile = true;
			FileHandler.isEdited = false;
		}
	}

	public void closeFile(GUI gui) {
		FileHandler.saveAlert(gui.getMainText().getText());
		gui.getMainText().clear();
		FileHandler.isSavedToFile = false;
		FileHandler.isEdited = false;
	}

	public void saveFile(GUI gui) {
		if (!FileHandler.isSavedToFile) {
			FileHandler.saveWithFileChooser(gui.getMainTextString());
		} else {
			FileHandler.saveToExistingFile(gui.getMainTextString());
		}
		if (FileHandler.mainFile == null) {
			; // nop
		} else {
			FileHandler.isSavedToFile = true;
			FileHandler.isEdited = false;
		}
	}
	
	public void graphing(GUI gui,Graph graph) {
		for (int i = 0; i <= 10; i ++) {
			long timer = System.currentTimeMillis();
			SentenceAnalyzer.oneLoopPercentageMethod(gui.getMainTextString(), (i*10));
			graph.addOneLoopData((i*10), (System.currentTimeMillis() - timer));
		}
		for (int i = 0; i <= 10; i ++) {
			long timer = System.currentTimeMillis();
			SentenceAnalyzer.wordPercentageCount(gui.getMainTextString(), (i*10));
			SentenceAnalyzer.sentencePercentageCount(gui.getMainTextString(), (i*10));
			SentenceAnalyzer.syllablePercentageCount(gui.getMainTextString(), (i*10));
			graph.addThreeLoopsData((i*10), (System.currentTimeMillis() - timer));
		}
		graph.saveData();
		graph.showGraph();
	}

	public void exitFile(GUI gui) {
		FileHandler.saveAlert(gui.getMainText().getText());
		System.exit(0);
	}

	public void copy(GUI gui) {
		gui.getMainText().copy();
	}

	public void cut(GUI gui) {
		gui.getMainText().cut();
	}

	public void delete(GUI gui) {
		gui.getMainText().replaceSelection("");
	}

	public void paste(GUI gui) {
		gui.getMainText().paste();
	}

	public void insertMarkov(GUI sp, MarkovSource ms) {
		sp.getMainText().appendText(ms.getDisplayedString());
	}

	public void markovSettings(MarkovSource ms) {
		MarkovGenerator.markovAlert(ms);
	}

	public void viewGraph(GUI gui) {

	}

}
