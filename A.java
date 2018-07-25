public class A {
    synchronized void methodA(B b)
    {
        System.out.println("4 methodB: " + Thread.currentThread().getName());
        b.last();
    }

    synchronized void last()
    {
      System.out.println("9 methodB: " + Thread.currentThread().getName());
      System.out.println(" Inside A.last()");
    }
}

class B
{
 synchronized void methodB(A a)
 {
  System.out.println("17 methodB: 37: " + Thread.currentThread().getName());
  a.last();
 }

synchronized void last()
 {
     System.out.println("23 last: " + Thread.currentThread().getName());
     System.out.println(" Inside B.last()");
 }
}

class Deadlock implements Runnable
{
    A a = new A();
    B b = new B();

        // Constructor
    Deadlock()
    {
      Thread t = new Thread(this);
      System.out.println("start: 35 " + Thread.currentThread().getName());
      t.start();
      System.out.println("start: 37 " + Thread.currentThread().getName());
      a.methodA(b);
    }

    public void run()
{
    System.out.println("run: 37" + Thread.currentThread().getName());
    b.methodB(a);
}

  public static void main(String args[] )
 {
  new Deadlock();
 }
}