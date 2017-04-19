package util;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil
{
    public static String getResourcePath(String fileName)
    {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        return loader.getResource(fileName).getFile();
    }

    public static String readFileContent(String path, String charSet) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(path)), charSet);
    }
}