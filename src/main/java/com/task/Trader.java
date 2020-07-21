package com.task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Trader {
  
  
  
    
    private static HashMap<String, String> transMap = new HashMap<String, String>();
    private static HashMap<String, Double> currencyMap = new HashMap<String, Double>();
    
    public static void main(String[] args) {
        BufferedReader br = null;       
        String money = "";
       
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            while(line!=null){
                String selectedLint = Transaction.checkRgx(line);  
                if(selectedLint.equalsIgnoreCase("assignment")){
                    Pattern ptn = Pattern.compile(Transaction.rgxAssignment);
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    String name = mcher.group(1).trim();
                    String roman = mcher.group(2).trim();
                    if(!transMap.containsKey(name)){
                        transMap.put(name, roman);
                    }               
                }else if(selectedLint.equalsIgnoreCase("credits")){
                    Pattern ptn = Pattern.compile(Transaction.rgxCredits);
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    money = mcher.group(4).trim();
                    String[] trans = mcher.group(1).split(" ");
                    int transValue = Transaction.getTransValue(trans,transMap);
                    String curr = mcher.group(2);
                    int credits = Integer.parseInt(mcher.group(3).trim());
                    double value = credits/transValue;
                    currencyMap.put(curr, value);
                }else if(selectedLint.equalsIgnoreCase("howmany")){
                    Pattern ptn = Pattern.compile(Transaction.rgxHowMany);
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    if(!money.equals(mcher.group(1))){
                        System.out.println(Transaction.Error);
                    }
                    String[] trans = mcher.group(2).split(" ");
                    int transValue = Transaction.getTransValue(trans,transMap);
                    String curr = mcher.group(3).trim();
                    double value = 0;
                    for(String currency:currencyMap.keySet()){
                        if(currency.equals(curr)){
                           value = currencyMap.get(currency);
                        }
                    }
                    double total = transValue*value;
                    if(total != 0){
                        System.out.println(mcher.group(2)+"is "+(long)total+" "+money);
                    }
                }else if(selectedLint.equals("howmuch")){
                    Pattern ptn = Pattern.compile(Transaction.rgxHowMuch);
                    Matcher mcher = ptn.matcher(line);
                    mcher.matches();
                    String[] trans = mcher.group(1).split(" ");
                    int transValue = Transaction.getTransValue(trans,transMap);
                    if(transValue!=0){
                        System.out.println(mcher.group(1)+"is "+transValue);
                    }
                }else{
                    System.out.println(Transaction.Error);
                }
                
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
        	 ex.printStackTrace();
        } 
        finally {
            try {
                br.close();
            } catch (IOException ex) {
            	 ex.printStackTrace(); 
            }
        }
    }
    
   

   
    
}