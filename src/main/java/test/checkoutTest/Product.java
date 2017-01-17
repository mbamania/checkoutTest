package test.checkoutTest;

public class Product {

	private double price;
	private double offerPrice;
	private ProductType type;

	public Product(ProductType type, double price, double offerPrice) {
		setType(type);
		setPrice(price);
		setOfferPrice(offerPrice);
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

}
