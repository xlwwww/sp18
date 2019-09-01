import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 10;
        assertTrue(Flik.isSameNumber(a,b));
        Integer c = 200;
        Integer d = 200;
        assertTrue(Flik.isSameNumber(c,d));
    }
}
