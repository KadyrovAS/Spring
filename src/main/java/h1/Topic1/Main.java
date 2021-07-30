package h1.Topic1;

import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите арифметическую операцию: ");
        String line = bufferedReader.readLine(); //читаем строку с клавиатуры


        System.out.println(CalculateArithmeticLine.calculate(line));
    }
}