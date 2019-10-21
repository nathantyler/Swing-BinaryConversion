package convwin;


public class Conversion {
	public static long getBinaryLength(long n) {
		long quotient = n, i = 0;
		if (n < 0)
			quotient += -1;

		while (quotient > 1) {
			quotient = quotient / 2;
			i++;
		}
		return i + 1;
	}

	public static String decimalToBinary(long n) {
		long length = getBinaryLength(n);
		String binaried = "";
		long quotient = n;
		if (n < 0)
			quotient += -1;

		for (int i = 0; i < length; i++) {
			binaried = (quotient % 2) + binaried;
			quotient = quotient / 2;
		}
		return binaried;
	}
	
	public static long binaryToDecimal(String bin) {
		long dec = 0;
		bin = new StringBuilder(bin).reverse().toString();
		for (int i = 0; i < bin.length(); i++) {
			int oneOr0 = Integer.parseInt(bin.charAt(i) + "");
			dec += Math.pow(2, i) * oneOr0;
		}
		return dec;
	}
	
	public static boolean isBinary(String bin) {
		if(bin.isEmpty())
			return false;
		return bin.equals(bin.replaceAll("[^01]", ""));
	}
}
