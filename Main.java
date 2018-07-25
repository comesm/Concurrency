public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread() {
            public void run() {
                System.out.println("Hello from new thread");
            }
        };
        //concurrent execution with start
        //
        myThread.start();
        Thread.yield();
        //Thread.sleep(1000);
        System.out.println("Hello from a main thread");
        //waits for thread to terminate --happens when join returns
        myThread.join();
    }

}
