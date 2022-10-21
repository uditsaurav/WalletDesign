

entities

AccountHolder{
int userId;
Double balance;
List<Transaction> transactionList;
DateTime createdTime;
}

Transaction{
int senderId;
int receiverId;
Double amountInvolved;
DateTime transactionTime;
OfferType offerType;
TransactionType transactionType;
}

AllAccounts{
List<AccountHolder> accountList;
}

OfferType{
OFFER1, OFFER2;
}

TransactionType{
USER, COMPANY;
}

----------------------------------------
class MVPUsers{
int firstUser;
int secondUser;
int thirdUser;
}
