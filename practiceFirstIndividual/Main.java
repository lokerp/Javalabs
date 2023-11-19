import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final String ZODIAC_SIGNS_COMPATIBILITY_PATH = "practiceFirstIndividual/zodiac_signs_compatibility.txt";

        Table<Byte, ZodiacSign> compatibilityTable = null;
        try {
            compatibilityTable = readSignsCompatibilityFromFile(ZODIAC_SIGNS_COMPATIBILITY_PATH);
        } catch (Exception e) {
            System.out.println("(" + ZODIAC_SIGNS_COMPATIBILITY_PATH + ") " + e.getMessage());
            System.exit(-1);
        }

        System.out.print("Введите дату рождения (вида 2007-12-23): ");
        LocalDate birthDate = null;
        try {
            birthDate = LocalDate.parse(scanner.next());
        }
        catch (DateTimeException e) {
            System.out.println("Некорректная дата рождения");
            System.exit(-1);
        }

        System.out.print("Укажите ваш пол (0 - мужской, 1 - женский): ");
        Gender gender = null;
        try {
            gender = Gender.values()[scanner.nextByte()];
        }
        catch (Exception e) {
            System.out.println("Некорректный ввод");
            System.exit(-1);
        }

        var allSuitableSigns = ZodiacSign.getSuitableByDate(birthDate);
        if (allSuitableSigns.size() == 2) {
            System.out.println("Для вас нашлось 2 соответствующих знака: " +
                                allSuitableSigns.get(0).getNameInRussian() + " и " +
                                allSuitableSigns.get(1).getNameInRussian());
            System.out.print("Выберите один из них (0 - " + allSuitableSigns.get(0).getNameInRussian() +
                                                 ", 1 - " + allSuitableSigns.get(1).getNameInRussian() +
                                                 ", -1 - отказаться от выбора): ");
            byte choice = 0;
            try {
                choice = scanner.nextByte();
            }
            catch (Exception e) {
                System.out.println("Некорректный ввод");
                System.exit(-1);
            }
            if (choice == 0)
                allSuitableSigns.remove(1);
            else if (choice == 1)
                allSuitableSigns.remove(0);
            else if (choice != -1) {
                System.out.println("Некорректный ввод");
                System.exit(-1);
            }
        }
        for (ZodiacSign sign : allSuitableSigns) {
            System.out.println("*".repeat(10));
            System.out.println("Ваш знак - " + sign.getNameInRussian());
            System.out.println(sign);
            var compatibilitySortedSigns = getSignsSortedByCompatibility(compatibilityTable, sign, gender);
            System.out.println("Наиболее подходящие знаки: ");
            for (int i = 0; i < 3; i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(compatibilitySortedSigns.get(i).getNameInRussian());
            }
            System.out.println("*".repeat(10));
        }

        scanner.nextLine();
        System.out.print("Напишите название знака, о котором желаете узнать больше: ");
        ZodiacSign preferredSign = null;
        try {
             var title = scanner.nextLine();
             preferredSign = ZodiacSign.parseFromRussian(title);
        }
        catch (Exception e) {
            System.out.println("Некорректный ввод");
            System.exit(-1);
        }
        System.out.println(preferredSign);

        for (ZodiacSign sign : allSuitableSigns) {
            System.out.println("*".repeat(10));
            System.out.println(sign.getNameInRussian());
            var compatibilitySortedSigns = getSignsSortedByCompatibility(compatibilityTable, sign, gender);
            System.out.println("Следующие подходящие знаки: ");
            for (int i = 2; i < 5; i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(compatibilitySortedSigns.get(i).getNameInRussian());
            }
            System.out.println("Самые неподходящие знаки: ");
            for (int i = 9; i < 12; i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(compatibilitySortedSigns.get(i).getNameInRussian());
            }
            System.out.println("*".repeat(10));
        }
    }

