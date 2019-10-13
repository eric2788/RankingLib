package com.ericlam.mc.rankcal.implement;

import com.ericlam.mc.rankcal.ArrayData;

import java.util.Arrays;

public final class ArrayCalculation implements ArrayData {
    private double[] ints;
    private double mean;
    private double sd;
    private double variance;

    /**
     * @param ints int array
     */
    ArrayCalculation(double... ints) {
        this.ints = ints;
        this.mean = Arrays.stream(ints).average().orElseThrow(()-> new IllegalStateException("Cannot find mean"));
        this.variance = Arrays.stream(ints).map(i -> Math.pow((i - mean), 2)).sum() / ints.length;
        sd = Math.sqrt(variance);
    }

    /**
     *
     * @return mean
     */
    public double getMean() {
        return mean;
    }

    /**
     *
     * @return standard deviation
     */
    public double getSd() {
        return sd;
    }

    /**
     *
     * @return variance
     */
    public double getVariance() {
        return variance;
    }

    @Override
    public String toString() {
        return "Array: "+ Arrays.toString(ints)+
                "\n"+"Mean: "+mean+
                "\n"+"Variance: "+variance+
                "\n"+"Standard Deviation: "+sd;
    }
}
