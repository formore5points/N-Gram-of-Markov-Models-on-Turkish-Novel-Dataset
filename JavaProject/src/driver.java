import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class driver {

	public static void top_100(HashMap<String, Integer> hm, List<String> Unigrams, HashMap<String, Integer> UniHash,
			FileWriter fw) throws IOException {

		FileWriter myWriter = fw;
		for (int i = 0; i < 100; i++) {
			Pair p = findMax(hm, Unigrams, UniHash);
			String word = p.getWord();
			String line = String.format("%d %s -> %.10f\n", i + 1, word, p.getProbability() * 100);
			myWriter.write(line);
			hm.put(word, 0);
		}
	}

	public static Pair findMax(HashMap<String, Integer> hm, List<String> Unigrams, HashMap<String, Integer> UniHash) {

		float max = 0;
		String max_key = null;

		for (Entry<String, Integer> entry : hm.entrySet()) {

			float value = probabilityMM(entry.getKey(), Unigrams, UniHash);

			if (entry.getValue() != 0 && value > max) {
				max = value;
				max_key = entry.getKey();
			}

		}
		return new Pair(max_key, max);
	}

	public static float probabilityMM(String Word, List<String> Unigrams, HashMap<String, Integer> UniHash) {

		String[] Tokens = Word.split("\\s+");
		float probability = 0;

		int size = Unigrams.size();
		if (Tokens.length == 1) {
			probability = UniHash.get(Tokens[0]) / (float) size;
		} else if (Tokens.length == 2) {
			probability = (UniHash.get(Tokens[0]) / (float) size) * (UniHash.get(Tokens[1]) / (float) size);
		} else if (Tokens.length == 3) {
			probability = (UniHash.get(Tokens[0]) / (float) size) * (UniHash.get(Tokens[1]) / (float) size)
					* (UniHash.get(Tokens[2]) / (float) size);
		}

		return probability;
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		FileWriter myWriter = new FileWriter("N-Grams.txt");

		Scanner file_reader = null;

		List<String> Unigrams = new ArrayList<String>();

		HashMap<String, Integer> UnigramHashmap = new HashMap<String, Integer>();
		HashMap<String, Integer> BigramHashmap = new HashMap<String, Integer>();
		HashMap<String, Integer> TrigramHashmap = new HashMap<String, Integer>();

		String Token;
		String file_name;
		File file;

		try {
			for (int i = 1; i <= 5; i++) {
				file_name = Integer.toString(i) + ".txt";
				file = new File(file_name);
				file_reader = new Scanner(file, "UTF-8");
			}
			JOptionPane.showMessageDialog(null, "Please click to start.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Be sure that the .exe and the .txt files are the same directory and .txt files are numerated like 1.txt,2.txt...,5.txt.");
			System.exit(0);
		}
		long totalTime = System.currentTimeMillis();
		long startTime = System.currentTimeMillis();
		for (int i = 1; i <= 5; i++) {

			file_name = Integer.toString(i) + ".txt";
			file = new File(file_name);
			file_reader = new Scanner(file, "UTF-8");
			while (file_reader.hasNextLine()) {
				String data = file_reader.nextLine();
				if (data.length() != 0) {
					String[] splitted = data.trim().split("[\\p{Punct}\\s]+");

					for (int j = 0; j < splitted.length; j++) {

						Token = splitted[j].trim();

						if (!Token.equals("")) {
							Unigrams.add(Token);
							if (!UnigramHashmap.containsKey(Token)) {
								UnigramHashmap.put(Token, 1);
							} else {
								UnigramHashmap.put(Token, UnigramHashmap.get(Token) + 1);
							}
						}
					}
				}
			}
			file_reader.close();
		}

		long estimatedTime = System.currentTimeMillis() - startTime;
		myWriter.write("File read and Unigram generation time is : " + estimatedTime + " ms.\n");
		startTime = System.currentTimeMillis();
		file_reader.close();

		for (int i = 0; i < Unigrams.size(); i++) {

			if (i + 1 < Unigrams.size()) {
				String Bigram = Unigrams.get(i) + " " + Unigrams.get(i + 1);
				if (!BigramHashmap.containsKey(Bigram)) {
					BigramHashmap.put(Bigram, 1);
				} else {
					BigramHashmap.put(Bigram, BigramHashmap.get(Bigram) + 1);
				}
			}
			if (i + 2 < Unigrams.size()) {
				String Trigram = Unigrams.get(i) + " " + Unigrams.get(i + 1) + " " + Unigrams.get(i + 2);
				if (!TrigramHashmap.containsKey(Trigram)) {
					TrigramHashmap.put(Trigram, 1);
				} else {
					TrigramHashmap.put(Trigram, TrigramHashmap.get(Trigram) + 1);
				}
			}

		}

		estimatedTime = System.currentTimeMillis() - startTime;
		myWriter.write("Bigram and Trigram generation time is : " + estimatedTime + " ms.\n\n");

		myWriter.write("# Word   %\n\n");

		myWriter.write(" -Unigrams-\n\n");
		startTime = System.currentTimeMillis();
		top_100(UnigramHashmap, Unigrams, UnigramHashmap, myWriter);
		estimatedTime = System.currentTimeMillis() - startTime;
		myWriter.write("--> Top 100 Unigram ordering time is : " + estimatedTime + " ms.\n\n");

		myWriter.write(" -Bigrams-\n\n");
		startTime = System.currentTimeMillis();
		top_100(BigramHashmap, Unigrams, UnigramHashmap, myWriter);
		estimatedTime = System.currentTimeMillis() - startTime;
		myWriter.write("--> Top 100 Bigram ordering time is : " + estimatedTime + " ms.\n\n");

		myWriter.write(" -Trigrams-\n\n");
		startTime = System.currentTimeMillis();
		top_100(TrigramHashmap, Unigrams, UnigramHashmap, myWriter);
		estimatedTime = System.currentTimeMillis() - startTime;
		myWriter.write("--> Top 100 Trigram ordering time is : " + estimatedTime + " ms.\n\n");

		long total_Time = System.currentTimeMillis() - totalTime;

		myWriter.write("Word Count : " + Unigrams.size() + "\n");
		myWriter.write("Unigram Count : " + UnigramHashmap.size() + "\n");
		myWriter.write("Bigram Count : " + BigramHashmap.size() + "\n");
		myWriter.write("Trigram Count : " + TrigramHashmap.size() + "\n\n");
		myWriter.write("Total time is : " + total_Time + " ms.");

		myWriter.close();

		JOptionPane.showMessageDialog(null, "N-Grams.txt file is generated in current working directory.");

	}
}
