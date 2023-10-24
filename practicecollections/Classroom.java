import java.util.*;

public class Classroom {
    private byte classNum;
    private final ArrayList<Student> students;

    public Classroom(byte classNum) throws IllegalArgumentException {
        if (classNum < 1 || classNum > 11)
            throw new IllegalArgumentException("Некорректный номер класса");
        this.classNum = classNum;
        students = new ArrayList<>();
    }

    public byte getClassNum() {
        return classNum;
    }

    public void setClassNum(byte classNum) {
        this.classNum = classNum;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudentsWithScore(byte score) {
        return students.stream().filter(s -> s.getLesson().getScore() == score).toList();
    }

    public double getAvgStudentsScore() throws NoSuchElementException {
        return students.stream().mapToDouble(s -> s.getLesson().getScore()).average().orElseThrow();
    }

    @Override
    public String toString() {
        return "Класс " + classNum + ". Ученики:\n" + ListExtension.listToString(students);
    }

}
