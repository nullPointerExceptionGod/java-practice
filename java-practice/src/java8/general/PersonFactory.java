package java8.general;

public interface PersonFactory<P extends Person> {

    P create(String firstName, String lastName);
}
