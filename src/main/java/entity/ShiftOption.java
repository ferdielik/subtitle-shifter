package entity;

import java.util.concurrent.TimeUnit;

public enum ShiftOption
{
    MILLISECONDS(TimeUnit.MILLISECONDS, "l", "Milliseconds", "Milliseconds"),
    SECONDS(TimeUnit.SECONDS, "s", "Seconds", "Seconds"),
    MINUTES(TimeUnit.MINUTES, "m", "Minutes", "Minutes"),
    HOURS(TimeUnit.HOURS, "h", "Hours", "Hours");

    String opt;
    String name;
    String description;
    TimeUnit unit;

    ShiftOption(TimeUnit unit, String opt, String description, String name)
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

    public TimeUnit getUnit()
    {
        return unit;
    }

    public String getOpt()
    {
        return opt;
    }
}