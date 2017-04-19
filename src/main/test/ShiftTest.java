import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import shifter.Shifter;
import shifter.SrtShifter;
import util.FileUtil;

public class ShiftTest
{
    Shifter shifter;

    @Before
    public void init()
    {
        shifter = new SrtShifter();
    }

    @Test
    public void shiftTest() throws Exception
    {
        String originalFile = FileUtil.getResourcePath("original.srt");
        String originalContent = FileUtil.readFileContent(originalFile, "iso-8859-1");

        String shiftedFile = FileUtil.getResourcePath("original17plus.srt");
        String shiftedContent = FileUtil.readFileContent(shiftedFile, "iso-8859-1");

        originalContent = shifter.shift(originalContent, TimeUnit.SECONDS, 17);

        Assert.assertTrue("must be equals", originalContent.equals(shiftedContent));

    }
}
