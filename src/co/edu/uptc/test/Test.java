package co.edu.uptc.test;

import co.edu.uptc.structures.MyList;

public class Test {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();

        list.add("juan");
        list.add("luis");
        list.add("ana");

        System.out.println(list);
    }
}
