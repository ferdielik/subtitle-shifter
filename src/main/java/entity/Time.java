package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Time
{
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat;

    public Time(String time, String format)
    {
        this.dateFormat = new SimpleDateFormat(format);
        try
        {
            calendar.setTime(dateFormat.parse(time));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isAfter(Time time)
    {
        return toMillis() > time.toMillis();
    }

    public String format()
    {
        return dateFormat.format(calendar.getTime());
    }

    public void add(int field, int amount)
    {
        calendar.add(field, amount);
    }

    public long toMillis()
    {
        long millis = TimeUnit.HOURS.toMillis(calendar.get(Calendar.HOUR_OF_DAY));

        millis += TimeUnit.MINUTES.toMillis(calendar.get(Calendar.MINUTE));
        millis += TimeUnit.SECONDS.toMillis(calendar.get(Calendar.SECOND));
        millis += calendar.get(Calendar.MILLISECOND);

        return millis;
    }
}