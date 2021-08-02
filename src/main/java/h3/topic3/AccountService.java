package h3.topic3;

import java.io.IOException;

public interface AccountService{
    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, IOException;
    int balance(int accountId) throws UnknownAccountException, IOException;
    void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, IOException;
    void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException, IOException;
}