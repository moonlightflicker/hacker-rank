package com.hackerrankjava.sparsearrays;

import java.util.Scanner;

public class SparseArrays {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = scanner.next();
        }

        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            String query = scanner.next();

            int count = 0;
            for (int j = 0; j < n; j++) {
                if (strings[j].equals(query)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    
        scanner.close();
    }

}