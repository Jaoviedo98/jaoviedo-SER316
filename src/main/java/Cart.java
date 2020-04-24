package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import main.java.FrozenFood;

public class Cart {

    protected int userAge;
    public List<Product> cart;

    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }


    /**
     * Calculates the final cost after all savings and tax has been applied. Also checks
     * that the user is of age to purchase alcohol if it is in their cart at checkout.
     * Sales tax is always AZ tax.
     * Calculation is based off of the following prices and deals:
     * Dairy -> $3
     * Meat -> $10
     * Produce -> $2 or 3 for $5
     * Alcohol -> $8
     * Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     * If there is an alcohol product in the cart and the user is under 21, then an
     * UnderAgeException should be thrown.
     * @return double totalCost
     * @throws UnderAgeException
     */
    public double calcCost() throws UnderAgeException {
        int subtotal = getSubtotal();
        int amountSaved = amount_Saved();
        subtotal -= amountSaved;
        return subtotal + getTax(subtotal, "AZ");
    }

    // calculates how much was saved in the current shopping cart based on the deals,
    // returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4:
    // create white box tests and fix the method, reach at least 98% coverage
    /**
     *
     *
     */
    public int amount_Saved() throws UnderAgeException {
        int produceCounter = getItemCount(Produce.class.toString());
        int alcoholCounter = getItemCount(Alcohol.class.toString());
        int frozenFoodCounter = getItemCount(FrozenFood.class.toString());
        int savings = getSavings(produceCounter, alcoholCounter, frozenFoodCounter);
        return savings;
    }

    public int getSavings(int produce, int alcohol, int frozen) throws UnderAgeException {
        int savings = 0;
        if(alcohol > 0 && userAge < 21) {
            throw new UnderAgeException("The User is not of age to purchase alcohol!");
        }
        savings += produce / 3;
        savings += Math.min(alcohol, frozen) * 3;
        return savings;
    }

    public int getSubtotal() {
        int subtotal = 0;
        for (int i = 0; i < cart.size(); i++) {
            subtotal += cart.get(i).getCost();
        }
        return subtotal;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalbt, String twoLetterStateAbbreviation) {
        HashMap<String, Double> taxes = new HashMap<String, Double>();
        taxes.put("AZ", 0.08);
        taxes.put("CA", 0.09);
        taxes.put("NY", 0.10);
        taxes.put("CO", 0.07);
        if(taxes.get(twoLetterStateAbbreviation) == null)
            return 0.0;
        return taxes.get(twoLetterStateAbbreviation) * totalbt;
    }
    
    public void addItem(Product np) {
        cart.add(np);
    }

    public boolean removeItem(Product productToRemove) {
        boolean test = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i) == productToRemove) {
                cart.remove(i);
                return true;
                //SER316-Start had a variable  - to true and then
                // returned variable, Simplified to return true
            }
        }
        return false;
    }

    public int getItemCount(String className) {
        int productCount = 0;
        for (int i = 0; i < cart.size(); i++) {
            if (((Product) this.cart.get(i)).getClass().toString().equalsIgnoreCase(className)) {
                productCount++;
            }
        }
            return productCount;
    }
}
