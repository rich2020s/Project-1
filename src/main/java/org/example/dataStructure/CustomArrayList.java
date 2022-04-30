package org.example.dataStructure;

import java.util.Arrays;

public class CustomArrayList <T> implements CustomList<T> {
    // size will be how many objects are in our array list, this size will be dynamic
    private int size = 0;
    // constant, because of the final keyword
    private static final int DEFAULT_CAPACITY = 10;
    // underlying structure, store any type of class:
    private Object elements[];

    // constructor:
    public CustomArrayList() {
        // when we create an array list, we make a new array with the default capacity
        elements = new Object[DEFAULT_CAPACITY];
    }

    // add elements to our array list:
    @Override
    public void add(T element) {
        // check if we need to resize, if we've reached the end of our array:
        if(size == elements.length) {
            resize();
        }
        // assign the element to the current spot as well as increase our size to adjust for the next spot
        // TODO test out ++ position (before or after)
        elements[size++] = element;
        // [1, _, _, _]
    }   //  |  |

    // because our underlying structure is just a regular array, we have to do the resizing manually
    private void resize() {
        int newSize = elements.length * 2;
        // using the Arrays class from java.util, we make a copy of the array with the new size:
        // we have to make a copy because we can't actually change the size of an array:
        elements = Arrays.copyOf(elements, newSize);
    }

    // return the corresponding object based on the index:
    @Override
    public T get(int i) {
        // first check if we are out bounds:
        if ( i >= size || i < 0) {
            // throw an index out of bounds exception if we are out of bounds:
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        return (T) elements[i];
    }

    // print out for debugging:
    @Override
    public void print() {
        // for each loop:
        for(Object element: elements) {
            if(element != null) System.out.print(element + ", ");
        }
        System.out.println();
        // System.out.println("Size: " + size);
    }

    @Override
    public void add(int i, T element) {
        // TODO: Implement this
        if(size == elements.length) {
            resize();
        }
        Object[] newArr = new Object[elements.length];
        int counter = 0;
        for(int j=0; j<size; j++) {
            if (j == i) {
                newArr[j] = element;
                counter = 1;
            }
            newArr[j + counter] = elements[j];
        }
        elements = newArr;
        size++;
    }
}

