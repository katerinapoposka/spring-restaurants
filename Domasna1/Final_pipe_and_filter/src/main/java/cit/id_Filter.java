package cit;

import cit.Filter;

public class id_Filter implements Filter<String> {


    @Override
    public String execute(String input) {
        String[] fields=input.split(",");
        String id="";
        if (!fields[0].equals("id")){
            fields[0] = fields[0].replace("/", " ").split(" ")[1];
        }

        return String.join(",",fields);
    }
}
