package menu.printer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class SimpleMenuPrinterTest {

    private MenuPrinter menuPrinter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        this.menuPrinter = new SimpleMenuPrinter();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testPrintAllMainCourses() {
        this.menuPrinter.printAllMainCourses();
        assertThat(outContent.toString(), containsString("1 |\tSpaghetti |\t19.99"));
    }

    @Test
    void testPrintAllDeserts() {
        this.menuPrinter.printAllDeserts();
        assertThat(outContent.toString(), containsString("1 |\tIce Cream |\t9.99"));
    }

    @Test
    void testPrintAllDrinks() {
        this.menuPrinter.printAllDrinks();
        assertThat(outContent.toString(), containsString("1 |\tCoca-Cola |\t2.99"));
    }
}