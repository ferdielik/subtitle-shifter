package shifter;

public interface Shifter
{
    String shift(String data, int field, int amount, TimeCheck timeCheck) throws Exception;
}


