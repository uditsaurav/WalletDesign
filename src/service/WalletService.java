package service;

import entity.AccountHolder;
import entity.OfferType;
import entity.OverallAccounts;
import entity.Transaction;
import entity.TransactionType;

import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;

public class WalletService {

    private static final OverallAccounts overallAccounts = new OverallAccounts();
    private static final HashMap<Integer, AccountHolder> userAccountMap = new HashMap<>();
    private static final Integer companyId = 9090;
    private static final PriorityQueue<AccountHolder> priorityQueue = new PriorityQueue<>((o1, o2) -> {
        if (o1.getTransactionList().size() < o2.getTransactionList().size()) {
            return 1;
        } else if (o1.getTransactionList().size() > o2.getTransactionList().size())
            return -1;
        return 0;
    });

    // CreateWallet <accountHolder1> <amount>
    public void createWallet(Integer userId, Double initialAmount) {
        AccountHolder accountHolder = new AccountHolder(userId, initialAmount);
        userAccountMap.put(userId, accountHolder);
        overallAccounts.getAccountList().add(accountHolder);
        priorityQueue.add(accountHolder);
        System.out.println("Account successfully created for userId " + userId);
    }

    // TransferMoney <accountHolder1> <accountHolder2> <amount>
    public void transferMoney(Integer senderId, Integer receiverId, Double txnAmount) {
        AccountHolder senderAccount = getAccountDetail(senderId);
        AccountHolder receiverAccount = getAccountDetail(receiverId);

        priorityQueue.remove(senderAccount);
        priorityQueue.remove(receiverAccount);

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

                Transaction transaction1 = createTransactionData(companyId, senderId, 10D, TransactionType.COMPANY_TO_USER, OfferType.OFFER1);
                Transaction transaction2 = createTransactionData(companyId, receiverId, 10D, TransactionType.COMPANY_TO_USER, OfferType.OFFER1);
                senderAccount.getTransactionList().add(transaction1);
                receiverAccount.getTransactionList().add(transaction2);
            }

            System.out.println(txnAmount + " amount successfully transferred from " + senderId + " to  " + receiverId);
            priorityQueue.add(senderAccount);
            priorityQueue.add(receiverAccount);
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

    // Overview
    public void displayOverview() {
        System.out.println("userId \t balances");
        for (AccountHolder accountHolder : overallAccounts.getAccountList()) {
            System.out.println(accountHolder.getUserId() + " " + accountHolder.getBalance());
        }
    }

    // Offer2
    public void offerTwoApplied() {
        System.out.println("userId \t noOfTransaction");
        for (AccountHolder accountHolder : priorityQueue) {
            System.out.print(accountHolder.getUserId() + "\t");
            System.out.println(accountHolder.getTransactionList().size());
        }

        AccountHolder accountHolder1 = priorityQueue.remove();
        accountHolder1.setBalance(accountHolder1.getBalance() + 15D);
        Transaction transaction1 = createTransactionData(companyId, accountHolder1.getUserId(), 15D, TransactionType.COMPANY_TO_USER, OfferType.OFFER2);
        accountHolder1.getTransactionList().add(transaction1);

        AccountHolder accountHolder2 = priorityQueue.remove();
        accountHolder2.setBalance(accountHolder2.getBalance() + 10D);
        Transaction transaction2 = createTransactionData(companyId, accountHolder2.getUserId(), 10D, TransactionType.COMPANY_TO_USER, OfferType.OFFER2);
        accountHolder2.getTransactionList().add(transaction2);

        AccountHolder accountHolder3 = priorityQueue.remove();
        accountHolder3.setBalance(accountHolder3.getBalance() + 5D);
        Transaction transaction3 = createTransactionData(companyId, accountHolder3.getUserId(), 5D, TransactionType.COMPANY_TO_USER, OfferType.OFFER2);
        accountHolder3.getTransactionList().add(transaction3);

        priorityQueue.add(accountHolder1);
        priorityQueue.add(accountHolder2);
        priorityQueue.add(accountHolder3);

        System.out.println("Offer2 Applied !!");
    }
}
