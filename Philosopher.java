import PhilosopherSolution.Chopstick;

import java.util.Random;

public class Philosopher {


    public static void main(String[] args) throws InterruptedException {


        class ChopStick {
            //private int pos;
            private int id;
            public ChopStick(int id) {
                this.id = id;

            }

           public int getId() {
                return this.id;
            }
        }

        class Philos extends Thread {
            private ChopStick left, right;
            private Random random;
            private int id;
            public Philos(ChopStick left, ChopStick right, int id) {
                this.left = left;
                this.right = right;
                random = new Random();
                this.id = id;
            }

            public ChopStick getLeft() {
                return this.left;
            }

            public void run() {
                try {
                    while (true) {
                        //System.out.println("hi");
                        int num =  random.nextInt(1000);
                        System.out.println("num: "  + num);
                        Thread.sleep(num);
                        synchronized (left) {
                            System.out.println("Philosopher: " +  this.id + " left locked: " + left.getId());
                            synchronized (right) {
                                System.out.println("Philosopher: " +  this.id + " right locked: " + right.getId());
                                Thread.sleep(random.nextInt(1000));
                            }
                        }
                    }
                } catch(InterruptedException e) {}
            }
        }
              ChopStick c = new ChopStick(0);
              ChopStick c1;
              Philos first = null;
        Philos[] arr = new Philos[5];
            for(int i = 0; i < 5; i++) {
                if(i == 4) {
                    c1 = first.getLeft();
                } else {
                    c1 = new ChopStick((i + 1) % 5);
                }
                Philos p = new Philos(c1, c, i);
                if(i == 0) {
                    first = p;
                }
                c = c1;
                arr[i] = p;
            }

            for(int i = 0; i < 5; i++) {
                arr[i].start();
            }
        for(int i = 0; i < 5; i++) {
            arr[i].join();
        }

        }

}







