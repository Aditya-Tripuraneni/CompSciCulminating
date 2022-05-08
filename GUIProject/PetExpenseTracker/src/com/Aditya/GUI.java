package com.Aditya;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GUI extends JFrame implements ActionListener {
    JLabel name, type, cost, title, view;

    JButton submit, viewData, saveData;
    JTextArea viewText;
    JTextField nameEntry, costEntry, typeEntry;
    JPanel leftPanel, rightPanel, topPanel, bottomPanel, centrePanel;
    JFrame frame = new JFrame();


    public GUI(){

        super("PET EXPENSE TRACKER");
        readCSVFile("C:\\Users\\Desktop\\Downloads\\GUIProject\\PetExpenseTracker\\src\\PetData.csv");

        Font font = new Font("SansSerif", Font.BOLD, 22);
        frame.setSize(800, 700);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        //TEXT
        title = createText("Welcome to the Pet Expense Tracker!", new Color(34, 255, 0), new Font("MV boli", Font.BOLD, 30), true);
        name = createText("Enter Pet Name!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        cost = createText("Enter Cost!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        type = createText("Enter Item!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);

        //TEXT FIELDS
        nameEntry = new JTextField();
        nameEntry.setPreferredSize(new Dimension (120,120));

        costEntry = new JTextField();
        costEntry.setPreferredSize(new Dimension(120, 120));

        typeEntry = new JTextField();
        typeEntry.setPreferredSize(new Dimension (120, 120));


        //---------------SUB PANELS---------------------------------------
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color (255, 145, 255));
        leftPanel.setPreferredSize(new Dimension(200,100));

        topPanel = new JPanel();
        topPanel.setBackground(new Color(88, 165, 255));
        topPanel.setPreferredSize(new Dimension (100,100));

        centrePanel = new JPanel();
        centrePanel.setBackground(new Color(0, 255, 166));

        //---------------SUB PANELS---------------------------------------


        //-----------------BUTTONS---------------------------------------
        submit = new JButton ("Submit" );
        submit.setBackground(Color.white);
        submit.addActionListener(this);


        viewData = new JButton ("View Data");

        viewData.setBackground(Color.white);
        saveData = new JButton ("Save Data");
        saveData.setBackground(Color.white);
        //-----------------BUTTONS---------------------------------------


        //PANEL LAYOUTS
        leftPanel.setLayout(new GridLayout(7,1,5,5));
        leftPanel.add(submit);
        leftPanel.add(name);
        leftPanel.add(nameEntry);
        leftPanel.add(cost);
        leftPanel.add(costEntry);
        leftPanel.add(type);
        leftPanel.add(typeEntry);


        topPanel.add(title);

        centrePanel.setLayout(new FlowLayout());
        centrePanel.add(viewData);
        centrePanel.add(saveData);

        frame.add(centrePanel, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(topPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit)
        {
            if (verifyText(costEntry.getText()))
            {
                String name = nameEntry.getText();
                String type = typeEntry.getText();
                double cost = Double.parseDouble(costEntry.getText());
                addRecord(new Pet(name , type, cost), "C:\\Users\\Desktop\\Downloads\\GUIProject\\PetExpenseTracker\\src\\PetData.csv");
            }
            nameEntry.setText("");
            costEntry.setText("");
            typeEntry.setText("");

        }
    }

    private void addRecord(Pet pet, String filename){
        try{
            FileWriter fw = new FileWriter(filename, true); //true just means append data
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(pet.name + "," + pet.type + "," + pet.cost);
            pw.flush(); //makes sure all data is written
            pw.close(); //closes file

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCSVFile(String filename){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(",");
                for (String i:data)
                    System.out.printf("%-10s", i);
            }
                System.out.println();
            reader.close();
        }
        catch(Exception e){
            System.out.println("Something went wrong when reading data file");
            e.printStackTrace();
        }

    }


    /*************************************************************************************
     METHOD:  verifyText()
     @Param: String field <Br>
        Text to verify is passed
     @Purpose: Checks if costEntry is valid data type
     ***********************************************************************************/
    private  boolean verifyText(String field){
        try{
            Double.parseDouble(field);
            return true;
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("Make sure you are entering a number");
            return false;
        }
    }

    /*************************************************************************************
       METHOD:  createText()
       @Param: String nameOFText <Br>
            Enter text you want
       @Param: Color colorText <Br>
            Object Color is passed in with RGB values desired
       @Param: Font font
            Font object is passed, user picks font, customized text and size
       @Param: boolean aligned
            If set to true, text is aligned else text is not aligned
       @Purpose: Used to create text on GUI without writing multiple lines of code.
     ***********************************************************************************/
    private  JLabel createText(String nameOFText, Color colorText, Font font, boolean aligned){
        JLabel text = new JLabel(nameOFText);
        if (aligned)
        {
            text.setHorizontalAlignment(JLabel.CENTER);
            text.setVerticalAlignment(JLabel.CENTER);
        }

        text.setForeground(colorText);
        text.setFont(font);
        return text;
    }
}
