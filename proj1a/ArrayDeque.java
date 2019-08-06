public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 3;
    }

    private void resize(int len) {
        T[] a = (T[]) new Object[len];
        if (nextFirst < nextLast) {
            System.arraycopy(array, (nextLast + 1) % array.length, a, a.length - size, size);
            nextFirst = a.length - size - 1;
            nextLast = 0;
        } else {
            System.arraycopy(array, 0, a, 0, nextLast);
            System.arraycopy(array, (nextLast + 1) % array.length, a, array.length + 1 + nextFirst, array.length - nextFirst - 1);
            nextFirst = array.length + nextFirst;
        }
        array = a;
    }

    public void addFirst(T item) {
        if (nextLast + 1 == nextFirst || nextFirst == 0) {
            resize(array.length * 2);
        }
        array[nextFirst] = item;
        size += 1;
        if (size == 1) {
            nextLast =(nextLast + 1) % array.length;
        }
        nextFirst = (nextFirst- 1)%array.length;
    }

    public void addLast(T item) {
        if (nextLast + 1 == nextFirst || nextFirst == 0) {
            resize(array.length * 2);
        }
        // ensure nextlast has space
        array[nextLast] = item;
        size += 1;
        if (size == 1) {
            nextFirst = (nextFirst-1)%array.length;
        }
        nextLast = (nextLast + 1) % array.length;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = nextFirst + 1; i < nextFirst + size + 1; i++) {
            System.out.print(array[i % array.length] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T tmp = array[(nextFirst + 1)%array.length];
        array[(nextFirst + 1)%array.length] = null;
        nextFirst = (nextFirst + 1)%array.length;
        size -= 1;
        if (size == 0) {
            nextLast = nextFirst;
        }
        float R = (float) size / array.length;
        if (R < 0.25 && array.length > 16) {
            resize(array.length / 2);
        }
        return tmp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast - 1 + array.length) % array.length;
        T tmp = array[nextLast];
        array[nextLast] = null;
        size -= 1;
        if (size == 0) {
            nextFirst = nextLast;
        }
        float R = (float) size / array.length;
        if (R < 0.25 && array.length > 16) {
            resize(array.length / 2);
        }
        return tmp;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return array[(nextFirst + 1 + index) % array.length];
    }

}
