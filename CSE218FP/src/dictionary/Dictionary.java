package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Dictionary {
	private final File DICTIONARY = new File("inputData\\dictionary.txt");
	private HashSet<String> dictionary;

	public Dictionary() {
		dictionary = new HashSet<String>();
		setUpDictionary();
	}

	private void setUpDictionary() {
		try {
			FileReader reader = new FileReader(DICTIONARY);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				dictionary.add(line.toLowerCase());
			}
			br.close();
		} catch (IOException e) {
			if (DICTIONARY != null) {
				e.printStackTrace();
			}
		}
	}

	public HashSet<String> getDictionary() {
		return dictionary;
	}

}
