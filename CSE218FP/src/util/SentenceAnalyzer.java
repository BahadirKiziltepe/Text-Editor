package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceAnalyzer {
	public static int syllableCount = 0;
	public static int wordCount = 0;
	public static int sentenceCount = 0;

	public static void oneLoopMethod(String mainText) {
		int syllableCount1 = 0;
		int wordCount1 = 0;
		int sentenceCount1 = 0;

		if (mainText == null || mainText.isEmpty()) {
			syllableCount1 = 0;
			wordCount1 = 0;
			sentenceCount1 = 0;
		}

		int syllablesInAWord = 0;

		for (int i = 0; i < mainText.length(); i++) {
			char current = Character.toLowerCase(mainText.charAt(i));
			char previous = 0;
			if (i != 0) {
				previous = Character.toLowerCase(mainText.charAt(i - 1));
			}
			if (current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u'
					|| current == 'y') {
				if (i != 0 && previous == 'a' || previous == 'e' || previous == 'i' || previous == 'o'
						|| previous == 'u' || previous == 'y') {
					; // nop
				} else if (syllablesInAWord > 1 && previous == 'e') {
					; // nop
				} else if (previous == 'e' && current == 'd') {
					; // nop
				} else {
					syllableCount1++;
					syllablesInAWord++;
				}
			} else if (current == ' ' || current == '\t' || current == '\n' || current == '\0') {
				if ((current != previous) && (previous != 10) && (previous != 32) && (previous != 0)
						&& (previous != 9)) {
					wordCount1++;
					syllablesInAWord = 0;
				}
			} else if (i != 0 && current == '.' || previous == '?' || current == '!') {
				if (previous == '.' || previous == '?' || previous == '!') {
					; // nop
				} else if (previous == '\n' || previous == '\0') {
					sentenceCount1++;
				} else {
					sentenceCount1++;
				}
			}

		}

		if (mainText.length() != 0) {
			char lastChar = mainText.charAt(mainText.length() - 1);
			if (lastChar == ' ' || lastChar == '\t' || lastChar == '\n' || lastChar == '\0') {
				; // nop
			} else {
				wordCount1++;
			}
		}

		syllableCount = syllableCount1;
		wordCount = wordCount1;
		sentenceCount = sentenceCount1;

	}

	public static void oneLoopPercentageMethod(String mainText, int percentage) {
		int syllableCount1 = 0;
		int wordCount1 = 0;
		int sentenceCount1 = 0;

		if (mainText == null || mainText.isEmpty()) {
			syllableCount1 = 0;
			wordCount1 = 0;
			sentenceCount1 = 0;
		}

		int end = mainText.length() * (percentage / 100);
		mainText = mainText.substring(0, end);

		int syllablesInAWord = 0;

		for (int i = 0; i < mainText.length(); i++) {
			char current = Character.toLowerCase(mainText.charAt(i));
			char previous = 0;
			if (i != 0) {
				previous = Character.toLowerCase(mainText.charAt(i - 1));
			}
			if (current == 'a' || current == 'e' || current == 'i' || current == 'o' || current == 'u'
					|| current == 'y') {
				if (i != 0 && previous == 'a' || previous == 'e' || previous == 'i' || previous == 'o'
						|| previous == 'u' || previous == 'y') {
					; // nop
				} else if (syllablesInAWord > 1 && previous == 'e') {
					; // nop
				} else if (previous == 'e' && current == 'd') {
					; // nop
				} else {
					syllableCount1++;
					syllablesInAWord++;
				}
			} else if (current == ' ' || current == '\t' || current == '\n' || current == '\0') {
				if ((current != previous) && (previous != 10) && (previous != 32) && (previous != 0)
						&& (previous != 9)) {
					wordCount1++;
					syllablesInAWord = 0;
				}
			} else if (i != 0 && current == '.' || previous == '?' || current == '!') {
				if (previous == '.' || previous == '?' || previous == '!') {
					; // nop
				} else {
					sentenceCount1++;
				}
			}

		}

		if (mainText.length() != 0) {
			char lastChar = mainText.charAt(mainText.length() - 1);
			if (lastChar == ' ' || lastChar == '\t' || lastChar == '\n' || lastChar == '\0') {
				; // nop
			} else {
				wordCount1++;
			}
		}

		syllableCount = syllableCount1;
		wordCount = wordCount1;
		sentenceCount = sentenceCount1;

	}

	public static double fleschScore(int syllableCount, int wordCount, int sentenceCount) {
		double fleschScore;
		if (syllableCount == 0 || wordCount == 0 || sentenceCount == 0) {
			fleschScore = 0;
		} else {
			double a = 1.015 * wordCount / sentenceCount;
			double b = 84.6 * syllableCount / wordCount;
			fleschScore = 206.835 - a - b;
		}
		return fleschScore;
	}

	public static int syllableCount(String mainText) {
		if (mainText == null || mainText.isEmpty()) {
			return 0;
		}

		String situation1 = "(?i)[aeiouy][aeiouy]*|e[aeiouy]*(?!d\\b)";
		String situation2 = "(?i)[aeiouy]([pdclkmntsrvg]e\\b)";
		Matcher matcher1 = Pattern.compile(situation1).matcher(mainText);
		Matcher matcher2 = Pattern.compile(situation2).matcher(mainText);
		int syllableCounter = 0;

		while (matcher1.find()) {
			syllableCounter++;
		}

		while (matcher2.find()) {
			syllableCounter--;
		}

		return syllableCounter;
	}

	public static int syllablePercentageCount(String mainText, int percentage) {
		if (mainText == null || mainText.isEmpty()) {
			return 0;
		}
		int end = mainText.length() * (percentage / 100);
		mainText = mainText.substring(0, end);

		String situation1 = "(?i)[aeiouy][aeiouy]*|e[aeiouy]*(?!d\\b)";
		String situation2 = "(?i)[aeiouy]([pdclkmntsrvg]e\\b)";
		Matcher matcher1 = Pattern.compile(situation1).matcher(mainText);
		Matcher matcher2 = Pattern.compile(situation2).matcher(mainText);
		int syllableCounter = 0;

		while (matcher1.find()) {
			syllableCounter++;
		}

		while (matcher2.find()) {
			syllableCounter--;
		}

		return syllableCounter;
	}

	public static int wordCount(String mainText) {
		if (mainText == null || mainText.isEmpty()) {
			return 0;
		}

		String[] words = mainText.split("\\s+");
		return words.length;
	}

	public static int wordPercentageCount(String mainText, int percentage) {
		if (mainText == null || mainText.isEmpty()) {
			return 0;
		}

		int end = mainText.length() * (percentage / 100);
		mainText = mainText.substring(0, end);

		String[] words = mainText.split("\\s+");
		return words.length;
	}

	public static int sentenceCount(String mainText) {
		if (mainText == null || mainText.isEmpty()) {
			return 0;
		}

		String[] count = mainText.split("[.?!]+");
		return count.length;
	}

	public static int sentencePercentageCount(String mainText, int percentage) {
		if (mainText == null || mainText.isEmpty()) {
			return 0;
		}

		int end = mainText.length() * (percentage / 100);
		mainText = mainText.substring(0, end);

		String[] count = mainText.split("[.?!]+");
		return count.length;
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
