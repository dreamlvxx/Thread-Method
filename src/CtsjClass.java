public class CtsjClass {
    public static void main(String[] args) {
        System.out.println("mainThread run");
        MyThread my = new MyThread();
        MyThread1 my1 = new MyThread1(my);
        my.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        my1.start();
        try {
            my1.join(); //这个join阻塞main线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("mainThread end");
    }


    static class MyThread extends Thread{
        public MyThread() {
            super("[Thread A]");
        }

        @Override
        public void run() {
            super.run();
            System.out.println(Thread.currentThread().getName() + "===start");
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "===do" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "===end");
        }
    }

    static class MyThread1 extends Thread{
        MyThread myThread;

        public MyThread1(MyThread myThread) {
            super("[Thread B]");
            this.myThread = myThread;
        }

        @Override
        public void run() {
            super.run();
            System.out.println(Thread.currentThread().getName() + "===start");
            try {
                myThread.join();//这个join阻塞B线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "===do" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "===end");
        }
    }



}
