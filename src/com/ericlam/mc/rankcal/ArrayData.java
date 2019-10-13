package com.ericlam.mc.rankcal;

/**
 * The getter of ArrayCalculation
 */
public interface ArrayData {

    /**
     * @return the mean of the double array
     */
    double getMean();

    /**
     *
     * @return the variance of the double array
     */
    double getVariance();

    /**
     *
     * @return the standard deviation of the double array
     */
    double getSd();

    /**
     *
     * @return the double array
     */
    double[] getScores();

}
