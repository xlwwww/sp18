import java.util.ArrayDeque;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> s = new LinkedListDeque<>();
        for (int i = 0;i<word.length();i++){
            s.addLast(word.charAt(i));
        }
        return s;
    }
    public boolean isPalindrome(String word){
        Deque<Character> realword = wordToDeque(word);
        return isPalindrome(realword);
    }

    public boolean isPalindrome(Deque<Character> realword){
        while(realword.size()>1){
            return realword.removeFirst()==realword.removeLast() && isPalindrome(realword);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> realword = wordToDeque(word);
        return isPalindrome(realword,cc);
    }
    public boolean isPalindrome(Deque<Character> word, CharacterComparator cc){
        OffByOne charcomp = (OffByOne) cc;
        while(word.size()>1){
            return charcomp.equalChars(word.removeFirst(),word.removeLast()) && isPalindrome(word,cc);
        }
        return true;
    }
}