//    public static List<ZodiacSign> readZodiacSignsFromFile(String fileName)
//            throws InputMismatchException,
//            IOException
//    {
//        BufferedReader reader = new BufferedReader(new FileReader(fileName));
//        int lineCount = 0;
//        var zodiacSigns = new HashSet<ZodiacSign>(12);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");
//
//        while (reader.ready()) {
//            String line = reader.readLine();
//            lineCount++;
//
//            line = line.replaceAll("/\\*.*?\\*/", "").strip();
//            if (line.isEmpty())
//                continue;
//
//            var words = Arrays.stream(line.split(" ", 4)).toList();
//            if (words.size() != 4)
//                throw new IllegalArgumentException("Incorrect input in line " + lineCount);
//
//            String zodiacName = null;
//            LocalDate startTime = null;
//            LocalDate endTime = null;
//            String description = null;
//            try {
//                zodiacName = words.get(0);
//                startTime = LocalDate.parse(words.get(1), formatter);
//                endTime = LocalDate.parse(words.get(2), formatter);
//                description = words.get(0);
//            }
//            catch (Exception e) {
//                throw new InputMismatchException("Incorrect input in line: " + lineCount);
//            }
//
//            var zodiacSign = new ZodiacSign(zodiacName, startTime, endTime, description);
//            if (zodiacSigns.contains(zodiacSign))
//                throw new InputMismatchException("Incorrect input in line: " + lineCount + ". Repeated sign");
//
//            zodiacSigns.add(zodiacSign);
//        }
//        if (zodiacSigns.size() != 12)
//            throw new InputMismatchException("Signs count is not equal 12!");
//        var zodiacSignsList = zodiacSigns.stream().toList();
//
//        Period period = Period.ZERO;
//        for (ZodiacSign z : zodiacSignsList) {
//            period.plus(Period.between(z.startTime(), z.endTime().plusDays(1)));
//        }
//        if (period.getYears() < 1)
//            throw new InputMismatchException("Signs are not covering the year!");
//
//        return zodiacSignsList;
//    }

    public static Table<Byte, ZodiacSign> readSignsCompatibilityFromFile(String fileName)
            throws InputMismatchException,
            IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int lineCount = 0;

        Table<Byte, ZodiacSign> compatibilityTable;
        var compatibilityTableBuilder = new TableBuilder<Byte, ZodiacSign>();

        boolean isFirstLine = true;

        while (reader.ready()) {
            String line = reader.readLine();
            lineCount++;

            line = line.replaceAll("/\\*.*?\\*/", "").strip();
            if (line.isEmpty())
                continue;

            if (isFirstLine) {
                isFirstLine = false;
                List<ZodiacSign> signs;
                try {
                    signs = Arrays.stream(line.split(" ")).map(ZodiacSign::parseFromRussian).toList();
                }
                catch (IllegalArgumentException e) {
                    throw new InputMismatchException("Incorrect input in line " + lineCount + ". " + e);
                }
                if (signs.size() != 12)
                    throw new InputMismatchException("Incorrect input in line " + lineCount + ". Signs count should be 12");
                if (new HashSet<>(signs).size() != 12)
                    throw new InputMismatchException("Incorrect input in line " + lineCount + ". Signs are repeating");
                compatibilityTableBuilder.setRowTags(signs);
                compatibilityTableBuilder.setColumnTags(signs);
            }
            else {
                List<Byte> probs;
                try {
                    probs = Arrays.stream(line.split(" ")).filter(s -> !s.isEmpty()).map(Byte::parseByte).toList();
                } catch (NumberFormatException e) {
                    throw new InputMismatchException("Incorrect input in line " + lineCount + ". Probabilities are not correct");
                }
                if (probs.size() != 12)
                    throw new IllegalArgumentException("Incorrect input in line " + lineCount);
                compatibilityTableBuilder.addRow(probs);
            }
        }

        try {
            compatibilityTable = compatibilityTableBuilder.build();
        }
        catch (InputMismatchException e) {
            throw new InputMismatchException("Can't build compatibility table: " + e);
        }
        reader.close();
        return compatibilityTable;
    }

    public static List<ZodiacSign> getSignsSortedByCompatibility(Table<Byte, ZodiacSign> compatibilityTable,
                                                                 ZodiacSign sign, Gender gender)
    {
        var probs = new HashMap<ZodiacSign, Byte>(12);
        if (gender == Gender.Male) {
            var column = compatibilityTable.getColumn(sign);
            var columnTags = compatibilityTable.getColumnTags();
            for (int i = 0; i < column.size(); i++)
                probs.put(columnTags.get(i), column.get(i));
        }
        else if (gender == Gender.Female) {
            var row = compatibilityTable.getRow(sign);
            var rowTags = compatibilityTable.getRowTags();
            for (int i = 0; i < rowTags.size(); i++)
                probs.put(rowTags.get(i), row.get(i));
        }
        var sortedList = new ArrayList<>(probs.entrySet().stream().sorted((x, y) -> Byte.compare(x.getValue(), y.getValue())).map(Map.Entry::getKey).toList());
        Collections.reverse(sortedList);
        return sortedList;
    }
}
