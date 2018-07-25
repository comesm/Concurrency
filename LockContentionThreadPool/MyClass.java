package LockContentionThreadPool;

import java.util.concurrent.*;

public class MyClass {

    static Object o1 = new Object();

    public void test1() {
        System.out.println("10 test1 trying to get inside 01 lock: " + Thread.currentThread().getName());
        synchronized (o1) {
            System.out.println("12 inside o1 Lock: " + Thread.currentThread().getName());
            System.out.println("11 test1: "+ Thread.currentThread().getName());
        }
    }

    public void test3(SetObserver sc) throws InterruptedException {
        System.out.println("18 test3 before the lock: " + Thread.currentThread().getName());
        synchronized(o1) {
            System.out.println("inside o1 lock 19: " + Thread.currentThread().getName());
            for(int i = 0; i < 100; i++) {
                System.out.println("current process has lock on o1 18  Test 3 >> " + i + " thead: " +Thread.currentThread().getName());
                sc.added(this, sc);
                System.out.println("line 23 trying to access lock on 24 ~~~~~~~~~: " +Thread.currentThread().getName());
                synchronized (sc) {
                    System.out.println("inside sc lock 23: " +Thread.currentThread().getName());
                    System.out.println("21 <<: " +Thread.currentThread().getName());
                }
            }
        }
    }
    //1: thread 1 has lock on sc - trying to get o1
    //2: main has lock on o1 - trying to get sc
    //3: thread 2 trying to get lock on sc


    public static void main(String[] args) throws InterruptedException {
        MyClass mc = new MyClass();
        mc.test3(new SetObserver() {
            @Override
            public void added(MyClass mc, SetObserver sc) {
                System.out.println(" 32 added " + Thread.currentThread().getName());
                //this code causes deadlock
                ExecutorService xc = Executors.newFixedThreadPool(1);
                xc.execute(new Runnable() {
                   @Override
                   public void run() {
                       System.out.println("44 Trying to get a lock on sc " + Thread.currentThread().getName());
                       synchronized (sc) {
                           System.out.println("46 inside lock on sc" + Thread.currentThread().getName());
                           System.out.println("inside sc lock 47"+ Thread.currentThread().getName());
                           System.out.println("48 Calling test 1"+ Thread.currentThread().getName());
                           mc.test1();
                       }
                   }
                });
            }
        });
    }
}
