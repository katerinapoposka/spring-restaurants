package cit;

import java.io.*;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PipeAndFilterProblem {

    public static void main(String[] args) throws IOException {
        ClassLoader loader = PipeAndFilterProblem.class.getClassLoader();
        cit.Pipe<String> pipe = new cit.Pipe<String>();
        File csv = new File("restorani.csv");
        FileOutputStream fos = new FileOutputStream(csv);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter fileWriter = new BufferedWriter(osw);

        id_Filter id_filter = new id_Filter();
        name_filter name_filter = new name_filter();
        column_filter column_filter = new column_filter();

        pipe.addFilter(column_filter);
        pipe.addFilter(id_filter);
        pipe.addFilter(name_filter);
        Scanner scanner = new Scanner(new File("C:\\Users\\PC\\Desktop\\PipeAndFilter\\src\\main\\resources\\restorani.csv"));
        String line = scanner.nextLine();
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String red = pipe.runFilters(line);
            if (!red.equals("")) {
                fileWriter.append(red).append("\n");
            }

        }
    }
}

