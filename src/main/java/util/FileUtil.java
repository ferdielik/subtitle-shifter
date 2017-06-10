package util;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil
{
    public static String getResourcePath(String filePath)
    {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        return loader.getResource(filePath).getFile();
    }

    public static String readFileContent(String path, String charSet) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(path)), charSet);
    }

    public static List<String> lines(String path, String charSet) throws Exception
    {
        return Files.lines(Paths.get(path), Charset.forName(charSet)).collect(Collectors.toList());
    }

    public static String getFileExtension(String filePath)
    {
        if (filePath.lastIndexOf(".") != -1 && filePath.lastIndexOf(".") != 0)
            return filePath.substring(filePath.lastIndexOf(".") + 1);
        else return "";
    }
}