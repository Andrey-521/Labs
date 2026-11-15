import dip.EmailSender;
import dip.NotificationService;
import dip.SmsSender;
import isp.MultiFunctionMachine;
import isp.OldPrinter;
import isp.Printable;
import lsp.Bird;
import lsp.FlyingBird;
import lsp.Penguin;
import lsp.Sparrow;
import ocp.DiscountCalculator;
import ocp.RegularDiscount;
import ocp.StudentDiscount;
import ocp.SuperVipDiscount;
import ocp.VipDiscount;
import srp.Report;
import srp.ReportCalculator;
import srp.ReportGenerator;
import srp.ReportSaver;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        demonstrateSrp();
        demonstrateOcp();
        demonstrateLsp();
        demonstrateIsp();
        demonstrateDip();
    }

    private static void demonstrateSrp() {
        System.out.println("\n========== SRP ==========");
        List<Integer> values = List.of(5, 10, 15, 20);

        ReportCalculator calculator = new ReportCalculator();
        Report report = calculator.calculate(values);

        ReportGenerator generator = new ReportGenerator();
        String reportText = generator.generate(report);
        System.out.println(reportText);

        ReportSaver saver = new ReportSaver();
        try {
            saver.save(reportText, Path.of("report.txt"));
            System.out.println("Отчёт сохранён в report.txt");
        } catch (IOException e) {
            System.err.println("Не удалось сохранить отчёт: " + e.getMessage());
        }
    }

    private static void demonstrateOcp() {
        System.out.println("\n========== OCP ==========");
        DiscountCalculator calculator = new DiscountCalculator();

        System.out.printf("Обычная скидка: %.2f%n",
                calculator.calculate(new RegularDiscount(), 1000));
        System.out.printf("Обычная скидка: %.2f%n",
                calculator.calculate(new VipDiscount(), 1000));
        System.out.printf("Super VIP скидка: %.2f%n",
                calculator.calculate(new SuperVipDiscount(), 1000));
        System.out.printf("Студенческая скидка: %.2f%n",
                calculator.calculate(new StudentDiscount(), 1000));
    }

    private static void demonstrateLsp() {
        System.out.println("\n========== LSP ==========");
        List<Bird> birds = List.of(new Sparrow(), new Penguin());
        for (Bird bird : birds) {
            bird.eat();
        }

        List<FlyingBird> flyingBirds = List.of(new Sparrow());
        for (FlyingBird bird : flyingBirds) {
            bird.fly();
        }
        System.out.println("Пингвин корректно используется как Bird, но не как FlyingBird.");
    }

    private static void demonstrateIsp() {
        System.out.println("\n========== ISP ==========");
        Printable oldPrinter = new OldPrinter();
        oldPrinter.print("Отчёт за неделю");

        MultiFunctionMachine multiFunctionMachine = new MultiFunctionMachine();
        multiFunctionMachine.print("Заявление");
        multiFunctionMachine.scan("Документ");
        multiFunctionMachine.fax("Договор");
    }

    private static void demonstrateDip() {
        System.out.println("\n========== DIP ==========");
        NotificationService emailNotification =
                new NotificationService(new EmailSender());
        emailNotification.send("Ваш заказ готов к выдаче!");

        NotificationService smsNotification =
                new NotificationService(new SmsSender());
        smsNotification.send("Курьер скоро прибудет.");
    }
}