package alertsCode;

import java.util.Date;

public class NotificationType {
    public NotificationType() {
        super();
    }
    
    private String message;
    private Date startDate;
    private Date endDate;

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

}
