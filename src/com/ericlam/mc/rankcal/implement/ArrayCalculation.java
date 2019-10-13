package com.ericlam.mc.rankcal.implement;

import com.ericlam.mc.rankcal.ArrayData;

import java.util.Arrays;

public final class ArrayCalculation implements ArrayData {
    private double[] doubles;
    private double mean;
    private double sd;
    private double variance;

    /**
     * @param doubles int array
     */
    ArrayCalculation(double... doubles) {
        this.doubles = doubles;
        this.mean = Arrays.stream(doubles).average().orElseThrow(() -> new IllegalStateException("Cannot find mean"));
        this.variance = Arrays.stream(doubles).map(i -> Math.pow((i - mean), 2)).sum() / doubles.length;
        sd = Math.sqrt(variance);
    }

    /**
     * @return mean
     */
    public double getMean() {
        return mean;
    }

    /**
     * @return standard deviation
     */
    public double getSd() {
        return sd;
    }

    @Override
    public double[] getScores() {
        return doubles;
    }

    /**
     * @return variance
     */
    public double getVariance() {
        return variance;
    }

    @Override
    public String toString() {
        return "Array: " + Arrays.toString(doubles) +
                "\n" + "Mean: " + mean +
                "\n" + "Variance: " + variance +
                "\n" + "Standard Deviation: " + sd;
    }
}
