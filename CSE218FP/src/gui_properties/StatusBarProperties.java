package gui_properties;

import dictionary.Dictionary;
import gui_setup.GUI;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.SentenceAnalyzer;

public class StatusBarProperties extends HBox {
	private GUI gui;
	private Dictionary dictionary;
	private Text wordCount, sentenceCount, fleschScore;
	private Button spellCheck;

	public StatusBarProperties(GUI gui) {
		this.gui = gui;
		initialize();

		addChildren(this, wordCount, sentenceCount, fleschScore);
		addChildren(this, spellCheck);
		StatusBarProperties.setMargin(spellCheck, new Insets(0, 0, 0, 50));
	}

	public void addChildren(StatusBarProperties status, Text... texts) {
		int count = 0;
		for (Text text : texts) {
			if (count == texts.length - 1) {
				status.getChildren().add(text);
			} else {
				status.getChildren().addAll(text, new Text(" | "));
			}
			count++;
		}
	}

	public void addChildren(StatusBarProperties status, Button... buttons) {
		for (Button button : buttons) {
			status.getChildren().add(button);
		}
	}

	public void initialize() {
		dictionary = new Dictionary();
		spellCheck = new Button("Spell Check");
		wordCount = new Text("Word Count: 0");
		sentenceCount = new Text("Sentence Count: 0");
		fleschScore = new Text("Flesch Score: 0");

		spellCheck.setOnAction(e -> {
			viewSpellCheck();
		});
	}

	public void viewSpellCheck() {
		Stage spellStage = new Stage();
		spellStage.initModality(Modality.APPLICATION_MODAL);
		spellStage.initOwner(new Stage());

		int count = 0;
		ListView<String> spellList = new ListView<String>();
		String[] checkSpell = gui.getMainTextString().split("([.,!?:;'\\\"-*#\\(\\)\\[\\]/]|\\s)+");
		spellList.getItems().add("");
		for (String word : checkSpell) {
			String temp = word.toLowerCase();
			if (!dictionary.getDictionary().contains(temp) && !SentenceAnalyzer.isNumeric(word)) {
				spellList.getItems().add(word);
				count++;
			}
		}
		spellList.getItems().add(0, "Error Count: " + count);
		spellList.getItems().remove(1);
		
		VBox spellBox = new VBox();
		spellBox.getChildren().add(spellList);
		Scene spellScene = new Scene(spellBox, 350, 400);
		spellStage.setScene(spellScene);
		spellStage.setTitle("Wrong Spelled Words");
		spellStage.show();
	}

	public Text getWordCount() {
		return wordCount;
	}

	public void setWordCount(Text wordCount) {
		this.wordCount = wordCount;
	}

	public void setWordCountText(String name) {
		this.wordCount.setText(name);
	}

	public Text getSentenceCount() {
		return sentenceCount;
	}

	public void setSentenceCount(Text sentenceCount) {
		this.sentenceCount = sentenceCount;
	}

	public void setSentenceCountText(String name) {
		this.sentenceCount.setText(name);
	}

	public Text getFleschScore() {
		return fleschScore;
	}

	public void setFleschScore(Text fleschScore) {
		this.fleschScore = fleschScore;
	}

	public void setFleschScoreText(String name, double value) {
		this.fleschScore.setText(name + " " + String.format("%.2f", value));
	}

}
