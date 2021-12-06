import java.util.*;
import java.util.concurrent.BlockingQueue;

public class ServeThread implements Runnable {

    private List<Food> foodList;
    private BlockingQueue<Food> queue;

    public ServeThread(List<Food> foodList, BlockingQueue<Food> queue) {
        this.foodList = foodList;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < foodList.size(); i++) {
            try {
                System.out.println("SERVER READY");
                Food foodServing = queue.take();
                System.out.println("SERVER STARTING: " + foodServing.toString());
                Thread.sleep(foodServing.getServeTime());
                System.out.println("SERVER ENDING: " + foodServing.toString());
            } catch (InterruptedException e) {}
        }

    }
}
