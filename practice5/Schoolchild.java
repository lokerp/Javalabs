import java.util.*;

public class Schoolchild extends Learner {
    private ArrayList<Olympiad> olympiads = new ArrayList<>();

    public Schoolchild(Person p) {
        super(p);
    }

    public List<Olympiad> getOlympiads() {
        return Collections.unmodifiableList(olympiads);
    }

    public void removeOlympiad(int index) throws IndexOutOfBoundsException{
        olympiads.remove(index);
    }

    public void addOlympiad(Olympiad olympiad){
        olympiads.add(olympiad);
    }

    @Override
    public boolean hasScholarship() {
        try {
            if (getLessonScore(LessonTitle.Math) != 5
             || getLessonScore(LessonTitle.Russian) != 5
             || getLessonScore(LessonTitle.History) != 5
             || getLessonScore(LessonTitle.English) != 5)
                return false;
        }
        catch (Exception e){
            return false;
        }
        return olympiads.stream().anyMatch(x -> x.getLvl() == OlympiadLvl.Regional
                                             || (x.getLvl() == OlympiadLvl.City && x.getPlace() <= 3
                                             || (x.getLvl() == OlympiadLvl.School && x.getPlace() == 1)));
    }
}
