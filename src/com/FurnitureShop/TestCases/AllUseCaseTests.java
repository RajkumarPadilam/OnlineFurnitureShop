package com.FurnitureShop.TestCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BasicSearchTestCase.class, BrowseProductsTestCase.class,
		ProductDetailsTestCase.class, StoreLocationTestCase.class })
public class AllUseCaseTests {

}
