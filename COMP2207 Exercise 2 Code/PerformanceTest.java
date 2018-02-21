import java.io.*;

/**
 * Created by Hannah on 16/11/2016.
 */
public class PerformanceTest {

    public static void main (String[] args) throws IOException
    {
        String csvPath = args[0];
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath));
        int count = 0;
        int [] averages = new int[100];
        while ((line = bufferedReader.readLine()) != null)
        {
            String[] site = line.split(",");
            System.out.println(site[1]);
            new PingSite(site[1], count, averages);
            count++;
        }
    }
}
