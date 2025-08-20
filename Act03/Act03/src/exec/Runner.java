package exec;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import classes.*;

public class Runner {
	
	/**
	 * Looks up a single figure from the list using ID as main search attribute
	 * @param figures - List of figures
	 * @param id - The id of the figure we're searching for. All ids should be unique, so there's no chance for repeated figures
	 * @return A SINGLE FIGURE
	 */
	public static Collectable findCollectableById(ArrayList<Collectable> figures, int id) {
		Collectable res = null;
		
		for (Collectable c : figures) {
			if (c.getId() == id) {
				res = c;
				break;
			}
		}
		
		return res;
	}
	
	public static void menu(int option, ArrayList<Collectable> figures) {
		switch(option) {
			case 0:
				System.out.println("======== Option 0: Show loaded data ========\n");
				System.out.println(figures);
				System.out.println("======== End Option 0 ========");
				break;
			case 1:
				/*
				 * Adds a new figure to the list
				 */
				System.out.println("======== Option 1: Add new Figure ========\n");
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Enter id:\n");
					String id = br.readLine();
					// TODO: Read id
					System.out.println("Enter name:\n");	
					String name = br.readLine();
					System.out.println("Enter brand:\n");
					String brand = br.readLine();
					// TODO: Create and add a NEW collectable to the list
					Collectable newFigure = new Collectable(id, name, brand);
					figures.add(newFigure);

					System.out.println("New figure added!");
				}
				catch(Exception e){
					e.printStackTrace();
				}
				System.out.println("======== End Option 1 ========");
				break;
			case 2:
				/*
				 * TODO: Add score to figure
				 * If figure doesn't exist, show message
				 * Example of score: 5.4
				 */
				System.out.println("======== Option 2: Add score to figure ========\n");
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Enter id:\n");
					// TODO: Read id
					int id = Integer.parseInt(br.readLine().trim());

					
					Collectable figure = findCollectableById(figures, 0);
					if(figure != null) {
						System.out.println("Enter score:\n");
						// TODO: Read and add score to figure
						// HINT: Parse double from "5.4" to 5.4 using Double class
						double score = Double.parseDouble(br.readLine().trim());
						figure.setScore(score);
						System.out.println("puntaje agregado con el id " + id + ": " + score);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				System.out.println("======== End Option 2 ========");
				break;
			case 3:
				/*
				 * TODO: Adds a single imperfection to a figure
				 * If figure doesn't exist, show message
				 * Example of imperfection: Missing piece
				 */
				System.out.println("======== Option 3: Add imperfection to figure ========\n");
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Enter id:\n");
					// TODO: Read id
					
					Collectable figure = findCollectableById(figures, 0);
					if(figure != null) {
						System.out.println("Enter imperfection:\n");
						// TODO: Read and add imperfection to figure
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				System.out.println("======== End Option 3 ========");
				break;
			case 4:
				System.out.println("======== Option 4: Save figures with imperfections and score >= 3 to file ========\n");
				/*
				 * TODO: 
				 * Save to file name "summary.txt" the figures with imperfections and the figures with a score of 3 or more
				 * If a figure checks the 2 conditions, add it just once to the file
				 * Save to file using name, brand and score and imperfections (Imperfections separated by -)
				 * Example:
				 * Figure 1, Brand 1, 4.5, Broken piece - Broken part - Scratch
				 */
				
			    try (PrintWriter escritor = new PrintWriter(new FileWriter("summary.txt", false))) { 			       
			    	for (Collectable figura : figures) {
			            List<String> imperfecciones = figura.getImperfections();
			            boolean tieneImperfecciones = (imperfecciones != null && !imperfecciones.isEmpty());

			            Double puntajeObj = figura.getScore(); 
			            double puntaje = (puntajeObj == null ? Double.NaN : puntajeObj.doubleValue());

			            boolean puntajeOk = (!Double.isNaN(puntaje) && puntaje >= 3.0);

			            if (tieneImperfecciones || puntajeOk) { 
			                String nombre = figura.getName();
			                String marca = figura.getBrand();

			                StringBuilder linea = new StringBuilder();
			                linea.append(nombre).append(", ").append(marca).append(", ");
			                if (Double.isNaN(puntaje)) {
			                    linea.append("n-a"); 
			                } else {
			                    linea.append(puntaje); 
			                }

			                if (tieneImperfecciones) {
			                    linea.append(", ");
			                    linea.append(String.join(" - ", imperfecciones)); 
			                }

			                escritor.println(linea.toString()); 
			            }
			        }
			        System.out.println("Se generó summary.txt correctamente.");
			    } catch (Exception e) {
			        e.printStackTrace();

			        
				 }
				System.out.println("======== End Option4. Thanks for playing! ========");
				System.exit(0);
				break;
			default:
				break;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("======== Welcome ========");
		System.out.println("0 - Show loaded data");
		System.out.println("1 - Add new Figure");
		System.out.println("2 - Add score to figure");
		System.out.println("3 - Add imperfection to figure");
		System.out.println("4 - Save figures with imperfections and score >= 3 to file");
		try {
			ArrayList<Collectable> figures = new ArrayList<Collectable>();
			/*
			 * Add figures
			 * Read file:
			 * Format ->
			 * id, name, brand
			 * HINT: .split return an array, NOT AN ARRAYLIST, AN ARRAY
			 */
			BufferedReader br1 = new BufferedReader(new FileReader(new File("data/figures.txt")));
			// TODO: Read entire file, add figures to list
			String row = br1.readLine(); //id,name,brand
			
			while ((row = br1.readLine()) != null) {
				row = row.trim();
				if (row.isEmpty()) continue;
				String[] parts = row.split(",");
				if (parts.length < 3) {
					continue;
				}
				
				String idStr = parts[0].trim();
				String name = parts[1].trim();
				String brand = parts[2].trim();
				try {
					int id = Integer.parseInt(idStr);
					Collectable c = new Collectable(id, name, brand);
					figures.add(c);
				} catch (NumberFormatException nfe) {
				}
			}
			br1.close();
			
			while(true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String option = br.readLine();
				int op = Integer.parseInt(option);
				menu(op, figures);
				System.out.println("0 - Show loaded data");
				System.out.println("1 - Add new Figure");
				System.out.println("2 - Add score to figure");
				System.out.println("3 - Add imperfection to figure");
				System.out.println("4 - Save figures with imperfections and score >= 3 to file\n");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
