package test.java;

import main.java.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import main.java.Cart;
import main.java.Cart1;
import main.java.Cart2;
import main.java.Cart3;
import main.java.Cart4;
import main.java.Cart5;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

    private Class<Cart> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Cart>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Cart0.class},
            {Cart1.class},
            {Cart2.class},
            {Cart3.class},
            {Cart4.class},
            {Cart5.class}
        };
        return Arrays.asList(classes);
    }
    
    private Cart createCart(int age) throws Exception {
        Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
        return constructor.newInstance(age);
    }

    // A sample Cart

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


    @org.junit.Before
    public void setUp() throws Exception {

        // all carts should be set up like this

        // cart created with an age 40 shopper
        cart1 = createCart(40);
        cart2 = createCart(23);
        cart3 = createCart(23);
        cart4 = createCart(23);
        cart5 = createCart(23);
        cart6 = createCart(23);
        cart7 = createCart(50);
        cart8 = createCart(18);
        cart9 = createCart(21);
        cart10 = createCart(15);
        cart11 = createCart(1000);

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

    // sample test
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
}