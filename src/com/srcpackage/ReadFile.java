package com.srcpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class ReadFile {
    public static Vector<Double> getData(int linesToSkip){
        Vector<Double> doubles = new Vector<>();
        try{
            File myObj = new File("src/data.txt");
            Scanner myReader = new Scanner(myObj);
            for(int j = linesToSkip; j > 0; j--){
                myReader.nextLine();
            }
            while (myReader.hasNextLine()){
                String raw_data = myReader.nextLine();
                String data = "";
                int start_index=0;
                for(int i = raw_data.length()-4; i > 0; i--){
                    if(raw_data.toCharArray()[i] == ' '){
                        start_index = i;
                        break;
                    }
                }
                data = raw_data.substring(start_index);
                double omega_z = Float.parseFloat(data);
                doubles.add(omega_z);
            }
            myReader.close();
        } catch(FileNotFoundException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return doubles;
    }
}
