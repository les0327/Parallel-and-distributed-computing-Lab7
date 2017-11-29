package les.pdc.lab7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Main {

    public static final int SIZE  = 5;
    public static final int LIMIT = Main.SIZE / 10  + 1;
    public static final int value = 1;

    /**
     * Parallel and distributed computing.
     * Lab 7. Java ForkJoin Framework
     * Lisovyi Volodymyr
     * IO-53
     * 27.11.17
     * F1: e = (A*B) + (C*(D*(MA*MD)))
     * F2: ML = SORT(MF + MG*MH)
     * F3: O = MAX(MP*MR)*T
     */
    public static void main(String[] args) {
        long start, end;
        ForkJoinPool pool = new ForkJoinPool(2, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, false);

        System.out.println("Main start");
        start = System.currentTimeMillis();
        pool.invoke(new RecursiveAction() {
            @Override
            protected void compute() {
                ForkJoinTask task1 = new Thread1();
                task1.fork();
                ForkJoinTask task2 = new Thread2();
                task2.fork();
                ForkJoinTask task3 = new Thread3();
                task3.fork();
                task1.join();
                task2.join();
                task3.join();
            }
        });
        end = System.currentTimeMillis();
        System.out.println("Main finish");
        System.out.printf("ForkJoin time = %dms%n%n", end - start);
        System.out.println(pool);
        pool.shutdown();
    }

}
