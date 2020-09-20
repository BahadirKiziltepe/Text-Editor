package markov_method;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import gui_setup.GUI;
import util.FileHandler;

public class MarkovSource {
	private GUI gui;
	Map<String, LinkedList<String>> wordMap;
	private Random random = new Random();
	private File markovFile;
	private String markovString, keyWord, displayedString;
	private int limit;

	public MarkovSource(GUI gui) {
		this.gui = gui;
		wordMap = new HashMap<String, LinkedList<String>>();
		limit = 0;
		displayedString = "";
	}

	public void markovMethod() {
		String wordList;
		
		if (markovFile != null) {
			wordList = FileHandler.readExtensionFile(markovFile);
		} else {
			wordList = gui.getMainTextString();
		}
			
			String[] words = wordList.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				if (!wordMap.containsKey(words[i])) {
					LinkedList<String> list = new LinkedList<String>();
					wordMap.put(words[i], list);
				}
				if (i != words.length - 1 && wordMap.get(words[i]) != null) {
					wordMap.get(words[i]).add(words[i + 1]);
				}
			}
		}

	public void markovChain(String keyWord) {
		displayedString = "";
		int randomWord;
		for (int i = 0; i < limit; i++) {
			randomWord = random.nextInt(wordMap.get(keyWord).size());
			displayedString += keyWord + " ";
			keyWord = wordMap.get(keyWord).get(randomWord);
			if (wordMap.get(keyWord).size() == 0) {
				String[] temp = displayedString.split(" ");
				randomWord = random.nextInt(temp.length);
				keyWord = temp[randomWord];
			}
		}
	}

	public String getDisplayedString() {
		return displayedString;
	}

	public void setDisplayedString(String displayedString) {
		this.displayedString = displayedString;
	}

	public File getMarkovFile() {
		return markovFile;
	}

	public void setMarkovFile(File markovFile) {
		this.markovFile = markovFile;
	}

	public String getMarkovString() {
		return markovString;
	}

	public void setMarkovString(String markovString) {
		this.markovString = markovString;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
