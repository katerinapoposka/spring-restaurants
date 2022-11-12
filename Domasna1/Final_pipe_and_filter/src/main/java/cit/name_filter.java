package cit;

public class name_filter implements Filter<String> {

    @Override
    public String execute(String input) {
        String[] fields=input.split(",");
        String name;
        name=fields[3];
        if(name.equals("")) return "";
        else return input;
    }
}
