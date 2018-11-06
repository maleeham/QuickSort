package msk180001;

import java.util.Random;





/**
 * Two methods to perform quick sort based on 2 partition algorithms
 * 
 * @author  Maleeha Koul msk180001
 *
 * @param <T> The data type on which QuickSort is being performed
 */

public class QuickSort<T> {

	
	public static <T extends Comparable<? super T>> void quickSort(T[] a){
		quickSort(a, 0, a.length - 1);
	}
	
	private static  <T extends Comparable<? super T>> void quickSort(T[] a, int p, int r) {
		if(p < r){
			int q = partition(a, p, r);
			quickSort(a, p, q-1);
			quickSort(a, q + 1, r);
		}
	}
	/**
	 * Partition take 1
	 * @param a : The array of elements
	 * @param p : Starting index of the partition
	 * @param r : Starting index of the partition
	 * @return : Returns the index around which the partition is done
	 */
	static <T extends Comparable<? super T>> int partition(T[] a, int p, int r) {
		double random = Math.random();
		int piv = p + (int)(random * (r - p));
		exchange(a, piv, r);
		T x = a[r];
		int i = p - 1;
		for(int j = p; j < r; j++){
			if(a[j].compareTo(x) < 0){
				i++;
				exchange(a, i, j);
			}
		}
		exchange(a, i+1, r);	
		return i + 1;
	}
	
	
	public static <T extends Comparable<? super T>> void quickSort1(T[] a){
		quickSort1(a, 0, a.length - 1);
	}
	
	private static  <T extends Comparable<? super T>> void quickSort1(T[] a, int p, int r) {
		if(p < r){
			int q = partition1(a, p, r);
			quickSort1(a, p, q);
			quickSort1(a, q + 1, r);
		}
	}

	/**
	 * Partition take 1
	 * 
	 * @param a : The array of elements
	 * @param p : Starting index of the partition
	 * @param r : Starting index of the partition
	 * @return : Returns the index around which the partition is done
	 */
	private static <T extends Comparable<? super T>> int partition1(T[] a, int p, int r) {
		double random = Math.random();
		int piv = p + (int)(random * (r - p));
		T x = a[piv];
		int i = p - 1;
		int j = r + 1;
		while(true){
			do{
				i++;
			} while(a[i].compareTo(x)< 0);
			
			do{
				j--;
			}while(a[j].compareTo(x)> 0);
			
			if(i >= j){
				return j;
			}
			exchange(a, i, j);

		}
	}

	/**
	 * Method to swap elements at position p and q
	 * 
	 * @param a : The array of elements
	 * @param p : One of the elements to be swapped
	 * @param q : One of the elements to be swapped
	 */
	private static <T extends Comparable<? super T>> void exchange(T[] a, int p, int q) {
		T temp = a[p];
		a[p] = a[q];
		a[q] = temp;
	}
	
	/**
	 * Sample driver class 
	 * @param args
	 */

	/*public static void main(String[] args) {
		Timer T = new Timer();

		int n = 10000000;
		Integer[] a1 = new Integer[n];					
		int j = 0;
		for(int i = a1.length - 1; i >= 0 ; i--){
			a1[i] = j++;								
		}
		Shuffle.shuffle(a1);							
		T.start();										
		QuickSort.quickSort1(a1);						
		System.out.println(T.end());
		for(int i = 10; i < 100; i++){
			System.out.print(a1[i] + "\t");
		}
		System.out.println();
		j = 0;
		for(int i = a1.length - 1; i >= 0 ; i--){
			a1[i] = j++;								//Assigning the position as the value to the Array
		}												//Shuffling the array make the data unsorted
		T.start();										
		QuickSort.quickSort(a1);						//Sorting the Array by calling MergeSort
		System.out.println(T.end());
		for(int i = 10; i < 100; i++){
			System.out.print(a1[i] + "\t");
		}
			
		
	}

*/

    public static void main(String[] args) {

        int n = 10000000;  int choice = random.nextInt(2)+1;
        if(args.length > 0) { n = Integer.parseInt(args[0]); }
        if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        Integer[] arr = new Integer[n];
        for(int i=0; i<n; i++) {
            arr[i] = i;
        }
        Timer timer = new Timer();
        System.out.println( choice);
        switch(choice) {
            case 1:
                Shuffle.shuffle(arr);
                //Shuffle.printArray(arr,"Original Array");
                //for(int i=0;i<)
                //printArray(arr);
               // System.out.println(arr.length);
               
                QuickSort.quickSort1(arr);
                //printArray(arr);
                break;
            case 2:
            	Shuffle.shuffle(arr);
            	QuickSort.quickSort(arr);
                //printArray(arr);
                break;// etc
        }
        timer.end();
        
        System.out.println("Choice: " + choice + "\n" + timer);
    }

	  /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

        public void scale(int num) { elapsedTime /= num; }

        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }

/** @author rbk : based on algorithm described in a book
/* Shuffle the elements of an array arr[from..to] randomly */
public static Random random = new Random();
public static class Shuffle {

    public static void shuffle(int[] arr) {
        shuffle(arr, 0, arr.length-1);
    }

    public static<T> void shuffle(T[] arr) {
        shuffle(arr, 0, arr.length-1);
    }

    public static void shuffle(int[] arr, int from, int to) {
        int n = to - from  + 1;
        for(int i=1; i<n; i++) {
            int j = random.nextInt(i);
            swap(arr, i+from, j+from);
        }
    }

    public static<T> void shuffle(T[] arr, int from, int to) {
        int n = to - from  + 1;
        Random random = new Random();
        for(int i=1; i<n; i++) {
            int j = random.nextInt(i);
            swap(arr, i+from, j+from);
        }
    }

    static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    static<T> void swap(T[] arr, int x, int y) {
        T tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static<T> void printArray(T[] arr, String message) {
        printArray(arr, 0, arr.length-1, message);
    }

    public static<T> void printArray(T[] arr, int from, int to, String message) {
        System.out.print(message);
        for(int i=from; i<=to; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }
}
}