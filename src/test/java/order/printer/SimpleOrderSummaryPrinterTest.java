package order.printer;

import menu.models.*;
import order.models.Order;
import order.utils.OrderTotalCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.NoSuchObjectException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class SimpleOrderSummaryPrinterTest {

    private OrderSummaryPrinter summaryPrinter;
    private Order order;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        summaryPrinter = new SimpleOrderSummaryPrinter();
        order = new Order();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void shouldPrintOrderSummaryWithDrink() throws NoSuchObjectException {
        order.addItemToOrder(new Drink(1, "TEST", 1F, "", false, false));
        summaryPrinter.printOrderSummary(order);

        assertThat(outContent.toString(), containsString("\n\nORDER SUMMARY"));
        assertThat(outContent.toString(), containsString("TEST |\t1.0 |\t1"));
        assertThat(outContent.toString(), containsString("TOTAL \t\t" + OrderTotalCalculator.computeOrderTotalPrice(order)));
    }

    @Test
    void shouldPrintOrderSummaryWithLunch() throws NoSuchObjectException {
        order.addItemToOrder(new Lunch(
                new MainCourse(1, "MAIN_COURSE", 1F, "", CuisinesType.POLISH),
                new Dessert(2, "DESSERT", 1F, "", false)
        ));
        summaryPrinter.printOrderSummary(order);
        assertThat(outContent.toString(), containsString("\n\nORDER SUMMARY"));
        assertThat(outContent.toString(), containsString("MAIN_COURSE |\t1.0 |\t1"));
        assertThat(outContent.toString(), containsString("DESSERT |\t1.0 |\t1"));
        assertThat(outContent.toString(), containsString("TOTAL \t\t" + OrderTotalCalculator.computeOrderTotalPrice(order)));
    }

    @Test
    void shouldPrintOrderSummaryWithMoreThanOneItem() throws NoSuchObjectException {
        order.addItemToOrder(new Drink(1, "TEST", 1F, "", false, false));
        order.addItemToOrder(new Lunch(
                new MainCourse(1, "MAIN_COURSE", 1F, "", CuisinesType.POLISH),
                new Dessert(2, "DESSERT", 1F, "", false)
        ));
        summaryPrinter.printOrderSummary(order);

        assertThat(outContent.toString(), containsString("\n\nORDER SUMMARY"));
        assertThat(outContent.toString(), containsString("TEST |\t1.0 |\t1"));
        assertThat(outContent.toString(), containsString("MAIN_COURSE |\t1.0 |\t1"));
        assertThat(outContent.toString(), containsString("DESSERT |\t1.0 |\t1"));
        assertThat(outContent.toString(), containsString("TOTAL \t\t" + OrderTotalCalculator.computeOrderTotalPrice(order)));
    }

}