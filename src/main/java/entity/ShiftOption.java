package entity;

import java.util.concurrent.TimeUnit;

public enum ShiftOption
{
    MILLISECONDS("Millieconds", "Millieconds", TimeUnit.MILLISECONDS),
    SECONDS("Seconds", "Seconds", TimeUnit.SECONDS),
    MINUTES("Minutes", "Minutes", TimeUnit.MINUTES),
    HOURS("Hours", "Hours", TimeUnit.HOURS),;

    String name;
    String description;
    TimeUnit unit;

    ShiftOption(String name, String description, TimeUnit unit)
    {
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

}