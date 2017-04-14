import util.FileUtil;
import util.Shifter;

public class Initializer
{
    public static void main(String[] args) throws Exception
    {
        String originalContent = FileUtil.readFileContent(args[0], "iso-8859-1");
        Shifter.shift(originalContent, Integer.parseInt(args[1]));
    }
}
