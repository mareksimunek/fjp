
public class StringMethods {
	
	
	
	

	public static void main(String[] args) {
		
		String text = "Hello World!";
		
		char firstLetter = text.charAt(0);
		int size = text.length();
		int isEqual = 0;
		if(text.equals("Hello World!")){
			isEqual = 1;
		}
		
		int indexOfW = text.indexOf("W");
		
		System.out.println("firstLetter - " + firstLetter);
		System.out.println("size - " + size);
		System.out.println("isEqual - " + isEqual);
		System.out.println("indexOfW - " + indexOfW);


	
		
	}
	
	

}
