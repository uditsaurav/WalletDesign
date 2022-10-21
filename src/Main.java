import service.WalletService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WalletService walletService = new WalletService();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String inputStr = scanner.next();
            if("exit".equals(inputStr))
                break;
            switch (inputStr) {
                case "CreateWallet":
                    walletService.createWallet(1234, 200.0);
                    walletService.createWallet(1111, 100.0);
                    walletService.createWallet(2222, 100.0);
                    walletService.createWallet(3333, 100.0);
                    walletService.createWallet(4444, 100.0);
                    break;
                case "TransferMoney":
                    walletService.transferMoney(1234, 1111, 50.0);
                    walletService.transferMoney(1234, 2222, 10.0);
                    walletService.transferMoney(3333, 2222, 50.0);
                    walletService.transferMoney(2222, 4444, 100.0);
                    break;
                case "Statement":
                    walletService.displayStatement(1234);
                    walletService.displayStatement(1111);
                    walletService.displayStatement(2222);
                    walletService.displayStatement(3333);
                    break;
                case "Overview":
                    walletService.displayOverview();
                    break;
                case "Offer2":
                    walletService.offerTwoApplied();
                    break;
                default:
                    System.out.println("Wrong command !!");
            }
        }

    }
}