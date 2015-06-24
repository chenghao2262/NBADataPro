package businesslogic.statsbl;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;

public class Compare {
	 double alpha = 0.05;
	 
	 public Compare(double aph){
		 alpha = aph;
	 }
	 /*
	  * 比较2个总体稳定性，若sample1比sample2更稳定在统计上显著，则返回1，反之返回-1，若无显著差异则返回0
	  */
	public int compareVar(double[] sample1, double[] sample2){
		int result = 0;
		if(sample1.length<1||sample2.length<1)
			return 0;
		FDistribution fd = new FDistribution(sample1.length-1,sample2.length-1);
		if(StatUtils.variance(sample2)==0)
			return -1;
		double d1Divd2 = StatUtils.variance(sample1)/StatUtils.variance(sample2);
		double low = d1Divd2/fd.inverseCumulativeProbability(1-alpha/2);
		double high = d1Divd2/fd.inverseCumulativeProbability(alpha/2);
		
//		System.out.println(d1Divd2+" "+low+" "+high);
		
		if(low>1)
			result = 1;
		if(high<1)
			result = -1;
		return result;		
	}
	
	/*
	 * 比较2个总体均值差，若sample1比sample2期望数值更大在统计上显著，则返回1，反之返回-1，若无显著差异则返回0
	 */
	public int compareE(double[] sample1, double[] sample2){
		int result = 0;
		double mean1 = StatUtils.mean(sample1);
		double mean2 = StatUtils.mean(sample2);
		double d1 = StatUtils.variance(sample1, mean1);
		double d2 = StatUtils.variance(sample2, mean2);
		
		NormalDistribution nd = new NormalDistribution();
		if(sample1.length<1||sample2.length<1)
			return 0;
		double halfLen =  nd.inverseCumulativeProbability(1-alpha/2)*Math.sqrt(d1/sample1.length+d2/sample2.length);
		double low = mean1-mean2-halfLen;
		double high = mean1-mean2+halfLen;
		
//		System.out.println("mean1: "+mean1+" mean2 "+mean2+" d1 "+d1+" d2 "+d2+" "+halfLen+" "+low+" "+high);
		
		if(low>0)
			result = 1;
		if(high<0)
			result = -1;
		return result;	
	}
	
	
}
