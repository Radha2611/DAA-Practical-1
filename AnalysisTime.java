package DAA;

import java.util.Arrays;
import java.util.Random;

public class AnalysisTime {
    
    public static float generateData( float max, float min){
        Random rand = new Random();
        float randomNum = rand.nextFloat() * (max - min) + min;
        return randomNum;
    }
    public static float[] generateFloatArray(int size, float max, float min) {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = generateData(max, min);
        }
        return arr;
    }
 
    public static float linearsearchMAX(float[] arr) {
        float max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
   
    public static float linearsearchMIN(float[] arr) {
        float min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }
    
    public static float NaiveMin(float[] arr){
        float min = arr[0];
        for(int i = 0; i < arr.length; i++){
            boolean isMin = true;
            for(int j = 0; j < arr.length; j++){
                if(j != i && arr[j] < arr[i]){
                    isMin = false;
                    break;
                }
            }
            if(isMin){
                min = arr[i];
                break;
            }
        }
        return min; 
    }


    public static float NaiveMax(float[] arr){
        float max = arr[0];
        for(int i = 0; i < arr.length; i++){
            boolean isMax = true;
            for(int j = 0; j < arr.length; j++){
                if(j != i && arr[j] > arr[i]){
                    isMax = false;
                    break;
                }
            }
            if(isMax){
                max = arr[i];
                break;
            }
        }
        return max; 
    }
    public static void Print(float[] arr, String label) {
        System.out.println("\n " + label + " Data");

        long startLinear = System.currentTimeMillis();
        linearsearchMAX(arr);
       linearsearchMIN(arr);
        long endLinear = System.currentTimeMillis();
        System.out.println("Linear Search Took: " + (endLinear - startLinear) + " ms");

        long startNaive = System.currentTimeMillis();
        NaiveMax(arr);
       NaiveMin(arr);
        long endNaive = System.currentTimeMillis();
        System.out.println("Naive Search Took: " + (endNaive - startNaive) + " ms");
    }

    public static int linear30(float[] arr,float target){
        for(int i=0;i<arr.length;i++){
            if(arr[i]>=target){
                return i;
            }
        }
        return -1;
    }
     public static int binary30(float[] arr) {
    int first = 0, last = arr.length - 1;
    int num = -1;

    while (first <= last) {
        int mid = (first+last) / 2;
        if (arr[mid] >= 30) {
            num = mid;
            last = mid - 1; 
        } else {
            first = mid + 1;
        }
    }
    return num;
}


    public static void main(String[] args) {
    int[] sizes = {100, 10000, 1000000};
    for (int n : sizes) {
        System.out.println("\n======= RESULTS FOR SIZE = " + n + " =======");

        float[] temp = generateFloatArray(n, 50, -20);
        float[] pressure = generateFloatArray(n, 1050, 950);

        Print(temp, "Temperature");
        Print(pressure, "Pressure");

        
        Arrays.sort(temp);
        Arrays.sort(pressure);

        
        System.out.println("\nLinear Search for Temperature ≥ 30:");
        long startL = System.currentTimeMillis();
        int linTemp = linear30(temp, 30);
        long endL = System.currentTimeMillis();
        System.out.println("Index: " + linTemp + " | Temp: " + (linTemp != -1 ? temp[linTemp] : "Not Found"));
        System.out.println("Time: " + (endL - startL) + " ms");

        System.out.println("\nLinear Search for Pressure ≥ 30:");
        long startLp = System.currentTimeMillis();
        int linPressure = linear30(pressure, 30);
        long endLp = System.currentTimeMillis();
        System.out.println("Index: " + linPressure + " | Pressure: " + (linPressure != -1 ? pressure[linPressure] : "Not Found"));
        System.out.println("Time: " + (endLp - startLp) + " ms");

     
        System.out.println("\nBinary Search for Temperature ≥ 30:");
        long startB = System.currentTimeMillis();
        int binTemp = binary30(temp);
        long endB = System.currentTimeMillis();
        System.out.println("Index: " + binTemp + " | Temp: " + (binTemp != -1 ? temp[binTemp] : "Not Found"));
        System.out.println("Time: " + (endB - startB) + " ms");
    }
}
}
