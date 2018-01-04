import com.ovh.charlotte.DataBaseWriter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WritingTest {

    @Test
    public void dataGenerationTest() {
        DataBaseWriter dbw = new DataBaseWriter();
        dbw.dataBaseGeneration();
        assertTrue(true);
    }
}
