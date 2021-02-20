
import java.io.IOException;
import java.util.ArrayList;


public class CreatureMain {
    private static ArrayList<String> titles = new ArrayList<>();
    private static ArrayList<UniqueWords> uniqueWordsArrayList = new ArrayList<>();

    public static void main(String[] args) {
        Integer lock = 0;
        ArrayList<Thread> treadsArray = new ArrayList<>();
        for (String filename : args) {
            Thread t = new Thread(new Consideration(filename, titles, uniqueWordsArrayList, lock));
            treadsArray.add(t);
            t.start();
        }
        for (Thread t : treadsArray) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            try {
                new Describe(titles, uniqueWordsArrayList).createCSV();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

