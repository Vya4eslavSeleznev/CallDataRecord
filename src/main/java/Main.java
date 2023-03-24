import report.ReportGeneration;

public class Main {

    public static void main(String[] args) {
        String phoneNumber = "79270106185";
        new ReportGeneration(phoneNumber).writeToFile();
    }
}
