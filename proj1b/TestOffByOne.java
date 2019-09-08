import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    //
    static TestOffByOne t = new TestOffByOne();
    @Test
    public void testcharcomp(){
        char a = 'a';
        char b = 'a';
        assertTrue(offByOne.equalChars(a,b));
    }

}
