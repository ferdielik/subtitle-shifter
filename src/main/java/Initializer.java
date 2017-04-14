import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import util.FileUtil;
import util.Shifter;

public class Initializer
{
    private static final String ENCODING = "iso-8859-1";

    public static void main(String[] args) throws Exception
    {
        String originalContent = FileUtil.readFileContent(args[0], ENCODING);
        String shiftedData = Shifter.shift(originalContent, TimeUnit.SECONDS, Integer.parseInt(args[1]));
        shiftedData = Shifter.shift(shiftedData, TimeUnit.MILLISECONDS, Integer.parseInt(args[2]));

        String newFilePath = System.getProperty("user.dir") + "/" + new File(args[0]).getName();
        System.out.println(newFilePath);

        Files.write(Paths.get(newFilePath), shiftedData.getBytes(ENCODING));
    }
}
