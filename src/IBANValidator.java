import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

class IBANValidator {
    private File folder;
    private HashMap<String, String> IBANResults = new HashMap<>();

    void IBANCheck(String accountNr) {
        if (IBANUtilities.testIBAN(accountNr)) {
            System.out.println("Your account number is valid, you can try logging in.");
        } else {
            System.out.println("Account number is not valid or it doesn't match Lithuanian IBAN standard.");
        }
    }

    void FileScannerForValidIBANs(String inputFilePath, String inputFileName) {
        String fullLinkToFile = inputFilePath.concat(inputFileName);
        folder = new File(fullLinkToFile);
        try (Stream<String> stream = Files.lines(Paths.get(String.valueOf(folder)))) {
            stream.filter(x -> x.length()!=0).forEach(x -> {
                        if (IBANUtilities.testIBAN(x)) {
                            IBANResults.put(x, "TRUE");
                        }
                        else {
                            IBANResults.put(x, "FALSE");
                        }
                        try {
                            Files.write(Paths.get(fullLinkToFile.replaceAll("\\.[^.]*", ".out")), () -> IBANResults.entrySet().stream()
                                    .<CharSequence>map(e -> e.getKey() + ";" + e.getValue())
                                    .iterator());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
