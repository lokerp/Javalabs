import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

public enum ZodiacSign {
    ARIES("Овен", LocalDate.of(ZodiacSign.YEAR, 3, 21), LocalDate.of(ZodiacSign.YEAR, 4, 20),
            "Овен - амбициозный, независимый, нетерпеливый.\nОвен, знак стихии Огня, открывает новый зодиакальный цикл, относится к стихии Огня, обладает особенной харизмой (качеством) первооткрывателя, инициативой и целеустремленностью. Даже обладающие спокойным темпераментом, Овны никогда не забывают про свои цели и, как правило, рано или поздно достигают желаемого. Инициатива и активность представителей этого знака позволяет находить новые задачи, которые Овен ставит перед своими последователями."),
    TAURUS("Телец", LocalDate.of(ZodiacSign.YEAR, 4, 21), LocalDate.of(ZodiacSign.YEAR, 5, 20),
            "Телец - основательный, музыкальный, практичный.\nФиксированный знак стихии Земли, созидатель и гурман, Телец воплощает собой принцип любви к жизни и ее благам, а также имеет качества упорства и практичности. Телец умеет и любит работать, терпеливо создает себе комфортные условия для жизни. Способен долго и терпеливо ждать созревания подходящих условий. Терпение Тельца удивительно, ему трудно учиться чему-то новому и приспосабливаться к незнакомым условиям."),
    GEMINI("Близнецы", LocalDate.of(ZodiacSign.YEAR, 5, 21), LocalDate.of(ZodiacSign.YEAR, 6, 21),
            "Близнецы - любопытный, нежный, добрый.\nЗнак подвижного креста стихии Воздуха. Близнецы обладают сильным характером, энергичны, независимы и общительны. Коммуникабельны, с веселым характером и темпераментным любопытством. Близнецы легко устанавливают связи со множеством разноплановых людей. Интересные эрудированные собеседники, темпераментные и неутомимые, Близнецы живут активной и насыщенной жизнью, легко приспосабливаются к любой обстановке. Обеспечивают связи и потоки информации между людьми."),
    CANCER("Рак", LocalDate.of(ZodiacSign.YEAR, 6, 22), LocalDate.of(ZodiacSign.YEAR, 7, 22),
            "Рак - интуитивный, эмоциональный, умный, страстный.\nЗнак стихии Воды находится под покровительством ночного светила. Управление Луны влияет на характер представителей этого знака, делая их ранимыми и чувствительными людьми. Луна и водная стихия знака дают Раку способность к эмпатии, возможность мгновенно угадывать мысли и чаяния других людей. Это решительные и благородные люди, часто патриоты. Но если жизнь Рака была полна лишений и несправедливости с детства, то обладают коварством и харизмой гангстера."),
    LEO("Лев", LocalDate.of(ZodiacSign.YEAR, 7, 23), LocalDate.of(ZodiacSign.YEAR, 8, 22),
            "Лев - горделивый, самоуверенный.\nФиксированный знак стихии Огня, Лев обладает даром созидания и настойчивостью в достижении своих целей. Это деятельный человек, стремящийся к успеху и популярности. Сильная, чувствительная и любящая натура, часто попадает под влияние эмоций и чувств. Лев великодушен, решителен и храбр. Самообладание и амбициозность являются сильными чертами этого знака. Не равнодушен к вниманию, лести и роскоши."),
    VIRGO("Дева", LocalDate.of(ZodiacSign.YEAR, 8, 23), LocalDate.of(ZodiacSign.YEAR, 9, 23),
            "Дева - элегантный, организованный, добрый.\nДева — второй знак стихии Земли, олицетворяющий справедливость и чистоту. Дева воплощает принцип порядка, победу разума над чувствами, умение видеть целое в деталях. Дева больше других знаков зодиака стремится к совершенству во всем, учится всю жизнь, но также учит и других. Стремление к лучшему заставляет Деву подмечать изъяны во всем, что ее окружает, что требует исправления."),
    LIBRA("Весы", LocalDate.of(ZodiacSign.YEAR, 9, 24), LocalDate.of(ZodiacSign.YEAR, 10, 23),
            "Весы - дипломатичный, артистичный, интеллигентный.\nЕдинственный неодушевленный символ в зодиакальном круге, Весы являются вторым знаком стихии Воздуха. Отличительной чертой представителей этого знака является стремление к гармонии во всем. Чувствительные к прекрасному, прирожденные дипломаты, обладающие стойкостью духа и несгибаемой волей к победе в любом соперничестве, Весы часто выступают в роли судей, а также юристов на всех уровнях. Постоянство, надежность и созидательная сила — лучшие качества этого знака."),
    SCORPIO("Скорпион", LocalDate.of(ZodiacSign.YEAR, 10, 24), LocalDate.of(ZodiacSign.YEAR, 11, 22),
            "Скорпион - чарующий, страстный, независимый.\nСкорпион фиксированный знак стихии Воды. Скорпион обладает природным магнетизмом и сильным характером. Выносливый, сдержанный в словах и эмоциях, Скорпион умеет хранить секреты и ценит верность. Скорпион — это знак внутренних изменений, преодоления слабости, борьбы до победного конца. Рожденный под этим знаком всю жизнь совершенствуется сам и стремится изменить мир к лучшему."),
    SAGITTARIUS("Стрелец", LocalDate.of(ZodiacSign.YEAR, 11, 23), LocalDate.of(ZodiacSign.YEAR, 12, 21),
            "Стрелец - авантюрный, творческий, волевой.\nСтрелец — знак стихии Огня, обладает ярко выраженной харизмой лидера, стремится к образованию, энергичен и увлечен идеей изменить весь мир. Всю жизнь Стрелец стремится к популярности, к высокой оценке своей работы и достижений со стороны близких людей. Стрелец почти всегда добивается успеха хотя бы в одном из многочисленных видов своей деятельности. У энергичного по натуре Стрельца обычно несколько специальностей, широкий спектр интересов и деловых связей."),
    CAPRICORN("Козерог", LocalDate.of(ZodiacSign.YEAR, 12, 22), LocalDate.of(ZodiacSign.YEAR, 1, 20),
            "Козерог - дотошный, умный, деятельный.\nЗнак Земной стихии, Козерог обладает даром не терять из виду главную цель и долго жить. Целеустремленность, стойкость в трудностях, ответственность — это сильные качества представителей этого знака. Козерог не боится одиночества, готов переносить любые житейские трудности, преодолеть любые препятствия. Свои глубокие чувства предпочитает никому не открывать, с трудом близко сходится с людьми и не любит терять дружеские связи. Если Козерогом кто-то пренебрег, то никогда не прощает и не возвращается."),
    AQUARIUS("Водолей", LocalDate.of(ZodiacSign.YEAR, 1, 21), LocalDate.of(ZodiacSign.YEAR, 2, 18),
            "Водолей - одаренный воображением,\nидеалистический, интуитивный. Знак фиксированного креста стихии Воздуха — Водолей изменчив по натуре, но не любит перемены, полон противоречий. Яркий индивидуалист, Водолей склонен к перепадам настроения, то элегантен, то неряшлив, страдает отсутствием самодисциплины, решителен и обладает ярким темпераментом. Не выносит рутины и скучных обязанностей. Независимость — как главное условие деятельности."),
    PISCES("Рыбы", LocalDate.of(ZodiacSign.YEAR, 2, 19), LocalDate.of(ZodiacSign.YEAR, 3, 20),
            "Рыбы - творческий, чувствительный,\nартистичный. Рыбы закрывают зодиакальный круг, представляя собой знак стихии Воды. Это мудрые и восприимчивые люди, отзывчивость которых часто приводит их к общению с манипуляторами. Подверженность чужому влиянию, высочайшая среди знаков зодиака способность к адаптации в любой окружающей обстановке, стойкость перед житейскими трудностями отличает типичных Рыб.");

