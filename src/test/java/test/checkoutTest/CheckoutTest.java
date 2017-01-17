package test.checkoutTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CheckoutTest {

	private Checkout checkoutSystem;
	private List<Product> products;
	private ArrayList<Offer> offers;

	@Before
	public void init() {
		checkoutSystem = new Checkout();
		products = new ArrayList<Product>();
		offers = new ArrayList<Offer>();
	}

	/**
	 * Test with no offers, just products and total should be sum of all price
	 * 205 should be total
	 */
	@Test
	public void getTotalCostTestNoOffers() {
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));
		products.add(new Product(ProductType.ORANGE, 25.0, 25.0));
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));

		double cost = checkoutSystem.getTotalCost(products, offers);
		assertTrue(cost == 205.0);
	}

	/**
	 * Test to see total with one offer, APPLE_BOGOF Apples are buy one get one
	 * free, so shoud only charge for 2 apples and not 3 145, should be total
	 */
	@Test
	public void getTotalCostWithOffer_APPLE_BOGOF() {
		products.clear();
		offers.clear();
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));
		products.add(new Product(ProductType.ORANGE, 25.0, 25.0));
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));

		offers.add(Offer.APPLE_BOGOF);

		double cost = checkoutSystem.getTotalCost(products, offers);
		assertTrue(cost == 145.0);
	}

	/**
	 * Test to see the offer 3 for 2 on orange, every 3 oranges, should only
	 * charge for 2 total should be 230 3 apples = 180, 2 orange = 50, not 3
	 */
	@Test
	public void getTotalCostWithOffer_ORANGE_3_FOR_2() {
		products.clear();
		offers.clear();
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));
		products.add(new Product(ProductType.ORANGE, 25.0, 25.0));
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));

		products.add(new Product(ProductType.ORANGE, 25.0, 25.0));
		products.add(new Product(ProductType.ORANGE, 25.0, 25.0));

		offers.remove(Offer.APPLE_BOGOF);
		offers.add(Offer.ORANGE_3_FOR_2);
		double cost = checkoutSystem.getTotalCost(products, offers);
		assertTrue(cost == 230.0);
	}

	/**
	 * Test to see the 2 offers and how they work together
	 */
	@Test
	public void getTotalCostWith_2_offer() {

		products.clear();
		offers.clear();
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));
		products.add(new Product(ProductType.ORANGE, 25.0, 25.0));
		products.add(new Product(ProductType.APPLE, 60.0, 60.0));

		products.add(new Product(ProductType.ORANGE, 25.0, 25.0));
		products.add(new Product(ProductType.ORANGE, 25.0, 25.0));

		offers.add(Offer.APPLE_BOGOF);
		offers.add(Offer.ORANGE_3_FOR_2);
		double cost = checkoutSystem.getTotalCost(products, offers);
		assertTrue(cost == 170.0);
	}

}
