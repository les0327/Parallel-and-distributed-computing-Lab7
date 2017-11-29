package les.pdc.lab7;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class Scalar extends RecursiveTask<Integer> {

    private int[] a;
    private int[] b;
    private int start;
    private int length;


    @Override
    protected Integer compute() {
        if (length <= Main.LIMIT) {
            return computeDirectly(a, b, start, length);
        } else {
            int split = length / 2;
            Scalar s1 = new Scalar(a, b, start, split);
            s1.fork();
            Scalar s2 = new Scalar(a, b, start + split , length - split);
            s2.fork();
            return s1.join() + s2.join();
        }
    }

    public int computeDirectly(int[] a, int[] b, int start, int length) {
        int result = 0;
        for (int i = start; i < start + length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }
}
