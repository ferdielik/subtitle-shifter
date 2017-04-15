package util;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Time;

public class Shifter
{
    private static final String TIME_PATTERN = "[0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3}"; // 00:09:43,024

    public static String shift(String data, TimeUnit timeUnit, int s) throws Exception
    {
        return shift(data, timeUnit.toMillis(s));
    }

    public static String shift(String data, long millis) throws Exception
    {
        System.out.println("started");
        Matcher m = Pattern.compile(TIME_PATTERN).matcher(data);
        StringBuffer sb = new StringBuffer();

        while (m.find())
        {
            Time time = new Time(m.group());
            time.add(TimeUnit.MILLISECONDS, (int) millis);
            m.appendReplacement(sb, time.toString());
        }
        m.appendTail(sb);

        return sb.toString();
    }
}


