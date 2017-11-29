package les.pdc.lab7;

import lombok.AllArgsConstructor;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class MatrixMax extends RecursiveTask<Integer> {

    private int[][] A;
    private int start;
    private int length;

    @Override
    protected Integer compute() {
        if (length <= Main.LIMIT) {
            return computeDirectly(A, start, length);
        } else {
            int split = length / 2;
            ForkJoinTask<Integer> task1 = new MatrixMax(A, start, split).fork();
            ForkJoinTask<Integer> task2 = new MatrixMax(A, start + split, length - split).fork();
            return Integer.max(task1.join(), task2.join());
        }
    }

    /**
     * Find max element from matrix
     * @param A - matrix
     * @return max element
     */
    private int computeDirectly(int[][] A, int start, int length) {
        int max  = Integer.MIN_VALUE;
        int size = A.length;
        for (int i = start; i < length - start; i++) {
            for (int j = 0; j < size; j++) {
                if (A[i][j] > max) {
                    max = A[i][j];
                }
            }
        }
        return max;
    }
}
