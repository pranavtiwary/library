package com.pt.library.utility;

import static java.util.Objects.nonNull;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * IO utility
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class IOUtility{

	private static final PrintStream outputWriter = new PrintStream(System.out);
	private static final Scanner inputReader =  new Scanner(System.in);

	private static final Predicate<String> NOT_EMPTY = s -> nonNull(s) && !s.isEmpty();

	private static final Predicate<String> IS_NUMERIC = s -> nonNull(s) && s.chars().allMatch(Character::isDigit);

	private static final Predicate<String> NON_NEGATIVE = line -> Integer.parseInt(line) > 0;

	private static final Predicate<String> INT_VALID_INPUT_DATA = NOT_EMPTY.and(IS_NUMERIC).and(NON_NEGATIVE);

	public static void writeOnConsole(String msg){
		try {
			outputWriter.write(msg.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Error in i/o");
		}
	}

	private static String readLine(){
		return inputReader.nextLine();
	}


	public static int readIntegerUntil(int min, int max, String message) {
		Predicate<String> USER_CONDITION = s -> Integer.parseInt(s) >= min && Integer.parseInt(s) <= max ;
		Predicate<String> retryCondition = INT_VALID_INPUT_DATA.and(USER_CONDITION).negate();
		String line = null;
		do {
			IOUtility.writeOnConsole(message);
			line = IOUtility.readLine();
		} while (retryCondition.test(line));
		return Integer.parseInt(line);
	}


	public static String readLineUntil(String message) {
		Predicate<String> retryCondition = NOT_EMPTY.negate();
		String line = null;
		do {
			IOUtility.writeOnConsole(message);
			line = IOUtility.readLine();
		} while (retryCondition.test(line));
		return line;
	}
}
