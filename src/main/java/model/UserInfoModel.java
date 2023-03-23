package model;

public class UserInfoModel {

    private String callType;
    private String dateFrom;
    private String dateTo;
    private double callTime;
    private String rate;

    public UserInfoModel(String callType, String dateFrom, String dateTo, double callTime, String rate) {
        this.callType = callType;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.callTime = callTime;
        this.rate = rate;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public double getCallTime() {
        return callTime;
    }

    public void setCallTime(double callTime) {
        this.callTime = callTime;
    }
}
