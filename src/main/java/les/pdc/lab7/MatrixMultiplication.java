package les.pdc.lab7;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveAction;

@AllArgsConstructor
public class MatrixMultiplication extends RecursiveAction{

    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int start;
    private int length;


    @Override
    protected void compute() {
        if (length <= Main.LIMIT) {
            computeDirectly(A, B, C, start, length);
        } else {
            int split = length / 2;
            invokeAll(
                    new MatrixMultiplication(A, B, C, start, split),
                    new MatrixMultiplication(A, B, C,start + split , length - split)
            );
        }
    }

    /**
     * Multiplication matrix A and B
     * @param A - matrix
     * @param B - matrix
     * @param C - A*B - matrix
     */
    private void computeDirectly(int[][] A, int[][] B, int[][] C, int start, int length) {
        int buf;
        int size = C.length;
        for (int i = start; i < start + length; i++ ) {
            for (int j = 0; j < size; j++) {
                buf = 0;
                for (int k = 0; k < size; k++) {
                    buf += A[i][k] * B[k][j];
                }
                C[i][j] = buf;
            }
        }
    }
}
