package aplana.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main{

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        //region Задача со звёздочкой
        // (похоже, что я не так понял суть задания(, просто программа работает как с абсолютным так и с относительным)
        System.out.print("Input ur filepath: ");
        String given_path = input.nextLine();
        Path path = Paths.get(given_path);
        //endregion


        System.out.println();
        //region Считывание файла по введённому пути
        try (FileInputStream fin = new FileInputStream(path.normalize().toString())){
            String s = "";
            int i = -1;
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
        //endregion

        System.out.println("Given words:");
        System.out.println(words);
        System.out.println();

        //region вывод отсортированного списка слов
        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Sorted by alphabet words:");
        System.out.println(words);
        System.out.println();
        //endregion


        //region самые повторяющиеся слова
        NavigableMap <String, Integer> map_of_words = new TreeMap<>();
        int max = -1;
        for (var word : words){
            String lower_word = word.toLowerCase();
            if (!map_of_words.containsKey(lower_word))
                map_of_words.put(lower_word, 1);
            else
                map_of_words.put(lower_word, map_of_words.get(lower_word) + 1);
            if (max < map_of_words.get(lower_word)) max = map_of_words.get(lower_word);
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
        //endregion

        //Sorry for bad en
    }
}