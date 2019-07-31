public class ArrayDeque<T> {
    private int size;
    private T [] array;
    private double R;
    private int frontindex;
    public ArrayDeque(){
        size=0;
        array = (T[])new Object [8];
        frontindex = 0;
        R = size/array.length;
    }
    private T[] resize(int len){
        T[] a = (T[]) new Object[len];
        System.arraycopy(array, 0, a, 0, size);
        array = a;
        return array;
    }
    public void addFirst(T item){
        if (size == array.length){
            resize(array.length*2);
        }
        T[] t = (T[]) new Object[array.length];
        t[0] = item;
        System.arraycopy(array,0,t,1,size);
        size +=1;
        array = t;
    }
    public void addLast(T item){
        if (size == array.length){
            resize(array.length*2);
        }
        array[size] = item;
        size +=1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for (int i =0;i<size;i++){
            System.out.print(array[i]);
            System.out.print(" ");
        }
    }
    public T removeFirst(){
        T tmp = array[0];
        array[0] = null;
        T[] a = (T[]) new Object[array.length];
        System.arraycopy(array, 1, a, 0, size);
        array = a;
        size -=1;
        if (R<0.25 &&array.length>16){
            resize(array.length/2);
        }
        return tmp;
    }
    public T removeLast(){
        T tmp = array[size-1];
        array[size-1]=null;
        size -=1;
        if (R<0.25 &&array.length>16){
            resize(array.length/2);
        }
        return tmp;
    }
    public T get(int index){
        return array[index];
    }
}
