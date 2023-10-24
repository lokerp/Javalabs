public enum LessonTitle {
    Informatics("Информатика"),
    Algebra("Алгебра"),
    Geometry("Геометрия"),
    Russian("Русский"),
    Geography("География"),
    ForeignLanguage("Иностранный"),
    Literature("Литература"),
    PE("Физкультура"),
    History("История"),
    Ecology("Экология"),
    SocialStudies("Обществознание"),
    Biology("Биология"),
    Chemistry("Химия"),
    Physics("Физика");

    private String titleInRussian;

    LessonTitle(String titleInRussian) {
        this.titleInRussian = titleInRussian;
    }

    public String getTitleInRussian() {
        return titleInRussian;
    }

    public static LessonTitle parseFromRussian(String str) {
        str = str.strip().toLowerCase();
        for (LessonTitle lt : LessonTitle.values())
            if (str.equals(lt.getTitleInRussian().toLowerCase()))
                return lt;
        throw new IllegalArgumentException("Can't parse lesson title from " + str);
    }
}
