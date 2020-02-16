package java8.general;

@FunctionalInterface
public interface Converter<F, T> {

	T convert(F from);
	
}