    private final static int YEAR = 2000;
    private final static int DAYS_IN_YEAR = 366;

    private final String nameInRussian;
    private final LocalDate startTime;
    private final LocalDate endTime;
    private final String descriptionInRussian;

    ZodiacSign(String nameInRussian, LocalDate startTime, LocalDate endTime, String descriptionInRussian) {
        this.nameInRussian = nameInRussian;
        this.startTime = startTime;
        this.endTime = endTime;
        this.descriptionInRussian = descriptionInRussian;
    }

    public static ZodiacSign parseFromRussian(String str) throws IllegalArgumentException {
        str = str.strip().toLowerCase();
        for (ZodiacSign zs : ZodiacSign.values())
            if (str.equals(zs.getNameInRussian().toLowerCase()))
                return zs;
        throw new IllegalArgumentException("Can't parse zodiac sign from " + str);
    }

    public static List<ZodiacSign> getSuitableByDate(LocalDate date) {
        date = date.withYear(YEAR);

        var signs = new ArrayList<ZodiacSign>();
        var allSigns = ZodiacSign.values();
        for (int i = 0; i < allSigns.length; i++) {
            if (isDateInInterval(date, allSigns[i].startTime, allSigns[i].endTime)) {
                signs.add(allSigns[i]);
                var dateMinus4 = date.minusDays(4).withYear(YEAR);
                var datePlus4 = date.plusDays(4).withYear(YEAR);
                var minusIndex = mod(i - 1, allSigns.length);
                var plusIndex = mod(i + 1, allSigns.length);
                if (isDateInInterval(dateMinus4, allSigns[minusIndex].startTime, allSigns[minusIndex].endTime))
                    signs.add(allSigns[minusIndex]);
                else if (isDateInInterval(datePlus4, allSigns[plusIndex].startTime, allSigns[plusIndex].endTime))
                    signs.add(allSigns[plusIndex]);
                break;
            }
        }

        return signs;
    }

    private static boolean isDateInInterval(LocalDate date, LocalDate start, LocalDate end) {
        return mod(date.getDayOfYear() - start.getDayOfYear(), DAYS_IN_YEAR)
                + mod(end.getDayOfYear() - date.getDayOfYear(), DAYS_IN_YEAR)
                == mod(end.getDayOfYear() - start.getDayOfYear(), DAYS_IN_YEAR);
    }

    private static int mod(int x, int y) {
        int res = x % y;
        if (res < 0)
            res += y;
        return res;
    }

    public String getNameInRussian() {
        return nameInRussian;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public String getDescriptionInRussian() {
        return descriptionInRussian;
    }

    @Override
    public String toString() {
        return "Название: " + nameInRussian + "\n"
             + "Дата начала: " + startTime.getMonth() + " " + startTime.getDayOfMonth() + "\n"
             + "Дата конца: " + endTime.getMonth() + " " + endTime.getDayOfMonth() + "\n"
             + "Описание: " + descriptionInRussian;
    }
}
