import java.util.*;

public class Schoolchild extends Learner {
    private int schoolNum = 0;
    private ArrayList<Olympiad> olympiads = new ArrayList<>();

    public Schoolchild(Person p, int schoolNum) throws IllegalArgumentException {
        super(p);
        setSchoolNum(schoolNum);
    }

    public int getSchoolNum() throws NoSuchElementException {
        if (schoolNum == 0)
            throw new NoSuchElementException("Номер школы не инициализирован");
        return schoolNum;
    }

    public void setSchoolNum(int schoolNum) throws IllegalArgumentException {
        if (schoolNum < 1)
            throw new IllegalArgumentException("Некорректный номер школы");
        this.schoolNum = schoolNum;
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

    @Override
    public String toString() {
        return super.toString() + " Schoolchild{" +
                                    "schoolNum=" + schoolNum +
                                    ", olympiads=" + olympiads +
                                    '}';
    }
}
