import java.util.*;
import java.util.concurrent.BlockingQueue;

public class CookThread implements Runnable {

    private List<Food> foodList;
    private BlockingQueue<Food> queue;

    public CookThread(List<Food> foodList, BlockingQueue<Food> queue) {
        this.foodList = foodList;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < foodList.size(); i++) {
            try {
                System.out.println("COOK READY");

                Food foodCooking = foodList.get(i);
                if (queue.size() == 3) {
                    System.out.println("COOK WAITING");
                }
                System.out.println("COOK STARTING: " + foodCooking.toString());
                Thread.sleep(foodCooking.getCookTime());
                System.out.println("COOK ENDING: " + foodCooking.toString());
                queue.put(foodCooking);
//                queue.notify();
            } catch (InterruptedException ex) {}
        }
     }
}
