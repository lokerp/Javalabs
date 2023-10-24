public class Student extends Person {
    private Classroom classroom;
    private Lesson lesson;

    public Student(String name, String surname, Classroom classroom, Lesson lesson) throws IllegalArgumentException {
        super(name, surname);
        if (classroom == null || lesson == null)
            throw new IllegalArgumentException("classroom and lesson can't be null");
        this.classroom = classroom;
        this.lesson = lesson;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return  "Имя: " + getName() +
                ", Фамилия: " + getSurname() +
                ", Класс: " + classroom.getClassNum() +
                ", Предмет: " + lesson.getTitle().getTitleInRussian() +
                ", Оценка: " + lesson.getScore();
    }
}
