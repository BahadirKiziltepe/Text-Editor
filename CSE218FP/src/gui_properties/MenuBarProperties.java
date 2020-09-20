package gui_properties;

import graph.Graph;
import gui_setup.GUI;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import markov_method.MarkovSource;

public class MenuBarProperties extends MenuBar{
	private GUI gui;
	private MarkovSource ms;
	private Graph graph;

	private MenuProperties optionFile, optionView, optionEdit;
	private MenuItemProperties fileNew, fileOpen, fileClose, fileSave, fileExit;
	private MenuItemProperties viewWordCount, viewSentenceCount, viewFleschScore, viewGraph;
	private MenuItemProperties editCopy, editCut, editDelete, editPaste, editMarkov, editMarkovSetting;

	public MenuBarProperties(GUI gui, MarkovSource ms) {
		this.gui = gui;
		this.ms = ms;
		initialize();
		menuItemFunctions();

		optionFile.addMenuItems(optionFile, fileNew, fileOpen, fileClose, fileSave, fileExit);
		optionView.addMenuItems(optionView, viewWordCount, viewSentenceCount, viewFleschScore, viewGraph);
		optionEdit.addMenuItems(optionEdit, editCopy, editCut, editDelete, editPaste, editMarkov, editMarkovSetting);
		addMenus(this, optionFile, optionView, optionEdit);
	}

	public void addMenus(MenuBarProperties menuBar, MenuProperties... menus) {
		for (MenuProperties menu : menus) {
			menuBar.getMenus().add(menu);
		}
	}

	public void menuItemFunctions() {

		fileNew.setOnAction(e -> {
			fileNew.newFile(gui);
		});

		fileOpen.setOnAction(e -> {
			fileOpen.openFile(gui);
		});

		fileClose.setOnAction(e -> {
			fileClose.closeFile(gui);
		});

		fileSave.setOnAction(e -> {
			fileSave.saveFile(gui);
		});

		fileExit.setOnAction(e -> {
			fileClose.exitFile(gui);
		});
		
		viewGraph.setOnAction(e -> {
			viewGraph.graphing(gui, graph);
		});
		
		editCopy.setOnAction(e -> {
			editCopy.copy(gui);
		});
		
		editCut.setOnAction(e -> {
			editCut.cut(gui);
		});
		
		editDelete.setOnAction(e -> {
			editCut.delete(gui);
		});
		
		editPaste.setOnAction(e -> {
			editPaste.paste(gui);
		});

		editMarkov.setOnAction(e -> {
			editMarkov.insertMarkov(gui, ms);
		});

		editMarkovSetting.setOnAction(e -> {
			editMarkovSetting.markovSettings(ms);
		});

	}

	public void initialize() {
		graph = new Graph();
		
		optionFile = new MenuProperties("");
		optionFile.setText("_File");
		optionView = new MenuProperties("_View");
		optionEdit = new MenuProperties("_Edit");

		fileNew = new MenuItemProperties("New");
		fileNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		fileOpen = new MenuItemProperties("Open");
		fileOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
		fileClose = new MenuItemProperties("Close");
		fileClose.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
		fileSave = new MenuItemProperties("Save");
		fileSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		fileExit = new MenuItemProperties("Exit");
		fileExit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));

		viewWordCount = new MenuItemProperties("Word Count: 0");
		viewSentenceCount = new MenuItemProperties("Sentence Count: 0");
		viewFleschScore = new MenuItemProperties("Flesch Score: 0");
		viewGraph = new MenuItemProperties("View Graph:\nOne Loop vs Three Loops");
		viewGraph.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

		editCopy = new MenuItemProperties("copy");
		editCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
		editCut = new MenuItemProperties("cut");
		editCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
		editDelete = new MenuItemProperties("delete");
		editDelete.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
		editPaste = new MenuItemProperties("paste");
		editPaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
		editMarkov = new MenuItemProperties("markov");
		editMarkov.setAccelerator(new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN));
		editMarkovSetting = new MenuItemProperties("markov settings");
		editMarkovSetting.setAccelerator(new KeyCodeCombination(KeyCode.K, KeyCombination.CONTROL_DOWN));
	}

	public MenuItemProperties getViewWordCount() {
		return viewWordCount;
	}

	public void setViewWordCount(String viewWordCount) {
		this.viewWordCount.setText(viewWordCount);
	}

	public MenuItemProperties getViewSentenceCount() {
		return viewSentenceCount;
	}

	public void setViewSentenceCount(String viewSentenceCount) {
		this.viewSentenceCount.setText(viewSentenceCount);
	}

	public MenuItemProperties getViewFleschScore() {
		return viewFleschScore;
	}

	public void setViewFleschScore(String viewFleschScore) {
		this.viewFleschScore.setText(viewFleschScore);
	}

}
