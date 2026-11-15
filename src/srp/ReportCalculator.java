package srp;

import java.time.LocalDateTime;
import java.util.List;

public class ReportCalculator {
    public Report calculate(List<Integer> data) {
        List<Integer> safeData = List.copyOf(data);
        int sum = safeData.stream().mapToInt(Integer::intValue).sum();
        double average = safeData.isEmpty() ? 0.0 : (double) sum / safeData.size();

        return new Report(sum, average, LocalDateTime.now());
    }
}
