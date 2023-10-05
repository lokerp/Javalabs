import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

abstract public class Learner extends Person {
    private HashMap<LessonTitle, Lesson> lessons;
    abstract public boolean hasScholarship();

    public Learner() {
        super();
        lessons = new HashMap<>();
    }

    public Learner(Person p) {
        super(p.getName(), p.getGender(), p.getAge());
        lessons = new HashMap<>();
    }
    public Learner(String name, Gender gender, short age) {
        super(name, gender, age);
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
}
