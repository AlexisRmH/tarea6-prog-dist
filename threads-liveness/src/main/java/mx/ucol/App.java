package mx.ucol;

public class App {
    public static void main(String[] args) {
        Drop drop = new Drop();

        //Creating and starting produce threads
        Thread[] producerThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            producerThreads[i] = new Thread(new Producer(drop));
            producerThreads[i].start();
        }

        //Creating and starting consumer threads
        Thread[] consumerThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            consumerThreads[i] = new Thread(new Consumer(drop));
            consumerThreads[i].start();
        }
    }
}