/**
 * Created by Hannah on 16/11/2016.
 */

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingSite {

    private int[] averages;

    public PingSite(String site, int count, int[] averages) throws IOException
    {
        this.averages = averages;
        String command = "ping -n 3 " + site + " -6";
        StringBuffer output = new StringBuffer();
        String [] times = new String[4];
        Process p;
        p = Runtime.getRuntime().exec(command);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        int i = 0, total = 0;
        while ((line = bufferedReader.readLine()) != null)
        {
            Pattern pattern = Pattern.compile("time=(\\d+)ms");
            Matcher m = null;
            m = pattern.matcher(line);
            if (m.find())
            {
                System.out.println(m.group(1));
                times[i] = m.group(1);
                i++;
            }
        }
        for (int j = 0; j < 3; j++)
        {
            if (times[j] != null)
            {
                total = total + Integer.parseInt(times[j]);
            }
        }
        averages[count] = total/3;
        System.out.println(count);
        System.out.println(averages[count]);
        if (count==99)
        {
            writeCSV();
        }
    }

    public void writeCSV() throws IOException
    {
        FileWriter fileWriter = new FileWriter(new File("C:/Users/Hannah/Documents/IPv6avgs.csv"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < 100; i++)
        {
            bufferedWriter.write(Integer.toString(averages[i]));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
