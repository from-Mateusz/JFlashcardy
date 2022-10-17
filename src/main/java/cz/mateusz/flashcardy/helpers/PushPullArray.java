package cz.mateusz.flashcardy.helpers;

public class PushPullArray<E> {

    private E[] elements;

    int residents;

    public PushPullArray(int size) {
        this.elements = (E[]) new Object[size];
    }

    public void push(E element) throws UnsupportedOperationException {
        if(residents >= elements.length) throw new UnsupportedOperationException("Array's limit has been reached");
        residents++;
        elements[residents - 1] = element;
    }

    public E pull() {
        if(residents == 0) return null;
        residents--;
        return elements[residents];
    }

    public E[] getElements() {
        return elements;
    }
}
