/*
 * @Author Frank He
 * @Version 03082018
 */
package osu.cse2123;

import java.util.Scanner;

public class SimpleProduct implements Product{
	
	//Create private variables
	private String name;
	private String type;
	private double price;
	private int quantity;
	private boolean inStock;
	
	//Assign initial values
	public SimpleProduct() {
		name = "";
		type = "";
		price = 0.0;
		quantity = 0;
		inStock = false;
	}
	
	//Set product name
	public void setName(String name) {
		this.name = name;
	}
	
	//Return product name
	public String getName() {
		return name;
	}
	
	//Set product type
	public void setType(String type) {
		this.type = type;
	}

	//Return product type
	public String getType() {
		return type;
	}

	//Set product price 
	public void setPrice(double price) {
		this.price = price;
	}

	//Return product price
	public double getPrice() {
		return price;
	}

	//Set product quantity 
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//Return product quantity
	public int getQuantity() {
		return quantity;
	}
		
	//Set product in stock boolean
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	//Return product in stock boolean
	public boolean getInStock() {
		return inStock;
	}
	
	/*
	 * readNextProduct
	 * @param inFile - a Scanner containing product entries
	 * @return false if the product cannot be completely read,
	 * 			true otherwise
	 */
	public boolean readNextProduct(Scanner inFile) {
		boolean returnValue = true;
		for(int i = 0;i<5;i++) {
			if(i == 0) {
				this.setName(inFile.nextLine());
			}
			else if(i == 1) {
				this.setType(inFile.nextLine());
			}
			else if(i == 2) {
				try {
					this.setPrice(inFile.nextDouble());				
				}
				catch(Exception e) {
					System.out.println("ERROR");
				}
			}

			else if(i == 3) {
				try {
					this.setQuantity(inFile.nextInt());					
				}
				catch(Exception e) {
					System.out.println("ERROR");
				}
			}

			else if(i == 4) {
				try {
					this.setInStock(inFile.nextBoolean());					
				}
				catch(Exception e) {
					System.out.println("ERROR");
				}
			}
			else {
				returnValue = false;
			}
			}
		return returnValue;
		}			
	
	//Return true if product name and type are the same, false otherwise
	public boolean equals(SimpleProduct i) {
		boolean result = false;
		if(i instanceof SimpleProduct) {
			SimpleProduct t1 = (SimpleProduct)i;
			if(this.name.equals(t1.getName())&&this.type.equals(t1.getType())) {
				result = true;
			}
		}
		return result;
	}
	
	//Return product information in string form 
	public String toString() {
		String info = "(" + this.name + "," + this.type + "," + this.price + "," + this.quantity + "," + this.inStock +")";
		return info;
	}
}


