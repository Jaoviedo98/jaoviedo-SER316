package test.java;
import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class CartTest
{
    Cart cart;
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;
    Cart cart5;
    Cart cart6;
    Cart cart7;
    Cart cart8;
    Cart cart9;
    Cart cart10;
    Cart cart11;
    Cart cart13;
    Cart cart14;
    Cart cart15;
    Cart cart16;

    double cart1Expected;
    double cart2Expected;
    double cart3Expected;
    double cart4Expected;
    double cart5Expected;
    double cart6Expected;
    double cart7Expected;
    boolean cart8Expected;
    boolean cart9Expected;
    double cart10Expected; //Produce cart
    double cart11Expected;
    @Before
    public void setUp() throws Exception {
        cart = new Cart(45);
        cart1 = new Cart(40);
        cart2 = new Cart(23);
        cart3 = new Cart(23);
        cart4 = new Cart(23);
        cart5 = new Cart(23);
        cart6 = new Cart(23);
        cart7 = new Cart(50);
        cart8 = new Cart(18);
        cart9 = new Cart(21);
        cart10 = new Cart(15);
        cart11 = new Cart(1000);

        cart13 = new Cart(21);
        cart14 = new Cart(20);
        cart14.addItem(new Alcohol());

        cart15 = new Cart(25);
        cart15.addItem(new Produce());
        cart15.addItem(new Produce());
        cart15.addItem(new Produce());
        cart15.addItem(new Alcohol());
        cart15.addItem(new FrozenFood());
        cart15.addItem(new Dairy());

        cart16 = new Cart(25);
        cart16.addItem(new Produce());
        cart16.addItem(new Produce());
        cart16.addItem(new Produce());
        cart16.addItem(new Alcohol());
        cart16.addItem(new FrozenFood());
        cart16.addItem(new Dairy());
        cart16.addItem(new Meat());

        for (int i = 0; i < 2; i++) {
            cart1.addItem(new Alcohol());
        }
        for(int i = 0; i < 3; i++) {
            cart1.addItem(new Dairy());
        }
        for(int i = 0; i < 4; i++) {
            cart1.addItem(new Meat());
        }
        cart2.addItem(new Meat());
        cart3.addItem(new Dairy());
        cart4.addItem(new Alcohol());
        cart5.addItem(new Produce());
        cart6.addItem(new FrozenFood());

        //cart7
        cart7.addItem(new FrozenFood());
        cart7.addItem(new Alcohol());

        cart8.addItem(new Alcohol());
        cart9.addItem(new Alcohol());

        for(int i = 0; i < 6; i++) {
            cart10.addItem(new Produce());
        }
        for(int i = 0; i < 6; i++) {
            cart11.addItem(new Produce());
            cart11.addItem(new Meat());
            cart11.addItem(new FrozenFood());
            cart11.addItem(new Dairy());
            cart11.addItem(new Alcohol());
        }
        cart1Expected = 70.2;
        cart2Expected = 10.80;
        cart3Expected = 3.24;
        cart4Expected = 8.64;
        cart5Expected = 2.16;
        cart6Expected = 5.40;
        cart7Expected = 10.80;
        cart8Expected = true;
        cart9Expected = true;
        cart10Expected = 10.80;
        cart11Expected = 159.84;
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getTaxNM()
    {
        assertEquals(0.0, cart.getTax(50, "NM"), .01);
    }
    @Test
    public void getTaxAZ()
    {
        assertEquals(4.0, cart.getTax(50, "AZ"), .01);
    }
    @Test
    public void getTaxCA()
    {
        assertEquals(4.5, cart.getTax(50, "CA"), .01);
    }
    @Test
    public void getTaxNY()
    {
        assertEquals(5.0, cart.getTax(50, "NY"), .01);
    }
    @Test
    public void getTaxCO()
    {
        assertEquals(3.5, cart.getTax(50, "CO"), .01);
    }
    @Test
    public void EmptyCart() throws UnderAgeException
    {
        assertEquals(0.0, cart13.Amount_saved(), 0.1);
    }
    @Test
    public void Full_Cart() throws UnderAgeException
    {
        assertEquals(4.0, cart15.Amount_saved(), 0.1);
    }
    @Test
    public void Fuller_Cart() throws UnderAgeException
    {
        assertEquals(4.0, cart16.Amount_saved(), 0.1);
    }
    @Test
    public void UnderageBuyer() throws UnderAgeException
    {
        boolean Ex = false;
        try {
            cart14.calcCost();
        }
        catch (UnderAgeException e) {
            Ex = true;
        }
        assertEquals(true, Ex);
    }
    @Test
    public void calcCostCart1() throws UnderAgeException {
        double amount = cart1.calcCost();
        assertEquals(cart1Expected, amount, .01);
    }
    @Test
    public void calcCostMeat() throws UnderAgeException {
        double amount = cart2.calcCost();
        assertEquals(cart2Expected, amount, .01);
    }
    @Test
    public void calcCostDairy() throws UnderAgeException {
        double amount = cart3.calcCost();
        assertEquals(cart3Expected, amount, .01);
    }
    @Test
    public void calcCostAlcohol() throws UnderAgeException {
        double amount = cart4.calcCost();
        assertEquals(cart4Expected, amount, .01);
    }
    @Test
    public void calcCostProduce() throws UnderAgeException {
        double amount = cart5.calcCost();
        assertEquals(cart5Expected, amount, .01);
    }
    @Test
    public void calcCostFrozenFood() throws UnderAgeException {
        double amount = cart6.calcCost();
        assertEquals(cart6Expected, amount, .01);
    }
    @Test
    public void calcCostAlcoholFrozenFoodCombo() throws UnderAgeException {
        double amount = cart7.calcCost();
        assertEquals(cart7Expected, amount, .01);
    }
    @Test
    public void UnderAgeAlcoholic() throws UnderAgeException {
        boolean Ex = false;
        try {
            cart8.calcCost();
        }
        catch (UnderAgeException e) {
            Ex = true;
        }

        assertEquals(cart8Expected, Ex);
    }
    @Test
    public void LegalAlcoholic() throws UnderAgeException {
        boolean Ex2 = true;
        try {
            cart9.calcCost();
        }
        catch (UnderAgeException e) {
            Ex2 = false;
        }

        assertEquals(cart9Expected, Ex2);
    }
    @Test
    public void ProduceKing() throws UnderAgeException {
        double amount = cart10.calcCost();
        assertEquals(cart10Expected, amount, .01);
    }
    @Test
    public void GroceryShopppingStudent() throws UnderAgeException {
        double amount = cart11.calcCost();
        assertEquals(cart11Expected, amount, .01);
    }
    @Test
    public void RemoveAlcohol() throws UnderAgeException
    {
        Alcohol beer = new Alcohol();
        cart13.addItem(beer);
        Boolean removed = cart13.RemoveItem(beer);
        assertEquals(true, removed);
    }
    @Test
    public void RemoveAlcoholFake() throws UnderAgeException
    {
        Alcohol beer = new Alcohol();
        Boolean removed = cart13.RemoveItem(beer);
        assertEquals(false, removed);
    }

}
