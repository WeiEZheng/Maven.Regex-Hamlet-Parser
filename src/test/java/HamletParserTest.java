import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HamletParserTest {
    private String hamletText;
    private HamletParser hamletParser;

    @Before
    public void setUp() {
        this.hamletParser = new HamletParser();
        this.hamletText = hamletParser.getHamletData();
    }

    @Test
    public void testChangeHamletToLeon() {
        String text= hamletParser.replace("Hamlet","Leon", hamletText);
        Assert.assertFalse(hamletParser.find("Hamlet", text));
        Assert.assertTrue(hamletParser.find("Leon", text));
    }

    @Test
    public void testChangeHoratioToTariq() {
        String text= hamletParser.replace("Horatio","Tariq", hamletText);
        Assert.assertFalse(hamletParser.find("Horatio", text));
        Assert.assertTrue(hamletParser.find("Tariq", text));
        System.out.println(text);
    }
}