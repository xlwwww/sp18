public class LinkedListDeque<T> {
    private class StuffNode{
        private T item;
        private StuffNode next;
        private StuffNode prev;
        private StuffNode(T item){
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }
    private StuffNode sentinel;
    private  StuffNode last;
    private  int size;
    public LinkedListDeque(){
        sentinel = new StuffNode(null);
        sentinel.next =sentinel;
        sentinel.prev = sentinel;
        last = sentinel.prev;
        size = 0;
    }
    public void addFirst(T item) {
        sentinel.next.prev = new StuffNode(item);
        sentinel.next.prev.next = sentinel.next;
        sentinel.next.prev.prev = sentinel;
        sentinel.next = sentinel.next.prev;
        last = sentinel.prev;
        size += 1;
    }
    public void addLast(T item){
        last.next = new StuffNode(item);
        last.next.prev = last;
        last.next.next = sentinel;
        last = last.next;
        sentinel.prev = last;
        size +=1;
    }
    public boolean isEmpty(){
        if (size==0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        StuffNode n = sentinel.next;
        for (int i=0;i<size;i++){
            System.out.print(n.item);
            System.out.print(" ");
            n = n.next;
        }
    }
    public T removeFirst(){
        if (sentinel.next==sentinel){
            return null;
        }
        sentinel.next.next.prev = sentinel;
        StuffNode n = sentinel.next;
        sentinel.next = sentinel.next.next;
        size -=1;
        return n.item;
    }
    public T removeLast(){
        if (sentinel.next==sentinel){
            return null;
        }
        StuffNode n = last;
        last = last.prev;
        last.next = sentinel;
        sentinel.prev = last;
        size -=1;
        return n.item;
    }
    public T get(int index){
        int i =0;
        StuffNode n = sentinel.next;
        if (n==null){
            return null;
        }
        while(i<index){
            n = n.next;
            i +=1;
        }
        return n.item;
    }
    private T getRecursive(int index,StuffNode node){
        if (node==null){
            return null;
        }
        if (index ==0){
            return node.item;
        }
        return getRecursive(index-1,node.next);
    }
    public T getRecursive(int index){
        return getRecursive(index,sentinel.next);
    }


}
