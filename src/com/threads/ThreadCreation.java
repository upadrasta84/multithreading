package com.threads;

public class ThreadCreation {

    public static void main(String[] args) {

        thread1();
        thread2();
        thread3();
        thread4();

        Thread thread1 = new TaskThread1();
        thread1.start();

        Thread thread2 = new Thread(new Task2());
        thread2.start();
    }

    private static void thread1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Code that will run in  a new thread
                System.out.println("we are now in thread "+Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });
        thread.setName("New Worker Thread");

        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName()+ " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName()+ " after starting a new thread");
    }

    //using lambdas
    private static void thread2() {
        new Thread( () ->{
            System.out.println("we are now in thread "+Thread.currentThread().getName());
            System.out.println("Current thread priority is " + Thread.currentThread().getPriority());

        }).start();
    }

    //thread with exception handler
    private static void thread3() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Code that will run in  a new thread
                throw new RuntimeException("my test exception");
            }
        });
        thread.setName("New Worker Thread3");

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName()
                        + " the error is " + e.getMessage());
            }
        });
        thread.start();
    }

    private static void thread4() {
        Runnable runnable = () -> {
            System.out.println("hello runnable");
        };
        Thread t = new Thread(runnable);
        t.start();
    }

    public static class TaskThread1 extends Thread {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }

    public static class Task2 implements Runnable {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }

}
