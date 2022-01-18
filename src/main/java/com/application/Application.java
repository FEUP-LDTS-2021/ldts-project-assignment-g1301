package com.application;

import com.menu.Menu;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try{
            Menu m = new Menu();
            m.interactions();
        }
        catch(IOException e){
            e.printStackTrace();;
        }
    }
}