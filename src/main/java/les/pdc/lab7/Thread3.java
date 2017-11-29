package les.pdc.lab7;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class Thread3 extends RecursiveAction {

    @Override
    protected void compute() {
        int[][] MP = new int[Main.SIZE][Main.SIZE];
        int[][] MR = new int[Main.SIZE][Main.SIZE];
        int[][] MF = new int[Main.SIZE][Main.SIZE];
        int[] T = new int[Main.SIZE];

        Arrays.fill(T, Main.value);
        for (int i = 0; i < Main.SIZE; i++) {
            Arrays.fill(MP[i], Main.value);
            Arrays.fill(MR[i], Main.value);
        }

        System.out.println("Thread3 start");
        new MatrixMultiplication(MP, MR, MF, 0, Main.SIZE).compute();
        new NumberVectorMultiplication(new MatrixMax(MF, 0, Main.SIZE).compute(), T, 0, Main.SIZE).compute();
        System.out.printf("Thread3 O = %s%n", Arrays.toString(T));
        System.out.println("Thread3 finish");
    }
}
