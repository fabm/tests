package pt;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class Equivalent extends BaseMatcher<String> {

    private int expected;

    public static Equivalent equivalent(int expected) {
        return new Equivalent(expected);
    }

    private Equivalent(int expected) {

        this.expected = expected;
    }

    private boolean tryDouble(String current) {
        try {
            return ((int) Double.parseDouble(current)) == expected;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean matches(Object item) {
        try {
            return Integer.parseInt(item.toString()) == expected;
        } catch (NumberFormatException e) {
            return tryDouble(item.toString());
        }
    }

    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
