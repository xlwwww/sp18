import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    public static class Node{
        public boolean iskey;
        public char key ;
        public HashMap<Character,Node> next;
        public Node(char key,boolean iskey){
            this.iskey = iskey;
            this.key = key;
            this.next = new HashMap();
        }
    }
    private Node root;
    MyTrieSet(){
        root = new Node('\0',false);
    }
    @Override
    public void clear() {
        root= null;
    }

    @Override
    public boolean contains(String key) {
        if(key==null || key.length()==0 ||root == null){
            return false;
        }
        Node cur = root;
        for (int i = 0;  i <key.length();i++){
            char c = key.charAt(i);
            if (!cur.next.containsKey(c)){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    @Override
    public void add(String key) {
        Node cur =  root;
        for (int i = 0; i<key.length();i++){
            char c = key.charAt(i);
            if (!cur.next.containsKey(c)){
                cur.next.put(c,new Node(c,false));
            }
            cur = (Node) cur.next.get(c);
        }
        cur.iskey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> ans;
        Node cur =  root;
        String tmp = "";
        for(int i = 0; i<prefix.length();i++){
            char c = prefix.charAt(i);
            tmp +=c;
            if (!cur.next.containsKey(c)){
               break;
            }
            cur = (Node) cur.next.get(c);
        }
        ans = help(tmp,new ArrayList<>(),cur);
        return ans;
    }

    @Override
    public String longestPrefixOf(String key) {
        Node cur = root;
        String prefix = "";
        for(int i=0;i<key.length();i++){
            char c = key.charAt(i);
            if(!cur.next.containsKey(c)){
                return prefix;
            }else{
                prefix +=c;
                cur = cur.next.get(c);
            }
        }
        return prefix;
    }
    private List<String> help(String s , List<String> x,Node cur){
        if(cur.iskey){
            x.add(s);
        }
        for (Object c:cur.next.keySet()){
            help(s+c,x, (Node) cur.next.get(c));
        }
        return x;
    }
}
