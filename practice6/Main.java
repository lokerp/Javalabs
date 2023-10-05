import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Main {

    private static final int[] NUMTOGENERATERANGE = {5, 20};
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
    private static final int[] SCHOOLCHILDSCHOOLNUMRANGE = {1, 5};

    private static final RandomGenerator randomGenerator = RandomGenerator.getDefault();

    public static void main(String[] args) {
        var schoolchildren = generateSchoolchildren();
        var students = generateStudents();

        System.out.println("Сгенерированные школьники: ");
        schoolchildren.forEach(Main::customListPrint);

        System.out.println("_".repeat(20));

        System.out.println("Сгенерированные студенты: ");
        students.forEach(Main::customListPrint);

        System.out.println("_".repeat(20));

        var learners = new ArrayList<Learner>(schoolchildren.size() + students.size());
        for (Learner s : schoolchildren) if (s.hasScholarship()) learners.add(s);
        for (Learner s: students) if (s.hasScholarship()) learners.add(s);
        learners.sort(Person::compareTo);

        System.out.println("Массив учащихся со стипендией, отсортированный по именам: ");
        System.out.println(learners);

        System.out.println("_".repeat(20));

        var bestSchoolchild = schoolchildren.stream().max(new PerformanceLearnerComparator<>()).orElse(null);
        var bestStudent = students.stream().max(new PerformanceLearnerComparator<>()).orElse(null);
        if (bestSchoolchild != null)
            System.out.println("Лучший по успеваемости школьник: " + bestSchoolchild);
        if (bestStudent != null)
            System.out.println("Лучший по успеваемости студент: " + bestStudent);

        System.out.println("_".repeat(20));

        var sortedSchoolchildren = schoolchildren.stream().sorted(new PerformanceLearnerComparator<Schoolchild>()
                                                              .thenComparing(Schoolchild::getSchoolNum));
        System.out.println("Список школьников, отсортированный по успеваемости и номеру школы: ");
        sortedSchoolchildren.forEach(Main::customListPrint);

        System.out.println("_".repeat(20));

        var sortedStudents = students.stream().sorted(new PerformanceLearnerComparator<>());
        System.out.println("Список студентов, отсортированный по успеваемости: ");
        sortedStudents.forEach(Main::customListPrint);
    }

    public static void customListPrint(Object x) {
        System.out.println("- " + x);
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

    public static ArrayList<Schoolchild> generateSchoolchildren() {
        var people = generatePeople(SCHOOLCHILDAGERANGE);
        var schoolchildren = new ArrayList<Schoolchild>();
        for (Person p : people) {
            var schoolNum = randomGenerator.nextInt(SCHOOLCHILDSCHOOLNUMRANGE[0], SCHOOLCHILDSCHOOLNUMRANGE[1] + 1);
            var schoolchild = new Schoolchild(p, schoolNum);
            schoolchild.setLessons(generateLessons(SCHOOLCHILDDEFAULTLESSONS, MARKSRANGE));

            var olympiadsNum = randomGenerator.nextInt(0, 4);
            for (int i = 0; i < olympiadsNum; i++)
                schoolchild.addOlympiad(new Olympiad(OlympiadLvl.values()[randomGenerator.nextInt(0, OlympiadLvl.values().length)],
                                                     randomGenerator.nextInt(1, 10)));

            schoolchildren.add(schoolchild);
        }
        return schoolchildren;
    }

    public static ArrayList<Person> generatePeople(int[] ages) {
        var numToGen = randomGenerator.nextInt(NUMTOGENERATERANGE[0], NUMTOGENERATERANGE[1] + 1);
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
