public class TestThread {
  public static Object lock1 =  new Object();
  public static Object lock2 = new Object();

  public static void main(String[] args) {
      System.out.println("6");
      ThreadDemo1 t1 = new ThreadDemo1();
      ThreadDemo2 t2 = new ThreadDemo2();
      t1.start();
      t2.start();
  }

  // --> thread 2 has lock 2 but needs lock 1
  // --> thread 1 has lock 1 but needs lock 2

  private static class ThreadDemo1 extends Thread {
      public void run() {
          synchronized(lock1) {
              System.out.println("1 Thread 1: Holding lock 1");
              try { Thread.sleep(10); }
              catch(InterruptedException e) {}
              System.out.println("3  Thread 1: Waiting for lock 2. ");

              synchronized (lock2) {
                  System.out.println("Thread 1: Holding lock 1 & 2 ");
              }

          }
      }
  }

  private static class ThreadDemo2 extends Thread {
      public void run() {
          synchronized (lock2) {
              System.out.println("2 Thread 2: Holding lock 2...");
              try { Thread.sleep(10); }
              catch(InterruptedException e) {}
              System.out.println("4 Thread 2: Waiting for lock 1. ");
              synchronized(lock1) {
                  System.out.println("Thread 2: holding lock 1 & 2");
              }
          }
      }
  }


}
