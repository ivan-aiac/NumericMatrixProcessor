package processor;

public class Matrix {

    public static double[][] addMatrices(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new RuntimeException("Must have same size matrices to add them.");
        }
        double[][] res = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }

    public static double[][] multiplyMatrices(double[][] a, double[][] b) {
        if (a[0].length != b.length) {
            throw new RuntimeException("Number of a.columns and b.rows must be equal.");
        }
        double[][] res = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                res[i][j] = 0;
                for (int k = 0; k < b.length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    public static double[][] multiplyByConstant(double[][] matrix, double constant) {
        double[][] res = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[i][j] = constant * matrix[i][j];
            }
        }
        return res;
    }

    public static double determinant(double[][] matrix) {
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double d = 0;
        for (int j = 0; j < matrix[0].length; j++){
            d += Math.pow(-1, j) * matrix[0][j] * determinant(subMatrix(matrix, 0, j));
        }
        return d;
    }

    public static double[][] adjointMatrix(double[][] matrix) {
        double[][] adjoint = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < adjoint.length; i++) {
            for (int j = 0; j < adjoint[0].length; j++) {
                adjoint[i][j] = Math.pow(-1, i + j) * determinant(subMatrix(matrix, i, j));
            }
        }
        return mainTranspose(adjoint);
    }

    public static double[][] inverseMatrix(double[][] matrix) {
        double determinant = determinant(matrix);
        if (determinant == 0.0) {
            throw new RuntimeException("Cannot inverse matrix, determinant is 0.");
        }
        return multiplyByConstant(adjointMatrix(matrix), 1 / determinant);
    }

    private static double[][] subMatrix(double[][] matrix, int removedRow, int removedCol) {
        double[][] subMatrix = new double[matrix.length - 1][matrix[0].length - 1];
        int col;
        for (int i = 0, row = 0; i < matrix.length; i++) {
            if (i != removedRow) {
                col = 0;
                for (int j = 0; j < matrix[0].length; j++) {
                    if (j != removedCol) {
                        subMatrix[row][col++] = matrix[i][j];
                    }
                }
                row++;
            }
        }
        return subMatrix;
    }

    public static double[][] mainTranspose(double[][] matrix) {
        double[][] transpose = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < transpose[0].length; i++) {
            for (int j = 0; j < transpose.length; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }

    public static double[][] sideTranspose(double[][] matrix) {
        double[][] transpose = new double[matrix.length][matrix[0].length];
        for (int i = transpose[0].length - 1; i >= 0; i--) {
            for (int j = transpose.length - 1; j >= 0 ; j--) {
                transpose[transpose[0].length - 1 - i][j] = matrix[transpose.length - 1 - j][i];
            }
        }
        return transpose;
    }

    public static double[][] verticalTranspose(double[][] matrix) {
        double[][] transpose = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < transpose.length; i++) {
            for (int j = transpose[0].length - 1; j >= 0; j--) {
                transpose[i][transpose[0].length - 1 - j] = matrix[i][j];
            }
        }
        return transpose;
    }

    public static double[][] horizontalTranspose(double[][] matrix) {
        double[][] transpose = new double[matrix.length][matrix[0].length];
        for (int i = transpose.length - 1; i >= 0 ; i--) {
            for (int j = transpose[0].length - 1; j >= 0; j--) {
                transpose[i][j] = matrix[transpose.length - 1 - i][j];
            }
        }
        return transpose;
    }


}
