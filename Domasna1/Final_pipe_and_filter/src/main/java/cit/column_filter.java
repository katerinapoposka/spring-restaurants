package cit;

import java.util.Arrays;

public class column_filter implements Filter<String>{

    @Override
    public String execute(String input) {
        String [] fields= input.split(",");
        String res="";
        int[] indecies={0,1,5,7,9,10,12,14,15,19,fields.length-2,fields.length-1};
        for(int i =0;i<indecies.length-1;i++)
        {
           res+=fields[indecies[i]]+",";
        }
        res+=fields[indecies[indecies.length-1]];
        return res;
    }
}
