package h3.topic1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainTopic1 {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigCalculator.class);
        ICalculator calculator = context.getBean(ICalculator.class);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите арифметическую операцию: ");
        String line = bufferedReader.readLine(); //читаем строку с клавиатуры

        System.out.println(CalculateArithmeticLine.calculate(calculator, line));
    }
}