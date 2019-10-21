package convwin;

import static convwin.Conversion.*;

public class Tester {

	public static void main(String[] args) {
		long dec = 1356l;
		System.out.println(dec);
		String bin = decimalToBinary(dec);
		//System.out.println(isBinary("11111111"));
		System.out.println(bin);
		System.out.println(binaryToDecimal(bin));
	}

}
