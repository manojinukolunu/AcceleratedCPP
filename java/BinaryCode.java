import java.util.Arrays;
import java.lang.IllegalArgumentException;

public class BinaryCode {
	public String[] decode(String message) {
		int messagelength = message.length();
		char[] messageArray = message.toCharArray();
		char[] value1 = new char[messagelength];
		value1[0] = '0';
		value1 = process(messageArray, value1);
		char[] value2 = new char[messagelength];
		value2[0] = '1';
		value2 = process(messageArray, value2);
		return new String[] { new String(value1), new String(value2) };
	}

	private char[] process(char[] message, char[] values) {
		if (message.length == 1) {
			if (message[0] != '0' && message[0] != '1')
				return "NONE".toCharArray();
			else
				return values;
		}
		for (int i = 0; i < message.length; i++) {
			if (i == 0) {
				values[i + 1] = (char) ('0' + Character.getNumericValue(message[i]) - Character.getNumericValue(values[i]) - Character.getNumericValue('0'));
				if (values[i + 1] != '0' && values[i + 1] != '1')
					return "NONE".toCharArray();
			} else if (i + 1 < message.length) {
				values[i + 1] = (char) ('0' + Character.getNumericValue(message[i]) - Character.getNumericValue(values[i]) - Character.getNumericValue(values[i - 1]));
				if (values[i + 1] != '0' && values[i + 1] != '1')
					return "NONE".toCharArray();
			}

		}
		if (Character.getNumericValue(values[message.length - 1]) + Character.getNumericValue(values[message.length - 2]) + 0 != Character.getNumericValue(message[message.length - 1]))
			return "NONE".toCharArray();
		return values;
	}

	private void test(String message, String dec1, String dec2) {
		String[] dec = decode(message);
		if (dec1.equals(dec[0]) && dec2.equals(dec2))
			System.out.println("True");

	}

	public static void main(String args[]) {
		BinaryCode code = new BinaryCode();
		code.test("123210122","011100011","NONE");
		code.test("11","01","10");
		code.test("22111","NONE","11001");
		code.test("123210120","NONE","NONE");
		code.test("3","NONE","NONE");
		code.test("12221112222221112221111111112221111","01101001101101001101001001001101001","10110010110110010110010010010110010");

	}
}
