import java.util.Comparator;

public class DishComplexityComparator implements Comparator<Dish> {

    @Override
    public int compare(Dish o1, Dish o2) {
        int i = Integer.compare(o1.getIngredients().size(), o2.getIngredients().size());
        if (i != 0)
            return i;
        return Integer.compare(o1.getGoodsPreparationTimeInMinutes() + o1.getDishPreparationTimeInMinutes(),
                               o2.getGoodsPreparationTimeInMinutes() + o2.getDishPreparationTimeInMinutes());

    }
}
