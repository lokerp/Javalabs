import java.util.EnumSet;

public enum CardinalDirection {
    NORTH("Север"),
    SOUTH("Юг"),
    EAST("Восток"),
    WEST("Запад"),
    CENTRAL("Центр");

    private final String nameInRussian;
    CardinalDirection(String nameInRussian) {
        this.nameInRussian = nameInRussian;
    }
    private String getNameInRussian() {
        return nameInRussian;
    }
    public static CardinalDirection parse(String str) throws IllegalArgumentException {
        str = str.strip().toLowerCase();
        for (CardinalDirection v : CardinalDirection.values())
            if (str.equals(v.name().toLowerCase()) || str.equals(v.nameInRussian.toLowerCase()))
                return v;
        throw new IllegalArgumentException("Can't parse cardinal direction from " + str);
    }

    public static boolean isSideCorrect(EnumSet<CardinalDirection> side) {
        return !(side == null || side.isEmpty() || side.size() > 2
                || (side.size() == 2 && side.contains(CardinalDirection.CENTRAL))
                || (side.contains(CardinalDirection.NORTH) && side.contains(CardinalDirection.SOUTH))
                || (side.contains(CardinalDirection.EAST) && side.contains(CardinalDirection.WEST)));
    }

    @Override
    public String toString() {
        return nameInRussian;
    }
}
