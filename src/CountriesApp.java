import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CountriesApp {
	private static Scanner scnr = new Scanner(System.in);
	private static Path filePath = Paths.get("population.txt");

	public static void main(String[] args) {

		System.out.println("Welcome to the Countries Maintenance Application! ");
		// information();
		System.out.println("1-See the list of Countries");
		System.out.println("2-Add a country");
		System.out.println("3-Delete a country");
		System.out.println("4-Edit a country");
		System.out.println("5-Exit");

		while (true) {

			System.out.println("Enter menu number: ");
			int command = scnr.nextInt();
			if (command == 5) {

				break;
			} else if (command == 1) {
				List<Country> things = readFile();

//				
				// things.sort((Country c1,Country c2)->
				// c1.getName().toLowerCase().compareTo(c2.getName().toLowerCase()));

				ObjectComparator comparator = new ObjectComparator();
				Collections.sort(things, comparator);

				int i = 1;
				for (Country thing : things) {

					System.out.println(i++ + ". " + thing);
				}
			} else if (command == 2) {
				Country Country = getCountryFromUser(scnr);
				System.out.println("Adding " + Country);
				appendLineToFile(Country);
			} else if (command == 3) {
//				Country Country = getCountryFromUser(scnr);
//				System.out.println("Adding " + Country);
//				appendLineToFile(Country);
				deleteCountry();
			} else if (command == 4) {
//				Country Country = getCountryFromUser(scnr);
//				System.out.println("Adding " + Country);
//				appendLineToFile(Country);
				editCountry();
			} else {
				System.out.println("Invalid command.");
			}
		}
		System.out.println("Goodbye.");
		scnr.close();
	}

	private static void deleteCountry() {

		List<Country> things = readFile();
		scnr.nextLine();

		String country = Validator.getString(scnr, "Enter country: ");

		for (int i = 0; i < things.size(); i++) {

			if (things.get(i).getName().equalsIgnoreCase(country)) {
				Country removeCountry = things.get(i);
				things.remove(i);
				System.out.println("Deleting " + removeCountry);

			}

		}

		// things.stream().filter(i ->i.getName().equals(""));
		try {
			Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING);
			Files.newInputStream(filePath, StandardOpenOption.TRUNCATE_EXISTING);
			for (Country thing : things) {
				appendLineToFile(thing);
			}

		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}

	private static void editCountry() {
		scnr.nextLine();
		String country = Validator.getString(scnr, "Enter country: ");
		int population = Validator.getInt(scnr, "Enter population: ");
		List<Country> things = readFile();
		for (Country thing : things) {
			if (thing.getName().equalsIgnoreCase(country)) {
				thing.setPopulation(population);
				System.out.println("Modifying " + thing);

			}
		}
		try {
			Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING);
			Files.newInputStream(filePath, StandardOpenOption.TRUNCATE_EXISTING);
			for (Country thing : things) {
				appendLineToFile(thing);
			}

		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}

	private static Country getCountryFromUser(Scanner scnr) {
		scnr.nextLine();
		// TODO #1 adjust this for your class, not Country
		String country = Validator.getString(scnr, "Enter country: ");
		int population = Validator.getInt(scnr, "Enter population: ");
		int revenue = Validator.getInt(scnr, "Enter revenue: ");

		return new Country(country, population, revenue);
	}

	/**
	 * Read all the objects from a file and store them in a List.
	 */
	public static List<Country> readFile() {
		try {
			List<String> lines = Files.readAllLines(filePath);

			// TODO #2 Add code here@@@ convert this list of String lines from
			// the file to a list of our objects. (Country, in my case)// TODO #2 Add code
			// here@@@ convert this list of String lines from
			// the file to a list of our objects. (Player, in my case)
			List<Country> countries = new ArrayList<>();
			for (String line : lines) {
				// System.out.println(line);
				String[] parts = line.split("@@@");
				// #2 select each part based on index position and convert in to a correct data
				// type
				// System.out.println(Arrays.toString(parts));
				String country = parts[0];
				int population = Integer.parseInt(parts[1]);
				int revenue = Integer.parseInt(parts[2]);

				countries.add(new Country(country, population, revenue));

			}
			return countries;
		} catch (IOException e) {
			System.out.println("Unable to read file.");
			return new ArrayList<>();
		}
	}

	/**
	 * Add an object to the end of the file.
	 */
	public static void appendLineToFile(Country thing) {
		String line = "TODO";

		// TODO #3 add code here@@@ convert your object to a string like
		// it should be as a line in the file. store it in the `line` variable.
		line = thing.getName() + "@@@" + thing.getPopulation() + "@@@" + thing.getRevenue();
		line = String.format("%s@@@%d@@@%d", thing.getName(), thing.getPopulation(), thing.getRevenue());
		// line=thing.toString
		// Files.write requires a list of lines. Create a list of one line.
		List<String> lines = Collections.singletonList(line);
		try {
			Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}

}

class ObjectComparator implements Comparator<Country> {

	public int compare(Country obj1, Country obj2) {
		return obj1.getName().toLowerCase().compareTo(obj2.getName().toLowerCase());
	}

}
