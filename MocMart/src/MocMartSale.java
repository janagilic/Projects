
/**
 *Jana Gilic
 *  1271874
 * CSC 3280 Section 1
 * jana.gilic@gmail.com
 * Assignment - MocMart
 * 8/27/2022 
 */
public class MocMartSale {
  
    private String firstName;
    private String lastName;
    private int numItemsOnList;
    private int[] itemsPurchased;
    private static int numSales = 0;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getNumItemsOnList() {
        return numItemsOnList;
    }
    public void setNumItemsOnList(int numItemsOnList) {
        this.numItemsOnList = numItemsOnList;
    }
    
    public int[] getItemsPurchased() {
        return itemsPurchased;
    }
    public void setItemsPurchased(int[] itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }
    
    public static int getNumSales() {
        return numSales;
    }
    public static void setNumSales(int numSales) {
        MocMartSale.numSales = numSales;
    }

    public static void increaseNumSales()
    {
        MocMartSale.numSales += 1;
    }
}

