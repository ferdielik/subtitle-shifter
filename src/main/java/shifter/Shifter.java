package shifter;

import java.util.concurrent.TimeUnit;

public interface Shifter
{
    public String shift(String data, TimeUnit timeUnit, int s) throws Exception;
}


