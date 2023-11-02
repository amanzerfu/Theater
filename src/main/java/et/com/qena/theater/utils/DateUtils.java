package et.com.qena.theater.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtils {
    public static Timestamp getCurrentTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new Timestamp(calendar.getTimeInMillis());
    }
}