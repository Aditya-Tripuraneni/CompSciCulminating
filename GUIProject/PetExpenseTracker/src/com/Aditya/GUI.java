package com.Aditya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;



/************************************************************************
 * The GUI class
 * This class contains all code in order for GUI to run smoothly
 * Once class is instantiated, app starts with call to init method.
 *************************************************************************/
public class GUI extends JFrame implements ActionListener {
    String filepath = "C:\\Users\\Admin\\Downloads\\GUIProject-20220510T140826Z-001\\GUIProject\\PetExpenseTracker\\src\\PetData.csv";
    JLabel name, type, cost, title, view, text, location, countryCode, weather, id, enterID;
    JPanel pane = (JPanel)this.getContentPane();
    JButton submit, viewData, deletePet, help, search, weatherSubmit, settings;
    JTextField nameEntry, costEntry, typeEntry, IDEntry, generalEntry, locationEntry, countryCodeEntry;
    JPanel leftPanel, rightPanel, topPanel, centrePanel;
    static JPanel topCentrePanel, midCentrePanel, bottomCentrePanel;
    private final JComboBox comboBox, SUDComboBox;
    private String[] data;
    String[] sort = {"Name (A to Z)", "Cost (Low to High)", "Cost (High to Low)", "ID (Low to High)"};
    ArrayList<Pet> pets = readCSVFile(filepath);
    WeatherAPI weatherReport = new WeatherAPI();

