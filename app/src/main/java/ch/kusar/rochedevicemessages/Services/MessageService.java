package ch.kusar.rochedevicemessages.Services;

import android.widget.Switch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ch.kusar.rochedevicemessages.Models.Message;
import ch.kusar.rochedevicemessages.Models.SeverityLevel;

/**
 * Created by ku5ar on 07.07.14.
 */
public class MessageService {

    public MessageService(){
    }

    public ArrayList<Message> getMessages(){
        return getDummyList();
    }

    private ArrayList<Message> getDummyList() {
        ArrayList<Message> dummyList = new ArrayList<Message>();

        for (int i = 0; i<10; i++){
            Message message = new Message();
            int severityId = i % 3;
            switch(severityId) {
                case 0: message.setSeverity(SeverityLevel.Info.toString()); break;
                case 1: message.setSeverity(SeverityLevel.Warning.toString()); break;
                case 2: message.setSeverity(SeverityLevel.Alarm.toString()); break;
            }

            message.setMessage("The calibration result with Id {" + i + "} has a severity of: " + message.getSeverity());
            message.setDesc("A more detailed description.");

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            message.setDate(dateFormat.format(date));

            dummyList.add(message);
        }

        return dummyList;
    }
}
