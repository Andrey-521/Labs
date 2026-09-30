import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PrimesGeneratorTest {
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 10;

        PrimesGenerator generator = new PrimesGenerator(n);
        List<Integer> primes = new ArrayList<>();

        System.out.print("Прямой порядок: ");
        Iterator<Integer> iterator = generator.iterator();

        while (iterator.hasNext()) {
            Integer prime = iterator.next();
            primes.add(prime);
            System.out.print(prime + " ");
        }

        System.out.println();
        System.out.print("Обратный порядок: ");

        ListIterator<Integer> reverseIterator = primes.listIterator(primes.size());
        while (reverseIterator.hasPrevious()) {
            System.out.print(reverseIterator.previous() + " ");
        }

        System.out.println();
    }
}
