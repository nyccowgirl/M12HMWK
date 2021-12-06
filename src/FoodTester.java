import java.util.*;
import java.util.concurrent.*;

public class FoodTester {

	public static void main(String[] args) {
		int iDEThreadCount = Thread.activeCount();
		 
		List<Food> foodList = new ArrayList<>();
		foodList.add(new Food("Spinach Dip", 2, 1));
		foodList.add(new Food("Burger", 5, 1));
		foodList.add(new Food("Pasta", 4, 3));
		foodList.add(new Food("Baked Alaska", 6, 20));
		foodList.add(new Food("Salad", 1, 1));
		foodList.add(new Food("Bruchetta", 3, 1));
		foodList.add(new Food("Bread", 1, 1));
		foodList.add(new Food("Fried Green Tomatoes", 2, 1));
		
		// INITIALIZE AND START YOUR THREADS HERE!
		BlockingQueue<Food> queue = new LinkedBlockingQueue<>(3);
		Thread cooker = new Thread(new CookThread(foodList, queue));
		Thread server = new Thread(new ServeThread(foodList, queue));
		cooker.start();
		server.start();
		
			
		int programTimeCounter=0;
		while(Thread.activeCount()>iDEThreadCount) {
			System.out.println("TIME " + programTimeCounter);
			programTimeCounter++;
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				
			}
		}
		
		// USE STREAMS HERE ON THE INITIAL LIST! 
		// NOTE: THIS PART HAS NOTHING TO DO WITH THE THREADS- JUST MORE STREAM PRACTICE! :)
		// USE METHOD REFERENCES!
		int totalCookTime = foodList.stream()
				.mapToInt(Food::getCookTime)
				.sum();
		int totalServeTime = foodList.stream()
				.mapToInt(Food::getServeTime)
				.sum();
		int programTimeCounterAlt = foodList.stream()
				.mapToInt(x -> Math.max(x.getCookTime(), x.getServeTime()))
				.sum();
		System.out.println("Total Cook Time = " + totalCookTime);
		System.out.println("Total Serve Time = " + totalServeTime);	
		System.out.println("Program Time = " + programTimeCounterAlt);
		// Think this should be 41 instead of 38.
	}

}
