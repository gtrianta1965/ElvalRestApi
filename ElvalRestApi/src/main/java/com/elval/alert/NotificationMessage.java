package com.elval.alert;

import java.util.Date;

public class NotificationMessage {
    public NotificationMessage() {
        super();
    }

    private String message;
    private Date startDate;
    private Date endDate;
    private Integer period;
    private String error;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
