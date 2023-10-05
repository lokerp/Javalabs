import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

abstract public class Learner extends Person {
    protected HashMap<LessonTitle, Lesson> lessons = new HashMap<>();
    abstract public boolean hasScholarship();

    public Learner(Person p) {
        super(p.getName(), p.getGender(), p.getAge());
        lessons = new HashMap<>();
    }

    public Learner(String name, Gender gender, short age, List<Lesson> initLessons) {
        super(name, gender, age);
        setLessons(initLessons);
    }

    public Lesson removeLesson(LessonTitle lessonTitle){
        return lessons.remove(lessonTitle);
    }

    public void setLessonScore(LessonTitle lesson, byte score) throws IllegalArgumentException {
        Lesson curLesson = lessons.getOrDefault(lesson, new Lesson(lesson, score));
        lessons.put(lesson, curLesson);
    }

    public byte getLessonScore(LessonTitle lesson) throws NoSuchElementException {
        Lesson curLesson = lessons.get(lesson);
        if (curLesson == null || curLesson.getScore() == 0)
            throw new NoSuchElementException("У обучающегося нет оценки за этот предмет");
        return curLesson.getScore();
    }

    public List<Lesson> getLessons() {
        return lessons.values().stream().toList();
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = new HashMap<>(lessons.size());
        for (Lesson lesson : lessons)
            this.lessons.put(lesson.getTitle(), lesson);

    }
    public double getPerformance() {
        var lessons = getLessons();
        return lessons.stream().mapToInt(Lesson::getScore).average().orElse(0) * lessons.size();
    }

    @Override
    public String toString() {
        return super.toString() + " Learner{" +
                                    "performance=" + getPerformance() +
                                    '}';
    }
}
