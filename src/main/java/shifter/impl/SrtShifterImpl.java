package shifter.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.SubtitleType;
import entity.Time;
import shifter.TimeCheck;
import shifter.Shifter;

public class SrtShifterImpl implements Shifter
{
    private static final String SRT_TIME = "[0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3}";
    private static final String SRT_TIME_SEPARATOR = " --> ";
    private static final String TIME_LINE_PATTERN = SRT_TIME + SRT_TIME_SEPARATOR + SRT_TIME; // 00:09:43,024

    public String shift(String data, int field, int amount, TimeCheck timeCheck) throws Exception
    {
        Matcher m = Pattern.compile(TIME_LINE_PATTERN).matcher(data);
        StringBuffer sb = new StringBuffer();

        while (m.find())
        {
            String times = shiftTimes(m.group(), timeCheck, field, amount);
            m.appendReplacement(sb, times);
        }
        m.appendTail(sb);

        return sb.toString();
    }

    private String shiftTimes(String times, TimeCheck timeCheck, int field, int amount)
    {
        String[] parts = times.split(SRT_TIME_SEPARATOR);

        Time start = new Time(parts[0], SubtitleType.SRT.getTimeFormat());
        if (timeCheck == null || timeCheck.isOk(start))
        {
            start.add(field, amount);
            times = times.replace(parts[0], start.format());

            Time end = new Time(parts[1], SubtitleType.SRT.getTimeFormat());
            end.add(field, amount);
            times = times.replace(parts[1], end.format());
        }
        return times;
    }
}


