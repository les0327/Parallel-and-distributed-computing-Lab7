package les.pdc.lab7;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveAction;

@AllArgsConstructor
public class VectorMatrixMultiplication extends RecursiveAction {

    private int[] A;
    private int[][] B;
    private int[] C;
    private int start;
    private int length;

    @Override
    protected void compute() {
        if (length <= Main.LIMIT) {
            computeDirectly(A, B, C, start, length);
        } else {
            int split = length / 2;
            invokeAll(
                    new VectorMatrixMultiplication(A, B, C, start, split),
                    new VectorMatrixMultiplication(A, B, C,start + split , length - split)
            );
        }
    }

    /**
     * Multiplication vector A with matrix B
     * @param A - vector
     * @param B - matrix
     * @param C - A*B - vector
     */
    private void computeDirectly(int [] A, int[][] B, int[] C, int start, int length) {
        int size = C.length;
        int buf;
        for (int i = start; i < start + length; i++ ) {
            buf = 0;
            for (int j = 0; j < size; j++) {
                buf += A[j] * B[j][i];
            }
            C[i] = buf;
        }
    }
}
