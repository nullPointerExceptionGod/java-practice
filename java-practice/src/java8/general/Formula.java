package java8.general;

public interface Formula {

	double calculate(double num);
	
	default double sqrt(double num) {
		return Math.sqrt(num);
	}

}
