package br.edu.ifg;

import org.w3c.dom.ls.LSOutput;

class LapTop{
    String model;
    double price;
}

public class App {
    public static void main(String[] args) {
        LapTop lpa = new LapTop();
        System.out.println(lpa.hashCode());
        lpa.model = "m 1";
        lpa.price = 7000;
        System.out.println(lpa.hashCode());

    }
}
