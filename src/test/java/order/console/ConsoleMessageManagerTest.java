package order.console;

import exceptions.CuisinesNotFoundException;
import menu.printer.MenuPrinter;
import menu.printer.SimpleMenuPrinter;
import order.console.ConsoleMessageManager;
import order.console.ConsoleUserInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class ConsoleMessageManagerTest {

    private ConsoleMessageManager messageManager;

    private ConsoleUserInteractor userInteractor;

    private MenuPrinter menuPrinter;

    @BeforeEach
    public void setUp() {
        this.messageManager = new ConsoleMessageManager();
        this.userInteractor = mock(ConsoleUserInteractor.class);
        this.menuPrinter = mock(SimpleMenuPrinter.class);

        this.messageManager.setMenuPrinter(menuPrinter);
    }

    @Test
    void shouldReturnTrueWhenUserInputsYesOrYOnLunchSelection() {
        when(userInteractor.askForLunch()).thenReturn("yes");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isLunchSelected(), equalTo(true));

        when(userInteractor.askForLunch()).thenReturn("y");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isLunchSelected(), equalTo(true));
    }

    @Test
    void shouldReturnFalseWhenUserInputsNoOrNOnLunchSelection() {
        when(userInteractor.askForLunch()).thenReturn("n");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isLunchSelected(), equalTo(false));

        when(userInteractor.askForLunch()).thenReturn("no");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isLunchSelected(), equalTo(false));
    }

    @Test
    void shouldReturnTrueWhenUserInputsYesOrYOnDrinkSelection() {
        when(userInteractor.askForDrink()).thenReturn("yes");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isDrinkSelected(), equalTo(true));

        when(userInteractor.askForDrink()).thenReturn("y");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isDrinkSelected(), equalTo(true));
    }

    @Test
    void shouldReturnFalseWhenUserInputsNoOrNOnDrinkSelection() {
        when(userInteractor.askForDrink()).thenReturn("n");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isDrinkSelected(), equalTo(false));

        when(userInteractor.askForDrink()).thenReturn("no");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isDrinkSelected(), equalTo(false));
    }

    @Test
    void shouldReturnFalseWhenUserInputsAnythingElseThanYesOrY() {
        when(userInteractor.askForLunch()).thenReturn("test");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isLunchSelected(), equalTo(false));
    }

    @Test
    void shouldGetMainCourseWhenCuisinesTypeWasValid() throws CuisinesNotFoundException {
        when(userInteractor.askForCuisinesType()).thenReturn(1);
        when(userInteractor.askForMainCourseIndex()).thenReturn(1);
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.askForMainCourseAndGetSelectedId(), equalTo(1));
    }

    /**
     * Creates infinite recursion loop because of error handling and hard coded
     */
    /*@Test
    void shouldThrowExceptionWhenWrongCuisinesTypeIdWasProvided() {
        when(userInteractor.askForCuisinesType()).thenReturn(999, 1);
        this.messageManager.setUserInteractor(userInteractor);
        this.messageManager.setMenuPrinter(new SimpleMenuPrinter());

        assertThrows(CuisinesNotFoundException.class, () -> this.messageManager.askForMainCourseAndGetSelectedId());
    }*/

    @Test
    void shouldShowOnlyFitDessertsWhenUserNotSelectedAll() {
        when(userInteractor.askForDessertType()).thenReturn("f");
        when(userInteractor.askForDessertIndex()).thenReturn(1);
        this.messageManager.setUserInteractor(userInteractor);

        this.messageManager.askForDessertAndGetSelectedId();

        verify(this.messageManager.getMenuPrinter()).printOnlyFitDesserts();
    }

    @Test
    void shouldShowAllDessertsWhenUserEntersS() {
        when(userInteractor.askForDessertType()).thenReturn("s");
        this.messageManager.setUserInteractor(userInteractor);

        this.messageManager.askForDessertAndGetSelectedId();

        verify(this.messageManager.getMenuPrinter()).printAllDeserts();
    }

    @Test
    void shouldShowAllDessertsWhenUserEntersShowAll() {
        when(userInteractor.askForDessertType()).thenReturn("show all");
        this.messageManager.setUserInteractor(userInteractor);

        this.messageManager.askForDessertAndGetSelectedId();

        verify(this.messageManager.getMenuPrinter()).printAllDeserts();
    }

    @Test
    void shouldReturnSelectedDessertIndex() {
        when(userInteractor.askForDessertType()).thenReturn("s");
        when(userInteractor.askForDessertIndex()).thenReturn(1);
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.askForDessertAndGetSelectedId(), equalTo(1));
    }

    @Test
    void shouldReturnSelectedDrinkIndex() {
        when(userInteractor.askForDrinkIndex()).thenReturn(1);
        this.messageManager.setUserInteractor(userInteractor);

        int selectedDrinkId = this.messageManager.askForDrinkAndGetSelectedId();

        verify(this.messageManager.getMenuPrinter()).printAllDrinks();
        assertThat(selectedDrinkId, equalTo(1));
    }

    @Test
    void shouldReturnTrueWhenUserConfirmsLemonToDrink() {
        when(userInteractor.askForAdditionalLemonForDrink()).thenReturn("yes");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isLemonRequestedForDrink(), equalTo(true));

        when(userInteractor.askForAdditionalLemonForDrink()).thenReturn("y");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isLemonRequestedForDrink(), equalTo(true));
    }

    @Test
    void shouldReturnTrueWhenUserConfirmsIceCubesToDrink() {
        when(userInteractor.askForAdditionalIceCubesForDrink()).thenReturn("yes");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isIceCubesRequestedForDrink(), equalTo(true));

        when(userInteractor.askForAdditionalIceCubesForDrink()).thenReturn("y");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isIceCubesRequestedForDrink(), equalTo(true));
    }

    @Test
    void shouldReturnFalseWhenUserNotRequestLemonForDrink() {
        when(userInteractor.askForAdditionalLemonForDrink()).thenReturn("test");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isLemonRequestedForDrink(), equalTo(false));
    }

    @Test
    void shouldReturnFalseWhenUserNotRequestIceCubesForDrink() {
        when(userInteractor.askForAdditionalIceCubesForDrink()).thenReturn("test");
        this.messageManager.setUserInteractor(userInteractor);

        assertThat(this.messageManager.isIceCubesRequestedForDrink(), equalTo(false));
    }

}