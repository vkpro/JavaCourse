package ru.luxoft.cources.lab10dynamic;

import java.util.Arrays;

public class GenericListAgregator<T extends Number> {
    T[] arr;

    public GenericListAgregator(T[] arr) {
        if (arr == null) {
            throw new IllegalStateException("Массив не должен быть null");
        }
        this.arr = arr;
    }

    public double getAvgValue() {
        double sum = 0.0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i].doubleValue();
        }
        return sum / arr.length;
    }

    public double getMaxValue() {
        double maxValue = arr[0].doubleValue();

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].doubleValue() > maxValue) {
                maxValue = arr[i].doubleValue();
            }
        }
        return maxValue;
    }

    public double getMinValue() {
        double minValue = arr[0].doubleValue();

        for (int i = 1; i < arr.length; i++) {
            if (minValue > arr[i].doubleValue()) {
                minValue = arr[i].doubleValue();
            }
        }
        return minValue;
    }

    @Override
    public String toString() {
        return "GenericListAgregator{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }

    public static void main(String[] args) {

        Integer[] arrI = {1, 2, 3, 4, 5, 99, -100};
        GenericListAgregator<Integer> arrInteger = new GenericListAgregator<>(arrI);
        System.out.println(arrInteger.toString());
        System.out.println("AvgValue " + arrInteger.getAvgValue());
        System.out.println("MaxValue " + arrInteger.getMaxValue());
        System.out.println("MinValue " + arrInteger.getMinValue());


        Double[] arrD = {1.1, 2.2, 3.3, 88.0, -45.5};
        GenericListAgregator<Double> arrDouble = new GenericListAgregator<>(arrD);
        System.out.println(arrDouble.toString());
        System.out.println("AvgValue " + arrDouble.getAvgValue());
        System.out.println("MaxValue " + arrDouble.getMaxValue());
        System.out.println("MinValue " +  arrDouble.getMinValue());

        Integer[] arrN = null;
        GenericListAgregator<Integer> arrNull = new GenericListAgregator<>(arrN);
//        Exception in thread "main" java.lang.IllegalStateException: Массив не должен быть null


//        String[] arrS = {"a", "b", "c"};
//        GenericListAgregator<String> arrString = new GenericListAgregator<>(arrS);
//        Type parameter 'java.lang.String' is not within its bound; should extend 'java.lang.Number'
    }
}
