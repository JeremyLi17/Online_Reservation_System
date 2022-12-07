package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jeremy on 2022/12/7
 */
public class FormatDate {

    private static final String DATE_PATTERN = "MM/dd/yyyy";
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat(DATE_PATTERN);

    public static String getCurrentDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        String curr_date = DATE_FORMAT.format(date);

        return curr_date;
    }
}
