package com.Aditya;


/***********************************************************************************
 * The Pet class
 * This class allows pets to be instantiated and they can be stored in the data file
 ***********************************************************************************/
public class Pet{
    String name;
    String type;
    double cost;
    int id;
    public Pet(String name, String type, double cost, int id)
    {
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.id = id;
    }


    public String toString() {
        return this.name + " " + this.type + " " + this.cost + " " + this.id;
    } 
}
