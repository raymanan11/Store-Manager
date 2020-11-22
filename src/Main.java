import com.google.gson.Gson;

public class Main {

    static class Student {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }

    public static void main(String[] args) {

        Student student = new Student("Raymond", 20);

        Gson gson = new Gson();

        String json = gson.toJson(student);

        System.out.println(json);
        System.out.println();

        Student student2 = gson.fromJson(json, Student.class);

        System.out.println(student2.name);
        System.out.println(student2.age);

    }

}

