import java.util.Objects;

public class Olympiad {
    private OlympiadLvl lvl;
    private int place;

    public Olympiad(OlympiadLvl lvl, int place) throws IllegalArgumentException {
        this.lvl = lvl;
        if (place <= 0)
            throw new IllegalArgumentException("Некорректное место в олимпиаде");
        this.place = place;
    }

    public OlympiadLvl getLvl() {
        return lvl;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) throws IllegalArgumentException {
        if (place <= 0)
            throw new IllegalArgumentException("Некорректное место в олимпиаде");
        this.place = place;
    }

    @Override
    public String toString() {
        return "Olympiad{" +
                "lvl=" + lvl +
                ", place=" + place +
                '}';
    }
}
