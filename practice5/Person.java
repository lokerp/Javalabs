public class Person implements Comparable<Person> {
    private String name;
    private Gender gender;
    private short age;

    public Person() {
        this.name = null;
        this.gender = null;
        this.age = -1;
    }

    public Person(String name, Gender gender, short age) throws IllegalArgumentException {
        if (name == null || name.equals(name.toLowerCase()))
            throw new IllegalArgumentException("Некорректное имя");
        this.name = name;
        this.gender = gender;
        if (age < 0)
            throw new IllegalArgumentException("Введен некорректный возраст");
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals(name.toLowerCase()))
            throw new IllegalArgumentException("Некорректное имя");
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) throws IllegalArgumentException {
        if (age < 0)
            throw new IllegalArgumentException("Некорректный возраст человека");
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person@" + this.hashCode() + "{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.name);
    }
}

