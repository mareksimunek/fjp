
public class Condition {
	
	public static int size = 5;
	public static int a = 10;
	
	

	public static void main(String[] args) {
		
		for(int i = 0; i < size ; i++){
			System.out.print(i);
		}
		System.out.println();
		int start = 0;
		while(start < size){
			System.out.println(start);
			start++;
		}
		start = 0;
	
		
		//ukázka použití metody v podmínce
		if(start < size && getNumber() > size){
			a = 15;
					
		}else {
			a = 0;
		}
		
		
		
		System.out.println(getNumber());
		System.out.println(getSum(1, 1));
		
	}
	
	public static int getNumber(){
		return a;
	}
	public static int getSum(int a, int b){
		return a + b;
	}
	
	
	

}
