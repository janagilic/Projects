

/**
 * Jana Gilic
 *  1271874
 * CSC 3280 Section 1
 * jana.gilic@gmail.com
 * Assignment - MocMart
 * 8/27/2022
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MocMart {
    public static void main(String[] args) throws Exception {

        FileInputStream is = new FileInputStream(new File("MocMart.in"));
        System.setIn(is);

        PrintStream os = new PrintStream(new File("MocMart.out"));
        System.setOut(os);
        
        final int maxProducts;
        final int maxSales;

        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        maxProducts = Integer.parseInt(console.readLine());
        maxSales = Integer.parseInt(console.readLine());

        MocMartProduct[] products = new MocMartProduct[maxProducts];
        MocMartSale[] sales = new MocMartSale[maxSales];


        while(true)
        {

            commandLine = console.readLine();

            if (commandLine.toUpperCase().startsWith("FINDITEM"))
            {
                System.out.println("FINDITEM:");
                if (MocMartProduct.getNumProducts() == 0)
                {
                    System.out.printf("\tCannot perform command; there are no items in the product database.%n");
                }
                else
                {
                    List<String> command = new ArrayList<String>(Arrays.asList(commandLine.split(" ")));
                    int search = Integer.parseInt(command.get(1));

                    int index = binarySearch(products, search, MocMartProduct.getNumProducts(), true);
                    if (index == -1)
                    {
                        System.out.printf("\tItem #%d was not found in the product database.%n", search);
                    }
                    else
                    {
                        //Find number of sold units and calculate totals
                        Double total = 0.0;
                        int unitsSold = 0;
                        for(int i = 0; i < MocMartSale.getNumSales(); i++)
                        {
                            int[] itemsSold = sales[i].getItemsPurchased();
                            for(int j = 0; j < itemsSold.length; j+=2)
                            {
                                if (itemsSold[j] == products[index].getItemNum())
                                {
                                    unitsSold += itemsSold[(j+1)];
                                    total += (products[index].getItemPrice() * itemsSold[(j+1)]);
                                }
                            }
                        }

                        System.out.printf("\tItem #%d (%s)%n\tPrice            :  $%1.2f%n\tCurrent Quantity :  %d%n\tUnits Sold       :  %d%n\tTotal Amount     :  $%1.2f%n", 
                        products[index].getItemNum(), products[index].getItemName(), products[index].getItemPrice(), products[index].getQuantity(),
                        unitsSold, total);
                    }
                }
                System.out.printf("%n");
            }
            else if (commandLine.toUpperCase().startsWith("ADDITEM"))
            {
                List<String> command = new ArrayList<String>(Arrays.asList(commandLine.split(" ")));
                
                MocMartProduct newItem = new MocMartProduct();
                newItem.setItemNum(Integer.parseInt(command.get(1))); 
                String name = command.get(2);
                newItem.setItemName(name.substring(0, Math.min(name.length(), 20))); 
                newItem.setItemPrice(Double.parseDouble(command.get(3)));
                newItem.setQuantity(Integer.parseInt(command.get(4))); 
                newItem.setRestockQuantity(Integer.parseInt(command.get(5))); 

                System.out.println("ADDITEM:");
                if (MocMartProduct.getNumProducts() == maxProducts)
                {
                    System.out.printf("\tMaximum number (%d) of products in the Moc Mart has been reached. Item %d can not be added in the product database.%n", 
                        maxProducts, newItem.getItemNum());
                }
                else
                {
                    // Check if exists
                    int sIndex = binarySearch(products, newItem.getItemNum(), MocMartProduct.getNumProducts(), false);

                    if(sIndex > -1 && MocMartProduct.getNumProducts() > 0)
                    {
                        System.out.printf("\tItem %d already exists in the product database.%n", 
                        newItem.getItemNum());
                    }
                    else
                    {
                        //Find order index
                        int index = MocMartProduct.getNumProducts();
                        for(int i = 0; i < MocMartProduct.getNumProducts(); i++)
                        {
                            if(newItem.getItemNum() < products[i].getItemNum())
                            {
                                index = i;
                                break;
                            }
                        }
                        products = addProductAtPos(products, index, newItem);

                        MocMartProduct.increaseNumProducts();

                        System.out.printf("\tItem %d, %s, with a cost of $%1.2f and initial stock of %d, has been added to the product database.%n", 
                        newItem.getItemNum(), newItem.getItemName(), newItem.getItemPrice(), newItem.getQuantity());
    
                    }
                    System.out.printf("%n");
                }
                
            }
            else if (commandLine.toUpperCase().startsWith("RESTOCK"))
            {
                System.out.println("RESTOCK:");
                int restocked = 0;
                for(int i = 0; i < MocMartProduct.getNumProducts(); i++)
                {
                    if(products[i].getQuantity() == 0) 
                    {
                        products[i].setQuantity(products[i].getRestockQuantity());
                        restocked +=1;
                        
                        System.out.printf("\tItem %d, %s, restocked to a quantity of %d.%n", 
                            products[i].getItemNum(), products[i].getItemName(), products[i].getRestockQuantity());
                    }
                }
                if (restocked == 0) System.out.println("\tThere are no items to restock.");

                System.out.printf("%n");

            }
            else if (commandLine.toUpperCase().startsWith("INVENTORY"))
            {
                System.out.println("INVENTORY:");
                if (MocMartProduct.getNumProducts() == 0)
                {
                    System.out.println("\tContains no items.");
                }
                else 
                {
                    System.out.println("\tContains the following items:");
                }

                for (int i = 0; i < MocMartProduct.getNumProducts(); i++)
                {
                    System.out.printf("\t| Item %6d | %-20s | $%7.2f | %4d unit(s) |%n", 
                        products[i].getItemNum(), products[i].getItemName(), products[i].getItemPrice(), products[i].getQuantity());
 
                }

                System.out.printf("%n");
            }
            else if (commandLine.toUpperCase().startsWith("CUSTOMER"))
            {
                System.out.println("CUSTOMER:");

                List<String> command = new ArrayList<String>(Arrays.asList(commandLine.split(" ")));

                if (MocMartSale.getNumSales() == maxSales)
                {
                    System.out.printf("\tMaximum number (%d) of sales in the Moc Mart has been reached.%n", 
                        maxSales);
                }
                else
                {
                    MocMartSale newSale = new MocMartSale();
                
                    String fName = command.get(1);
                    String lName = command.get(2);
                    newSale.setFirstName(fName.substring(0, Math.min(fName.length(), 20)));
                    newSale.setLastName(lName.substring(0, Math.min(lName.length(), 20)));
    
                    int numOfProd = Integer.parseInt(command.get(3));
                    newSale.setNumItemsOnList(numOfProd);
    
                    System.out.printf("\tCustomer %s %s came and made ", 
                        fName, lName);

                    int itemsCounter = 0;
                    int itemsPurchased = 0;
                    int[] arr = new int[numOfProd];
                    for (int i = 0; i < numOfProd; i+=2)
                    {
                        
                        arr[(i+4)-4] = Integer.parseInt(command.get(i+4));
                        arr[(i+5)-4] = Integer.parseInt(command.get(i+5));
    
                        // Find product 
                        int pIndex = binarySearch(products, arr[(i+4)-4], MocMartProduct.getNumProducts(), false);
                        if(pIndex == -1)
                        {
                            arr[(i+5)-4] = 0;
                        }
                        else
                        {
                            itemsCounter += 1;                            
                            // Check quantities
                            if(products[pIndex].getQuantity() == 0)
                            {
                                arr[(i+5)-4] = 0;
                                itemsCounter -= 1;                                
                            }
                            else if(products[pIndex].getQuantity() < arr[(i+5)-4])
                            {
                                arr[(i+5)-4] = products[pIndex].getQuantity();
                                products[pIndex].setQuantity(0);
                            }
                            else
                            {
                                products[pIndex].setQuantity(products[pIndex].getQuantity() - arr[(i+5)-4]);
                            }
                            itemsPurchased += arr[(i+5)-4];
                        }
                    }

                    if(itemsCounter == 0)
                    {
                        System.out.printf("no purchases.%n");
                    }
                    else
                    {
                        newSale.setItemsPurchased(arr);
                        newSale.setNumItemsOnList(itemsPurchased);

                        sales[MocMartSale.getNumSales()] = newSale;
                        MocMartSale.increaseNumSales(); 
                        System.out.printf("some purchases.%n");
                    }
                    
                }

                System.out.printf("%n");
            }
            else if (commandLine.toUpperCase().startsWith("PRINTSALESSUMMARY"))
            {
                System.out.println("PRINTSALESSUMMARY:");
                System.out.println("Moc Mart Sales Report:");

                Double grandTotal = 0.0;

                for(int i = 0; i < MocMartSale.getNumSales(); i++)
                {
                    System.out.printf("\tSale #%d, %s %s purchased %d item(s):%n", (i+1), sales[i].getFirstName(), sales[i].getLastName(), sales[i].getNumItemsOnList());

                    int[] itemsSold = sales[i].getItemsPurchased();
                    Double total = 0.0;
                    for(int j = 0; j < itemsSold.length; j+=2)
                    {
                        if (itemsSold[(j+1)] > 0)
                        {
                            // Find product
                            int index = binarySearch(products, itemsSold[j], MocMartProduct.getNumProducts(), false);
                            if (index == -1)
                            {
                                // ?!?
                            }
                            else
                            {
                                total += (products[index].getItemPrice() * itemsSold[(j+1)]);

                                System.out.printf("\t\t| Item %6d | %-20s | $%7.2f (x%4d) |%n", 
                                    products[index].getItemNum(), products[index].getItemName(), products[index].getItemPrice(), itemsSold[(j+1)]);
                            }
                       }
                    }

                    grandTotal += total;
                    System.out.printf("\t\tTotal: $%1.2f%n", total);

                }

                System.out.printf("\tGrand Total: $%1.2f%n", grandTotal);
                System.out.printf("%n");
            }
            else if (commandLine.toUpperCase().startsWith("QUIT"))
            {
                System.out.printf("Goodbye.%n");
                return;
            }

        }

    }

    static int binarySearch(MocMartProduct arr[], int x, int size, boolean print)
    {
        if (print) System.out.printf("\tPerforming Binary Search...(Indices viewed: ");

        int l = 0, r = size - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (print) System.out.printf(m + " ");

            // Check if x is present at mid
            if (arr[m].getItemNum() == x)
            {
                if (print) System.out.printf(")%n");
                return m;
            }
    
            // If x greater, ignore left half
            if (arr[m].getItemNum() < x)
                l = m + 1;
    
            // If x is smaller, ignore right half
            else
                r = m - 1;

        }
 
        // if we reach here, then element was not present
        if (print) System.out.printf(")%n");
        return -1;
    }

    public static MocMartProduct[] addProductAtPos(MocMartProduct a[], int pos, MocMartProduct product) {
        MocMartProduct result[] = new MocMartProduct[a.length];
        for(int i = 0; i < pos; i++)
            result[i] = a[i];
        result[pos] = product;
        for(int i = pos + 1; i < a.length; i++)
            result[i] = a[i - 1];
        return result;
    }

}
