import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFrequency {

    public static void main(String[] args) {
        String text = "Java is fun. Java is powerful, and learning Java is useful.";

        Map<String, Integer> frequencies = countWords(text);

        Set<String> distinctWords = frequencies.keySet();

        System.out.println("Различные слова: " + distinctWords);
        System.out.println("Частоты слов: " + frequencies);
    }

    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> frequencies = new HashMap<>();
        Pattern wordPattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = wordPattern.matcher(text.toLowerCase(Locale.ROOT));

        while (matcher.find()) {
            String word = matcher.group();
            frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
        }

        return frequencies;
    }
}
