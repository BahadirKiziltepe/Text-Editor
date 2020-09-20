package gui_setup;

import gui_properties.MenuBarProperties;
import gui_properties.StatusBarProperties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import markov_method.MarkovSource;
import util.FileHandler;
import util.SentenceAnalyzer;

public class GUI {
	private MarkovSource markovSource;
	private BorderPane screen;
	private MenuBarProperties menuBar;
	private StatusBarProperties statusBar;
	private TextArea mainText;

	public GUI() {
		initialize();
		dynamicValues();
	}

	public Scene setProperties() {
		screen = new BorderPane();
		statusBar = new StatusBarProperties(this);
		menuBar = new MenuBarProperties(this, markovSource);
		screen.setTop(menuBar);
		screen.setBottom(statusBar);
		screen.setCenter(mainText);
		Scene scene = new Scene(screen, 800, 640);
		return scene;
	}

	public void initialize() {
		markovSource = new MarkovSource(this);
		mainText = new TextArea("");
		mainText.setWrapText(true);
	}

	public void dynamicValues() {
		mainText.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				SentenceAnalyzer.oneLoopMethod(mainText.getText());
				statusBar.setWordCountText("Word Count: " + SentenceAnalyzer.wordCount);
				statusBar.setSentenceCountText("Sentence Count: " + SentenceAnalyzer.sentenceCount);
				statusBar.setFleschScoreText("Flesch Score:", SentenceAnalyzer.fleschScore(
						SentenceAnalyzer.syllableCount, SentenceAnalyzer.wordCount, SentenceAnalyzer.sentenceCount));
				menuBar.setViewWordCount(statusBar.getWordCount().getText());
				menuBar.setViewSentenceCount(statusBar.getSentenceCount().getText());
				menuBar.setViewFleschScore(statusBar.getFleschScore().getText());

				FileHandler.isEdited = true;

			}
		});

	}

	public TextArea getMainText() {
		return mainText;
	}

	public String getMainTextString() {
		return mainText.getText();
	}

	public void setMainText(String mainText) {
		this.mainText.setText(mainText);
	}

}