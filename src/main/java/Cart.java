package main.java;

import main.java.FrozenFood;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected int userAge;
    public List<Product> cart;
    public int cartStorage;

    /**
     * Calculates the final cost after all savings and tax has been applied. Also checks
     * that the user is of age to purchase alcohol if it is in their cart at checkout. Sales tax is always AZ tax.
     *
     * Calculation is based off of the following prices and deals:
     * Dairy -> $3
     * Meat -> $10
     * Produce -> $2 or 3 for $5
     * Alcohol -> $8
     * Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     *
     * If there is an alcohol product in the cart and the user is under 21, then an
     * UnderAgeException should be thrown.
     *
     * @return double totalCost
     * @throws UnderAgeException
     */
    public double calcCost() throws UnderAgeException {
        double totalCost = 0.0D;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int produceCounter = 0;
        for(int i = 0; i < cart.size(); i++)
        {
            totalCost += (double)((Product)this.cart.get(i)).getCost();
            if (((Product)this.cart.get(i)).getClass() == Alcohol.class) {
                if (this.userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
                alcoholCounter++;
            }
            else if (((Product)this.cart.get(i)).getClass() == FrozenFood.class) {
                frozenFoodCounter++;
            }
            else if (((Product)this.cart.get(i)).getClass() == Produce.class) {
                produceCounter++;
            }
            if(alcoholCounter > 0 && frozenFoodCounter > 0)
            {
                totalCost -= 3;
                alcoholCounter--;
                frozenFoodCounter--;
            }
            if(produceCounter >= 3)
            {
                totalCost--;
                produceCounter -= 3;
            }
        }

        return totalCost + getTax(totalCost, "AZ"); //implement me, will be important for assignment 4 (nothing to do here for assignment 3)
    }

    // calculates how much was saved in the current shopping cart based on the deals, returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4: create white box tests and fix the method, reach at least 98% coverage
    public int Amount_saved() throws UnderAgeException {
        int subTotal = 0;
        int costAfterSavings = 0;
        double produce_counter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;
        for(int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings =costAfterSavings+cart.get(i).getCost();
            if (cart.get(i).getClass() == Produce.class) { //SER316-Start if (cart.get(i).getClass().toString() == Produce.class.toString()) {, compares two strings with equal, removed toString
                produce_counter++;
                if (produce_counter >= 3) {
                    costAfterSavings -= 1;
                    produce_counter = 0;
                }
            }
            else if (cart.get(i).getClass() ==Alcohol.class) {//SER316-Start if (cart.get(i).getClass().toString() == Alcohol.class.toString()) {, compares two strings with equal, removed toString
                alcoholCounter++;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
            }
            else if (cart.get(i).getClass() == FrozenFood.class) {//SER316-Start if (cart.get(i).getClass().toString() == FrozenFood.class.toString()) {, compares two strings with equal, removed toString
                frozenFoodCounter++;
            }
            else if (cart.get(i).getClass() == Dairy.class)//SER316-Start repeated statement as above changed to Dairy.class, but does not affect anything in the code
                dairyCounter++;
            if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                costAfterSavings = costAfterSavings - 3; //SER316-start costAfterSavings = costAfterSavings + 3, adds 3 instead of subtracting it
                alcoholCounter--;
                frozenFoodCounter--;
            }
        }
        return subTotal - costAfterSavings;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBT, String twoLetterUSStateAbbreviation) {
        double newTotal = 0;
        switch (twoLetterUSStateAbbreviation) {
            case "AZ":
                newTotal = totalBT * .08;
                break;
            case "CA":
                newTotal = totalBT * .09;
                break;
            case "NY":
                newTotal = totalBT * 0.1; break; //SER316-Start had no break, added it in line to not mess up the numbering on graph
            case "CO":
                newTotal = totalBT * .07;
                break;
            default:
                return newTotal;//SER316-Start changed to return newTotal instead od TotalBT
        }
        return newTotal;
    }

    public void addItem(Product np) {
      cart.add(np);
    }

    public boolean RemoveItem(Product productToRemove)
    {
    		boolean test = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i) == productToRemove) {
                 cart.remove(i);
                 return true;
                 //SER316-Start had a variable  - to true and then returned variable, Simplified to return true
            }
        }
        return false;
    }

    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }
}
