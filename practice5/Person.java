public class Person {
    private String name;
    private Gender gender;
    private short age;

    public Person() {
        this.name = null;
        this.gender = null;
        this.age = -1;
    }

    public Person(String name, Gender gender, short age) throws IllegalArgumentException {
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
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}

