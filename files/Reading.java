import java.util.Scanner;


public class Reading {
	
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		System.out.println("Write a line: ");
		String line = sc.nextLine();
		System.out.println("Your line:");
		System.out.println(line);
	}

}
