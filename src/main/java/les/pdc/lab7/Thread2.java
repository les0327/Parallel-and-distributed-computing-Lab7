package les.pdc.lab7;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class Thread2 extends RecursiveAction {

    @Override
    protected void compute() {
        int[][] MF = new int[Main.SIZE][Main.SIZE];
        int[][] MG = new int[Main.SIZE][Main.SIZE];
        int[][] MH = new int[Main.SIZE][Main.SIZE];
        int[][] MY = new int[Main.SIZE][Main.SIZE];
        int[][] ML = new int[Main.SIZE][Main.SIZE];

        for (int i = 0; i < Main.SIZE; i++) {
            Arrays.fill(MF[i], Main.value);
            Arrays.fill(MG[i], Main.value);
            Arrays.fill(MH[i], Main.value);
        }

        System.out.println("Thread2 start");
        new MatrixMultiplication(MG, MH, MY, 0, Main.SIZE).compute();
        new MatrixAddition(MF, MY, ML, 0, Main.SIZE).compute();
        new MatrixSort(ML, 0, Main.SIZE).compute();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Main.SIZE; i++)
            sb.append(Arrays.toString(ML[i])).append("\n");
        System.out.printf("Thread2 ML = %n%s", sb.toString());
        System.out.println("Thread2 finish");
    }
}
