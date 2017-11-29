package les.pdc.lab7;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveAction;

@AllArgsConstructor
public class MatrixSort extends RecursiveAction {

    private int[][] A;
    private int start;
    private int length;

    @Override
    protected void compute() {
        if (length <= Main.LIMIT) {
            computeDirectly(A, start, length);
        } else {
            int split = length / 2;
            invokeAll(
                    new MatrixSort(A, start, split),
                    new MatrixSort(A, start + split, length - split)
            );
        }
    }

    /**
     * Sort matrix A
     * @param A - matrix
     */
    private void computeDirectly(int[][] A, int start, int length) {
        int index;
        int max  ;
        int size = A.length;
        for (int line = start; line < start + length; line++) {
            for (int i = 0; i < size; i++) {
                index = i;
                max   = A[line][i];
                for (int j = i + 1; j < size; j++) {
                    if (A[line][j] > max) {
                        index = j;
                        max   = A[line][j];
                    }
                }
                A[line][index] = A[line][i];
                A[line][i]     = max;
            }
        }
    }
}
