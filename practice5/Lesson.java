import java.util.Objects;

public class Lesson {
    private final LessonTitle title;
    private byte score;

    public Lesson(LessonTitle title, byte score) throws IllegalArgumentException {
        this.title = title;
        if (score < 1 || score > 5)
            throw new IllegalArgumentException("Некорректная оценка за предмет");
        this.score = score;
    }

    public LessonTitle getTitle() {
        return title;
    }

    public byte getScore() {
        return score;
    }

    public void setScore(byte score) throws IllegalArgumentException {
        if (score < 0 || score > 5)
            throw new IllegalArgumentException("Некорректная оценка за предмет");
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return title == lesson.title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
