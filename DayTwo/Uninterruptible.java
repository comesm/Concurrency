package DayTwo;

public class Uninterruptible {


    public static void main(String[] args) throws InterruptedException {

        final Object o1 = new Object();
        final Object o2 = new Object();

        Thread t1 = new Thread() {
            public void run() {
                try {
                    synchronized (o1) {
                        System.out.println("Lock on o1 " + Thread.currentThread().getName());
                        Thread.sleep(1000);
                        synchronized (o2) {
                        System.out.println("Lock on o2 " + Thread.currentThread().getName());

                        }

                    }

                } catch (InterruptedException e) {
                    System.out.println("t1 interrupted");
                }
            }

        };


        Thread t2 = new Thread() {
            public void run() {
                try {
                    synchronized (o2) {
                        System.out.println("Lock on o2 " + Thread.currentThread().getName());
                        Thread.sleep(1000);
                        synchronized (o1) {
                        System.out.println("Lock on o1 " + Thread.currentThread().getName());
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("t2 interrupted");
                }
            }

        };
        t1.start();
        t2.start();
        Thread.sleep(2000);
        System.out.println(("HI"));
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();
    }

}
