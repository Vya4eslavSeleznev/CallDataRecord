package Report;

import calculation.RateCalculation;
import model.ReportModel;
import model.UserInfoModel;

import java.util.ArrayList;
import java.util.List;

public class ReportGeneration {

    private static final double PRECISION = 1000;

    private List<UserInfoModel> userInfo;
    private List<ReportModel> reportInfo;

    public ReportGeneration(List<UserInfoModel> userInfo) {
        this.userInfo = userInfo;
    }

    private void createReportList() {
        reportInfo = new ArrayList<>();

        for(int i = 0; i < userInfo.size(); i++) {
            reportInfo.add(new ReportModel(
              userInfo.get(i).getCallType(),
              userInfo.get(i).getStartDate(),
              userInfo.get(i).getEndDate(),
              userInfo.get(i).getDuration(),
              0
            ));
        }
    }

    public void generate() {
        createReportList();
        RateCalculation rateCalculation = new RateCalculation();
        List<Double> costs = rateCalculation.basicCalculation(userInfo);

        System.out.println(
          "Tariff index: " + userInfo.get(0).getRate() + "\n" +
          "----------------------------------------------------------------------------\n" +
          "Report for phone number " + userInfo.get(0).getPhoneNumber() + ":\n" +
          "----------------------------------------------------------------------------\n" +
          "| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n" +
          "----------------------------------------------------------------------------\n"
        );

//        for(ReportModel reportModel : reportInfo) {
//            System.out.println(
//              "|\t" + reportModel.getCallType() + "\t" +
//              "|\t" + reportModel.getStartDate() + "\t" +
//              "|\t" + reportModel.getEndDate() + "\t" +
//              "|\t" + reportModel.getDuration() + "\t" +
//              "|\t" + reportModel.getCost() + "\t|"
//            );
//        }

        for(int i = 0; i < userInfo.size(); i++) {
            System.out.println(
              "|\t" + reportInfo.get(i).getCallType() + "\t" +
                "|\t" + reportInfo.get(i).getStartDate() + "\t" +
                "|\t" + reportInfo.get(i).getEndDate() + "\t" +
                "|\t" + reportInfo.get(i).getDuration() + "\t" +
                "|\t" + Math.round(costs.get(i) * PRECISION) / PRECISION + "\t|"
            );
        }

        System.out.println(
          "----------------------------------------------------------------------------\n" +
          "|\t\t\t\tTotal Cost: |\t" + reportInfo.get(0).getCost() + " rubles |\n" +
          "----------------------------------------------------------------------------\n"
        );
    }
}