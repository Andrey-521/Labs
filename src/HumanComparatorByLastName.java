import java.util.Comparator;

public class HumanComparatorByLastName implements Comparator<Human> {

    @Override
    public int compare(Human first, Human second) {
        return first.getLastName().compareTo(second.getLastName());
    }
}
