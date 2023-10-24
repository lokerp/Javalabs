public class Person {
    private String name;
    private String surname;

    public Person(String name, String surname) throws IllegalArgumentException {
        if (name.isEmpty() || !Character.isUpperCase(name.charAt(0)))
            throw new IllegalArgumentException("Некорректное имя");
        if (surname.isEmpty() || !Character.isUpperCase(surname.charAt(0)))
            throw new IllegalArgumentException("Некорректное имя");
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals(name.toLowerCase()))
            throw new IllegalArgumentException("Некорректное имя");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

