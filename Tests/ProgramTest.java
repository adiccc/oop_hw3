import model.game.Program;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProgramTest {

    @Test
    public void test() throws IOException {
        List<String> outputList = new ArrayList<>();
        Program p = new Program(outputList::add, "./Tests/TestFiles/gameFlow.txt");
        p.start();
        String programOutput = String.join("\n", outputList).trim();
        String expectedOutput = new String(Files.readAllBytes(Paths.get("./Tests/TestFiles/currectOutputGameFlow.txt"))).trim();
        Assert.assertEquals(expectedOutput, programOutput);
    }
}
