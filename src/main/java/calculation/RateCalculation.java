package calculation;

import model.UserInfoModel;

import java.util.ArrayList;
import java.util.List;

public class RateCalculation {

    private static final int LIMIT_TIME_FOR_UNLIMITED = 300;
    private static final int LIMIT_TIME_FOR_BASIC = 100;

    private static final double UNLIMITED_PRICE = 100;
    private static final double PER_MINUTE_PRICE = 1.5;
    private static final double BASIC_PRICE = 0.5;

    private double unlimitedRate(double seconds, boolean higherThanLimit) {
        if(!higherThanLimit)
            return (seconds * UNLIMITED_PRICE) / (LIMIT_TIME_FOR_UNLIMITED * 60);

        return seconds / 60;
    }

    private double perMinuteRate(double seconds) {
        return (seconds * PER_MINUTE_PRICE) / 60;
    }

    private double basic(double seconds, boolean higherThanLimit) {
        if(!higherThanLimit)
            return (seconds * BASIC_PRICE) / (LIMIT_TIME_FOR_BASIC * 60);

        return perMinuteRate(seconds);
    }

    public List<Double> unlimitedCalculation(List<UserInfoModel> userInfo) {
        List<Double> costs = new ArrayList<>();
        double totalDuration = 0;
        boolean higherThanLimit = false;

        for(UserInfoModel userInfoModel : userInfo) {
            if(totalDuration > LIMIT_TIME_FOR_UNLIMITED * 60)
                higherThanLimit = true;

            double duration = userInfoModel.getDuration();
            costs.add(unlimitedRate(duration, higherThanLimit));
            totalDuration += duration;
        }

        return costs;
    }

    public List<Double> basicCalculation(List<UserInfoModel> userInfo) {
        List<Double> costs = new ArrayList<>();
        double totalDuration = 0;
        boolean higherThanLimit = false;

        for(UserInfoModel userInfoModel : userInfo) {
            if(userInfoModel.getCallType().equals("02")) {
                costs.add(0.0);
                continue;
            }

            if(totalDuration > LIMIT_TIME_FOR_BASIC * 60)
                higherThanLimit = true;

            double duration = userInfoModel.getDuration();
            costs.add(basic(duration, higherThanLimit));
            totalDuration += duration;
        }

        return costs;
    }

    public List<Double> perMinuteCalculation(List<UserInfoModel> userInfo) {
        List<Double> costs = new ArrayList<>();

        for(UserInfoModel userInfoModel : userInfo)
            costs.add(perMinuteRate(userInfoModel.getDuration()));

        return costs;
    }
}
