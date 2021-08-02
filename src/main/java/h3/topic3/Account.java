package h3.topic3;

public class Account {
    int id; //уникальный идентификатор счета
    String holder; //владелец счета
    int amount; //сумма на счете

    public Account(int id, String holder, int amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", holder='" + holder + '\'' +
                ", amount=" + amount +
                '}';
    }
}