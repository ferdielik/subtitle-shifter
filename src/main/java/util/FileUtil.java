package util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil
{
    public static String getResourcePath(String fileName)
    {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        return loader.getResource(fileName).getFile();
    }

    public static String readFileContent(String path, String charSet) throws Exception
    {
        File file = new File(path);
        return new String(Files.readAllBytes(Paths.get(path)), charSet);
    }

    public static List<String> readFileContentAsList(String path) throws Exception
    {
        return Files.lines(Paths.get(path)).collect(Collectors.toList());
    }
}