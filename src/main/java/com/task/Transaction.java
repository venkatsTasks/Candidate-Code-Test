package com.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transaction {
	public static String Error = "I have no idea what you are talking about";
	public static String rgxAssignment = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
	public static String rgxCredits = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
	public static String rgxHowMany= "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
	public static String rgxHowMuch = "^how much is ((?:\\w+[^0-9] )+)\\?$";
	 public static String checkRgx(String s){
	        String flag = Error;
	        String[] rgxArray = new String[]{rgxAssignment,rgxCredits,rgxHowMany,rgxHowMuch};
	        for(int i = 0;i<rgxArray.length;i++){
	            Pattern ptn = Pattern.compile(rgxArray[i]);
	            Matcher mcher = ptn.matcher(s);
	            if(mcher.matches()){
	                switch(i){
	                    case 0:
	                        flag = "assignment";
	                    break;
	                    case 1:
	                        flag = "credits";
	                    break;
	                    case 2:
	                        flag = "howmany";
	                    break;
	                    case 3:
	                        flag = "howmuch";
	                    break;
	                    default:
	                        break;
	                }
	            }
	        }     
	        return flag;
	    }
	 public static int getValueByRoman(String roman){
	        switch(roman){
	            case "I":
	                return Roman.I.getValue();
	            case "V":
	                return Roman.V.getValue();
	            case "X":
	                return Roman.X.getValue();
	            case "L":
	                return Roman.L.getValue();
	            case "C":
	                return Roman.C.getValue();
	            case "D":
	                return Roman.D.getValue();
	            case "M":
	                return Roman.M.getValue();
	            default:
	                return 0;
	        }
	    }
	    
	    
	 public static int getTransValue(String[] trans,HashMap<String, String> transMap){
	        int value = 0;
	        List<String> romans = new ArrayList<String>();
	        for(int i=0;i<trans.length;i++){
	        	if(!transMap.keySet().contains(trans[i])){
	        		System.out.println("Invalid input detected!");
	        		return 0;
	        	}
	             romans.add(transMap.get(trans[i]));
	        }
	        List<Integer> src = new ArrayList<Integer>();
	        for(String s : romans){
	            src.add(getValueByRoman(s));
	        }
	        
	        List<Integer> newSrc = doSubstract(src);;
	        for(int i : newSrc){
	            value+=i;
	        }
	        
	        return value;
	    }
	    
	 public static List<Integer> doSubstract(List<Integer> numbers)
		{
			int currentElement;
			
			for(int i = 0 ; i < numbers.size() -1; i++)
			{
				currentElement = numbers.get(i);
				if( currentElement < numbers.get(i+1))
				{
					numbers.set(i, -currentElement);
				}
			}
			return numbers;
		}
}
