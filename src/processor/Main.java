package processor;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    private static final String MAIN_MENU = "1. Add matrices\n2. Multiply matrix by a constant\n3. Multiply matrices\n4. Transpose matrix\n5. Calculate a determinant\n6. Inverse matrix\n0. Exit\nYour choice: ";
    private static final String TRANSPOSE_MENU = "1. Main diagonal\n2. Side diagonal\n3. Vertical line\n4. Horizontal line\nYour choice: ";
    private static final String ERROR_MESSAGE = "The operation cannot be performed.";
    private static final String[] SINGLE_MATRIX_MESSAGES = {"Enter matrix size: ", "Enter matrix: "};
    private static final String[] FIRST_MATRIX_MESSAGES = {"Enter the size of first matrix: ", "Enter first matrix: "};
    private static final String[] SECOND_MATRIX_MESSAGES = {"Enter the size of second matrix: ", "Enter second matrix: "};

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int action = -1;
        while (action != 0) {
            try {
                System.out.println(MAIN_MENU);
                action = scanner.nextInt();
                switch (action){
                    case 1:
                        addMatrices();
                        break;
                    case 2:
                        constantMultiply();
                        break;
                    case 3:
                        multiplyMatrices();
                        break;
                    case 4:
                        transposeMatrix();
                        break;
                    case 5:
                        calculateDeterminant();
                        break;
                    case 6:
                        inverseMatrix();
                        break;
                    default:
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println(ERROR_MESSAGE);
            }
        }
    }

    private static double[][] readMatrix(String[] messages) {
        System.out.println(messages[0]);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        System.out.println(messages[1]);

        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        return matrix;
    }

    private static void addMatrices() {
        double[][] a = readMatrix(FIRST_MATRIX_MESSAGES);
        double[][] b = readMatrix(SECOND_MATRIX_MESSAGES);

        printMatrix(Matrix.addMatrices(a, b));
    }

    private static void multiplyMatrices() {
        double[][] a = readMatrix(FIRST_MATRIX_MESSAGES);
        double[][] b = readMatrix(SECOND_MATRIX_MESSAGES);

        printMatrix(Matrix.multiplyMatrices(a, b));
    }

    private static void constantMultiply() {
        double[][] matrix = readMatrix(SINGLE_MATRIX_MESSAGES);
        System.out.println("Enter constant: ");
        int constant = scanner.nextInt();

        printMatrix(Matrix.multiplyByConstant(matrix, constant));
    }

    private static void transposeMatrix() {
        System.out.println(TRANSPOSE_MENU);
        int type = scanner.nextInt();
        double[][] matrix = readMatrix(SINGLE_MATRIX_MESSAGES);

        switch (type) {
            case 1:
                printMatrix(Matrix.mainTranspose(matrix));
                break;
            case 2:
                printMatrix(Matrix.sideTranspose(matrix));
                break;
            case 3:
                printMatrix(Matrix.verticalTranspose(matrix));
                break;
            case 4:
                printMatrix(Matrix.horizontalTranspose(matrix));
                break;
            default:
                break;
        }
    }

    private static void calculateDeterminant() {
        double[][] matrix = readMatrix(SINGLE_MATRIX_MESSAGES);
        double determinant = Matrix.determinant(matrix);

        System.out.println("The result is:");
        if (determinant == (long) determinant) {
            System.out.printf("%d\n", (long) determinant);
        } else {
            System.out.printf("%.2f\n", determinant);
        }
    }

    private static void inverseMatrix() {
        double[][] matrix = readMatrix(SINGLE_MATRIX_MESSAGES);
        try {
            System.out.println("The result is:");
            printMatrix(Matrix.inverseMatrix(matrix));
        } catch (RuntimeException e) {
            System.out.println("This matrix doesn't have an inverse." + e.getMessage());
        }
    }

    private static void printMatrix(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat format = new DecimalFormat(" #.##;-#.##");
        sb.append("\n");
        for (double[] row: matrix) {
            for (double num: row) {
                sb.append(String.format("%-8s", format.format(num)));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
