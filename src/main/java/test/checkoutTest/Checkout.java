package test.checkoutTest;

import java.util.List;
import java.util.stream.Collectors;

public class Checkout {

	/**
	 * get total cost based on offers applied, if offers are empty, the total is
	 * returned else offer discount is calculated on offer proce property of
	 * product
	 * 
	 * @param products
	 * @param activeOffers
	 * @return
	 */
	public double getTotalCost(List<Product> products, List<Offer> activeOffers) {

		double totalCost = products.stream().mapToDouble(obj -> obj.getPrice()).sum();
		if (!activeOffers.isEmpty()) {
			activeOffers.forEach(offer -> applyOffer(products, offer));
			return products.stream().mapToDouble(obj -> obj.getOfferPrice()).sum();
		}

		return totalCost;

	}

	/**
	 * Applies a particular offer dynamically to the products
	 * 
	 * @param products
	 * @param offer
	 */
	private void applyOffer(List<Product> products, Offer offer) {
		switch (offer) {
		case APPLE_BOGOF:
			applyAppleBogof(products);
			break;
		case ORANGE_3_FOR_2:
			applyOrange3For2(products);
			break;
		default:
			break;
		}
	}

	/**
	 * applies this specific 3 for 2 orange offer on list of product, the offer
	 * price is set as required
	 * 
	 * @param products
	 */
	private void applyOrange3For2(List<Product> products) {
		List<Product> apples = products.stream().filter(p -> p.getType().equals(ProductType.ORANGE))
				.collect(Collectors.toList());

		int i = 0;
		while (i < apples.size()) {
			if (i % 3 == 0)
				apples.get(i).setOfferPrice(0.0);
			i++;
		}

	}

	/**
	 * applies the apple BOGOF offer to list of products, the offer price is
	 * updated for calculation later
	 * 
	 * @param products
	 */
	private void applyAppleBogof(List<Product> products) {
		List<Product> apples = products.stream().filter(p -> p.getType().equals(ProductType.APPLE))
				.collect(Collectors.toList());

		if (apples.size() == 2)
			apples.get(1).setOfferPrice(0.0);

		int i = 1;
		while (i < apples.size()) {
			if (i % 2 == 0)
				apples.get(i).setOfferPrice(0.0);
			i++;
		}

	}

}
