/**
 * This class is to get data from the .data files and
 * then format in a way which can be used to for database.
 * 
 * @author Jinglu Yan 18/03/2015
 * 
 */

import java.io.*;
import java.util.*;

public class FileReader {
    
    private File file;  
    private Scanner input;
    ArrayList<String> array;
    
    public FileReader(String fileAddress) throws FileNotFoundException{
        file = new File(fileAddress) ;
        array = new ArrayList<String>();
        input = new Scanner(file);
        int i = 0;

        //Store .data files data into the array
        while(input.hasNextLine()){
            //for table header
            String tmp;
            if(i == 0){
                tmp = input.nextLine().replace(" ","_").replace("|",",");
            }
            //real data in table
            else{                    
                tmp = input.nextLine().replace("|",",");
            }
            array.add(tmp);
            i++;
        }
    }
    
    /**
     * Format strings for SQL statement
     */
    public void formatForPeople(){
        
        int i = 1;
        while(i < array.size()){
            String ele = (String) array.get(i);
            //removes the empty strings in the ArrayList
            if("".equals(ele)){
                array.remove(i);
            }
            else{
                input = new Scanner(ele);
                String tmp =input.next().replace(",", "','").replaceFirst("'", "")+"'";
                array.set(i, tmp);
                i++;
            }
        }
    }
}