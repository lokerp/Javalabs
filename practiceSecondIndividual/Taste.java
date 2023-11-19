public enum Taste {
    SPICY("Острый"),
    SWEET("Сладкий"),
    BITTER("Горький"),
    SOUR("Кислый");

    private final String nameInRussian;
    Taste(String nameInRussian) {
        this.nameInRussian = nameInRussian;
    }
    private String getNameInRussian() {
        return nameInRussian;
    }
    public static Taste parse(String str) throws IllegalArgumentException {
        str = str.strip().toLowerCase();
        for (Taste v : Taste.values())
            if (str.equals(v.name().toLowerCase()) || str.equals(v.nameInRussian.toLowerCase()))
                return v;
        throw new IllegalArgumentException("Can't parse taste from " + str);
    }

    @Override
    public String toString() {
        return nameInRussian;
    }
}
