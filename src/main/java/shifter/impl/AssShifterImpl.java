package shifter.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.SubtitleType;
import entity.Time;
import shifter.TimeCheck;
import shifter.Shifter;

public class AssShifterImpl implements Shifter
{
    private static final String ASS_TIME = "[0-9]+:[0-9]{2}:[0-9]{2}.[0-9]+";
    private static final String TIME_LINE_PATTERN = "Dialogue: [0-9]," + ASS_TIME + "," + ASS_TIME;
    private static final String DIALOG_ATTR_SEPARATOR = ",";

    @Override
    public String shift(String data, int field, int amount, TimeCheck timeCheck) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile(TIME_LINE_PATTERN).matcher(data);

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
        String[] parts = times.split(DIALOG_ATTR_SEPARATOR);

        Time start = new Time(parts[1], SubtitleType.ASS.getTimeFormat());
        if (timeCheck == null || timeCheck.isOk(start))
        {
            start.add(field, amount);
            times = times.replace(parts[1], start.format());

            Time end = new Time(parts[2], SubtitleType.ASS.getTimeFormat());
            end.add(field, amount);
            times = times.replace(parts[2], end.format());
        }
        return times;
    }
}


