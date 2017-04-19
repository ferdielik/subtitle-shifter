import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import entity.ShiftOption;
import entity.Time;
import shifter.Shifter;
import shifter.SrtShifter;
import util.FileUtil;

public class Initializer
{
    private static final String ENCODING = "iso-8859-1";
    private static final String FILE_ARG = "files";
    private static final String NEW_NAME = "new-name";

    private Shifter srtShifter = new SrtShifter();

    public static void main(String[] args) throws Exception
    {
        new Initializer().run(args);
    }

    private void run(String[] args) throws Exception
    {
        CommandLine commandLine = parse(args);
        String filePath = commandLine.getOptionValue(FILE_ARG);
        String shiftedData = FileUtil.readFileContent(filePath, ENCODING);

        for (ShiftOption option : ShiftOption.values())
        {
            String value = commandLine.getOptionValue(option.getName());
            if (value != null && !value.equals(""))
            {
                shiftedData = srtShifter.shift(shiftedData, option.getUnit(), Integer.valueOf(value));
                System.out.println(value + " " + option.getName() + "  shifted");
            }
        }

        String newFileName = commandLine.getOptionValue(NEW_NAME) == null ? new File(filePath).getName() : commandLine.getOptionValue(NEW_NAME) + ".srt";
        String newFilePath = System.getProperty("user.dir") + "/" + newFileName;
        Files.write(Paths.get(newFilePath), shiftedData.getBytes(ENCODING));
    }


    private CommandLine parse(String[] args)
    {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        CommandLine cmd = null;

        for (ShiftOption sOption : ShiftOption.values())
        {
            options.addOption(new Option(sOption.getOpt(), sOption.getDescription(), true, sOption.getDescription()));
        }

        options.addOption(new Option("", NEW_NAME, true, "new name"));
        Option file = new Option("", FILE_ARG, true, "new name");
        file.setRequired(true);
        options.addOption(file);

        try
        {
            cmd = parser.parse(options, args);
        }
        catch (ParseException e)
        {
            formatter.printHelp("subtitle-shifter", options);
            System.exit(1);
        }

        return cmd;
    }
}
