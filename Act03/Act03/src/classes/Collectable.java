package classes;

import java.util.ArrayList;
 
public class Collectable {
	int id = 0;
	String name = null;
	String brand = null;
	
	/**
	 * Number to identify a figure. All ids should be unique.
	 */
	// TODO: Create id (number) attribute.
	
	/**
	 * Name of the figure.
	 */
	// TODO: Create name attribute
	
	
	/**
	 * Brand that published/released the figure.
	 */
	// TODO: Create brand attribute
	
	/**
	 * Collection score [0,10]
	 * If a figure already has a score over 0.0, just change the value.
	 */
	private double score;
	
	/**
	 * List of figure imperfections (text).
	 */
	private ArrayList<String> imperfections;
	
	/**
	 * Constructor
	 * @param nId - Figure id
	 * @param nName - Figure name
	 * @param nBrand - Figure brand
	 */
	public Collectable(int nId, String nName, String nBrand) {
		this.id = nId;
		this.name = nName;
		this.brand = nBrand;
		this.score = 0.0;
		this.imperfections = new ArrayList<>();
		// TODO: assign id
		// TODO: assign name
		// TODO: assign brand
		// TODO: initialize score as 0.0
		// TODO: initialize list as empty ArrayList
	} 
	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getBrand() {
	    return brand;
	}

	public void setBrand(String brand) {
	    this.brand = brand;
	}

	public double getScore() {
	    return score;
	}

	public void setScore(double score) {
	    if(score >= 0 && score <= 10) {   
	        this.score = score;
	    }
	}

	public ArrayList<String> getImperfections() {
	    return imperfections;
	}

	public void setImperfections(ArrayList<String> imperfections) {
	    this.imperfections = imperfections;
	}
	// TODO: Getters/Setters
	
	public void addImperfection(String imperfection) {
		this.imperfections.add(imperfection);
	}
	
	public String toString() {
		String desc = "Id: %d - Name: %s - Brand: %s - Score: %.2f\n"; // creo que le faltaba un punto y 2
		return String.format(desc, this.id, this.name, this.brand, this.score);
	}

}
