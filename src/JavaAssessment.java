/**
 * This class contains main method which is used to run the whole program 
 * and output the solution. 
 * 
 * @author Jinglu Yan 18/03/2015
 */

import java.io.IOException;
import java.sql.SQLException;

public class JavaAssessment {
    
    public static void main(String [] args) throws SQLException, IOException{
        DatabaseAcc dbAcc = new DatabaseAcc();
        dbAcc.inputOrderData("Order.data");
        dbAcc.inputPersonData("Person.data");
        dbAcc.personsWithOneOrder(); 
        dbAcc.ordersWithName("Lily"); 
    }
}