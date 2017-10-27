package com.businessapp;

import com.businessapp.AppTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.businessapp.customer.IndividualCustomerTest;

/**
 * Created by nhunimuni on 27.10.17.
 */

@RunWith(Suite.class)
@SuiteClasses({
        AppTest.class,
        IndividualCustomerTest.class
        })


class TestSuite {

}
