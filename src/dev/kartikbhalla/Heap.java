package dev.kartikbhalla;

public class Heap {

    private int[] items = new int[10];
    private int size;

    public void insert(int item) {

        if (isFull())
            throw new IllegalStateException();

        items[size++] = item;
        bubbleUp();
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();

        var root = items[0];
        items[0] = items[--size];
        bubbleDown();

        return root;

    }

    public boolean isFull() {
        return size == items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleUp() {
        var index = size - 1;
        while (index > 0 && items[index] > items[getParentIndex(index)]) {
            swap(index, getParentIndex(index));

            index = getParentIndex(index);
        }
    }

    private void bubbleDown() {
        var index = 0;
        while (index <= size && !isValidParent(index)) {

            int largerChildIndex = getLargerChildIndex(index);
            swap(index, largerChildIndex);

            index = largerChildIndex;
        }
    }

    private int getLargerChildIndex(int index) {

        if (!hasLeftChild(index)) return index;
        if (!hasRightChild(index)) return getLeftChildIndex(index);

        if (getLeftChild(index) > getRightChild(index))
            return getLeftChildIndex(index);

        return getRightChildIndex(index);
    }

    private boolean isValidParent(int index) {

        if (!hasLeftChild(index)) return true;
        if (!hasRightChild(index)) return items[index] >= getLeftChild(index);

        return items[index] >= getLeftChild(index) &&
                items[index] >= getRightChild(index);

    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) <= size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) <= size;
    }

    private int getLeftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private int getRightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    private int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void swap(int first, int second) {
        var temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }

}