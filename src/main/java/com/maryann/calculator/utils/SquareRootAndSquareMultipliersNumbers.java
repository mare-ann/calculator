package com.maryann.calculator.utils;


    public class SquareRootAndSquareMultipliersNumbers {

        public static double powNumbers (double number, double numberTwo){
            double powNumber = Math.pow(number, numberTwo);
            return powNumber;
        }

        public static double getRoots (double number, double numberTwo){
            double getRoot = Math.pow(number, 1/numberTwo);
            return getRoot;
        }
}
