import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HumanCollectionsTest {

    public static void main(String[] args) {
        List<Human> humans = Arrays.asList(
                new Human("Anna", "Smith", 25),
                new Human("John", "Brown", 30),
                new Human("Kate", "Smith", 28),
                new Human("Mike", "Davis", 25),
                new Human("Anna", "Smith", 25)
        );

        Set<Human> hashSet = new HashSet<>(humans);
        System.out.println("HashSet: " + hashSet);

        Set<Human> linkedHashSet = new LinkedHashSet<>(humans);
        System.out.println("LinkedHashSet: " + linkedHashSet);

        Set<Human> treeSet = new TreeSet<>(humans);
        System.out.println("TreeSet по естественному порядку: " + treeSet);

        Set<Human> byLastName = new TreeSet<>(new HumanComparatorByLastName());
        byLastName.addAll(humans);
        System.out.println("TreeSet по фамилии: " + byLastName);

        Set<Human> byAge = new TreeSet<>(
                (first, second) -> Integer.compare(first.getAge(), second.getAge())
        );
        byAge.addAll(humans);
        System.out.println("TreeSet по возрасту: " + byAge);
    }
}
