package h1.topic1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("contextCalculator.xml");
        ICalculator calculator = (ICalculator) context.getBean("calculator");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите арифметическую операцию: ");
        String line = bufferedReader.readLine(); //читаем строку с клавиатуры


        System.out.println(CalculateArithmeticLine.calculate(calculator, line));
    }
}