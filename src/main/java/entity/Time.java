package entity;

import java.util.concurrent.TimeUnit;

public class Time // HH:mm:ss,SSS
{
    private long millis = 0;

    public Time(String time)
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