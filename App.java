import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    private static StringBuffer lines = new StringBuffer();

    public static void modifyLines() {
        System.out.println("Modify lines with " + Thread.currentThread().getName());
        synchronized (lines) {
            System.out.println("Entering modify lines() synchronised " + Thread.currentThread()
            .getName());
            lines.append("Modified");
            System.out.println(lines.toString());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        synchronized (lines) {
            System.out.println("Entering main() synchronized by " + Thread.currentThread().getName());
            alienMethod();
        }
    }

    public static void alienMethod() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(new Runnable() {
            @Override
            public void run() {
               // lines.append("HI");
                System.out.println("lines: " + lines.toString());
                modifyLines();
            }
        });
        es.shutdown();
    }

}
