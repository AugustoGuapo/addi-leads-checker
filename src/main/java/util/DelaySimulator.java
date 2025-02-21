package util;

import java.util.Random;

public class DelaySimulator {

    //Hidden constructor for utility class
    private DelaySimulator(){}

    public static void simulateDelay() {
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
