package test;

import org.junit.Assert;
import org.junit.Test;

import util.FileUtil;
import util.Shifter;

public class ShiftTest
{
    @Test
    public void shiftTest() throws Exception
    {
        String originalFile = FileUtil.getResourcePath("original.srt");
        String originalContent = FileUtil.readFileContent(originalFile, "iso-8859-1");

        String shiftedFile = FileUtil.getResourcePath("original17plus.srt");
        String shiftedContent = FileUtil.readFileContent(shiftedFile, "iso-8859-1");

        originalContent = Shifter.shift(originalContent, 17);

        Assert.assertTrue("must be equals", originalContent.equals(shiftedContent));

    }
}
