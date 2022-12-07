package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author jeremy on 2022/12/7
 */
public class FormatDate {

    private static final String DATE_PATTERN = "yyyy/MM/dd";
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat(DATE_PATTERN);

    public static String getCurrentDate(Date date) {
//        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        String curr_date = DATE_FORMAT.format(date);

        return curr_date;
    }
}
