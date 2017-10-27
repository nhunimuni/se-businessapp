package com.businessapp.customer;

import com.businessapp.model.IndividualCustomer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Suite;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nhunimuni on 20.10.17.
 */
public class IndividualCustomerTest {




    @Before
    /*public void setUp() {

        //Person 1
        name1 = "Kenny";
        lastname1 = "Park";
        id1 = "A001";
        a = new IndividualCustomer();
        date = new Date(2000, 06, 11);

    }*/

    @Test
    public void firstName(){
        IndividualCustomer a = new IndividualCustomer();

        a.setFirstName("Kenny");
        String name1 = a.getFirstName();
        assertEquals(name1, a.getFirstName());

        a.setFirstName(null);
        name1 = a.getFirstName();
        assertEquals(name1, a.getFirstName());

        a.setFirstName("");
        name1 = a.getFirstName();
        assertEquals(name1, a.getFirstName());
    }

    @Test
    public void lastName(){
        IndividualCustomer a = new IndividualCustomer();

        a.setName("Tom");
        String name1 = a.getName();
        assertEquals(name1, a.getName());

        a.setName(null);
        name1 = a.getName();
        assertEquals(name1, a.getName());

        a.setName("");
        name1 = a.getName();
        assertEquals(name1, a.getName());
    }

    @Test
    public void id(){
        IndividualCustomer a = new IndividualCustomer();

        a.setId("A100");
        String name1 = a.getId();
        assertEquals(name1, a.getId());

        a.setId(null);
        name1 = a.getId();
        assertEquals(name1, a.getId());

        a.setId("");
        name1 = a.getId();
        assertEquals(name1, a.getId());
    }

    @Test
    public void created(){
        IndividualCustomer a = new IndividualCustomer();

        a.setCreated(new Date(2000, 06, 11));
        Date date = a.getCreated();
        assertEquals(date, a.getCreated());

        a.setCreated(null);
        date = a.getCreated();
        assertEquals(date, a.getCreated());
    }

}
