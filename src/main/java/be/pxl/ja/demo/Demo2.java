package be.pxl.ja.demo;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Demo2 {
    public static void main(String[] args) {
        IntStream.rangeClosed(0,10).forEach(System.out::println);

        OptionalInt max = IntStream.rangeClosed(0, 10).max();

        if(max.isPresent()) System.out.println(max.getAsInt());
    }
}
