import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Main {
    private static final String[] MALENAMES = {
            "Вася",
            "Володя",
            "Серега",
            "Макс",
            "Матвей",
            "Егор",
            "Санек",
            "Владислав",
    };

    private static final String[] FEMALENAMES = {
            "Анастасия",
            "Елизавета",
            "Виолетта",
            "Анна",
            "Вероника",
            "Ольга"
    };

    private static final LessonTitle[] SCHOOLCHILDDEFAULTLESSONS = {
            LessonTitle.Russian,
            LessonTitle.English,
            LessonTitle.Math,
            LessonTitle.History,
            LessonTitle.Physics
    };

    private static final LessonTitle[] STUDENTDEFAULTLESSONS = {
            LessonTitle.Russian,
            LessonTitle.Algebra,
            LessonTitle.History,
            LessonTitle.Calculus,
            LessonTitle.CS
    };

    private static final int[] SCHOOLCHILDAGERANGE = {7, 18};
    private static final int[] STUDENTAGERANGE = {18, 30};
    private static final int[] MARKSRANGE = {4, 5};

    private static final RandomGenerator randomGenerator = RandomGenerator.getDefault();

    public static void main(String[] args) {
        var schoolchilds = generateSchoolchilds();
        var students = generateStudents();

        System.out.println("Сгенерированные школьники: ");
        for (int i = 0; i < schoolchilds.size(); i++) {
            System.out.println(i + 1 + ": " + schoolchilds.get(i).toString());
        }

        System.out.println("Сгенерированные студенты: ");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i + 1 + ": " + students.get(i).toString());
        }

        System.out.println("Девочки, получившие первые места на олимпиадах: ");
        for (int i = 0; i < schoolchilds.size(); i++){
            var schoolchild = schoolchilds.get(i);
            if (schoolchild.getGender() == Gender.Female
                && schoolchild.getOlympiads().stream().anyMatch(o -> o.getPlace() == 1))
                System.out.println(i + 1 + ": " + schoolchild);
        }

        System.out.println("Студенты, имеющие оценки за курсовые работы: ");
        for (int i = 0; i < students.size(); i++){
            var student = students.get(i);
            if (!student.getCourseWorks().isEmpty())
                System.out.println(i + 1 + ": " + student);
        }

        var learners = new ArrayList<Learner>(schoolchilds.size() + students.size());

        for (Learner s : schoolchilds) if (s.hasScholarship()) learners.add(s);
        for (Learner s: students) if (s.hasScholarship()) learners.add(s);

        System.out.println("Массив учащихся со стипендией: ");
        System.out.println(learners);
    }

    public static ArrayList<Student> generateStudents() {
        var people = generatePeople(STUDENTAGERANGE);
        var students = new ArrayList<Student>();
        for (Person p : people) {
            var student = new Student(p);
            student.setLessons(generateLessons(STUDENTDEFAULTLESSONS, MARKSRANGE));

            var courseWorksNum = randomGenerator.nextInt(0, 2);
            for (int i = 0; i < courseWorksNum; i++)
                student.addCourseWork(generateLesson(LessonTitle.CourseWork, MARKSRANGE));

            students.add(student);
        }
        return students;
    }

    public static ArrayList<Schoolchild> generateSchoolchilds() {
        var people = generatePeople(SCHOOLCHILDAGERANGE);
        var schoolchilds = new ArrayList<Schoolchild>();
        for (Person p : people) {
            var schoolchild = new Schoolchild(p);
            schoolchild.setLessons(generateLessons(SCHOOLCHILDDEFAULTLESSONS, MARKSRANGE));

            var olympiadsNum = randomGenerator.nextInt(0, 4);
            for (int i = 0; i < olympiadsNum; i++)
                schoolchild.addOlympiad(new Olympiad(OlympiadLvl.values()[randomGenerator.nextInt(0, OlympiadLvl.values().length)],
                                                     randomGenerator.nextInt(1, 10)));

            schoolchilds.add(schoolchild);
        }
        return schoolchilds;
    }

    public static ArrayList<Person> generatePeople(int[] ages) {
        var numToGen = randomGenerator.nextInt(5, 20);
        var people = new ArrayList<Person>(numToGen);

        for (int i = 0; i < numToGen; i++) {
            var randomGender = Gender.values()[randomGenerator.nextInt(0, Gender.values().length)];
            var randomName = randomGender == Gender.Male ? MALENAMES[randomGenerator.nextInt(0, MALENAMES.length)]
                                                         : FEMALENAMES[randomGenerator.nextInt(0, FEMALENAMES.length)];
            var randomAge = randomGenerator.nextInt(ages[0], ages[1]);
            people.add(new Person(randomName, randomGender, (short) randomAge));
        }

        return people;
    }

    public static ArrayList<Lesson> generateLessons(LessonTitle[] lessonsTypes, int[] marksRange) {
        var lessons = new ArrayList<Lesson>();
        for (LessonTitle lessonsType : lessonsTypes)
            lessons.add(generateLesson(lessonsType, marksRange));
        return lessons;
    }

    public static Lesson generateLesson(LessonTitle lessonType, int[] marksRange) {
        return new Lesson(lessonType,
                (byte) randomGenerator.nextInt(marksRange[0], marksRange[1] + 1));
    }
}
