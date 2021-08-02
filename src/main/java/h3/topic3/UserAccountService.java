package h3.topic3;

import java.io.IOException;
import java.util.function.Predicate;

public class UserAccountService implements AccountService {
    Store store;
    public UserAccountService(Store store) throws IOException {
        this.store = store;
    }

    @Override
    public void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, IOException {
        Account account = getAccount(accountId);
        if (account.amount < amount)
            throw new NotEnoughMoneyException("Недостаточно денег на счете");
        account.amount -= amount;
    }

    @Override
    public int balance(int accountId) throws UnknownAccountException, IOException {
        return getAccount(accountId).amount;
    }

    @Override
    public void deposit(int accountId, int amount) throws UnknownAccountException, IOException {
        Account account = getAccount(accountId);
        account.amount += amount;
    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException, IOException {
        Account accountFrom = getAccount(from);
        Account accountTo = getAccount(to);
        if (accountFrom.amount < amount)
            throw new NotEnoughMoneyException("Недостаточно денег на счете");
        accountFrom.amount -= amount;
        accountTo.amount += amount;
    }

    private Account getAccount(int accountId) throws UnknownAccountException, IOException {
        Predicate<Object>preLambda=x->{
            Account account = (Account)x;
            return account.id == accountId;
        };

        Object account;
        try {
            account = this.store.read().stream().filter(preLambda).findAny().get();
        } catch (Throwable e) {
            throw new UnknownAccountException("Аккаунт с id=" + accountId + " в базе данных не найден!");
        }

        return (Account) account;
    }

}