package h1.topic1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class CalculateArithmeticLine{
    //Парсит строку line, обрабатывая рекурсивно операции в скобках
//Учитывает очередность операций
    public static int calculate(ICalculator calculator, String line){
        String operationLine = line;



        //ищем  скобки
        ArrayList<Integer> leftBracketPosition = new ArrayList<>();
        ArrayList<Integer> rightBracketPosition = new ArrayList<>();
        int tempArg = 0;
        while (true) {
            leftBracketPosition.clear();
            rightBracketPosition.clear();
            for (int i = 0; i < operationLine.length(); i++) {
                if (operationLine.substring(i, i + 1).compareTo("(") == 0)
                    leftBracketPosition.add(i);
                if (operationLine.substring(i, i + 1).compareTo(")") == 0)
                    rightBracketPosition.add(i);
                if (leftBracketPosition.size()!=0 && leftBracketPosition.size() == rightBracketPosition.size())
                    break;
            }
            if (leftBracketPosition.size() == 0)
                break;
            if (leftBracketPosition.size() != rightBracketPosition.size())
                throw new RuntimeException("Некорректная арифметическая запись со скобками");
            int n = rightBracketPosition.size() - 1;
            if (leftBracketPosition.get(0) > rightBracketPosition.get(n))
                throw new RuntimeException("Некорректная арифметическая запись со скобками");

            tempArg = calculate(calculator,
                    operationLine.substring(leftBracketPosition.get(0) + 1, rightBracketPosition.get(n)));
            operationLine = operationLine.substring(0, leftBracketPosition.get(0)) +
                    tempArg + operationLine.substring(rightBracketPosition.get(n) + 1);
        }

        //выполняем умножение и деление
        while (true){
            Integer operatorPosition = null;
            for (int i = 0; i < operationLine.length(); i ++){
                if (operationLine.substring(i, i + 1).compareTo("*") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("/") == 0) {
                    operatorPosition = i;
                    break;
                }
            }
            if (operatorPosition == null)
                break;
            int leftPosition = 0;
            int rightPosition = operationLine.length();
            for (int i = operatorPosition - 1; i >=0; i --){
                if (operationLine.substring(i, i + 1).compareTo("+") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("-") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("*") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("/") == 0) {
                    leftPosition = i + 1;
                    break;
                }
            }
            for (int i = operatorPosition + 1; i < operationLine.length(); i ++){
                if (operationLine.substring(i, i + 1).compareTo("+") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("-") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("*") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("/") == 0) {
                    rightPosition = i;
                    break;
                }
            }
            String arg1 = operationLine.substring(leftPosition, operatorPosition).trim();
            String arg2 = operationLine.substring(operatorPosition + 1, rightPosition).trim();
            if (operationLine.substring(operatorPosition, operatorPosition + 1).compareTo("*") == 0)
                tempArg = calculator.mult(Integer.valueOf(arg1), Integer.valueOf(arg2));
            else if (operationLine.substring(operatorPosition, operatorPosition + 1).compareTo("/") == 0)
                tempArg = calculator.div(Integer.valueOf(arg1), Integer.valueOf(arg2));
            operationLine = operationLine.substring(0,leftPosition) + tempArg +
                    operationLine.substring(rightPosition);
        }

        //выполняем сложение и вычитание
        while (true){
            Integer operatorPosition = null;
            for (int i = 0; i < operationLine.length(); i ++){
                if (operationLine.substring(i, i + 1).compareTo("+") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("-") == 0) {
                    operatorPosition = i;
                    break;
                }
            }
            if (operatorPosition == null)
                break;
            int leftPosition = 0;
            int rightPosition = operationLine.length();
            for (int i = operatorPosition - 1; i >=0; i --){
                if (operationLine.substring(i, i + 1).compareTo("+") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("-") == 0) {
                    leftPosition = i + 1;
                    break;
                }
            }
            for (int i = operatorPosition + 1; i < operationLine.length(); i ++){
                if (operationLine.substring(i, i + 1).compareTo("+") == 0 ||
                        operationLine.substring(i, i + 1).compareTo("-") == 0) {
                    rightPosition = i;
                    break;
                }
            }
            String arg1 = operationLine.substring(leftPosition, operatorPosition).trim();
            String arg2 = operationLine.substring(operatorPosition + 1, rightPosition).trim();
            if (operationLine.substring(operatorPosition, operatorPosition + 1).compareTo("+") == 0)
                tempArg = calculator.sum(Integer.valueOf(arg1), Integer.valueOf(arg2));
            else if (operationLine.substring(operatorPosition, operatorPosition + 1).compareTo("-") == 0)
                tempArg = calculator.diff(Integer.valueOf(arg1), Integer.valueOf(arg2));
            operationLine = operationLine.substring(0,leftPosition) + tempArg +
                    operationLine.substring(rightPosition);
        }


        return tempArg;
    }

}
