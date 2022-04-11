import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class FileAnalysis {

    private List<String> words = new ArrayList<>();
    private Map<String, Integer> wordsMap = new HashMap<>();

    void analyse() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу (либо абсолютный, либо из корня проекта):" +
                "C:\\Users\\User\\IdeaProjects\\javaAdvanced\\src\\main\\resources\\check.txt" +
                "\nsrc/main/java/StringArray.java");
        readFile(scanner.nextLine());
        words.sort(new WordComparator());
        print();
        getStatistics();
        theMostFrequentWord();
        scanner.close();
    }

    private void theMostFrequentWord() throws ClassCastException {
        SortedSet<Integer> set = new TreeSet<>(wordsMap.values());
        System.out.println("\nСлова, встречающиеся максимальное число раз:");
        for (Map.Entry<String, Integer> el : wordsMap.entrySet()) {
            if (el.getValue().equals(set.last())) {
                System.out.println("\t" + el.getKey());
            }
        }
        System.out.println("\nЭти слова встречались в файле " + set.last() + " раз");
    }

    private void getStatistics() {
        System.out.println("\nСтатистика:");
        int k = 1;
        for (int i = 0; i < words.size() - 1; i++) {
            if (words.get(i).equalsIgnoreCase(words.get(i + 1))) {
                k++;
            } else {
                wordsMap.put(words.get(i), k);
                k = 1;
                if (i == words.size() - 2) {
                    wordsMap.put(words.get(i + 1), 1);
                }
            }
        }
        for (Map.Entry<String, Integer> el : wordsMap.entrySet()) {
            System.out.println("\t" + el.getKey() + "\t" + el.getValue());
        }
    }

    private void readFile(String p) throws IOException {
//        File file = Path.of(p).toAbsolutePath().toFile();
        File file = new File(p);

        Set<Character> separators = new HashSet<>();
        separators.add('\n');
        separators.add('\t');
        separators.add('\r');
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add(':');
        separators.add(';');
        separators.add('-');
        separators.add('(');
        separators.add(')');
        separators.add('"');

        FileReader reader = new FileReader(file);
        int c;
        StringBuilder word = new StringBuilder();
        while ((c = reader.read()) != -1) {
            if (!separators.contains((char) c)) {
                word.append((char) c);
            } else if (!word.toString().equals("")) {
                words.add(word.toString());
                word = new StringBuilder();
            }
        }

    }

    private static class WordComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }

    private void print() {
        System.out.println("Слова из файла в алфавитном порядке:");
        for (String w : words) {
            System.out.println("\t" + w);
        }
    }
}