import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IBANValidator IBANValidator = new IBANValidator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu:" +
                " \n1. Select file to check valid IBAN numbers." +
                " \n2. Check if your account number matches LT IBAN standard." +
                " \n3. Exit program.");
        boolean isNumeric = false;
        while (!isNumeric)
            try {
                System.out.println("Enter menu option: ");
                int codeNumber = scanner.nextInt();

                switch (codeNumber) {

                    case 1:
                        System.out.println("Please first enter root location of a file you want to scan," +
                                "ex: F:\\IBANValidator\\src\\test\\ \n" +
                                "then press enter and specify it's name, including type, ex: test.txt " +
                                "file will be created in the same " +
                                "location with suffix \".out\".");
                        IBANValidator.FileScannerForValidIBANs(scanner.next(), scanner.next());

                        break;
                    case 2:
                        System.out.println("Please enter an account number you want to test.");
                        IBANValidator.IBANCheck(scanner.next());
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Please select valid option.");
                        continue;
                }
                scanner.nextLine();
                isNumeric = true;
            } catch (InputMismatchException ime) {
                System.out.println("Invalid character found");
                scanner.nextLine();
            }


    }
}
