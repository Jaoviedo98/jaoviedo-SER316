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
        double totalCost = 0.0D;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int produceCounter = 0;
        for (int i = 0; i < cart.size(); i++) {
            totalCost += (double)((Product)this.cart.get(i)).getCost();
            if (((Product)this.cart.get(i)).getClass() == Alcohol.class) {
                if (this.userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
                alcoholCounter++;
            } else if (((Product)this.cart.get(i)).getClass() == FrozenFood.class) {
                frozenFoodCounter++;
            } else if (((Product)this.cart.get(i)).getClass() == Produce.class) {
                produceCounter++;
            }
            if (alcoholCounter > 0 && frozenFoodCounter > 0) {
                totalCost -= 3;
                alcoholCounter--;
                frozenFoodCounter--;
            }
            if (produceCounter >= 3) {
                totalCost--;
                produceCounter -= 3;
            }
        }

        return totalCost + getTax(totalCost, "AZ");
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
        int subTotal = 0;
        int costAfterSavings = 0;
        double produceCounter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;
        for (int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();
            //SER316-Start if (cart.get(i).getClass().toString() == Produce.class.toString())
            // {, compares two strings with equal, removed toString
            if (cart.get(i).getClass() == Produce.class) {
                produceCounter++;
                if (produceCounter >= 3) {
                    costAfterSavings -= 1;
                    produceCounter = 0;
                }
                //SER316-Start if (cart.get(i).getClass().toString() == Alcohol.class.toString())
                // {, compares two strings with equal, removed toString
            } else if (cart.get(i).getClass() == Alcohol.class) {
                alcoholCounter++;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
                //SER316-Start if (cart.get(i).getClass().toString() == FrozenFood.class.toString())
                // {, compares two strings with equal, removed toString
            } else if (cart.get(i).getClass() == FrozenFood.class) {
                frozenFoodCounter++;
                //SER316-Start repeated statement as above changed to Dairy.class,
                // but does not affect anything in the code
            } else if (cart.get(i).getClass() == Dairy.class) {
                dairyCounter++;
            }
            if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                //SER316-start costAfterSavings = costAfterSavings + 3,
                // adds 3 instead of subtracting it
                costAfterSavings = costAfterSavings - 3;
                alcoholCounter--;
                frozenFoodCounter--;
            }
        }
        return subTotal - costAfterSavings;
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
}
