package les.pdc.lab7;

import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Thread1 extends RecursiveAction {

    @Override
    protected void compute() {
        int[] A = new int[Main.SIZE];
        int[] B = new int[Main.SIZE];
        int[] C = new int[Main.SIZE];
        int[] D = new int[Main.SIZE];
        int[][] MA = new int[Main.SIZE][Main.SIZE];
        int[][] MD = new int[Main.SIZE][Main.SIZE];
        int[][] MC = new int[Main.SIZE][Main.SIZE];
        int[] F = new int[Main.SIZE];

        Arrays.fill(A, Main.value);
        Arrays.fill(B, Main.value);
        Arrays.fill(C, Main.value);
        Arrays.fill(D, Main.value);
        for (int i = 0; i < Main.SIZE; i++) {
            Arrays.fill(MA[i], Main.value);
            Arrays.fill(MD[i], Main.value);
        }

        System.out.println("Thread1 start");
        new MatrixMultiplication(MA, MD, MC, 0, Main.SIZE).compute();
        new VectorMatrixMultiplication(D, MC, F, 0, Main.SIZE).compute();

        ForkJoinTask<Integer> task1 = new Scalar(A, B, 0, Main.SIZE);
        task1.fork();
        ForkJoinTask<Integer> task2 = new Scalar(C, F, 0, Main.SIZE);
        task2.fork();
        int e = task1.join() + task2.join();
        System.out.printf("Thread1: e = %d%n", e);
        System.out.println("Thread1 finish");
    }

}
