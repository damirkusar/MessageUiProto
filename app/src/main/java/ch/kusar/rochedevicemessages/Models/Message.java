package ch.kusar.rochedevicemessages.Models;

import java.util.Date;

import ch.kusar.rochedevicemessages.R;

/**
 * Created by ku5ar on 07.07.14.
 */
public class Message {
    private String message;
    private String description;
    private String date;
    private String severity;

    public Message(){

    }

    public int getImageId() {

        if (this.getSeverity().equals(SeverityLevel.Info.toString())){
            return R.drawable.ic_label_info;
        }
        if (this.getSeverity().equals(SeverityLevel.Warning.toString())){
            return R.drawable.ic_label_warning;
        }
        if (this.getSeverity().equals(SeverityLevel.Alarm.toString())){
            return R.drawable.ic_label_alarm;
        }

        return R.drawable.ic_label_info;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return message + "\n" + description;
    }
}
