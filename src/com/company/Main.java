package com.company;

import logic.*;

public class Main {

    public static void main(String[] args) {


        DataBase dataBase = new DataBase();
        dataBase.init();
        CLI cli = new CLI();
        cli.init(dataBase);


	// write your code here
    }
}
