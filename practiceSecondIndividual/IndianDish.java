import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class IndianDish extends Dish {
    private final String state;
    private final EnumSet<CardinalDirection> stateWorldSide;

    public IndianDish(String name,
                      List<String> ingredients,
                      Boolean isVegetarian,
                      int goodsPreparationTime,
                      int dishPreparationTime,
                      Taste taste,
                      Type type,
                      String state,
                      EnumSet<CardinalDirection> stateWorldSide)
    {
        super(name, ingredients, isVegetarian, goodsPreparationTime, dishPreparationTime, taste, type);
        this.state = state;
        this.stateWorldSide = stateWorldSide;
    }

    public static IndianDish parseFromStrings(List<String> data) throws IllegalArgumentException {
        if (data == null || data.size() != 9)
            throw new IllegalArgumentException("Incorrect data: data is empty");

        Boolean isVegetarian = null;
        if (data.get(2).equalsIgnoreCase("vegetarian"))
            isVegetarian = true;
        else if (data.get(2).equalsIgnoreCase("non vegetarian"))
            isVegetarian = false;
        else if (!data.get(2).equals("-1"))
            throw new IllegalArgumentException("Incorrect data: no info about vegetarian");

        int goodsPreparationTimeInMinutes = -1;
        try {
            goodsPreparationTimeInMinutes = Integer.parseInt(data.get(3));
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Incorrect data: no info about goods preparation time");
        }

        int dishPreparationTimeInMinutes = -1;
        try {
            dishPreparationTimeInMinutes = Integer.parseInt(data.get(4));
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Incorrect data: no info about goods preparation time");
        }

        Taste taste = Taste.NOTSTATED;
        if (!data.get(5).equals("-1")) {
            try {
                taste = Taste.parse(data.get(5));
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Incorrect data: no info about taste");
            }
        }

        Dish.Type type = Type.NOTSTATED;
        if (!data.get(6).equals("-1")) {
            try {
                type = Type.parse(data.get(6));
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Incorrect data: no info about type");
            }
        }

        EnumSet<CardinalDirection> stateWorldSide = null;
        if (!data.get(8).equals("-1")) {
            try {
                var sidesString = data.get(8).split(" ");
                List<CardinalDirection> sides = new ArrayList<>();
                for (String sideString : sidesString)
                    sides.add(CardinalDirection.parse(sideString));
                stateWorldSide = EnumSet.copyOf(sides);
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Incorrect data: no info about state world side");
            }
            if (!CardinalDirection.isSideCorrect(stateWorldSide))
                throw new IllegalArgumentException("Incorrect data: incorrect info about state world side");
        }

        String name = data.get(0);
        if (data.get(0).equals("-1"))
            name = "NOTSTATED";
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Incorrect data: incorrect name");

        List<String> ingredients = Arrays.stream(data.get(1).replaceAll(",", " ")
                                                            .replaceAll(" +", " ")
                                                            .split(" ")).toList();
        if (ingredients.isEmpty())
            throw new IllegalArgumentException("Incorrect data: ingredients is empty");
        if (ingredients.get(0).equals("-1"))
            ingredients = null;


        String state = data.get(7);
        if (data.get(7).equals("-1"))
            state = "NOTSTATED";
        if (state == null || state.isBlank())
            throw new IllegalArgumentException("Incorrect data: incorrect state");

        return new IndianDish(name,
                              ingredients,
                              isVegetarian,
                              goodsPreparationTimeInMinutes,
                              dishPreparationTimeInMinutes,
                              taste,
                              type,
                              state,
                              stateWorldSide);

    }

    public String getState() {
        return state;
    }

    public EnumSet<CardinalDirection> getStateWorldSide() {
        return stateWorldSide;
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
                ", Индийский штат=" + state +
                ", Сторона света=" + stateWorldSide +
                "}";
    }
}
