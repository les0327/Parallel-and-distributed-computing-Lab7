package les.pdc.lab7;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveAction;

@AllArgsConstructor
public class NumberVectorMultiplication extends RecursiveAction {

    private int num;
    private int[] A;
    private int start;
    private int length;


    @Override
    protected void compute() {
        if (length <= Main.LIMIT) {
            computeDirectly(num, A, start, length);
        } else {
            int split = length / 2;
            invokeAll(
                    new NumberVectorMultiplication(num, A, start, split),
                    new NumberVectorMultiplication(num, A, start + split, length - split)
            );
        }
    }

    /**
     * Multiplication vector A with number num
     * @param num - number
     * @param A   - vector
     */
    private void computeDirectly(int num, int[] A, int start, int length) {
        for (int i = start; i < start + length; i++)
            A[i] *= num;
    }
}
