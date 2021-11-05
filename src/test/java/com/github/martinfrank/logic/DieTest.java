package com.github.martinfrank.logic;

import org.junit.Assert;
import org.junit.Test;

public class DieTest {

    @Test
    public void singleDieTest(){
        Die d6 = new Die("D6");
        Assert.assertEquals(6, d6.sides);
        Assert.assertEquals(1, d6.amount);
        Assert.assertEquals(0,d6.addition);
        Assert.assertEquals(1,d6.min());
        Assert.assertEquals(6,d6.max());

        Die oneD6 = new Die("1D6");
        Assert.assertEquals(6, oneD6.sides);
        Assert.assertEquals(1, oneD6.amount);
        Assert.assertEquals(0,oneD6.addition);
        Assert.assertEquals(1,oneD6.min());
        Assert.assertEquals(6,oneD6.max());

        Die twoD6 = new Die("2D6");
        Assert.assertEquals(6, twoD6.sides);
        Assert.assertEquals(2, twoD6.amount);
        Assert.assertEquals(0,twoD6.addition);
        Assert.assertEquals(2,twoD6.min());
        Assert.assertEquals(12,twoD6.max());

        Die twentyD6 = new Die("20D6");
        Assert.assertEquals(6, twentyD6.sides);
        Assert.assertEquals(20, twentyD6.amount);
        Assert.assertEquals(0,twentyD6.addition);
        Assert.assertEquals(20,twentyD6.min());
        Assert.assertEquals(120,twentyD6.max());

        Die twentyD60 = new Die("20D60");
        Assert.assertEquals(60, twentyD60.sides);
        Assert.assertEquals(20, twentyD60.amount);
        Assert.assertEquals(0,twentyD60.addition);
        Assert.assertEquals(20,twentyD60.min());
        Assert.assertEquals(1200,twentyD60.max());
    }

    @Test
    public void dieWithSingleAdditionTest(){
        Die d6 = new Die("D6+1");
        Assert.assertEquals(6, d6.sides);
        Assert.assertEquals(1, d6.amount);
        Assert.assertEquals(1,d6.addition);
        Assert.assertEquals(2,d6.min());
        Assert.assertEquals(7,d6.max());

        Die oneD6 = new Die("1D6+1");
        Assert.assertEquals(6, oneD6.sides);
        Assert.assertEquals(1, oneD6.amount);
        Assert.assertEquals(1,oneD6.addition);
        Assert.assertEquals(2,oneD6.min());
        Assert.assertEquals(7,oneD6.max());

        Die twoD6 = new Die("2D6+1");
        Assert.assertEquals(6, twoD6.sides);
        Assert.assertEquals(2, twoD6.amount);
        Assert.assertEquals(1,twoD6.addition);
        Assert.assertEquals(3,twoD6.min());
        Assert.assertEquals(13,twoD6.max());

        Die twentyD6 = new Die("20D6+1");
        Assert.assertEquals(6, twentyD6.sides);
        Assert.assertEquals(20, twentyD6.amount);
        Assert.assertEquals(1,twentyD6.addition);
        Assert.assertEquals(21,twentyD6.min());
        Assert.assertEquals(121,twentyD6.max());

        Die twentyD60 = new Die("20D60+1");
        Assert.assertEquals(60, twentyD60.sides);
        Assert.assertEquals(20, twentyD60.amount);
        Assert.assertEquals(1,twentyD60.addition);
        Assert.assertEquals(21,twentyD60.min());
        Assert.assertEquals(1201,twentyD60.max());
    }

    @Test
    public void dieWithTwoDigitAdditionTest(){
        Die d6 = new Die("D6+13");
        Assert.assertEquals(6, d6.sides);
        Assert.assertEquals(1, d6.amount);
        Assert.assertEquals(13,d6.addition);
        Assert.assertEquals(14,d6.min());
        Assert.assertEquals(19,d6.max());

        Die oneD6 = new Die("1D6+13");
        Assert.assertEquals(6, oneD6.sides);
        Assert.assertEquals(1, oneD6.amount);
        Assert.assertEquals(13,oneD6.addition);
        Assert.assertEquals(14,oneD6.min());
        Assert.assertEquals(19,oneD6.max());

        Die twoD6 = new Die("2D6+13");
        Assert.assertEquals(6, twoD6.sides);
        Assert.assertEquals(2, twoD6.amount);
        Assert.assertEquals(13,twoD6.addition);
        Assert.assertEquals(15,twoD6.min());
        Assert.assertEquals(25,twoD6.max());

        Die twentyD6 = new Die("20D6+13");
        Assert.assertEquals(6, twentyD6.sides);
        Assert.assertEquals(20, twentyD6.amount);
        Assert.assertEquals(13,twentyD6.addition);
        Assert.assertEquals(33,twentyD6.min());
        Assert.assertEquals(133,twentyD6.max());

        Die twentyD60 = new Die("20D60+13");
        Assert.assertEquals(60, twentyD60.sides);
        Assert.assertEquals(20, twentyD60.amount);
        Assert.assertEquals(13,twentyD60.addition);
        Assert.assertEquals(33,twentyD60.min());
        Assert.assertEquals(1213,twentyD60.max());
    }

    @Test
    public void staticDieTest(){
        Die one = new Die("0D1+1");
        Assert.assertEquals(1, one.sides);
        Assert.assertEquals(0, one.amount);
        Assert.assertEquals(1,one.addition);
        Assert.assertEquals(1,one.min());
        Assert.assertEquals(1,one.max());
    }

    @Test
    public void rangeTest(){
        Die twoD6plus1 = new Die("2D6+1");
        for (int i = 0; i < 100; i++){
            Assert.assertTrue(twoD6plus1.result() >= twoD6plus1.min()  );
            Assert.assertTrue(twoD6plus1.result() <= twoD6plus1.max()   );
        }
    }
}
