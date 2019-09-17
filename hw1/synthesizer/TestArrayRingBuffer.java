package synthesizer;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(0);
        arb.enqueue(1);
        System.out.println(arb.dequeue());
        System.out.println(arb.dequeue());
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        assertTrue(arb.isFull());
        //arb.print();

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {

        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
