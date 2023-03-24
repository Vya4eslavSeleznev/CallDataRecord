package report;

import calculation.RateCalculation;
import model.UserInfoModel;
import parser.FileParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ReportGeneration {

    private static final double PRECISION = 1000;
    private static final String URL = "reports/report.txt";

    private List<UserInfoModel> userInfo;

    public ReportGeneration(String phoneNumber) {
        try {
            this.userInfo = new FileParser().findUserInfo(phoneNumber);
        }
        catch(IOException e) {
            System.out.println("Invalid file path :(");
        }
    }

    public StringBuilder prepareData(String rate) {
        StringBuilder sb = new StringBuilder();
        List<Double> costs;

        switch(rate) {
            case "06":
                costs = new RateCalculation().unlimitedCalculation(userInfo);
                break;
            case "03":
                costs = new RateCalculation().perMinuteCalculation(userInfo);
                break;
            case "11":
                costs = new RateCalculation().basicCalculation(userInfo);
                break;
            default:
                return sb.append("Incorrect rate type :(");
        }

        sb.append("Tariff index: ").append(userInfo.get(0).getRate()).append("\n");
        sb.append("-------------------------------------------------------------------------");
        sb.append("------------------------------------------------------------------------\n");
        sb.append("Report for phone number ").append(userInfo.get(0).getPhoneNumber()).append(":\n");
        sb.append("-------------------------------------------------------------------------");
        sb.append("------------------------------------------------------------------------\n");
        sb.append("|\tCall Type\t|\t\tStart Time\t\t|\tEnd Time\t\t\t|\tDuration\t|\tCost\t|\n");
        sb.append("-------------------------------------------------------------------------");
        sb.append("------------------------------------------------------------------------\n");

        for(int i = 0; i < userInfo.size(); i++) {
            Duration duration = Duration.ofMillis(userInfo.get(i).getDuration() * 1000);
            String timeFormat = String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());

            sb.append("|\t").append(userInfo.get(i).getCallType()).append("\t\t");
            sb.append("|\t").append(userInfo.get(i).getStartDate()).append("\t");
            sb.append("|\t").append(userInfo.get(i).getEndDate()).append("\t");
            sb.append("|\t").append(timeFormat).append("\t");
            sb.append("|\t").append(Math.round(costs.get(i) * PRECISION) / PRECISION).append("\t|\n");
        }

        double totalCost = costs.stream()
          .mapToDouble(Double::doubleValue)
          .sum();

        sb.append("-------------------------------------------------------------------------");
        sb.append("------------------------------------------------------------------------\n");
        sb.append("|\t\t\t\t\t\t\t\t\t\t\t    Total Cost: |\t\t\t")
          .append(Math.round(totalCost * PRECISION) / PRECISION).append(" rubles \t|\n");
        sb.append("-------------------------------------------------------------------------");
        sb.append("------------------------------------------------------------------------\n");
        sb.append("\t\t\t/Powered by Vyacheslav Seleznev/");

        return sb;
    }

    public void writeToFile() {
        File file = new File(URL);
        StringBuilder sb;

        if(userInfo == null || userInfo.size() == 0)
            sb = new StringBuilder("Not enough data to generate a report :(");
        else
            sb = prepareData(userInfo.get(0).getRate());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(sb);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
