package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[])new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if(this.isEmpty()!=true) {
            T tmp = rb[first];
            fillCount--;
            first = (first+1)%capacity;
            return tmp;
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        T tmp = rb[first];
        return tmp;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }

    @Override
    public void enqueue(T x) {
        if(isFull()){
            throw new RuntimeException("Ring Buffer Overflow");
        }
        if(this.isEmpty()){
            last = first;
            rb[last] = x;
        }else if(isFull()!=true) {
            last = (last+1)%capacity;
            rb[last] = x;
        }
        fillCount++;
    }

    @Override
    public boolean isEmpty() {
        return fillCount==0;
    }

    @Override
    public boolean isFull() {
        return capacity==fillCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new RingIterator();
    }
    // TODO: When you get to part 5, implement the needed code to support iteration.
    /*
    public void print(){
        for (int i = first; i < first+capacity;i++){
            System.out.print(rb[i%capacity]);
            System.out.print(" ");
        }
        System.out.println();
    }
    */

    private class RingIterator implements Iterator<T>{
        private int i;
        private int count = 0;
        public RingIterator(){
            i = first;
            count = 0;
        }
        @Override
        public boolean hasNext() {
            return count<fillCount;
        }

        @Override
        public T next() {
            T tmp = rb[i%capacity];
            i++;
            count++;
            return tmp;
        }
    }
}
