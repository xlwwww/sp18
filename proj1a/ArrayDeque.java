public class ArrayDeque<T> {

    private int size;

    private T [] array;

    public ArrayDeque(){

        size=0;

        array = (T[])new Object [8];

    }

    private void resize(int len){

        T[] a = (T[]) new Object[len];

        System.arraycopy(array, 0, a, 0, size);

        array = a;

    }


    public void addFirst(T item){

        if (size == array.length){

            resize(array.length*2);

            System.out.println(array.length);

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

        System.out.println();

    }

    public T removeFirst(){

        if (size==0){

            return null;

        }

        T tmp = array[0];

        T[] a = (T[]) new Object[array.length];

        System.arraycopy(array, 1, a, 0, size-1);

        array = a;

        size -=1;

        float R = (float) size/array.length;

        if (R<0.25 &&array.length>16){

            resize(array.length/2);

        }

        return tmp;

    }

    public T removeLast(){

        if (size==0){

            return null;

        }

        T tmp = array[size-1];

        array[size-1]=null;

        size -=1;

        float R = (float) size/array.length;

        if (R<0.25 &&array.length>16){

            resize(array.length/2);

        }

        return tmp;

    }

    public T get(int index){

        if (index>=size){

            return null;

        }

        return array[index];

    }


}
