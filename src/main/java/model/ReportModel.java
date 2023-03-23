package model;

import java.util.Date;

public class ReportModel {

    private String callType;
    private Date startDate;
    private Date endDate;
    private double duration;
    private double cost;

    public ReportModel(String callType, Date startDate, Date endDate, double duration, double cost) {
        this.callType = callType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.cost = cost;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
