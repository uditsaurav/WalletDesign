package service;

import entity.AccountHolder;
import entity.OfferType;
import entity.OverallAccounts;
import entity.Transaction;
import entity.TransactionType;

import java.util.HashMap;
import java.util.Objects;

public class WalletService {

    OverallAccounts overallAccounts = new OverallAccounts();
    private static HashMap<Integer, AccountHolder> userAccountMap = new HashMap<>();

    // CreateWallet <accountHolder1> <amount>
    public void createWallet(Integer userId, Double initialAmount) {
        AccountHolder accountHolder = new AccountHolder(userId, initialAmount);
        userAccountMap.put(userId, accountHolder);
        overallAccounts.getAccountList().add(accountHolder);
        System.out.println("Account successfully created for userId " + userId);
    }

    // TransferMoney <accountHolder1> <accountHolder2> <amount>
    public void transferMoney(Integer senderId, Integer receiverId, Double txnAmount) {
        AccountHolder senderAccount = getAccountDetail(senderId);
        AccountHolder receiverAccount = getAccountDetail(receiverId);
        if (receiverAccount == null || senderAccount.getBalance() <= 0) {
            System.out.println("Not Possible");
        } else {
            senderAccount.setBalance(senderAccount.getBalance() - txnAmount);
            receiverAccount.setBalance(receiverAccount.getBalance() + txnAmount);

            Transaction transaction = createTransactionData(senderId, receiverId, txnAmount, TransactionType.USER_TO_USER, OfferType.NONE);
            senderAccount.getTransactionList().add(transaction);
            receiverAccount.getTransactionList().add(transaction);

            if (Objects.equals(receiverAccount.getBalance(), senderAccount.getBalance())) {
                senderAccount.setBalance(senderAccount.getBalance() + 10D);
                receiverAccount.setBalance(receiverAccount.getBalance() + 10D);

                Transaction transaction1 = createTransactionData(senderId, receiverId, 10D, TransactionType.COMPANY_TO_USER, OfferType.OFFER1);
                senderAccount.getTransactionList().add(transaction1);
                receiverAccount.getTransactionList().add(transaction1);
            }

            System.out.println(txnAmount + " amount successfully transferred from " + senderId + " to  " + receiverId);
        }
    }

    private Transaction createTransactionData(Integer senderId, Integer receiverId, Double txnAmount, TransactionType userToUser, OfferType offerType) {
        Transaction transaction = new Transaction();
        transaction.setSenderId(senderId);
        transaction.setReceiverId(receiverId);
        transaction.setAmountInvolved(txnAmount);
        transaction.setTransactionTime(System.currentTimeMillis());
        transaction.setTransactionType(userToUser);
        transaction.setOfferType(offerType);
        return transaction;
    }

    private AccountHolder getAccountDetail(Integer senderId) {
        return userAccountMap.get(senderId);
    }

    // Statement <accountHolder1>
    public void displayStatement(Integer userId) {
        AccountHolder accountHolder = getAccountDetail(userId);
        System.out.println("Transactions of user: " + userId);
        for (Transaction t : accountHolder.getTransactionList()) {
            System.out.print(t.getSenderId() + " ");
            System.out.print(t.getReceiverId() + " ");
            System.out.print(t.getAmountInvolved() + " ");
            System.out.print(t.getTransactionTime() + " ");
            System.out.print(t.getOfferType() + " ");
            System.out.println(t.getTransactionType() + " ");

        }
    }

    // overview
    public void displayOverview() {
        for (AccountHolder accountHolder : overallAccounts.getAccountList()) {
            System.out.println(accountHolder.getUserId() + " " + accountHolder.getBalance());
        }
    }

    public void offerTwoApplied() {
        for (AccountHolder accountHolder : overallAccounts.getAccountList()) {
            System.out.println(accountHolder);
        }
    }
}
