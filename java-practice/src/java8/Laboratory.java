package java8;

public class Laboratory {

	public static void main(String[] args) {
		
		
	}
	
	private static void defaultMethod() {
		// anonymous object
		Formula formula = new Formula() {
			
			@Override
			public double calculate(double num) {
				// sqrt is a default method in the same interface
				return (sqrt(num) + 2);
			}
		};
		
		System.out.println(formula.calculate(9));
		System.out.println(formula.sqrt(16));
		
		//TODO: try to understand this output -> java8.Laboratory$1
		System.out.println(formula.getClass());
	}

}
