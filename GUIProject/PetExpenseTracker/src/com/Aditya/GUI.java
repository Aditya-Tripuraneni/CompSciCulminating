package com.Aditya;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.*;


public class GUI extends JFrame implements ActionListener {
    JLabel name, type, cost, title, view;
    JButton submit, viewData, saveData, deletePet, help, search;
    JTextArea viewText;
    JTextField nameEntry, costEntry, typeEntry, generalEntry;
    JPanel leftPanel, rightPanel, topPanel, centrePanel, middleCentrePanel, bottomCentrePanel;
    JFrame frame = new JFrame();
    JComboBox comboBox, SUDComboBox;
    String[] data;
    ArrayList<Pet> pets;


    public GUI(){

        super("PET EXPENSE TRACKER");
        pets = readCSVFile("C:\\Users\\Desktop\\Downloads\\GUIProject\\PetExpenseTracker\\src\\PetData.csv");
        apiAttempt();

        frame.setSize(800, 700);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        //TEXT
        title = createText("Welcome to the Pet Expense Tracker!", new Color(34, 255, 0), new Font("MV boli", Font.BOLD, 30), true);
        name = createText("Enter Pet Name!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        cost = createText("Enter Cost!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        type = createText("Enter Item!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        view = createText("View By: ", new Color(255, 145, 255), new Font ("MV boli", Font.BOLD, 20), true);


        //TEXT FIELDS
        nameEntry = new JTextField();
        nameEntry.setPreferredSize(new Dimension (120,120));

        costEntry = new JTextField();
        costEntry.setPreferredSize(new Dimension(120, 120));

        typeEntry = new JTextField();
        typeEntry.setPreferredSize(new Dimension (120, 120));
        
        generalEntry = new JTextField();
        generalEntry.setPreferredSize(new Dimension(120, 50));



        //---------------SUB PANELS---------------------------------------
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color (255, 145, 255));
        leftPanel.setPreferredSize(new Dimension(200,100));

        topPanel = new JPanel();
        topPanel.setBackground(new Color(88, 165, 255));
        topPanel.setPreferredSize(new Dimension (100,100));

        centrePanel = new JPanel();
        centrePanel.setBackground(new Color(0, 255, 166));
        
        middleCentrePanel = new JPanel();
        middleCentrePanel.setLayout(new BorderLayout());
        middleCentrePanel.setBackground(new Color(0, 255, 166));

        bottomCentrePanel = new JPanel();
        bottomCentrePanel.setLayout(new FlowLayout());
        bottomCentrePanel.setBackground(new Color (0, 255, 166));
        
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color (0, 255, 166));

        //---------------SUB PANELS---------------------------------------


        //-----------------BUTTONS---------------------------------------
        submit = new JButton ("Submit" );
        submit.setBackground(Color.white);
        submit.addActionListener(this);
       
        deletePet = new JButton("Delete Pet");
        deletePet.setBackground(Color.red);
        deletePet.setPreferredSize(new Dimension(100,50)); //This is how to resize buttons

        help = new JButton("Help");
        help.setBackground(Color.green);
        help.setPreferredSize(new Dimension(100,50));
        
        search = new JButton("GO!");
        search.setBackground(new Color(11, 106, 199));
        search.setPreferredSize(new Dimension(100, 50));

        viewData = new JButton ("View Data");
        viewData.setPreferredSize(new Dimension(100,50));
        viewData.setBackground(Color.yellow);

        saveData = new JButton ("Save Data");
        saveData.setPreferredSize(new Dimension(100,50));
        saveData.setBackground(Color.yellow);
        //-----------------BUTTONS---------------------------------------
        
        
        
        
        
        //------------------COMBO BOXES----------------------------------
        String[] messages = {"Name (A to Z)", "Cost (Low to High)", "Cost (High to Low)"};
        comboBox = new JComboBox(messages);
        comboBox.setBackground(Color.white);

        String[] searchDeleteUpdateOptions = {"Search Pet", "Delete Pet", "Update Pet"};
        SUDComboBox = new JComboBox(searchDeleteUpdateOptions);
        SUDComboBox.setBackground(Color.white);
        //------------------COMBO BOXES----------------------------------


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
        
        centrePanel.setLayout(new BorderLayout());

        middleCentrePanel.setLayout(new FlowLayout());
        middleCentrePanel.add(view);
        middleCentrePanel.add(comboBox);
        middleCentrePanel.add(viewData);
        middleCentrePanel.add(saveData);

        bottomCentrePanel.add(SUDComboBox);
        bottomCentrePanel.add(search);
        bottomCentrePanel.add(generalEntry);


        
        rightPanel.setLayout(new GridLayout(14,100,70,10));
        rightPanel.add(help);

        frame.add(centrePanel, BorderLayout.CENTER);
        centrePanel.add(middleCentrePanel, BorderLayout.NORTH);
        centrePanel.add(bottomCentrePanel, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(rightPanel, BorderLayout.EAST);
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

    /*************************************************************************************
     METHOD:  addRecord()
     @Param: Pet pet <Br>
        Pet is passed to be added to CSV file
     @Param: String fileName <Br>
        Pet is added to accordingly based on file
     @Purpose: Adds new pets to CSV file database
     ***********************************************************************************/
    private void addRecord(Pet pet, String filename){
        try{
            FileWriter fw = new FileWriter(filename, true); //true just means append data
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            int n = Integer.parseInt(data[0]);
            pw.println(n+1+ ","+pet.name + "," + pet.type + "," + pet.cost);
            pw.flush(); //makes sure all data is written
            pw.close(); //closes file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*************************************************************************************
     METHOD:  reasdCSVFile()
     @Param: String fileName <Br>
        User passes filename to be read
     @Purpose: reads CSV file, converting data into objects stored in arraylist
     ***********************************************************************************/
    private ArrayList<Pet> readCSVFile(String fileName){
        ArrayList<Pet> pets = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                data = line.split(",");
                if (!data[1].equals("Name") && !data[2].equals("Item") && !data[3].equals("Cost"))
                    pets.add(new Pet(data[1], data[2], Double.parseDouble(data[3])));
                for (String i:data)
                    System.out.printf("%-15s", i);
                System.out.println(Arrays.toString(data));
            }
                System.out.println();
            reader.close();
        }
        catch(Exception e){
            System.out.println("Something went wrong when reading data file");
            e.printStackTrace();
        }
        return pets;
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

    private void apiAttempt()  {
        try {
            String key = "5f7a027211e426d583d92fb83dcf2c29";
            URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q={Toronto},{+1}&appid=5f7a027211e426d583d92fb83dcf2c29");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int resp = conn.getResponseCode();
            System.out.println(resp);
            System.out.println(conn.getResponseMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
