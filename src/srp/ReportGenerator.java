package srp;

public class ReportGenerator {
    public String generate(Report report) {
        return """
                === ОТЧЁТ ===
                Сумма: %d
                Среднее значение: %.2f
                Дата создания: %s
                """.formatted(
                report.sum(),
                report.average(),
                report.generatedAt()
        );
    }
}