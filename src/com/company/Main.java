package com.company;
import java.util.Scanner;
/**
 * Created by Hashir on 9/29/2016.
 */
class Lab2
{
    public static void main(String args[])                          //Main Class
    {
        Scanner input = new Scanner(System.in);                     //Using Scanner for user input
        System.out.println("Please Choose Method :  \n (1)Iterative Method \n (2)Strassan Method ");
        int choice = input.nextInt();                                //Taking number of rows of 1st matrix

        if (choice ==1 ) {                                              //Iterative Method
            int rows1;                                                  //Number of rows of first matrix
            int cols1;                                                  //Number of cols of first matrix
            int rows2;                                                  //Number of rows of second matrix
            int cols2;                                                  //Number of cols of second matrix
            int sum = 0;                                                //Store sum
            int i, j, k;                                                //Declaring different iterators


            System.out.println("Enter # of rows of first matrix");
            rows1 = input.nextInt();                                    //Taking number of rows of 1st matrix
            System.out.println("Enter # of columns of first matrix");
            cols1 = input.nextInt();                                    //Taking number of cols of 2nd matrix

            int matrix1[][] = new int[rows1][cols1];                    //Declaring first matrix

            System.out.println("Enter the elements of first matrix");

            for (i = 0; i < rows1; i++)
                for (j = 0; j < cols1; j++)
                    matrix1[i][j] = input.nextInt();                    //Taking elements of first matrix

            System.out.println("Enter # of rows of second matrix");
            rows2 = input.nextInt();                                    //Taking number of rows of 2nd matrix
            System.out.println("Enter # of columns of second matrix");
            cols2 = input.nextInt();

            if (cols1 != rows2)                                       //Checks if Number of columns of first matrix is equal to number of rows of 2nd
                System.out.println("Error ! Columns of first matrix must be equal to Rows of second matrix");
            else {
                int matrix2[][] = new int[rows2][cols2];                //Declaring first matrix
                int product[][] = new int[rows1][cols2];               //Matrix to store result

                System.out.println("Enter the elements of second matrix");

                for (i = 0; i < rows1; i++)
                    for (j = 0; j < cols2; j++)
                        matrix2[i][j] = input.nextInt();                 //Taking elements of first matrix

                for (i = 0; i < rows1; i++) {
                    for (j = 0; j < cols2; j++) {
                        for (k = 0; k < rows2; k++) {
                            sum = sum + matrix1[i][k] * matrix2[k][j];  //Product of matrix1 and 2 = ∑_(k=1)^m 〖〖A 〗_ik B_kj 〗
                        }
                        product[i][j] = sum;                           //Storing result of each value
                        sum = 0;
                    }
                }

                System.out.println("Product of entered matrices:-");

                for (i = 0; i < rows1; i++) {
                    for (j = 0; j < cols2; j++)
                        System.out.print(product[i][j] + "\t");       //Printing result

                    System.out.print("\n");
                }
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////

        else {
            // Method 2
            class method2 {                                    //Strassan metthod (recursive)
                public int[][] multiplication(int[][] matrix1, int[][] matrix2) //function which takes 2 matrices and computes the product
                {
                    int size = matrix1.length;
                    int[][] product = new int[size][size];
                    if (size == 1)
                        product[0][0] = matrix1[0][0] * matrix2[0][0]; //Defining the base case
                    else {
                        //Dividing matrix1 into 4 halves
                        int[][] matrix1_11 = new int[size / 2][size / 2];
                        int[][] matrix1_12 = new int[size / 2][size / 2];
                        int[][] matrix1_21 = new int[size / 2][size / 2];
                        int[][] matrix1_22 = new int[size / 2][size / 2];
                        //Dividing matrix2 into 4 halves
                        int[][] matrix2_11 = new int[size / 2][size / 2];
                        int[][] matrix2_12 = new int[size / 2][size / 2];
                        int[][] matrix2_21 = new int[size / 2][size / 2];
                        int[][] matrix2_22 = new int[size / 2][size / 2];
                        //Splitting matrix1  into 4 halves
                        split(matrix1, matrix1_11, 0, 0);
                        split(matrix1, matrix1_12, 0, size / 2);
                        split(matrix1, matrix1_21, size / 2, 0);
                        split(matrix1, matrix1_22, size / 2, size / 2);
                        //Splitting matrix2  into 4 halves
                        split(matrix2, matrix2_11, 0, 0);
                        split(matrix2, matrix2_12, 0, size / 2);
                        split(matrix2, matrix2_21, size / 2, 0);
                        split(matrix2, matrix2_22, size / 2, size / 2);
                        //Applying Formulae for P1 to P7
                        int[][] P1 = multiplication(add(matrix1_11, matrix1_22), add(matrix2_11, matrix2_22));
                        int[][] P2 = multiplication(add(matrix1_21, matrix1_22), matrix2_11);
                        int[][] P3 = multiplication(matrix1_11, sub(matrix2_12, matrix2_22));
                        int[][] P4 = multiplication(matrix1_22, sub(matrix2_21, matrix2_11));
                        int[][] P5 = multiplication(add(matrix1_11, matrix1_12), matrix2_22);
                        int[][] P6 = multiplication(sub(matrix1_21, matrix1_11), add(matrix2_11, matrix2_12));
                        int[][] P7 = multiplication(sub(matrix1_12, matrix1_22), add(matrix2_21, matrix2_22));
                        //Calculating product of each position
                        int[][] product11 = add(sub(add(P1, P4), P5), P7);
                        int[][] product12 = add(P3, P5);
                        int[][] product21 = add(P2, P4);
                        int[][] product22 = add(sub(add(P1, P3), P2), P6);
                        //Joining all products to resulataant matrix
                        combine(product11, product, 0, 0);
                        combine(product12, product, 0, size / 2);
                        combine(product21, product, size / 2, 0);
                        combine(product22, product, size / 2, size / 2);
                    }
                    return product; // return resultant
                }

                public void combine(int[][] C, int[][] P, int iB, int jB) {
                    for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
                        for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                            P[i2][j2] = C[i1][j1];
                }



                public void split(int[][] P, int[][] C, int iB, int jB) {
                    for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
                        for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                            C[i1][j1] = P[i2][j2];
                }

                public int[][] sub(int[][] A, int[][] B) {
                    int n = A.length;
                    int[][] C = new int[n][n];
                    for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)
                            C[i][j] = A[i][j] - B[i][j];
                    return C;
                }


                public int[][] add(int[][] A, int[][] B) {
                    int n = A.length;
                    int[][] C = new int[n][n];
                    for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)
                            C[i][j] = A[i][j] + B[i][j];
                    return C;
                }

            }
            method2 strassan = new method2();
            System.out.println("Please enter order of matrix (N) :");
            int N = input.nextInt();
            System.out.println("Enter elements for  matrix 1\n");
            int[][] matrix1 = new int[N][N];
            for (int x = 0; x < N; x++)
                for (int y = 0; y < N; y++)
                    matrix1[x][y] = input.nextInt();
            System.out.println("Enter elements for matrix 2\n");
            int[][] matrix2 = new int[N][N];
            for (int x = 0; x < N; x++)
                for (int y = 0; y < N; y++)
                    matrix2[x][y] = input.nextInt();
            int[][] result = strassan.multiplication(matrix1, matrix2);
            System.out.println("\nProduct of matrices MATRIX 1 and  MATRIX 2 : ");
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++)
                    System.out.print(result[x][y] + " ");
                System.out.println();
            }
        }














    }
}