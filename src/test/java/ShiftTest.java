import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import entity.SubtitleType;
import util.FileUtil;

public class ShiftTest
{

    @Test
    public void shiftTest() throws Exception
    {
        String originalFile = FileUtil.getResourcePath("original.srt");
        String originalContent = FileUtil.readFileContent(originalFile, "iso-8859-1");

        String shiftedFile = FileUtil.getResourcePath("original17plus.srt");
        String shiftedContent = FileUtil.readFileContent(shiftedFile, "iso-8859-1");

        SubtitleType subtitleType = SubtitleType.findByExtension(FileUtil.getFileExtension(originalFile));
        originalContent = subtitleType.getShifter().shift(originalContent, Calendar.SECOND, 17, null);

        Assert.assertTrue("must be equals", originalContent.equals(shiftedContent));

    }

    @Test
    public void shiftAssTest() throws Exception
    {
        String originalFile = FileUtil.getResourcePath("original.ass");
        String originalContent = FileUtil.readFileContent(originalFile, "UTF-8");

        String shiftedFile = FileUtil.getResourcePath("original-21-plus.ass");
        String shiftedContent = FileUtil.readFileContent(shiftedFile, "UTF-8");

        SubtitleType subtitleType = SubtitleType.findByExtension(FileUtil.getFileExtension(originalFile));
        originalContent = subtitleType.getShifter().shift(originalContent, Calendar.SECOND,     21, null);

        Assert.assertTrue("must be equals", originalContent.equals(shiftedContent));

    }

}
