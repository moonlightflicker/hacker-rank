package com.hackerrankjava.divisiblesumpairs;

import java.util.Scanner;

public class DivisibleSumPairs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] array = new int[n];
        
        for (int i = 0; scanner.hasNextInt(); i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = array[i] + array[j];
                if (sum % k == 0) res++;
            }
        }
        System.out.println(res);
    }
    
}