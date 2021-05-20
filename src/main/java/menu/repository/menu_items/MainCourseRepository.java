package menu.repository.menu_items;

import exceptions.CuisinesNotFoundException;
import menu.models.CuisinesType;
import menu.models.MainCourse;
import menu.models.MenuItem;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainCourseRepository extends MenuItemRepository {

    private final List<MenuItem> availableMainCourses = Arrays.asList(
            new MainCourse(1,"Spaghetti", 19.99F, "", CuisinesType.ITALIAN),
            new MainCourse(2,"Lasagne", 15.99F, "Available meat type: beef, pork.", CuisinesType.ITALIAN),
            new MainCourse(3,"Pasta with Pesto", 16.99F, "", CuisinesType.ITALIAN),
            new MainCourse(4,"Dumplings", 10.99F, "Available with: meat, goat cheese, cabbage and blueberries.", CuisinesType.POLISH),
            new MainCourse(5,"Pork Chop with Potatoes", 22.99F, "", CuisinesType.POLISH),
            new MainCourse(6,"Burrito", 8.99F, "Available spicy types: mild, medium, hot, extra hot", CuisinesType.MEXICAN),
            new MainCourse(7,"Spicy BBQ Beef", 29.99F, "", CuisinesType.MEXICAN)
    );

    public MainCourseRepository() {
        this.menuItems = availableMainCourses;
    }


    public List<MenuItem> getMainCoursesByCuisinesTypeId(int cuisinesId) throws CuisinesNotFoundException {
        Optional<CuisinesType> typeOptional = Arrays.stream(CuisinesType.values())
                .filter(cuisinesType -> cuisinesType.index == cuisinesId)
                .findFirst();

        if (typeOptional.isEmpty()) {
            throw new CuisinesNotFoundException("Cuisines with id: " + cuisinesId + " not found.");
        }

        return this.availableMainCourses.stream()
                .filter(mainCourse -> ((MainCourse) mainCourse).getCuisinesType() == typeOptional.get())
                .collect(Collectors.toList());
    }

}
