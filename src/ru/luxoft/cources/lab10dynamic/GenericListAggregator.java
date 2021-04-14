package ru.luxoft.cources.lab10dynamic;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

public class GenericListAggregator<T extends Number> {
    private final T[] arr;

    public GenericListAggregator(T[] arr) {
        if (arr == null) {
            throw new IllegalStateException("Массив не должен быть null");
        }
        this.arr = arr;
    }

    public static void main(String[] args) {

        Integer[] arrI = {1, 2, 3, 4, 5, 99, -100};
        GenericListAggregator<Integer> arrInteger = new GenericListAggregator<>(arrI);
        System.out.println(arrInteger.toString());
        System.out.println("AvgValue " + arrInteger.getAvgValue());
        System.out.println("MaxValue " + arrInteger.getMaxValue());
        System.out.println("MinValue " + arrInteger.getMinValue());


        Double[] arrD = {1.1, 2.2, 3.3, 88.0, -45.5};
        GenericListAggregator<Double> arrDouble = new GenericListAggregator<>(arrD);
        System.out.println(arrDouble.toString());
        System.out.println("AvgValue " + arrDouble.getAvgValue());
        System.out.println("MaxValue " + arrDouble.getMaxValue());
        System.out.println("MinValue " + arrDouble.getMinValue());
        System.out.println(arrDouble.statistics());

//        GenericListAggregator<Integer> arrNull = new GenericListAggregator<>(null);
//        Exception in thread "main" java.lang.IllegalStateException: Массив не должен быть null


//        String[] arrS = {"a", "b", "c"};
//        GenericListAggregator<String> arrString = new GenericListAggregator<>(arrS);
//        Type parameter 'java.lang.String' is not within its bound; should extend 'java.lang.Number'
    }

    public double getAvgValue() {
        return Arrays.stream(arr).mapToDouble(Number::doubleValue).average().orElse(0.0);
    }

    public double getMaxValue() {
        return Arrays.stream(arr).mapToDouble(Number::doubleValue).max().orElse(0.0);
    }

    public double getMinValue() {
        return Arrays.stream(arr).mapToDouble(Number::doubleValue).min().orElse(0.0);
    }

    public DoubleSummaryStatistics statistics() {
        return Arrays.stream(arr).mapToDouble(Number::doubleValue).summaryStatistics();
    }


    @Override
    public String toString() {
        return "GenericListAggregator{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}
