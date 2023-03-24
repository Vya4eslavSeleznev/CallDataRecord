package model;

import java.util.Date;

public class UserInfoModel {

    private String phoneNumber;
    private String callType;
    private Date startDate;
    private Date endDate;
    private long duration;
    private String rate;

    public UserInfoModel(String phoneNumber, String callType, Date startDate, Date endDate, long duration,
                         String rate) {
        this.phoneNumber = phoneNumber;
        this.callType = callType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.rate = rate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
