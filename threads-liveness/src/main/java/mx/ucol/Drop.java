package mx.ucol;

import java.lang.reflect.Array;

public class Drop {
    private String[] buffer = new String[10];
    private String message;

    public synchronized String take() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Someone interrupted this thread." + e);
            }
        }

        for (int i = 0; i < 10; i++){
            if (buffer[i] != null){
                this.message = buffer[i];
                buffer[i] = null;
                break;
            }
        }

        notifyAll();
        return this.message;

    }

    public synchronized void put(String message) {
        while (!isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }

        for (int i = 0; i < 10; i++){
            if (buffer[i] == null){
                buffer[i] = message;
                break;
            }
        }

        notifyAll();
    }

    public boolean isEmpty() {
        for (int i = 0; i < 10; i++) {
            if(buffer[i] != null) {
                return false;
            }
        }
        return true;
    }
}
