package aplana.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main{

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();

        try(FileInputStream fin=new FileInputStream("text.txt")) {

            words.add("first stroke");
            String s = "";
            int i = -1;
            boolean flag = false;
            while( (i = fin.read()) != -1 ){
                try {
                    if (!Character.isAlphabetic((char) i) || (char) i == '\r' || (char) i == '\n') {
                        if (s != "") {
                            words.add(s);
                        }
                        s = "";
                        continue;
                    }
                } catch (ClassCastException ex){
                    System.out.println("Wrong chars in the given file");
                    System.exit(0);
                }

                s += (char)i;
            }
            if (s!= "")
                words.add(s);

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Something gone wrong w file");
            System.exit(0);
        }

        System.out.println("Given words:");
        System.out.println(words);
        System.out.println();

        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Sorted by alphabet words:");
        System.out.println(words);
        System.out.println();

        NavigableMap <String, Integer> map_of_words = new TreeMap<>();
        int max = -1;
        for (var word : words){
            if (!map_of_words.containsKey(word))
                map_of_words.put(word, 1);
            else
                map_of_words.put(word, map_of_words.get(word) + 1);
            if (max < map_of_words.get(word)) max = map_of_words.get(word);
        }
        System.out.println("amount of max repeats of words: " + max);

        System.out.print("Max repeated words: ");
        Collection<String> collection= map_of_words.keySet();
        for (String key : collection) {
            int value = map_of_words.get(key);
            if (key != null) {
                if (value == max) {
                    System.out.print('[' + key + "] " +
                            "");
                }
            }
        }

        //Sorry for bad en
    }
}