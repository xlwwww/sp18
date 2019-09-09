import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {

    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalidrome(){
        boolean d2= palindrome.isPalindrome("abcba");
        assertFalse(d2);

    }
    @Test
    public void testoffbyone(){
        String a = "detrude";
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(a,cc));
    }
    @Test
    public void testoffbyn(){
        String a ="aaf" ;
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome(a,cc));
    }
}
