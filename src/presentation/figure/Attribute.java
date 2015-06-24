package presentation.figure;

public class Attribute{
	private int index;
	private double value;
	private double absoluteValue;
	private String name;
	
	public Attribute(String name, double absoluteValue, double value, int index){
		this.value = value;
		this.index = index;
		this.absoluteValue = absoluteValue;
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public int getIndex(){
		return index;
	}
	
	public double getValue(){
		return value;
	}
	
	public double getAbsoluteValue(){
		return absoluteValue;
	}
}
