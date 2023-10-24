import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final String FILEREADNAME = "practicecollections/data_school.txt";
        final String FILEWRITEBASECLASSROOMNAME = "practicecollections/class_.txt";
        final String FILEWRITEBASESTATEMENTNAME = "practicecollections/statement_class_.txt";

        List<Classroom> classes = null;

        try {
            classes = getClassesFromFile(FILEREADNAME);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
            System.exit(-1);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка при считывании файла: " + e.getMessage());
            System.exit(-1);
        }

        if (classes.isEmpty()) {
            System.out.println("Файл пуст");
            System.exit(-1);
        }

        try {
            for (var c : classes)
                writeClassToFile(FILEWRITEBASECLASSROOMNAME, c);
            System.out.println("Классы успешно записаны в файлы!");
        }
        catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
            System.exit(-1);
        }

        System.out.println("_".repeat(20));

        System.out.println("Журнал");
        classes.forEach(System.out::println);

        System.out.println("_".repeat(20));

        System.out.println("Введите оценку, учеников с которой хотите посмотреть: ");
        byte score = 0;
        try {
            score = scanner.nextByte();
            if (score < 2 || score > 6) {
                System.out.println("Ошибка ввода: оценка может принимать значение от 2 до 5");
                System.exit(-1);
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
            System.exit(-1);
        }
        System.out.println("Список учеников с заданной оценкой по классам: ");
        for (var c : classes) {
            var studentsWithScore = c.getStudentsWithScore(score);
            if (!studentsWithScore.isEmpty())
                System.out.println("Класс " + c.getClassNum() + ":\n" +
                        ListExtension.listToString(c.getStudentsWithScore(score)));
        }

        System.out.println("_".repeat(20));

        System.out.println("Классы, отсортированные по средней успеваемости:");
        classes.stream().sorted(Comparator.comparingDouble(Classroom::getAvgStudentsScore))
                        .forEach(c -> System.out.println("Класс " + c.getClassNum() + ". Средняя успеваемость: " + c.getAvgStudentsScore()));

        System.out.println("_".repeat(20));

        System.out.println("Введите предмет, учеников с которым хотите посмотреть: ");
        try {
            LessonTitle lessonTitle = LessonTitle.parseFromRussian(scanner.next());
            System.out.println("Список учеников всех классов с предметом " + lessonTitle.getTitleInRussian() +
                               ", отсортированный по фамилиям: ");
            classes.stream().flatMap(c -> c.getStudents().stream())
                            .filter(s -> s.getLesson().getTitle() == lessonTitle)
                            .sorted(Comparator.comparing(Person::getSurname))
                            .forEach(System.out::println);
        }
        catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
            System.exit(-1);
        }

        System.out.println("_".repeat(20));

        System.out.println("Введите номер класса, по которому хотите составить ведомость: ");
        byte number = 0;
        try {
            number = scanner.nextByte();
            if (number < 1 || number > 11) {
                System.out.println("Ошибка ввода: номер класса может принимать значение от 1 до 11");
                System.exit(-1);
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
            System.exit(-1);
        }
        byte finalNumber = number;
        var foundClass = classes.stream().filter(c -> c.getClassNum() == finalNumber).findAny().orElse(null);
        if (foundClass == null)
            System.out.println("Класс не был найден в журнале!");
        else {
            try {
                writeClassStatementToFile(FILEWRITEBASESTATEMENTNAME, foundClass);
                System.out.println("Ведомость была успешно записана в файл!");
            }
            catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }


        System.out.println("Введите имя и фамилию ученика через пробел, класс которого хотите найти: ");
        scanner.nextLine();
        var string = scanner.nextLine();
        var nameAndSurname = string.split(" ");
        if (nameAndSurname.length != 2) {
            System.out.println("Ошибка ввода!");
            System.exit(-1);
        }
        try {
            var foundClasses = findClassroomsByStudentName(classes, nameAndSurname[0].strip(), nameAndSurname[1].strip());
            if (foundClasses.size() == 1)
                System.out.println("Найденный класс: " + foundClasses.get(0).getClassNum());
            else {
                System.out.print("Нашлось несколько классов, где учатся подходящие ученики: ");
                foundClasses.forEach(c -> System.out.print(c + " "));
                System.out.println();
            }
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        System.out.println("_".repeat(20));

        System.out.print("Предмет с самой высокой средней успеваемостью: ");
        try {
            var bestLesson = classes.stream().flatMap(c -> c.getStudents().stream().map(Student::getLesson))
                    .collect(Collectors.groupingBy(Lesson::getTitle, Collectors.averagingInt(Lesson::getScore)))
                    .entrySet().stream().max(Comparator.comparingDouble(l -> l.getValue())).orElseThrow().getKey();
            System.out.println(bestLesson.getTitleInRussian());
        }
        catch (NoSuchElementException e) {
            System.out.println("Ошибка! Предмет не был найден :(");
            System.exit(-1);
        }

    }

    public static List<Classroom> getClassesFromFile(String fileName)
            throws InputMismatchException,
            IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int lineCount = 0;

        HashMap<Byte, Classroom> classes = new HashMap<Byte, Classroom>();

        while (reader.ready()) {
            String line = reader.readLine();
            lineCount++;

            String surname = null;
            String name = null;
            Classroom classroom = null;
            LessonTitle lessonTitle = null;
            byte score = 0;

            Lesson lesson = null;
            Student student = null;

            String[] words = line.strip().replaceAll(" +", " ").split(" ");
            if (words.length != 5)
                throw new InputMismatchException("Incorrect input in line " + lineCount);

            surname = words[0];
            name = words[1];

            try {
                classroom = new Classroom(Byte.parseByte(words[2]));
                lessonTitle = LessonTitle.parseFromRussian(words[3]);
                score = Byte.parseByte(words[4]);
                lesson = new Lesson(lessonTitle, score);
                student = new Student(name, surname, classroom, lesson);
            } catch (Exception e) {
                throw new InputMismatchException("Incorrect input in line " + lineCount);
            }

            Classroom mapClass = classes.getOrDefault(classroom.getClassNum(), null);
            if (mapClass == null)
                classes.put(classroom.getClassNum(), classroom);
            else
                mapClass.addStudent(student);
        }
        reader.close();

        return classes.values().stream().toList();
    }

    public static void writeClassToFile(String fileName, Classroom classroom) throws IOException {
        fileName = fileName.replace(".", classroom.getClassNum() + ".");

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write(classroom.toString());

        writer.close();
    }

    public static void writeClassStatementToFile(String fileName, Classroom classroom) throws IOException {
        fileName = fileName.replace(".", classroom.getClassNum() + ".");

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write("Класс " + classroom.getClassNum() + "\n");

        var statement = classroom.getStudents().stream().collect(Collectors.groupingBy(s -> s.getLesson().getTitle()));
        var keys = statement.keySet().stream().toList();
        for (int i = 0; i < keys.size(); i++) {
            writer.write("Предмет: " + keys.get(i).getTitleInRussian() + "\n");
            writer.write(ListExtension.listToString(statement.get(keys.get(i))));
            if (i != keys.size() - 1)
                writer.newLine();
        }

        writer.close();
    }

    public static List<Classroom> findClassroomsByStudentName(List<Classroom> classes, String name, String surname) {
        var students = classes.stream().flatMap(c -> c.getStudents().stream())
                .filter(s -> s.getName().equals(name) && s.getSurname().equals(surname)).toList();
        if (students.isEmpty())
            throw new NoSuchElementException("Couldn't find classroom by name: " + name + " and surname: " + surname);
        return students.stream().map(Student::getClassroom).toList();
    }
}
