package com.Aditya;
import javax.swing.*;


/***********************************************************************************
 * The Main class
 * This class allows GUI to be instantiated and the app can run within this class
 * Contains main method
 ***********************************************************************************/
public class Main {
    private static final long serialVersionUID = 1L;
    /***********************************************************
     * main method <BR>
     *     Instantiate GUI class and calls init method
     * @Param: String[] args
     ***********************************************************/
    public static void main(String[] args) {
      GUI g = new GUI();
      g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