    public GUI(){
        super("PET EXPENSE TRACKER");

        //---------------------------------------------------TEXT-----------------------------------------------------------------------------------------------------------
        title = createText("Welcome to the Pet Expense Tracker!", new Color(34, 255, 0), new Font("MV boli", Font.BOLD, 30), true);
        name = createText("Enter Pet Name!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        cost = createText("Enter Cost!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        type = createText("Enter Item!", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        id =  createText("Enter ID", new Color(91, 77, 255, 255), new Font("MV boli", Font.ITALIC, 20), false);
        view = createText("View By: ", new Color(255, 145, 255), new Font ("MV boli", Font.BOLD, 20), true);
        location = createText("Does your pet require walks? Get Weather Report! City:", new Color(246, 2, 2), new Font("MV boli", Font.CENTER_BASELINE, 20),false);
        countryCode =  createText("Country Code: ", new Color(246, 2, 2), new Font("MV boli", Font.CENTER_BASELINE, 20),false);
        weather = createText("Temperature in " + weatherReport.getRegion()+ " " + String.format("%.2f", weatherReport.getTemperature())+"C",
                new Color(246, 2,2), new Font("MV boli", Font.CENTER_BASELINE, 20), false);
        enterID = createText("Enter Pet ID: ", new Color(91, 77, 255, 255), new Font("MV boli", Font.BOLD, 20), false);
        //---------------------------------------------------TEXT-----------------------------------------------------------------------------------------------------------

        //-------------------------TEXT FIELDS---------------------------------------
        nameEntry = new JTextField();
        nameEntry.setPreferredSize(new Dimension (120,120));

        costEntry = new JTextField();
        costEntry.setPreferredSize(new Dimension(120, 120));

        typeEntry = new JTextField();
        typeEntry.setPreferredSize(new Dimension (120, 120));

        generalEntry = new JTextField();
        generalEntry.setPreferredSize(new Dimension(120, 50));

        locationEntry = new JTextField();
        locationEntry.setPreferredSize(new Dimension(120, 50));

        countryCodeEntry = new JTextField();
        countryCodeEntry.setPreferredSize(new Dimension(120, 50));

        IDEntry = new JTextField();
        IDEntry.setPreferredSize(new Dimension(120, 50));
        //-------------------------TEXT FIELDS---------------------------------------

        //---------------SUB PANELS---------------------------------------
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color (255, 145, 255));
        leftPanel.setPreferredSize(new Dimension(200,100));

        topPanel = new JPanel();
        topPanel.setBackground(new Color(88, 165, 255));
        topPanel.setPreferredSize(new Dimension (100,100));

        centrePanel = new JPanel();
        centrePanel.setBackground(new Color(0, 255, 166));

        topCentrePanel = new JPanel();
        topCentrePanel.setLayout(new BorderLayout());
        topCentrePanel.setBackground(new Color(0, 255, 166));

        midCentrePanel = new JPanel();
        midCentrePanel.setLayout(new FlowLayout());
        midCentrePanel.setBackground(new Color (0, 255, 166));

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
        deletePet.setPreferredSize(new Dimension(100,50));

        help = new JButton("Help");
        help.setBackground(Color.green);
        help.setPreferredSize(new Dimension(100,50));
        help.addActionListener(this);

        search = new JButton("GO!");
        search.setBackground(new Color(11, 106, 199));
        search.setPreferredSize(new Dimension(100, 50));
        search.addActionListener(this);

        viewData = new JButton ("View Data");
        viewData.setPreferredSize(new Dimension(100,50));
        viewData.setBackground(Color.yellow);
        viewData.addActionListener(this);

        settings = new JButton ("Settings");
        settings.setPreferredSize(new Dimension(100, 50));
        settings.setBackground(Color.red);
        settings.addActionListener(this);

        weatherSubmit = new JButton("Get Weather");
        weatherSubmit.setPreferredSize(new Dimension (150,50));
        weatherSubmit.setBackground(Color.yellow);
        weatherSubmit.addActionListener(this);
        //-----------------BUTTONS---------------------------------------

        //------------------COMBO BOXES----------------------------------
        comboBox = new JComboBox(sort);
        comboBox.setBackground(Color.white);

        String[] searchDeleteUpdateOptions = {"Search Pet", "Delete Pet", "Update Pet"};
        SUDComboBox = new JComboBox(searchDeleteUpdateOptions);
        SUDComboBox.setBackground(Color.white);
        //------------------COMBO BOXES----------------------------------

        //----------------------PANEL LAYOUTS------------------------------
        leftPanel.setLayout(new GridLayout(9,1,5,5));
        leftPanel.add(submit);
        leftPanel.add(name);
        leftPanel.add(nameEntry);
        leftPanel.add(cost);
        leftPanel.add(costEntry);
        leftPanel.add(type);
        leftPanel.add(typeEntry);
        leftPanel.add(id);
        leftPanel.add(IDEntry);

        topPanel.add(title);

        centrePanel.setLayout(new BorderLayout());

        topCentrePanel.setLayout(new FlowLayout());

        topCentrePanel.add(weather);
        topCentrePanel.add(view);
        topCentrePanel.add(comboBox);
        topCentrePanel.add(viewData);
        topCentrePanel.add(settings);

        midCentrePanel.add(enterID);
        midCentrePanel.add(SUDComboBox);
        midCentrePanel.add(search);
        midCentrePanel.add(generalEntry);

        bottomCentrePanel.add(location);
        bottomCentrePanel.add(locationEntry);
        bottomCentrePanel.add(countryCode);
        bottomCentrePanel.add(countryCodeEntry);
        bottomCentrePanel.add(weatherSubmit);

        rightPanel.setLayout(new GridLayout(14,100,70,10));
        rightPanel.add(help);

        JPanel pane = (JPanel)this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(centrePanel, BorderLayout.CENTER);
        centrePanel.add(topCentrePanel, BorderLayout.NORTH);
        centrePanel.add(midCentrePanel, BorderLayout.CENTER);
        centrePanel.add(bottomCentrePanel, BorderLayout.SOUTH);
        pane.add(leftPanel, BorderLayout.WEST);
        pane.add(topPanel, BorderLayout.NORTH);
        pane.add(rightPanel, BorderLayout.EAST);
        //----------------------PANEL LAYOUTS------------------------------

        setSize(800, 700);
        pack();
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == submit)
        {
            if (verifyTextToDouble(costEntry.getText()) && verifyInteger(IDEntry.getText()))
            {
                if(!checkIDExistance(Integer.parseInt(IDEntry.getText())))
                {
                    JOptionPane.showMessageDialog(pane, "Data submitted successfully");
                    String name = nameEntry.getText();
                    String type = typeEntry.getText();
                    addRecord(new Pet(name , type, Double.parseDouble(costEntry.getText()), Integer.parseInt(IDEntry.getText())), filepath);
                    pets.add(new Pet(name , type, Double.parseDouble(costEntry.getText()), Integer.parseInt(IDEntry.getText())));
                }
                else
                    JOptionPane.showMessageDialog(null, "Please make a different ID, this one is in use!", "Alert" , JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Please enter valid cost or ID!", "Alert", JOptionPane.INFORMATION_MESSAGE);
            nameEntry.setText("");
            costEntry.setText("");
            typeEntry.setText("");
            IDEntry.setText("");
        }

        else if (e.getSource() == viewData)
        {
            String selected = comboBox.getSelectedItem().toString();

            switch (selected)
            {
                case "Name (A to Z)" -> mergeName(pets);
                case "Cost (Low to High)" -> mergeLowByCost(pets);
                case "Cost (High to Low)" -> mergeHighByCost(pets);
                case "ID (Low to High)" -> mergeSortByID(pets);
            }
            displayPetData();
        }
        else if (e.getSource() == weatherSubmit)
        {
            weatherReport = new WeatherAPI(locationEntry.getText() , countryCodeEntry.getText());
            weather.setText("Temperature in " + weatherReport.getRegion() + " " + String.format("%.2f", weatherReport.getTemperature())+"C");
            locationEntry.setText("");
            countryCodeEntry.setText("");
        }

        else if(e.getSource() == search)
        {
            if (verifyInteger(generalEntry.getText()))
            {
                String selected = SUDComboBox.getSelectedItem().toString();
                if ((selected.equals("Search Pet")))
                {
                    Pet displayPet = searchPet(Integer.parseInt(generalEntry.getText()));

                    if (displayPet != null)
                        JOptionPane.showMessageDialog(null, "Name: " + displayPet.name + " \nItem: " + displayPet.type + "\nCost: $" + displayPet.cost, "Alert", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Could not find pet! Make sure ID exists!", "Alert", JOptionPane.INFORMATION_MESSAGE);
                }
                if (selected.equals("Delete Pet"))
                {
                    if (checkIDExistance(Integer.parseInt(generalEntry.getText())))
                    {
                        deletePet(generalEntry.getText(), filepath);
                        deletePet(Integer.parseInt(generalEntry.getText()));
                        JOptionPane.showMessageDialog(null, "Data successfully deleted", "Alert", JOptionPane.INFORMATION_MESSAGE);

                    }

                    else
                        JOptionPane.showMessageDialog(null, "Could not find ID to delete! Make sure the ID exists!", "Alert", JOptionPane.OK_OPTION);
                }

                if (selected.equals("Update Pet"))
                    if (checkIDExistance(Integer.parseInt(generalEntry.getText()))){
                        displayUpdatedPet();
                    }
            }
            else
                JOptionPane.showMessageDialog(null, "Please make sure you enter a number!", "Alert", JOptionPane.INFORMATION_MESSAGE);
            generalEntry.setText("");
        }
        else if (e.getSource() == help)
            displayHelp();

        else if (e.getSource() == settings)
        {
            SettingsUI settingsUI = new SettingsUI();
            settingsUI.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
    }


    /********************************************************************
     METHOD:  displayHelp()
     @Purpose: Explains how app works on GUI
     *********************************************************************/
    private void displayHelp()
    {
        JOptionPane.showMessageDialog(null,"Welcome to the Pet Expense Tracker! Use this software to help keep any expenses on your pets and more! Here's how to use the Expense Tracker:\n" +
                "\n" +
                "-To add any data for new  pets, look to the left part of the screen to input a pet's name, the cost of the item you purchased, what the item purchased is, and the ID of the item. \nOnce this is done, click submit and then the save data button to save this data into the records.\n" +
                "\n" +
                "-You can then view all expenses of your pets, by clicking on the bar that says Name(A to Z) and it will display all pet expenses based on the listing order of your choice. \nYou can click the view data button to show all your expenses in that order. \n" +
                "\n" +
                "-If you need to look for a pet or make changes to it, you can click on the bar that says Search Pet, and will show a list of options. \nYou can then click go, and it will show any data you've saved for that pet. \nIf you need to remove data for a pet, you can select the delete pet option in that bar, and it will delete all data for that pet. \nIf you want to make changes to any data for a pet, you can select the update pet option in the same bar, and then you will be able to update the data for that pet. \n" +
                "\n" +
                "-If you need to take your pet for a walk, you can check the weather in your city to make sure that you can do so. \nAt the bottom of the screen, enter the city you live in and the Country Code (make sure to include the + when typing the code in). \nClick get weather after that to see what the weather is like in that city.\n You may need to refresh app once data is entered to actually see physical change!\n\nEnjoy using this software! \n\n\n Authors: Simon Berhe, Varuhn Anandaraj, Aditya Tripuraneni", "Help", JOptionPane.INFORMATION_MESSAGE);
    }


    /********************************************************************
     METHOD:  displayPetData()
     @Purpose: Shows pet's data based on user's preference
     *********************************************************************/
    private void displayPetData()
    {
        JFrame viewWindow = new JFrame();
        viewWindow.setSize(640, 480);
        viewWindow.setTitle("Pets Data");
        viewWindow.setLayout(new GridLayout(pets.size(), 4));
        for (Pet animal : pets)
        {
            text = createText(animal.name + " ID: " + animal.id, new Color(55, 219, 55), new Font("MV boli", Font.BOLD, 30), true);
            viewWindow.add(text);
        }
        viewWindow.setVisible(true);
    }


    /********************************************************************
     METHOD:  displayUpdatedPet()
     @Purpose: Displays update feature so users can update pet
     *********************************************************************/
    private void displayUpdatedPet(){
        JTextField updatedName = new JTextField();
        JTextField updatedID = new JTextField();
        JTextField updatedCost = new JTextField();
        JTextField updatedType = new JTextField();

        Object[] options = {
                "Name", updatedName,
                "Cost", updatedCost,
                "Item", updatedType,
                "ID", updatedID
        };
        int choice = JOptionPane.showConfirmDialog(null, options, "Update Pet", JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION)
        {
            if (verifyTextToDouble(updatedCost.getText()) && verifyInteger(updatedID.getText()))
            {
                Pet updatedPet = new Pet(updatedName.getText(), updatedType.getText(), Double.parseDouble(updatedCost.getText()), Integer.parseInt(updatedID.getText()));
                updatePet(filepath, generalEntry.getText(), updatedPet);
                updatePet(updatedPet, Integer.parseInt(generalEntry.getText()));
            }
            else
                JOptionPane.showMessageDialog(null, "Make sure your ID was an Integer, and a number was entered for cost!", "Alert", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /********************************************************************
     METHOD:  mergeName()
     @Param: ArrayList<Pet> pets <BR>
     Array list of pets to be sorted
     @Purpose: Sorts pets by name in alphabetical order
     *********************************************************************/
    private void mergeName(ArrayList<Pet> pets)
    {
        if (pets.size() > 1)
        {
            ArrayList<Pet> firstHalf = new ArrayList<Pet> (pets.size()/2);
            ArrayList<Pet> secondHalf = new ArrayList<Pet>(pets.size() - pets.size()/2);

            for (int i = 0; i < pets.size(); i++)
            {
                if (i < pets.size()/2)
                    firstHalf.add(pets.get(i));
                else
                    secondHalf.add(pets.get(i));
            }

            mergeName(firstHalf);
            mergeName(secondHalf);

            int l = 0, r = 0, i = 0;

            while (l < firstHalf.size() && r < secondHalf.size())
            {
                String firstName = firstHalf.get(l).name;
                String secondName = secondHalf.get(r).name;
                if (firstName.compareTo(secondName) > 0)
                    pets.set(i++, secondHalf.get(r++));
                else
                    pets.set(i++, firstHalf.get(l++));
            }
            while (l < firstHalf.size())
                pets.set(i++, firstHalf.get(l++));

            while (r < secondHalf.size())
                pets.set(i++, secondHalf.get(r++));
        }
    }


    /********************************************************************
     METHOD:  mergeLowByCost()
     @Param: ArrayList<Pet> pets <BR>
     Array list of pets to be sorted
     @Purpose: sorts pets by cost in increasing order
     *********************************************************************/
    private void mergeLowByCost(ArrayList<Pet> pets)
    {
        if (pets.size() > 1)
        {
            ArrayList<Pet> firstHalf = new ArrayList<Pet> (pets.size()/2);
            ArrayList<Pet> secondHalf = new ArrayList<Pet>(pets.size() - pets.size()/2);

            for(int i = 0; i < pets.size(); i ++)
            {
                if (i < pets.size()/2)
                    firstHalf.add(pets.get(i));
                else
                    secondHalf.add(pets.get(i));
            }

            mergeLowByCost(firstHalf);
            mergeLowByCost(secondHalf);

            int l = 0, r = 0, i = 0;

            while (l < firstHalf.size() && r < secondHalf.size())
            {
                double firstAge = firstHalf.get(l).cost;
                double secondAge = secondHalf.get(r).cost;
                if (firstAge >= secondAge)
                    pets.set(i++, secondHalf.get(r++));
                else
                    pets.set(i++, firstHalf.get(l++));
            }

            while (l < firstHalf.size())
                pets.set(i++, firstHalf.get(l++));

            while (r < secondHalf.size())
                pets.set(i++, secondHalf.get(r++));
        }
    }


    /********************************************************************
     METHOD:  mergeSortByID()
     @Param: ArrayList<Pet> pets <BR>
     Array list of pets to be sorted
     @Purpose: sorts pets by ID, in increasing order
     *********************************************************************/
    private void mergeSortByID(ArrayList<Pet> pets)
    {
        if (pets.size() > 1)
        {
            ArrayList<Pet> firstHalf = new ArrayList<>(pets.size() / 2);
            ArrayList<Pet> secondHalf = new ArrayList<>(pets.size() - pets.size() / 2);

            for(int i = 0; i < pets.size(); i ++)
            {
                if (i < pets.size()/2)
                    firstHalf.add(pets.get(i));
                else
                    secondHalf.add(pets.get(i));
            }

            mergeSortByID(firstHalf);
            mergeSortByID(secondHalf);

            int l = 0, r = 0, i = 0;

            while (l < firstHalf.size() && r < secondHalf.size())
            {
                int leftID = firstHalf.get(l).id;
                int rightID = secondHalf.get(r).id;
                if (leftID >= rightID)
                    pets.set(i++, secondHalf.get(r++));
                else
                    pets.set(i++, firstHalf.get(l++));
            }

            while (l < firstHalf.size())
                pets.set(i++, firstHalf.get(l++));

            while (r < secondHalf.size())
                pets.set(i++, secondHalf.get(r++));
        }
    }


    /********************************************************************
     METHOD:  mergeHighByCost()
     @Param: ArrayList<Pet> pets <BR>
     Array list of pets to be sorted
     @Purpose: sorts pets by cost in decreasing order
     *********************************************************************/
    private void mergeHighByCost(ArrayList<Pet> pets)
    {
        if (pets.size() > 1)
        {
            ArrayList<Pet> firstHalf = new ArrayList<>(pets.size() / 2);
            ArrayList<Pet> secondHalf = new ArrayList<>(pets.size() - pets.size() / 2);

            for(int i = 0; i < pets.size(); i ++)
            {
                if (i < pets.size()/2)
                    firstHalf.add(pets.get(i));
                else
                    secondHalf.add(pets.get(i));
            }

            mergeHighByCost(firstHalf);
            mergeHighByCost(secondHalf);

            int l = 0, r = 0, i = 0;

            while (l < firstHalf.size() && r < secondHalf.size())
            {
                double firstAge = firstHalf.get(l).cost;
                double secondAge = secondHalf.get(r).cost;
                if (firstAge <= secondAge)
                    pets.set(i++, secondHalf.get(r++));
                else
                    pets.set(i++, firstHalf.get(l++));
            }

            while (l < firstHalf.size())
                pets.set(i++, firstHalf.get(l++));

            while (r < secondHalf.size())
                pets.set(i++, secondHalf.get(r++));
        }
    }


    /*************************************************************************************
     METHOD:  addRecord()
     @Param: Pet pet <BR>
     Pet is passed to be added to CSV file
     @Param: String fileName <BR>
     Pet is added to accordingly based on file
     @Purpose: Adds new pets to CSV file database
     ***********************************************************************************/
    private void addRecord(Pet pet, String filename)
    {
        try
        {
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(pet.id+ ","+pet.name.toUpperCase() + "," + pet.type + "," + pet.cost);
            pw.flush(); //makes sure all data is written
            pw.close();
        } catch (IOException e) {e.printStackTrace();}
    }


    /*************************************************************************************
     METHOD:  reasdCSVFile()
     @Param: String fileName <BR>
     User passes filename to be read
     @Purpose: Reads CSV file, converting data into objects stored in arraylist
     ***********************************************************************************/
    private  ArrayList<Pet> readCSVFile(String filename)
    {
        ArrayList<Pet> petsData = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                data = line.split(",");
                if (!data[0].equals("ID") && !data[1].equals("Name") && !data[2].equals("Item") && !data[3].equals("Cost"))
                    petsData.add(new Pet(data[1], data[2], Double.parseDouble(data[3]), Integer.parseInt(data[0])));
                for (String i:data) //this can be deleted later we only keep this now for testing purposes
                    System.out.printf("%-10s", i);
            }
            System.out.println(data);
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong when reading data file");
            e.printStackTrace();
        }
        return petsData;
    }



    /********************************************************************
     METHOD:  deletePet()
     @Param: int id <Br>
        Id to be deleted from array list
     @Purpose: Deletes pet containing id from array list
     *********************************************************************/
    private void deletePet(int id)
    {
        mergeSortByID(pets);
        int index = searchPetIndex(id);
        pets.remove(index);
    }


    /********************************************************************
     METHOD:  updatePet()
     @Param: Pet updatedPet <Br>
        New pet with updated data
     @Param: int id <Br>
        Id of pet to be updated
     @Purpose: Updates the current pet searched from id, replaces with new pet
     *********************************************************************/
    private void updatePet(Pet updatedPet, int id)
    {
        mergeSortByID(pets);
        int index = searchPetIndex(id);
        pets.set(index, updatedPet);
    }


    /*************************************************************************************
     METHOD:  deletePet()
     @Param: String fileName <BR>
     User passes filename to be read
     @Param: String id <BR>
     ID of pet
     @Purpose:  Deletes the pet, by ID placed.
     ***********************************************************************************/
    private void deletePet(String id, String filepath)
    {
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String currentLine;
        String[] data;

        try{
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null)
            {
                data = currentLine.split(",");
                System.out.println(Arrays.toString(data));
                if (!(data[0].equals(id)))
                    pw.println(currentLine);
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            fw.close();
            bw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }


    /********************************************************************
     METHOD:  updatePet()
     @Param: String filepath  <BR>
     filepath of record to be changed
     @Param: String id <BR>
     Pet's ID
     @Param: Pet updatedPet <BR>
     new pet to update old pet
     @Purpose:  Updates pet if user wants to change information
     *********************************************************************/
    private void updatePet(String filepath, String id, Pet updatedPet)
    {
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String currentLine;
        String[] data;
        try{
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null)
            {
                data = currentLine.split(",");
                System.out.println(Arrays.toString(data));
                if(data[0].equals(id))
                    pw.println(updatedPet.id + "," + updatedPet.name + "," + updatedPet.type + "," + updatedPet.cost);
                else
                    pw.println(currentLine);
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            fw.close();
            bw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }


    /*************************************************************************************
     METHOD:  verifyTextToDouble()
     @Param: String field <BR>
     Text to verify is passed
     @Purpose: Checks if costEntry is valid data type
     ***********************************************************************************/
    private boolean verifyTextToDouble(String field){
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
     METHOD:  verifyInteger()
     @Param: String field <BR>
     String value user enters is passed
     @Purpose: Verifies if user enters integer
     ***********************************************************************************/
    private boolean verifyInteger(String field){
        try
        {
            Integer.parseInt(field);
            System.out.println("VALID");
            return true;
        }
        catch (NumberFormatException e )
        {
            e.printStackTrace();
            System.out.println("Invalid ID make sure integer of 5 digits");
            return false;
        }
    }


    /*************************************************************************************
     METHOD:  searchPet()
     @Param: int id <BR>
     User passes id of pet to be read
     @Purpose: Searches for specific pet and returns the data of pet
     ***********************************************************************************/
    private Pet searchPet(int id)
    {
        mergeSortByID(pets);

        int left = 0;
        int right = pets.size() - 1;

        while (left <= right)
        {
            int midIndex = (left + right)/2;
            int midValue = pets.get(midIndex).id;

            if (midValue == id){
                System.out.println("PET FOUND HERE IS URE DATA: " + pets.get(midIndex));
                return pets.get(midIndex);
            }
            else if (midValue > id)
                right = midIndex -1;
            else
                left = midIndex + 1;
        }
        return null;
    }

    private int searchPetIndex(int targetID)
    {
        mergeSortByID(pets);

        int left = 0;
        int right = pets.size() - 1;

        while (left <= right)
        {
            int midIndex = (left + right)/2;
            int midValue = pets.get(midIndex).id;

            if (midValue == targetID){
                System.out.println("PET FOUND HERE IS URE DATA: " + pets.get(midIndex));
                return midIndex;
            }
            else if (midValue > targetID)
                right = midIndex -1;
            else
                left = midIndex + 1;
        }
        return -1;
    }


    /********************************************************************
     METHOD:  checkIDExistance()
     @Param: int id <BR>
     ID of pet
     @Purpose: checks for duplicate id when user creates ID
     *********************************************************************/
    private boolean checkIDExistance(int id ){
        mergeSortByID(pets);
        int left = 0;
        int right = pets.size() - 1;

        while (left <= right)
        {
            int midIndex = (left + right)/2;
            int midValue = pets.get(midIndex).id;

            if (midValue == id){
                return true;
            }
            else if (midValue > id)
                right = midIndex -1;
            else
                left = midIndex + 1;
        }
        return false;
    }


    /*************************************************************************************
     METHOD:  createText()
     @Param: String nameOFText <BR>
     Enter text you want
     @Param: Color colorText <BR>
     Object Color is passed in with RGB values desired
     @Param: Font font <BR>
     Font object is passed, user picks font, customized text and size
     @Param: boolean aligned <BR>
     If set to true, text is aligned else text is not aligned
     @Purpose: Used to create text on GUI without writing multiple lines of code.
     ***********************************************************************************/
    public static JLabel createText(String nameOFText, Color colorText, Font font, boolean aligned)
    {
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