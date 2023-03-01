package com.threads;

class DeadLock2 {
	static class SharedClass {
		private int counter1 = 0;
		private int counter2 = 0;

		private final Object lock1 = new Object();
		private final Object lock2 = new Object();

		public void incrementCounter1() {
			synchronized (lock1) {
				this.counter1++;
				System.out.println("Calling 2");
				incrementCounter2();
				System.out.println("Called 2");
			}
		}

		public void incrementCounter2() {
			synchronized (lock2) {
				this.counter2++;
				System.out.println("Calling 1");
				incrementCounter1();
				System.out.println("Called 1");
			}
		}
	}

	public static void main(final String [] args) {
		final SharedClass sharedObject = new SharedClass();

		final Thread thread1 = new Thread(() -> {

			sharedObject.incrementCounter1();

		});

		final Thread thread2 = new Thread(() -> {

			sharedObject.incrementCounter2();

		});

		thread1.start(); //since the threads are accessing functions which have synchronized blocks of code that use different locks, both are free to call separately
		thread2.start();
	}
}

class SyncDifferentObject {
	static class SharedClass {
		private final int counter = 0;

		public synchronized void increment(final String s) {
			System.out.println(s);
		}
	}

	public static void main(final String [] args) {
		final SharedClass sharedObject1 = new SharedClass();
		final SharedClass sharedObject2 = new SharedClass();

		final Thread thread1 = new Thread(() -> {
			while (true) {
				sharedObject1.increment("thread1");
			}
		});

		final Thread thread2 = new Thread(() -> {
			while (true) {
				sharedObject2.increment("thread2");
			}
		});

		thread1.start(); //since we are calling on different objects, both of them will execute at same time
		thread2.start();
	}
}

class SyncOfDiffLock {
	static class SharedClass {
		private int counter1 = 0;
		private int counter2 = 0;

		private final Object lock1 = new Object();
		private final Object lock2 = new Object();

		public void incrementCounter1() {
			synchronized (lock1) {
				this.counter1++;
			}
		}

		public void incrementCounter2() {
			synchronized (lock2) {
				this.counter2++;
			}
		}
	}

	public static void main(final String [] args) {
		final SharedClass sharedObject = new SharedClass();

		final Thread thread1 = new Thread(() -> {
			while (true) {
				sharedObject.incrementCounter1();
			}
		});

		final Thread thread2 = new Thread(() -> {
			while (true) {
				sharedObject.incrementCounter2();
			}
		});

		thread1.start(); //since the threads are accessing functions which have synchronized blocks of code that use different locks, both are free to call separately
		thread2.start();
	}
}

class SyncSameObject {
	static class SharedClass {
		private final int counter = 0;

		public synchronized void increment(final String s) {
			System.out.println(s);
		}
	}

	public static void main(final String [] args) {
		final SharedClass sharedObject1 = new SharedClass();

		final Thread thread1 = new Thread(() -> {
			while (true) {
				sharedObject1.increment("thread1");
			}
		});

		final Thread thread2 = new Thread(() -> {
			while (true) {
				sharedObject1.increment("thread2");
			}
		});

		thread1.start(); //since we are calling on same objects, both of them cannot execute at same time
		thread2.start();
	}
}


public class SyncThread {
	public static void main(final String [] args) {
		//SyncDifferentObject.main(null);
		//  SyncOfDiffLock.main(null);
		// DeadLock.main(null);
		SyncSameObject.main(null);
	}
}