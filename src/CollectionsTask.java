import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CollectionsTask {
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 20;
        if (n < 0) {
            throw new IllegalArgumentException("N не может быть отрицательным");
        }

        Random random = new Random();
        Integer[] numbers = new Integer[n];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(101);
        }
        System.out.println("1. Массив: " + Arrays.toString(numbers));

        List<Integer> list = new ArrayList<>(Arrays.asList(numbers));
        System.out.println("2. Список: " + list);

        Collections.sort(list);
        System.out.println("3. По возрастанию: " + list);

        Collections.reverse(list);
        System.out.println("4. В обратном порядке: " + list);

        Collections.shuffle(list);
        System.out.println("5. Перемешанный список: " + list);

        Collections.rotate(list, 1);
        System.out.println("6. Циклический сдвиг на 1: " + list);

        // Сохраняем список до фильтрации: он понадобится для поиска повторов.
        List<Integer> beforeFiltering = new ArrayList<>(list);

        // Оставляем по одному экземпляру каждого числа,
        // сохраняя порядок его первого появления.
        list = new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println("7. Только уникальные значения: " + list);

        // Возвращаемся к исходному набору элементов и оставляем все
        // вхождения тех чисел, которые встретились больше одного раза.
        Map<Integer, Integer> frequencies = countOccurrences(beforeFiltering);
        list = new ArrayList<>(beforeFiltering);
        list.removeIf(number -> frequencies.get(number) == 1);
        System.out.println("8. Все вхождения дублирующихся чисел: " + list);

        Integer[] resultArray = list.toArray(new Integer[0]);
        System.out.println("9. Массив из списка: " + Arrays.toString(resultArray));

        // Частоты считаются для всего первоначально сгенерированного массива.
        System.out.println("10. Частота чисел в исходном массиве: "
                + countOccurrences(Arrays.asList(numbers)));
    }

    private static Map<Integer, Integer> countOccurrences(List<Integer> numbers) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (Integer number : numbers) {
            counts.put(number, counts.getOrDefault(number, 0) + 1);
        }

        return counts;
    }
}
