public class Main {
    static final int size = 100000000;
    static final int h = size / 4;
    static float[] arr = new float[size];
    public static void main(String[] args) throws InterruptedException {
        float[] arrPol = new float[h];
        float[] arrPol2 = new float[h];
        float[] arrPol3 = new float[h];
        float[] arrPol4 = new float[h];

        long b = System.currentTimeMillis();


        float[] polArr = new float[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=1;
        }
        System.out.println(System.currentTimeMillis()-b);

        long a = System.currentTimeMillis();

        Thread t1 = new Thread(() -> {
            System.arraycopy(arr, 0, arrPol, 0, h);
            for (int i = 0; i < arrPol.length; i++) {
                arrPol[i]=calc(i, arrPol[i]);
            }
        });
        Thread t2 = new Thread(() -> {
            System.arraycopy(arr, h, arrPol2, 0, h);
            for (int i = 0; i < arrPol2.length; i++) {
                arrPol2[i]=calc(i+h, arrPol2[i]);
            }
        });
        Thread t3 = new Thread(() -> {
            System.arraycopy(arr, h*2, arrPol3, 0, h);
            for (int i = 0; i < arrPol3.length; i++) {
                arrPol3[i]=calc(i+(h*2), arrPol3[i]);
            }
        });
        Thread t4 = new Thread(() -> {
            System.arraycopy(arr, h*3, arrPol3, 0, h);
            for (int i = 0; i < arrPol3.length; i++) {
                arrPol3[i]=calc(i+h*3, arrPol3[i]);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.arraycopy(arrPol, 0, polArr, 0, h);
        System.arraycopy(arrPol2, 0, polArr, h, h);
        System.arraycopy(arrPol3, 0, polArr, h*2, h);
        System.arraycopy(arrPol4, 0, polArr, h*3, h);
        System.err.println(System.currentTimeMillis()-a);
    }
    public static float calc (int i, float a){
        return a = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}