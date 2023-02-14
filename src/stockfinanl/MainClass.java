/*
Roll_No      Group Member Name                           ID_Number
1           LALISA    BULA                                Ru2359/13
2           KUMA      LETA                                Ru2440/13
3           SITRA     JEMAL                               Ru0013/13
4           RIBQA     WONDU                               Ru0051/13
5           HERMELA   HAILEGIORGIS                        Ru2124/13
*/
package stockfinanl;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
public class MainClass {
 public static void main(String[] args) throws IOException,InputMismatchException {
        Scanner input=new Scanner(System.in);
        Stock stockObject=new Stock();
    ArrayList<Stock> foodList=new ArrayList(); 
    LoginClass loginObject=new LoginClass();
    int itemId;
    int numItems=0;
    loginObject.logIn();
    int choose=0; 
       do {
        System.out.println("============This is list of Services of the system===========");
        System.out.println("=====1.Add product to stock market and write in the file=====");
        System.out.println("=====2.Display current stock=================================");
        System.out.println("=====3.Search for an item from the file======================");
        System.out.println("=====4.Buy Item(s)==========================================");
        System.out.println("=====5.Sell Item(s)==========================================");
        System.out.println("=====6.Log out===============================================");
        System.out.println("=====7.Display stock from the file===========================");
        System.out.println("=====8.Exit==================================================");
        System.out.print("       Select the one you want to visit:");
        
        try{
                        
            Scanner cc = new Scanner(System.in);
            choose = cc.nextInt();
            switch (choose) {
           case 1:
               stockObject.addItem();
               foodList.add(stockObject);
               Collections.sort(foodList);
               numItems++;
               break;
               case 2:
               String display= "Stock:";
                for(int i = 0; i < numItems; i++) {
                display += foodList.get(i).toString();
                }
                System.out.println(display);
                break;
                case 3:
                System.out.println("Enter itemCode you want for search");
                 itemId=input.nextInt();
                 System.out.println(stockObject.searchItem(itemId));              
                 break;
                case 4:
                    stockObject.updateQuantity(foodList, numItems, true);
                    break;
                case 5:
                stockObject.updateQuantity(foodList,numItems,false);
                    break;
                case 6:
                    System.out.println("Logged out Successfully");
                 loginObject.logIn();
                 break;
                case 7:
               stockObject.displayInfo();
                    break;
                case 8:
                    System.out.println("Exiting..."); //exiting switch case
                    break;

                default:
                    System.out.println("Invalid key\n re enter from the list");
                    //mainPage();
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("\n\n\t=====Error!!! Please input only Numbers ======================\n\n");

        }

    }  while (choose != 8);
    }
}
