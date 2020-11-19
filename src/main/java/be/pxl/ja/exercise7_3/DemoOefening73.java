package be.pxl.ja.exercise7_3;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoOefening73 {
    public static void main(String[] args) {

        List<Student> studentList = StudentDao.createStudents();
        //LocalDate today = LocalDate.now();
        //System.out.println(today);

        //Welke studenten zijn er vandaag jarig. Toon hun naam. Je verandert best een
        //geboortedata van de studenten om je stream uit te testen.
        System.out.println("1. studenten, die vandaag jarig zijn");

        List<Student> todayBirthdayList = studentList.stream()
                .filter(s-> s.getDateOfBirth().getMonth()==LocalDate.now().getMonth() && s.getDateOfBirth().getDayOfMonth() == LocalDate.now().getDayOfMonth())
                .collect(Collectors.toList());

                //.forEach(Student::getName);
        //todayBirthdayList.forEach(System.out::println);
        todayBirthdayList.stream().map(Student::getName).forEach(System.out::println);


//********************************************************************************************
        //Toon alle gegevens van de student met de naam Carol.
        System.out.println();
        System.out.println("2. alle gegevens van de student met de naam Carol");
        // List<Student> forCarol = StudentDao.createStudents();

        List<Student> nameCarol = StudentDao.createStudents().stream()
                .filter(student -> student.getName().equals("Carol"))
                .collect(Collectors.toList());
        nameCarol.forEach(System.out::println);

//********************************************************************************************
        //Hoeveel studenten studeerden af in 2017?
        System.out.println();
        System.out.println("3. Hoeveel studenten studeerden af in 2017?");

        List<Student> students2017 = StudentDao.createStudents();

        int graduated2017 = (int) students2017.stream()
                .filter(student -> student.getGraduationYear()==2017)
                .count();
        System.out.println(graduated2017);


//********************************************************************************************
        //• Wat is de hoogste score ooit behaald? Wie behaalde deze hoogste score ooit?
        System.out.println();
        System.out.println("4. hoogste score ooit behaald");

        List<Student> forMaxScore = StudentDao.createStudents();

        forMaxScore.stream()
                .sorted()
                .limit(1)
                .forEach(System.out::println);

//********************************************************************************************
        //• Wie is de oudste persoon in de lijst?     ??????????
        System.out.println();
        System.out.println("5. oudste persoon in de lijst");

        List<Student> oudsteStudent = StudentDao.createStudents();
//        Stream<Object> objectStream = oudsteStudent.stream()
//                .flatMap(Student -> Student.getDateOfBirth().getYear().stream())
//                .mapToInt();
        Stream<Integer> maxAge = oudsteStudent.stream()
                .map(s -> s.getDateOfBirth().getYear())
                .sorted()
                .limit(1);

        maxAge.forEach(System.out::println);
        
//        StudentDao.createStudents().stream()
//                .map(Student::getDateOfBirth)
//                .sorted()
//                .min();


//********************************************************************************************
        //• Geef de namen van alle studenten gescheiden door komma (,) in e´en String. ´
        System.out.println();
        System.out.println("6. namen van alle studenten");

        List<Student> studentsNames = StudentDao.createStudents();

        String names= studentsNames.stream()
                .map(Student::getName)
                //.map(s -> s.concat(", "))
                .collect(Collectors.joining(", "));
        System.out.println(names);


//********************************************************************************************
        //• Wie is de jongste student van afstudeerjaar 2018?
        System.out.println();
        System.out.println("7. de jongste student van afstudeerjaar 2018");
        
        Optional<LocalDate> jongstestudent2018Dateofbirth = StudentDao.createStudents().stream()
                .filter(student -> student.getGraduationYear() == 2018)
                .map(Student::getDateOfBirth)
                .min(LocalDate::compareTo);

        LocalDate testDate = jongstestudent2018Dateofbirth.get(); // check

//        List<Student> st = StudentDao.createStudents().stream()
//                .filter(s -> s.getDateOfBirth()== jongsteStudent2018DateOfBirth.get())
//                .forEach(System.out::println);
//                //.forEach(System.out::println);



//********************************************************************************************
        //• Maak een map met per afstudeerjaar de gemiddelde score.
        System.out.println();
        System.out.println("8. map met per afstudeerjaar de gemiddelde score.");

        Stream<Integer> integerStream = StudentDao.createStudents().stream()
                .filter(s -> s.getGraduationYear() == 2017)
                .map(Student::getScore);
        //.flatMapToInt(Student::getScore);
        //integerStream.average();

        //System.out.println("" + average);

//********************************************************************************************
        //• Sorteer de studenten eerst op afstudeerjaar (recentste jaar eerst) en per afstudeerjaar volgens hun score
        // (van hoog naar laag). Toon naam, afstudeerjaar en score.
        System.out.println();
        System.out.println("9 . Gesoorterd op jaar en score:");

        StudentDao.createStudents().stream()
                .sorted(new ComparatorGradYear())
                //.sorted(Student::compareTo)
                .forEach(student -> System.out.printf("%s %s %d \n", student.getName(),student.getGraduationYear(),student.getScore()));


    }
}

class ComparatorGradYear implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getGraduationYear()-o2.getGraduationYear();
    }
}
