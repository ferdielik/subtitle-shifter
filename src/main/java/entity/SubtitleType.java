package entity;

import shifter.impl.AssShifterImpl;
import shifter.Shifter;
import shifter.impl.SrtShifterImpl;

public enum SubtitleType
{
    SRT("srt", "HH:mm:ss,SSS", new SrtShifterImpl()),
    ASS("ass", "HH:mm:ss.SS", new AssShifterImpl());

    private String extension;
    private String timeFormat;
    private Shifter shifter;

    SubtitleType(String extension, String timeFormat, Shifter shifter)
    {
        this.extension = extension;
        this.timeFormat = timeFormat;
        this.shifter = shifter;
    }

    public String getExtension()
    {
        return extension;
    }

    public Shifter getShifter()
    {
        return shifter;
    }

    public String getTimeFormat()
    {
        return timeFormat;
    }

    public static SubtitleType findByExtension(String extension)
    {
        return valueOf(extension.toUpperCase());
    }
}
