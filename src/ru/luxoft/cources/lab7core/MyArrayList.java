package ru.luxoft.cources.lab7core;

import javax.sound.midi.Soundbank;

public class MyArrayList<T> {
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    public void add(T item) {
        if (pointer == array.length - 1)
            resize(array.length * 2);
        array[pointer++] = item;
    }

    public T get(int index) {
        return (T) array[index];
    }

    public int size() {
        return pointer;
    }

    public void remove(int index) {
        for (int i = index; i < pointer; i++)
            array[i] = array[i + 1];
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
            resize(array.length / 2); // если элементов в CUT_RATE раз меньше чем
        // длина массива, то уменьшу в два раза
    }

    public static void main(String[] args) {
        MyArrayList<String> myList = new MyArrayList<>();
        System.out.println("Add some elements to MyArrayList...");
        myList.add("a");
        myList.add("b");
        myList.add("c");
        System.out.println("Get elements from MyArrayList...");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
        System.out.println("Remove element 1 from MyArrayList...");
        myList.remove(1);
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }
}
