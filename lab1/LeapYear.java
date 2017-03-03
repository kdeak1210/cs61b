/**
 * Starter program to test java in ubuntu, git/hub, sublimeText..
 * 
 * Is the year a leap year? Does everything in main..
 * A year is a leap year if it is either:
 * 		1. divisible by 400 or
 * 		2. divisible by 4 and not by 100
 */

public class LeapYear {

	public static void main(String[] args) {

		int year = 2003; // The year to test

		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			System.out.println("The year " + year + " is a leap year");
		} else {
			System.out.println("The year " + year + " is not a leap year");
		}

	}
}
