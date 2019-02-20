import java.math.BigInteger;

public class IBANUtilities {

    private final static int IBAN_LT_LENGTH = 20;
    private final static BigInteger MODULUS = new BigInteger("97");

    protected static boolean testIBAN(String IBAN) {
        String tempIBAN = IBAN.trim().replaceAll("\\s+", "");

        if (tempIBAN.length() != IBAN_LT_LENGTH) return false;

        tempIBAN = tempIBAN.substring(4) + tempIBAN.substring(0, 4);

        StringBuilder transformedIBAN = new StringBuilder();

        int intValue;
        char[] chars = tempIBAN.toCharArray();
        for (Character character :
                chars) {

            intValue = Character.getNumericValue(character);
            if (intValue <= -1) return false;
            else transformedIBAN.append(intValue);
        }
        BigInteger IBANNumber = new BigInteger(transformedIBAN.toString());
        return IBANNumber.mod(MODULUS).intValue() == 1;
    }
}