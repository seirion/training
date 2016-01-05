
import javax.swing.JOptionPane;

public class Main {

    public static void main(String [] args) {
        MyThread t1 = new MyThread("*");
        MyThread t2 = new MyThread("**");
        MyThread t3 = new MyThread("***");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(2000);
            t1.suspend();
            Thread.sleep(2000);
            t2.suspend();
            Thread.sleep(3000);
            t1.resume();
            Thread.sleep(3000);
            t1.stop();
            t2.resume();
            t2.stop();
            Thread.sleep(2000);
            t3.stop();
        }
        catch (InterruptedException e) {}
    }
}

class MyThread implements Runnable {
    static final int RUNNING = 0;
    static final int SUSPENDED = 1;
    static final int STOPPED = 2;

    private int state = RUNNING;
    Thread t;

    MyThread(String name) {
        t = new Thread(this, name);
    }

    public synchronized void setState(int s) {
        state = s;
        if (s == RUNNING) {
            notify();
        }
        else {
            t.interrupt();
        }
    }

    public synchronized boolean checkState() {
        while (state == SUSPENDED) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        return state == STOPPED;
    }

    public void run() {
        String name = Thread.currentThread().getName();

        while (true) {
            System.out.println(name);
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
            }

            if (checkState()) {
                break;
            }
        }

        System.out.println(name + " - finished");
    }

    public void suspend() { setState(SUSPENDED); }
    public void resume() { setState(RUNNING); }
    public void stop() { setState(STOPPED); }

    public void start() { t.start(); }
}
