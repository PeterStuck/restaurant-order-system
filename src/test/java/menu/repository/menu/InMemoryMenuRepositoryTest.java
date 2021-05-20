package menu.repository.menu;

import exceptions.CuisinesNotFoundException;
import exceptions.MenuItemNotFoundException;
import menu.models.MenuItem;
import menu.repository.menu.InMemoryMenuRepository;
import menu.repository.menu.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class InMemoryMenuRepositoryTest {

    private MenuRepository repository;

    @BeforeEach
    public void setUp() {
        this.repository = new InMemoryMenuRepository();
    }

    @Test
    void shouldReturnStoredMainCourses() {
        /**
         * Should be minimum 1 of each cuisines type main course.
         */
        assertThat(this.repository.getAllMainCourse().size(), greaterThanOrEqualTo(3));
    }

    @Test
    void shouldFilterMainCoursesByCuisinesType() throws CuisinesNotFoundException {
        /**
         * Should be minimum 1 of each cuisines type main course.
         */
        assertThat(this.repository.getMainCoursesByCuisinesTypeId(1).size(), greaterThanOrEqualTo(1));
        assertThat(this.repository.getMainCoursesByCuisinesTypeId(2).size(), greaterThanOrEqualTo(1));
        assertThat(this.repository.getMainCoursesByCuisinesTypeId(3).size(), greaterThanOrEqualTo(1));
    }

    @Test
    void shouldThrowExceptionWhenPassedWrongCuisinesIndex() {
        assertThrows(CuisinesNotFoundException.class, () -> {
            this.repository.getMainCoursesByCuisinesTypeId(999);
        });
    }

    @Test
    void shouldReturnStoredDesserts() {
        assertThat(this.repository.getAllDeserts(), hasSize(4));
    }

    @Test
    void shouldRetrieveOnlyFitDesserts() {
        assertThat(this.repository.getFitDesserts(), hasSize(2));
    }

    @Test
    void shouldReturnStoredDrinks() {
        assertThat(this.repository.getAllDrinks(), hasSize(5));
    }

    @Test
    void whenMainCourseWithIdIsPresentShouldReturnObject() throws MenuItemNotFoundException {
        int searchedId = 1;
        MenuItem mainCourse = this.repository.findMainCourseById(searchedId);

        assertThat(mainCourse, notNullValue());
        assertThat(mainCourse.getId(), equalTo(searchedId));
    }

    @Test
    void whenMainCourseWithPassedIdNotExistsThenThrowException() {
        assertThrows(MenuItemNotFoundException.class, () -> {
            this.repository.findMainCourseById(999);
        });
    }

    @Test
    void whenDessertWithIdIsPresentShouldReturnObject() throws MenuItemNotFoundException {
        int searchedId = 1;
        MenuItem dessert = this.repository.findDessertById(searchedId);

        assertThat(dessert, notNullValue());
        assertThat(dessert.getId(), equalTo(searchedId));
    }

    @Test
    void whenDessertWithPassedIdNotExistsThenThrowException() {
        assertThrows(MenuItemNotFoundException.class, () -> {
            this.repository.findDessertById(999);
        });
    }

    @Test
    void whenDrinkWithIdIsPresentShouldReturnObject() throws MenuItemNotFoundException {
        int searchedId = 1;
        MenuItem drink = this.repository.findDrinkById(searchedId);

        assertThat(drink, notNullValue());
        assertThat(drink.getId(), equalTo(searchedId));
    }

    @Test
    void whenDrinkWithPassedIdNotExistsThenThrowException() {
        assertThrows(MenuItemNotFoundException.class, () -> {
            this.repository.findDrinkById(999);
        });
    }

}