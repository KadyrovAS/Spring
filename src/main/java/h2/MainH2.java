package h2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainH2 {
    private static String parseArg(String arg){
        if (arg.length() < 3)
            return null;
        if (arg.substring(0,1).compareTo("[") != 0 ||
                arg.substring(arg.length()-1).compareTo("]") != 0)
            return null;
        return arg.substring(1, arg.length()-1);
    }

    private static int commandBalance(String[] commands, UserAccountService service) throws IOException {
        if (commands.length != 2)
            return -1;
        String arg = parseArg(commands[1]);
        if (arg == null)
            return -1;
        int id = Integer.valueOf(arg);
        int amount;
        try {
            amount = service.balance(id);
        }catch (UnknownAccountException e){
            return -1;
        }
        return amount;
    }

    private static int commandWithdraw(String[] commands, UserAccountService service) throws IOException {
        if (commands.length !=3)
            return -1;
        String arg = parseArg(commands[1]);
        if (arg == null)
            return -1;
        int id=Integer.valueOf(arg);
        arg = parseArg(commands[2]);
        if (arg == null)
            return -1;
        int amount = Integer.valueOf(arg);
        try {
            service.withdraw(id, amount);
        }catch (UnknownAccountException e){
            return -1;
        }catch (NotEnoughMoneyException e){
            return -1;
        }
        System.out.println("Ваша команда выполнена корректно");
        return 0;
    }

    private static int commandDeposit(String[] commands, UserAccountService service) throws IOException  {
        if (commands.length !=3)
            return -1;
        String arg = parseArg(commands[1]);
        if (arg == null)
            return -1;
        int id = Integer.valueOf(arg);
        arg = parseArg(commands[2]);
        int amount = Integer.valueOf(arg);
        try {
            service.deposit(id, amount);
        }catch (UnknownAccountException e){
            return -1;
        }
        System.out.println("Ваша команда выполнена корректно");
        return 0;
    }

    private static int commandTransfer(String[] commands, UserAccountService service) throws IOException {
        if (commands.length !=4)
            return -1;
        String arg = parseArg(commands[1]);
        if (arg == null)
            return -1;
        int idFrom = Integer.valueOf(arg);
        arg = parseArg(commands[2]);
        if (arg == null)
            return -1;
        int idTo = Integer.valueOf(arg);
        arg = parseArg(commands[3]);
        if (arg == null)
            return -1;
        int amount = Integer.valueOf(arg);
        try {
            service.transfer(idFrom, idTo, amount);
        }catch (UnknownAccountException e){
            return -1;
        }catch (NotEnoughMoneyException e){
            return -1;
        }
        System.out.println("Ваша команда выполнена корректно");
        return 0;
    }

    private static int parsLine(String line, UserAccountService service) throws IOException, UnknownAccountException, NotEnoughMoneyException {
        String[] commands = line.split(" ");
        if (commands[0].toUpperCase().compareTo("BALANCE") == 0){
            int amount = commandBalance(commands, service);
            if (amount < 0)
                return -1;
            System.out.println("Баланс: " + amount);
            return 0;
        }
        else if (commands[0].toUpperCase().compareTo("WITHDRAW") == 0){
            return commandWithdraw(commands, service);
        }
        else if (commands[0].toUpperCase().compareTo("DEPOSIT") == 0){
            return commandDeposit(commands, service);
        }
        else if (commands[0].toUpperCase().compareTo("TRANSFER") == 0){
            return commandTransfer(commands, service);
        }
        else
            return -1;
    }

    public static void main(String[] args) throws IOException, UnknownAccountException, NotEnoughMoneyException {
        ApplicationContext context = new ClassPathXmlApplicationContext("contextAccountService.xml");
        UserAccountService service = (UserAccountService) context.getBean("accountService");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        service.store.read().forEach(System.out::println);

        while (true){
           System.out.print("Введите команду: ");
           String line = bufferedReader.readLine();
           if (line.trim().toUpperCase().compareTo("EXIT") == 0)
               break;
           if (parsLine(line, service) != 0)
               System.out.println("Вы ошиблись при вводе команды.");
           else
               service.store.write(service.store.read());
       }
    }
}
