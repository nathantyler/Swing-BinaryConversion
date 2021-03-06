package convwin;

/*
 * By Nathan Tyler N.
 * Last edit 1-31-2020.
 * */

import org.jetbrains.annotations.*;

import java.math.*;

public class Converter {

    @Deprecated
    public static long getBinaryLength(long n) {
        long quotient = n, i = 0;
        if (n < 0)
            quotient *= -1;

        while (quotient > 1) {
            quotient = quotient / 2;
            i++;
        }
        return i + 1;
    }

    public static String decimalToBinary(long n) {
        StringBuilder binary     = new StringBuilder(n == 0 ? "0" : "");
        boolean       isNegative = n < 0;
        long          quotient   = Math.abs(n);

        while (quotient > 0) {
            binary.insert(0, (quotient % 2));
            quotient = quotient / 2;
        }
        return !isNegative ? binary.toString() : "-" + binary;
    }

    public static String decimalToBinary(@NotNull BigInteger n) {
        BigInteger    quotient   = n.abs();
        boolean       isNegative = n.compareTo(BigInteger.ZERO) < 0;
        StringBuilder binary     = new StringBuilder(n.equals(BigInteger.ZERO) ? "0" : "");

        while (quotient.compareTo(BigInteger.ZERO) > 0) {
            binary.insert(0, quotient.mod(BigInteger.TWO).toString());
            quotient = quotient.divide(BigInteger.TWO);
        }

        return !isNegative ? binary.toString() : "-" + binary;
    }

    public static long binaryToDecimalLong(@NotNull String bin) throws ArithmeticException {
        long    dec        = 0;
        boolean isNegative = bin.charAt(0) == '-';
        String  binAbsVal  = bin.replace("-", "");

        for (int i = 0; i < bin.length(); i++) {
            boolean charAtIisOne = binAbsVal.charAt(i) == '1';
            if (charAtIisOne)
                dec += Math.pow(2, (binAbsVal.length() - 1) - i);
        }
        return isNegative ? Math.negateExact(dec) : dec;
    }

    public static BigInteger binaryToDecimalBigInteger(@NotNull String bin) {
        BigInteger dec        = new BigInteger("0");
        boolean    isNegative = bin.charAt(0) == '-';
        String     binAbsVal  = bin.replace("-", "");

        for (int i = 0; i < binAbsVal.length(); i++) {
            boolean charAtIisOne = binAbsVal.charAt(i) == '1';
            if (charAtIisOne)
                dec = dec.add(BigInteger.TWO.pow((binAbsVal.length() - 1) - i));
        }
        return isNegative ? dec.negate() : dec;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isBinary(@NotNull String bin) {
        if (bin.isEmpty())
            return false;
        return bin.equals(bin.replaceAll("[^01]", ""));
    }
}
