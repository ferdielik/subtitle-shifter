package util;


import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

class Time // format >  HH:mm:ss,SSS
{
    private long millis = 0;

    Time(String time) throws ParseException
    {
        String[] parts = time.split(":");

        millis += TimeUnit.HOURS.toMillis(Integer.parseInt(parts[0]));
        millis += TimeUnit.MINUTES.toMillis(Integer.parseInt(parts[1]));

        String[] parts2 = parts[2].split(",");
        millis += TimeUnit.SECONDS.toMillis(Integer.parseInt(parts2[0]));
        millis += Integer.parseInt(parts2[1]);
    }

    @Override
    public String toString()
    {
        return String.format("%02d:%02d:%02d,%03d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                millis - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis))
        );
    }

    public void add(TimeUnit timeUnit, int i)
    {
        millis += timeUnit.toMillis(i);
    }
}
