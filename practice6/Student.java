import java.util.*;

public class Student extends Learner {
    private ArrayList<Lesson> courseWorks = new ArrayList<>();

    public Student(Person p) {
        super(p);
    }

    public List<Lesson> getCourseWorks() {
        return Collections.unmodifiableList(courseWorks);
    }

    public void addCourseWork(Lesson lesson) {
        if (lesson.getTitle() != LessonTitle.CourseWork)
            throw new IllegalArgumentException("Попытка добавить некурсовую работу");
        courseWorks.add(lesson);
    }

    public void setCourseWorks(ArrayList<Lesson> courseWorks) {
        if (courseWorks.stream().anyMatch(x -> x.getTitle() != LessonTitle.CourseWork))
            throw new IllegalArgumentException("Попытка добавить некурсовую работу");
        this.courseWorks = courseWorks;
    }


    @Override
    public boolean hasScholarship() {
        try {
            if (getLessons().stream().mapToInt(Lesson::getScore).average().getAsDouble() >= 4.75
                && courseWorks.stream().anyMatch(c -> c.getScore() == 5))
                return true;
        }
        catch (Exception ignored) { }
        return false;
    }

    @Override
    public double getPerformance() {
        var lessons = this.lessons.values().stream().toList();;
        return lessons.stream().mapToInt(Lesson::getScore).average().orElse(0) * lessons.size();
    }

    @Override
    public String toString() {
        return super.toString() + " Student{" +
                "courseWorks=" + courseWorks +
                '}';
    }
}
