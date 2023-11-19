import java.sql.Timestamp;
import java.util.List;

public class Dish {
    public enum Type {
        MAINCOURSE("Основное"),
        DESSERT("Десерт"),
        SNACK("Закуска");
        private final String nameInRussian;
        Type(String nameInRussian) {
            this.nameInRussian = nameInRussian;
        }
        private String getNameInRussian() {
            return nameInRussian;
        }
        public static Type parse(String str) throws IllegalArgumentException {
            str = str.strip().toLowerCase().replaceAll(" ", "");
            for (Type v : Type.values())
                if (str.equals(v.name().toLowerCase()) || str.equals(v.nameInRussian.toLowerCase()))
                    return v;
            throw new IllegalArgumentException("Can't parse dish type from " + str);
        }

        @Override
        public String toString() {
            return nameInRussian;
        }
    }

    protected final String name;
    protected final List<String> ingredients;
    protected final Boolean isVegetarian;
    protected final int goodsPreparationTimeInMinutes;
    protected final int dishPreparationTimeInMinutes;
    protected final Taste taste;
    protected final Type type;

    public Dish(String name,
                List<String> ingredients,
                Boolean isVegetarian,
                int goodsPreparationTimeInMinutes,
                int dishPreparationTime,
                Taste taste,
                Type type)
    {
        this.name = name;
        this.ingredients = ingredients;
        this.isVegetarian = isVegetarian;
        this.goodsPreparationTimeInMinutes = goodsPreparationTimeInMinutes;
        this.dishPreparationTimeInMinutes = dishPreparationTime;
        this.taste = taste;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public int getGoodsPreparationTimeInMinutes() {
        return goodsPreparationTimeInMinutes;
    }

    public int getDishPreparationTimeInMinutes() {
        return dishPreparationTimeInMinutes;
    }

    public Taste getTaste() {
        return taste;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Блюдо " + name +
                "{" +
                "Ингредиенты=" + ingredients +
                ", Вегетарианское=" + isVegetarian +
                ", Время подготовки продуктов в минутах=" + goodsPreparationTimeInMinutes +
                ", Время готовки блюда в минутах=" + dishPreparationTimeInMinutes +
                ", Вкус=" + taste +
                ", Тип=" + type +
                "}";
    }
}
