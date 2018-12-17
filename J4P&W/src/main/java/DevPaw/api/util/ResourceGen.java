package DevPaw.api.util;


public class ResourceGen {
	public void add(ResourceGen r) {
		this.monet += r.monet;
		this.remonet += r.remonet;
		this.coal += r.coal;
		this.oil += r.oil;
		this.iron += r.iron;
		this.baux += r.baux;
		this.steel += r.steel;
		this.alum += r.alum;
		this.gaso += r.gaso;
		this.uran += r.uran;
		this.lead += r.lead;
		this.munit += r.munit;
		this.food += r.food;
		this.barr += r.barr;
		this.fact += r.fact;
		this.hang += r.hang;
		this.dry += r.dry;
	}
	
	
	public double monet = 0;
	
	public double test = 0;
	public double remonet = 0;
	public double coal = 0;
	public double oil = 0;
	public double iron = 0;
	public double baux = 0;
	public double steel = 0;
	public double alum = 0;
	public double gaso = 0;
	public double uran = 0;
	public double lead = 0;
	public double munit = 0;
	public double food = 0;
	
	public int barr = 0;
	public int fact = 0;
	public int hang = 0;
	public int dry = 0;
	public boolean AGC = true;
	
	public String toString() {
		return "Money: $" + monet +  "\nCoal: " + String.format("%3.3f",coal) + "\nOil: " + String.format("%3.3f",oil) + "\nIron: " + String.format("%3.3f",iron)+ "\nBauxite: " + String.format("%3.3f",baux) + "\nSteel: " + String.format("%3.3f",steel) + "\nAluminum: " + String.format("%3.3f",alum) + "\nGasoline: " + String.format("%3.3f",gaso) + "\nUranium: " + String.format("%3.3f",uran) + "\nLead: " + String.format("%3.3f",lead) + "\nMunitions: " + String.format("%3.3f",munit) + "\nFood: " + String.format("%3.3f",food) + "\nBarracks: " + barr + "\nFactories: " + fact + "\nHangars: " + hang + "\nDrydocks: " + dry;  
	}
	public String[] toStrings() {
		return new String[]{String.format("%3.3f",monet),String.format("%3.3f",coal),String.format("%3.3f",oil),String.format("%3.3f",iron),String.format("%3.3f",baux),String.format("%3.3f",steel),String.format("%3.3f",alum),String.format("%3.3f",gaso),String.format("%3.3f",uran),String.format("%3.3f",lead),String.format("%3.3f",munit),String.format("%3.3f",food),barr+"".trim(),fact+"".trim(),hang+"".trim(),dry+"".trim()};
	}
}
