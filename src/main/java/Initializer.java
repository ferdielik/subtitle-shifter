import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import entity.ShiftOption;
import entity.SubtitleType;
import entity.Time;
import shifter.Shifter;
import util.FileUtil;

public class Initializer
{
    private static final String ARG_FILE = "subtitle-file";
    private static final String ARG_OVERRIDE = "override";
    private static final String ARG_AFTER = "after";
    private static final String ARG_ENCODING = "encoding";

    private String encoding;
    private Path subtitleFilePath;
    private Shifter shifter;
    private CommandLine commandLine;

    public static void main(String[] args) throws Exception
    {
        new Initializer().run(args);
    }

    private void run(String[] args) throws Exception
    {
        initialize(args);
        String subtitleData = processSubtitle();
        Files.write(Paths.get(getFilePath()), subtitleData.getBytes(encoding));
        System.out.println(getFilePath());
    }

    private void initialize(String[] args)
    {
        commandLine = parse(args);
        subtitleFilePath = Paths.get(commandLine.getOptionValue(ARG_FILE));
        encoding = commandLine.getOptionValue(ARG_ENCODING) == null ? "utf-8" : commandLine.getOptionValue(ARG_ENCODING);

        String ext = FileUtil.getFileExtension(subtitleFilePath.toString());
        SubtitleType subType = SubtitleType.findByExtension(ext);
        shifter = subType.getShifter();
    }

    private String processSubtitle() throws Exception
    {
        String subtitleData = FileUtil.readFileContent(subtitleFilePath.toString(), encoding);

        for (ShiftOption option : ShiftOption.values())
        {
            String value = commandLine.getOptionValue(option.getName());
            if (value != null && !value.equals(""))
            {
                subtitleData = shifter.shift(subtitleData, option.getUnit(), Integer.valueOf(value), time ->
                {
                    String afterTimeArg = commandLine.getOptionValue(ARG_AFTER);
                    if (afterTimeArg == null)
                        return true;

                    Time from = new Time(afterTimeArg, "HH:mm:ss");
                    return time.isAfter(from);

                });
                System.out.println(value + " " + option.getName() + "  shifted");
            }
        }

        return subtitleData;
    }

    private String getFilePath()
    {
        boolean override = commandLine.hasOption(ARG_OVERRIDE);
        if (override)
        {
            return subtitleFilePath.toString();
        }
        return subtitleFilePath.getParent().toString() + "/resync-" + subtitleFilePath.getFileName();
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

        options.addOption(new Option("o", ARG_OVERRIDE, false, "Override subtitle"));
        options.addOption(new Option("e", ARG_ENCODING, true, "Subtitle encoding"));
        options.addOption(new Option("a", ARG_AFTER, true, "After time"));

        Option file = new Option("f", ARG_FILE, true, "Subtitle file");
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
