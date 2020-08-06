
public class Country {
	private String name;
	private int population;
	private int revenue;

	public Country() {
		super();
	}

	public Country(String name, int population,int revenue) {
		super();
		this.name = name;
		this.population = population;
		this.revenue = revenue;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", population=" + population + ", revenue=" + revenue + "]";
	}

	
}
