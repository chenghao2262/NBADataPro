package businesslogic.playerbl;

public class RegressionItem implements Comparable<Object>{
	public String attri;
	public double param;
	
	public RegressionItem(String a,double p){
		attri = a;
		param = p;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		RegressionItem item = (RegressionItem) o;
		if(param<item.param)
			return -1;
		else if(param==item.param)
			return 0;
		else
			return 1;
	}

}
