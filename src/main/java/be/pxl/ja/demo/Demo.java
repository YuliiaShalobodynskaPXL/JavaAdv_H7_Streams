package be.pxl.ja.demo;

import be.pxl.ja.exercise7_3.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 2018, 82, LocalDate.of(1998,5,4)));
        students.add(new Student("Bob", 2017, 90, LocalDate.of(1998,8,8)));
        students.add(new Student("Carol", 2018, 67, LocalDate.of(1997, 12,23)));
        students.add(new Student("David", 2018, 80, LocalDate.of(1997, 3,23)));
        students.add(new Student("Eric", 2017, 55, LocalDate.of(1998, 4,15)));
        students.add(new Student("Frank", 2018, 49, LocalDate.of(1996, 10,11)));
        students.add(new Student("Gary", 2017,88, LocalDate.of(1996, 7,12)));
        students.add(new Student("Henry", 2017, 98, LocalDate.of(1997, 8,12)));
        students.add(new Student("Ivan", 2018, 66, LocalDate.of(1999, 3,21)));
        students.add(new Student("John", 2017, 52, LocalDate.of(1997, 1,3)));

        /*List<Student> newList = new ArrayList<>();
        for (Student student : students){
            if(student.getGraduationYear()==2017 && student.getScore()>=70){
                newList.add(student);
            }
        }
        System.out.println(newList);*/

        List<Student> newStudentList = students.stream()
                .filter(s -> s.getGraduationYear()==2017)
                .filter(s->s.getScore()>=70)
                .collect(Collectors.toList());


        newStudentList.forEach(System.out::println);

        //long count = newStudentList.stream().count();

        students.stream()
                .sorted((x,y) -> x.getScore() - y.getScore())
                .limit(3)
                .forEach(System.out::println);

    }
}
