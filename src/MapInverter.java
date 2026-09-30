import java.util.LinkedHashMap;
import java.util.Map;

public class MapInverter {

    public static <K, V> Map<V, K> invert(Map<K, V> source) {
        Map<V, K> result = new LinkedHashMap<>();

        for (Map.Entry<K, V> entry : source.entrySet()) {
            V value = entry.getValue();

            if (result.containsKey(value)) {
                throw new IllegalArgumentException(
                        "Нельзя обменять ключи и значения: значение встречается несколько раз: "
                                + value
                );
            }

            result.put(value, entry.getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        Map<String, Integer> original = new LinkedHashMap<>();
        original.put("one", 1);
        original.put("two", 2);
        original.put("three", 3);

        Map<Integer, String> inverted = invert(original);

        System.out.println("Исходная Map: " + original);
        System.out.println("Map после обмена: " + inverted);
    }
}

