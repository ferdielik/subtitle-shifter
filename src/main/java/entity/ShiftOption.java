package entity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public enum ShiftOption
{
    MILLISECONDS(Calendar.MILLISECOND, "l", "Milliseconds", "Milliseconds"),
    SECONDS(Calendar.SECOND, "s", "Seconds", "Seconds"),
    MINUTES(Calendar.MINUTE, "m", "Minutes", "Minutes"),
    HOURS(Calendar.HOUR_OF_DAY, "h", "Hours", "Hours");

    String opt;
    String name;
    String description;
    int unit;

    ShiftOption(int unit, String opt, String description, String name)
    {
        this.opt = opt;
        this.name = name;
        this.description = description;
        this.unit = unit;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getUnit()
    {
        return unit;
    }

    public String getOpt()
    {
        return opt;
    }
}