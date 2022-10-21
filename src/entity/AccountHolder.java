package entity;

import java.util.ArrayList;
import java.util.List;

public class AccountHolder {
    Integer userId;
    Double balance;
    List<Transaction> transactionList;
    Long createdTime;

    public AccountHolder(int userId, Double balance) {
        this.userId = userId;
        this.balance = balance;
        setTransactionList(new ArrayList<>());
        setCreatedTime(System.currentTimeMillis());
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public int getUserId() {
        return userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
