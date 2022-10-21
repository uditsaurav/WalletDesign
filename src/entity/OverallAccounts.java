package entity;

import java.util.ArrayList;
import java.util.List;

public class OverallAccounts {
    List<AccountHolder> accountList;

    public OverallAccounts() {
        this.accountList = new ArrayList<>();
    }

    public List<AccountHolder> getAccountList() {
        return accountList;
    }
}
