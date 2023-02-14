/*This class holds all the fuctionalities of the system
like adding,searching,updatingthe product to the stock
*/
package stockfinanl;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Stock implements Comparable<Stock> {
        int itemCode; //which specify each item uniquely
	 String itemName; 
	 float itemPrice; 
	 int itemQuantity;
	 float itemCost; 
        String filename;
        Scanner input=new Scanner(System.in);
    ArrayList<Stock> foodList=new ArrayList();
    int getItemCode(){
    return itemCode;
    }
    //this method adds item to the stock
	public void addItem() throws NumberFormatException{//adds food items information to inventory
            boolean test=true;
    System.out.println("Enter the name of the file on wich you want to add the item");
    filename=input.nextLine();
    do{
    try{
    FileWriter myWriter=new FileWriter(filename,true);
    inputCode();
    myWriter.write("ItemId:"+itemCode);
    input.nextLine();
    System.out.print("Enter the name of the item you want to add: ");
    itemName=input.nextLine();
    myWriter.write("  ItemName:"+itemName);
    System.out.print("Enter the quantity for the item: "); 
    itemQuantity = input.nextInt();
    myWriter.write("  ItemQuantity:"+itemQuantity);
    input.nextLine();
    System.out.print("Enter the cost of the item: "); 
    itemCost = input.nextFloat();
    myWriter.write("  ItemCost:"+itemCost);
    System.out.print("Enter the sales price of the item: ");
    itemPrice = input.nextFloat();
    myWriter.write("  ItemPrice:"+itemPrice+"\n");
    test=false;
    input.nextLine();
    myWriter.close();
        }
       catch (Exception e) {
	System.out.println("Invalid entry");
	input.nextLine();
            }
        }
            while(test);
            }
        //this method handle valid item code 
  public void inputCode() { 
		int done = -1;
		do { //do-while to take in code of item
			try {
				System.out.print("Enter correct code for the item: ");
				itemCode = input.nextInt();
				done = 0;
			} catch (InputMismatchException e) { //catches input mismatch exception
				System.out.println("Invalid code");
				input.nextLine();
			}
		} while (done==-1);
	}
  //displayInfo  method displays the item from the file
       public void displayInfo() throws IOException{
        try{
        //String toString(){}
        System.out.println("Enter the name of the file on wich your current stock is there");
        filename=input.nextLine();
        FileReader reader=new FileReader(filename);
        Scanner readr=new Scanner(reader);
        String line;
        while (readr.hasNext()) //Reading the content line by line
            {
            line=readr.nextLine();
          
            
        System.out.println(line);//
        }
        reader.close();
    }   
    catch (FileNotFoundException e ) {
	System.out.println("File Not Found, ignoring...");
	}
     }
     //displayed the information about food item in formatted string
         public String toString() { 
         return "\nItem Code: " + itemCode + "   Item Name:"  + itemName + "   Item Quantiy: " + itemQuantity +
	 " price: $" + String.format("%.2f", itemPrice) +
	    " cost: $" + String.format("%.2f", itemCost);}
      String searchItem(int itemid) {
          String line=null;
          System.out.println("Enter the name of the file on wich your current stock is there");
           filename=input.nextLine();
          try{
          FileReader read = new FileReader(filename);
        Scanner readr=new Scanner(read);
        while(readr.hasNext()){
        line=readr.nextLine();
        if(line.contains("ItemId:"+itemid)){
            System.out.println("#########Item is found#########fruit!\n");
        break;}
        }
         }
          catch (FileNotFoundException e ) {
	System.out.println("File Not Found, ignoring...");}
          catch(IOException e){
          System.out.println("Error while search from file!");
          }
        return line;
       }
      //this method search the item from the stock by its code
   int BinarySearch(ArrayList<Stock>foodList,int Itemcode,int first, int last){
   if (first <= last) {
   int mid = first + (last - first) / 2;
   if (foodList.get(mid).getItemCode() < Itemcode) {
   first = mid + 1;
   return BinarySearch(foodList, Itemcode, first, last);
   } else if (foodList.get(mid).getItemCode()== Itemcode) {
   return mid;
   } else {
   last = mid -1;
   return BinarySearch(foodList, Itemcode, first, last);
   }
   }
   return -1;
   }
   // this method update the quantity of the item while sell or buy any item from the stock
   public boolean updateQuantity (ArrayList<Stock>foodList,int numItems,boolean buyOrSell) {
           try {    
	    if (numItems > 0) {  
           int quantity;      
	    System.out.print("Enter the code for the item: ");
	    int code = input.nextInt(); 
	    input.nextLine();
	    int index = BinarySearch(foodList,code,0,numItems-1); 
	    boolean quantityGoods = false; 
	    if (index != -1) {     
	    if (buyOrSell) {    
	    System.out.print("Enter valid quantity to buy: ");
	    quantity = input.nextInt();
	    input.nextLine();
	    quantityGoods = foodList.get(index).updateItem(quantity); } 
	     else {
	    System.out.print("Enter valid quantity to sell: ");
	    quantity = input.nextInt();
	    input.nextLine();
	    quantityGoods =foodList.get(index).updateItem(quantity *-1);
            }
	     }
	     return quantityGoods;
	     }} catch (ArrayIndexOutOfBoundsException e) { 
	    System.out.println();
	        } catch (NumberFormatException e) { 
	        System.err.println("Please enter an integer"); 
	        }
	        return false;
	    }
   //updateItem method add or substract itemQuantity
           public boolean updateItem(int amount) {
	   int result = itemQuantity + amount;
	   if(result < 0) {
	   return false;
	   } else {
	   itemQuantity= result; 
	   return true;
		}
	}
    @Override
    public int compareTo(Stock o) {
    return this.itemCode-o.itemCode;
    }
}