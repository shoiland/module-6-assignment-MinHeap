import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null){
            throw new IllegalArgumentException();
        }
        if ((this.size + 1) % INITIAL_CAPACITY == 0){
            T[] newBackingArray = (T[]) new Comparable[(this.size + 1) * 2];
            for (int i = 0; i < backingArray.length - 1; i++){
                newBackingArray[i+1] = backingArray[i+1];
            }
            backingArray = newBackingArray;
        }
        backingArray[this.size + 1] = data;
        this.size++;
        int parentIndex = this.size / 2;
        upHeaveAdd(this.size, parentIndex);
    }

    private void upHeaveAdd(int dataIndex, int parentIndex){
        if (parentIndex == 0){
            return;
        }
        if (backingArray[parentIndex].compareTo(backingArray[dataIndex]) > 0){
            T temp = backingArray[parentIndex];
            backingArray[parentIndex] = backingArray[dataIndex];
            backingArray[dataIndex] = temp;
            upHeaveAdd(parentIndex, parentIndex/2);
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (this.size == 0){
            throw new NoSuchElementException();
        }
        T removeItem = backingArray[1];
        backingArray[1] = backingArray[this.size];
        backingArray[this.size] = null;
        this.size--;
        int parentIndex = 1;
        int childLeft = 2;
        int childRight = 3;
        downHeaveRemove(parentIndex, childLeft, childRight);
        return removeItem;
    }

    private void downHeaveRemove(int parent, int leftChild, int rightChild) {
        try{
            if (backingArray[rightChild] == null && backingArray[leftChild] == null) {
                return;
            }
            if (backingArray[rightChild] == null){
                if (backingArray[parent].compareTo(backingArray[leftChild]) > 0) {
                    T temp = backingArray[parent];
                    backingArray[parent] = backingArray[leftChild];
                    backingArray[leftChild] = temp;
                    downHeaveRemove(leftChild, leftChild * 2, leftChild * 2 + 1);
                }
            }
            if (backingArray[leftChild] == null){
                T temp = backingArray[parent];
                backingArray[parent] = backingArray[rightChild];
                backingArray[rightChild] = temp;
                downHeaveRemove(rightChild, rightChild * 2, rightChild * 2 + 1);
            }
            boolean leftSmaller = backingArray[leftChild].compareTo(backingArray[rightChild]) < 0;
            if (leftSmaller) {
                if (backingArray[parent].compareTo(backingArray[leftChild]) > 0) {
                    T temp = backingArray[parent];
                    backingArray[parent] = backingArray[leftChild];
                    backingArray[leftChild] = temp;
                    downHeaveRemove(leftChild, leftChild * 2, leftChild * 2 + 1);
                }
            } else {
                if (backingArray[parent].compareTo(backingArray[rightChild]) > 0) {
                    T temp = backingArray[parent];
                    backingArray[parent] = backingArray[rightChild];
                    backingArray[rightChild] = temp;
                    downHeaveRemove(rightChild, rightChild * 2, rightChild * 2 + 1);
                }
            }
        }
        catch (Exception e){
            return;
        }

    }
    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}