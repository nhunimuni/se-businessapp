package com.businessapp.customer;

import com.businessapp.model.IndividualCustomer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nhunimuni on 20.10.17.
 */
public class IndividualCustomerTest {

    private IndividualCustomer a;
    private IndividualCustomer b;
    private IndividualCustomer c;
    private String name1;
    private String name2;
    private String name3;
    private String id1 = new String("Noah");


    @Before
    public void setUp() {
        a = new IndividualCustomer();
        name1 = "Kenny";
        a.setFirstName(name1);

        b = new IndividualCustomer();
        name2 = null;
        b.setFirstName(name2);

        c = new IndividualCustomer();
        name3 = "";
        c.setFirstName(name3);

        a.setId(id1);
    }

    @Test
    public void name(){
        assertEquals(name1, a.getFirstName());
        assertEquals(name2, b.getFirstName());
        assertEquals(name3, c.getFirstName());

        assertThat(id1 == a.getId(), is(true));
    }



    @Test
    public void getFirstName() throws Exception {
    }

    @Test
    public void setFirstName() throws Exception {
    }

}