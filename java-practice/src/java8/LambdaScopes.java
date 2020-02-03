package java8;

public class LambdaScopes {

    private static int outerStaticNum;
    private int outerNum;

    public void testScopes() {
        Converter<Integer, String> converter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        } ;

        Converter<Integer, String> converter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

}
