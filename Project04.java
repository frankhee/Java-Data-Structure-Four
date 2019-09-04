/*
 * @Author Frank He
 * @Version 03082018
 */
package osu.cse2123;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Project04 {

	public static void main(String[] args) {

		// Prompt user for file name
		Scanner in = new Scanner(System.in);
		System.out.print("Enter database filename: ");
		String fileName = in.nextLine();

		// Issue try statement
		try {
			File newFile = new File(fileName);
			Scanner fileScan = new Scanner(newFile);
			
			//Create array list for simple product objects
			ArrayList<SimpleProduct> summary = new ArrayList<>();
			
			//Collect customer and product information
			String Fname = "";
			String Lname = "";
			String address = "";
			String city = "";
			String state = "";
			String zip = "";
			double salesTax = 0.0;
			int n = 0;
			while (fileScan.hasNext()) {
				if (n == 0) {
					Lname = fileScan.nextLine();
					Fname = fileScan.nextLine();
					address = fileScan.nextLine();
					city = fileScan.nextLine();
					state = fileScan.nextLine();
					zip = fileScan.nextLine();
					salesTax = fileScan.nextDouble();
					n = 1;
				}
				SimpleProduct i = new SimpleProduct();
				fileScan.nextLine();
				i.setName(fileScan.nextLine());
				i.setType(fileScan.nextLine());
				i.setPrice(fileScan.nextDouble());
				i.setQuantity(fileScan.nextInt());
				i.setInStock(fileScan.nextBoolean());
				summary.add(i);
			}
			
			//Create queue and stack for outgoing and outstanding products
			Queue<SimpleProduct> FIFO = new LinkedList<>();
			Stack<SimpleProduct> delayed = new Stack<>();
			
			//Collect objects from the array list
			for (int i = 0; i < summary.size(); i++) {
				if (summary.get(i).getInStock()) {
					FIFO.add(summary.get(i));
				} else {
					delayed.push(summary.get(i));
				}
			}
			
			//Print out customer information 
			System.out.println("Shipping to:");
			System.out.println("       " + Fname + " " + Lname);
			System.out.println("       " + address);
			System.out.println("       " + city + " " + state + " " + zip);
			System.out.println("-------------------------------------------------------------------------------");
			
			//Create order summary for outgoing products 
			double subTotal = 0;
			double sum = 0;
			double shipping = 0;
			while(!FIFO.isEmpty()) {
				SimpleProduct out = FIFO.remove();
				System.out.printf("%3d%2s%-40s%-20s%10.2f%n", out.getQuantity(), " x ", out.getName(),
						"(" + out.getType() + ")", out.getPrice()*out.getQuantity());
				subTotal = subTotal + out.getPrice()*out.getQuantity();
			}
			if (subTotal >= 10 && subTotal < 25) {
				shipping = subTotal * 0.05;
			} else if (subTotal < 10) {
				shipping = subTotal * 0.15;
			}

			double tax = subTotal * salesTax;
			sum = subTotal + tax + shipping;
			
			System.out.println("-------------------------------------------------------------------------------");
			System.out.printf("%-56s%20.2f%n", "Subtotal:", subTotal);
			System.out.printf("%-56s%20.2f%n", "Sales Tax: " + "(" + salesTax + ")", tax);
			System.out.printf("%-56s%20.2f%n", "Shipping:", shipping);
			System.out.println("-------------------------------------------------------------------------------");
			System.out.printf("%-56s%20.2f%n", "Total:", sum);
			System.out.println("-------------------------------------------------------------------------------");
			
			//Create order summary for outstanding products
			System.out.println("Orders Outstanding For:");
			System.out.println("Shipping to:");
			System.out.println("       " + Fname + " " + Lname);
			System.out.println("       " + address);
			System.out.println("       " + city + " " + state + " " + zip);
			System.out.println("-------------------------------------------------------------------------------");

			double outStanding = 0;
			while(!delayed.empty()) {
				SimpleProduct out = delayed.pop();
				System.out.printf("%3d%2s%-40s%-20s%10.2f%n", out.getQuantity(), " x ", out.getName(),
						"(" + out.getType() + ")", out.getPrice()*out.getQuantity());
				outStanding = outStanding + out.getPrice()*out.getQuantity();
			}
			System.out.println("-------------------------------------------------------------------------------");
			System.out.printf("%-56s%20.2f%n", "Outstanding Balance:", outStanding);
			System.out.println("-------------------------------------------------------------------------------");
		} 
		
		//Issue catch statement for any file error 
		catch (IOException e) {
			System.out.println("ERROR");
		}
	}
}