import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final String DATA_INDIAN_DISHES_PATH = "practiceSecondIndividual/data_indian_dishes.txt";
        final String RESULT_PATH = "practiceSecondIndividual/result.txt";
        List<IndianDish> dishes = null;
        try {
            dishes = readIndianDishesFromFile(DATA_INDIAN_DISHES_PATH);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        System.out.print("Напишите желаемый штат (-1 - не выбирать): ");
        String state = scanner.nextLine();

        System.out.println("Выберите желаемую сторону света или их комбинацию через пробел (-1 - не выбирать)");
        System.out.println("0 - Север\n1 - Юг\n2 - Восток\n3 - Запад\n4 - Центр");
        String worldSideString = scanner.nextLine();
        EnumSet<CardinalDirection> stateSide = null;
        if (!worldSideString.equals("-1")) {
            try {
                var sidesString = worldSideString.replaceAll(" +", " ").split(" ");
                List<CardinalDirection> sidesList = Arrays.stream(sidesString)
                        .map(s -> CardinalDirection.values()[Integer.parseInt(s)])
                        .toList();
                stateSide = EnumSet.copyOf(sidesList);
            } catch (Exception e) {
                System.out.println("Некорректный ввод!");
                System.exit(-1);
            }
            if (!CardinalDirection.isSideCorrect(stateSide)) {
                System.out.println("Некорректный ввод!");
                System.exit(-1);
            }
        }

        System.out.println("Выберите желаемый тип блюда (-1 - не выбирать)");
        System.out.println("0 - Основное\n1 - Десерт\n2 - Закуска");
        Dish.Type type = Dish.Type.NOTSTATED;
        String dishTypeString = scanner.nextLine();
        if (!dishTypeString.equals("-1")) {
            try {
                type = Dish.Type.values()[Integer.parseInt(dishTypeString)];
            }
            catch (Exception e) {
                System.out.println("Некорректный ввод!");
                System.exit(-1);
            }
        }

        System.out.print("Напишите название ингредиента, который должен отсутствовать (-1 - не выбирать): ");
        String missingIngredient = scanner.nextLine();

        var filteredDishes = filterDishes(dishes, state, stateSide, type, missingIngredient).stream()
                .sorted((x, y) -> Integer.compare(x.dishPreparationTimeInMinutes, y.dishPreparationTimeInMinutes)).toList();
        System.out.println("Найденные блюда:");
        System.out.println(ListExtension.listToString(filteredDishes));

        var complexitySortedDishes = filteredDishes.stream().sorted(new DishComplexityComparator()).toList();
        var mostComplexDishes = complexitySortedDishes.stream().limit(2).toList();
        var complexitySortedDishesReversed = new ArrayList<>(complexitySortedDishes);
        Collections.reverse(complexitySortedDishesReversed);
        var easiestDishes = complexitySortedDishesReversed.stream().limit(2).toList();
        System.out.println("Самые сложные блюда: ");
        System.out.println(ListExtension.listToString(mostComplexDishes));
        System.out.println("Самые простые блюда: ");
        System.out.println(ListExtension.listToString(easiestDishes));

        Map<String, Map<Dish.Type, String>> groupedDishes = dishes.stream().collect(
                Collectors.groupingBy(IndianDish::getState,
                        Collectors.groupingBy(IndianDish::getType,
                                Collectors.flatMapping(x -> x.getIngredients().stream(),
                                        Collectors.collectingAndThen(Collectors.toList(), Main::getMostCommonItem)))));

        var dishesTable = getDishesTable(groupedDishes);
        System.out.println("Таблица блюд: ");
        System.out.println(dishesTable);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_PATH))){
            writer.write("Найденные блюда:\n");
            writer.write(ListExtension.listToString(filteredDishes) + "\n");
            writer.write("Самые сложные блюда:\n");
            writer.write(ListExtension.listToString(mostComplexDishes));
            writer.write("Самые простые блюда:\n");
            writer.write(ListExtension.listToString(easiestDishes));
            writer.write("Таблица блюд: \n");
            writer.write(dishesTable);
        }
        catch (IOException e) {
            System.out.println("Ошибка при открытии результирующего файла: " + e.getMessage());
        }

    }

    public static List<IndianDish> readIndianDishesFromFile(String fileName) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<IndianDish> dishes = new ArrayList<>();
        List<String> data = new ArrayList<>(9);
        int lineCount = 0;

        while (reader.ready()) {
            String line = reader.readLine();
            lineCount++;
            data.add(line);
            if (data.size() != 9) {
                continue;
            }
            IndianDish dish = null;
            try {
                dish = IndianDish.parseFromStrings(data);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Attention! Error in line " + (lineCount - 8));
            }
            if (dish != null) {
                dishes.add(dish);
                data.clear();
            }
            else
                data.remove(0);
        }
        reader.close();
        return dishes;
    }

    public static List<IndianDish> filterDishes(List<IndianDish> dishes,
                                                String state,
                                                EnumSet<CardinalDirection> stateSide,
                                                Dish.Type type,
                                                String missingIngredient) {
        return dishes.stream().filter(d ->
                (state.equals("-1") || d.getState().equalsIgnoreCase(state)
             && (stateSide == null || (d.getStateWorldSide() != null &&  d.getStateWorldSide().containsAll(stateSide) && stateSide.containsAll(d.getStateWorldSide()))))
             && (type == null || type == Dish.Type.NOTSTATED || d.getType() == type)
             && (missingIngredient.equals("-1") || !d.getIngredients().contains(missingIngredient.toLowerCase())))
                              .toList();
    }

    public static <T> T getMostCommonItem(List<T> list) {
        var map = new HashMap<T, Integer>();
        for (T t : list) {
            map.put(t, map.getOrDefault(t, -1) + 1);
        }
        var maxEntry = map.entrySet().stream().max((x, y) -> Integer.compare(x.getValue(), y.getValue())).orElse(null);
        T max = null;
        if (maxEntry != null)
            max = maxEntry.getKey();
        return max;
    }

    public static String getDishesTable(Map<String, Map<Dish.Type, String>> groupedDishes) {
        StringBuilder sbTable = new StringBuilder();
        var groupedDishesList = groupedDishes.entrySet().stream().toList();
        for (int i = 0; i < groupedDishes.size(); i++) {
            String state = groupedDishesList.get(i).getKey();
            var typeAndIngredientList = groupedDishesList.get(i).getValue().entrySet().stream().toList();
            int padding = state.length() + 1;
            sbTable.append(state).append(" ");
            for (int j = 0; j < typeAndIngredientList.size(); j++) {
                if (j != 0)
                    sbTable.append(" ".repeat(padding));
                var type = typeAndIngredientList.get(j).getKey();
                var mostCommonIngredient = typeAndIngredientList.get(j).getValue();
                sbTable.append(type).append(" ").append(mostCommonIngredient).append("\n");
            }
            sbTable.append("\n");
        }
        return sbTable.toString();
    }
}
