package com.robbiedaves.wordgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WordGen {

    public static void main(String[] args) {
        WordGen wordGen = new WordGen();
        wordGen.genWords();
    }

    List<String> words = new ArrayList<String>();

    public WordGen() {
        this.loadWords();
    }

    public void genWords() {
        for (int i =0 ; i < 100; i++) {
            System.out.println(generateWord());
        }
    }

    private String generateWord() {
        String word1 = words.get(generateRandomInt(0, words.size()));
        String word2 = words.get(generateRandomInt(0, words.size()));
        return capitalizeFirstLetter(word1) + capitalizeFirstLetter(word2);
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    private int generateRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    private void loadWords() {
        try {
            File file = this.getFile("file/wordlist.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String word;
            while ((word = bufferedReader.readLine()) != null) {
                words.add(word);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile(String fileName) {
        // Get the file from the resource folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file;
    }

}
