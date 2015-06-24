package presentation.player.vs;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentation.common.VSLegend;
import presentation.player.SingleVSBarLabel;


public class VSContentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> items;
	ArrayList<Double> avg1;
	ArrayList<Double> avg2;
	
	private ArrayList<SingleVSBarLabel> bars;
	
	int length = 430;
	static Color blue = new Color(14,105,176);
	static Color grey = Color.GRAY;
	
	private int[] avgAndVariance;
	
	//做与联盟平均值对比时候
	public VSContentPanel(ArrayList<String> itemsNeedAdd,ArrayList<Double> avg1,
			ArrayList<Double> avg2, boolean isPlayer,Point p){
		
		bars = new ArrayList<SingleVSBarLabel>();
		
		this.avg1 = avg1;
		this.avg2 = avg2;
		initArray(itemsNeedAdd);

		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		fl.setVgap(0);
		this.setLayout(fl);

//		this.setBounds(30, 300, 1020,360);
		this.setBounds(p.x, p.y, 1280,360);

		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		addBarsAvg(isPlayer);
		this.setVisible(true);
//		System.out.println(avg1);
//		System.out.println(avg2);
	}

	
	public VSContentPanel(ArrayList<String> itemsNeedAdd,ArrayList<Double> avg1,
			ArrayList<Double> avg2, boolean isPlayer,Point p,int[] avgAndVariance){
		
		this.avgAndVariance = avgAndVariance;

		bars = new ArrayList<SingleVSBarLabel>();

		this.avg1 = avg1;
		this.avg2 = avg2;
		initArray(itemsNeedAdd);

		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		fl.setVgap(0);
		this.setLayout(fl);

		this.setBounds(p.x, p.y, 1280,360);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		addBars(isPlayer);
		this.setVisible(true);
	}

	private void initArray(ArrayList<String> itemsNeedAdd){
		items = new ArrayList<String>();
		for(String item : itemsNeedAdd){
			items.add(item);
		}
	}
	
	private void addBars(boolean isPlayer){
//		bars.clear();
		for(int i = 0; i < items.size();i++){
			SingleVSBarLabel bar = new SingleVSBarLabel(avg1.get(i),avg2.get(i),items.get(i),length,isPlayer, avgAndVariance[2*i], avgAndVariance[2*i+1]);
			bars.add(bar);
			this.add(bar);
		}
	}
	
	private void addBarsAvg(boolean isPlayer){
//		bars.clear();
		for(int i = 0; i < items.size();i++){
			SingleVSBarLabel bar = new SingleVSBarLabel(avg1.get(i),avg2.get(i),items.get(i),length,isPlayer);
			bars.add(bar);
			this.add(bar);
		}
	}

		
	public static void main(String[] args){
//		JFrame jf = new JFrame();
//		jf.setLayout(null);
//		jf.setSize(1280,700);
//		jf.setLocationRelativeTo(null);
//		
//		ArrayList<String> itemsNeedAdd = new ArrayList<String>();
//		ArrayList<Double> avg1 = new ArrayList<Double>();
//		ArrayList<Double> avg2 = new ArrayList<Double>();
//		
//		itemsNeedAdd.add("场均得分"); avg1.add(5.9); avg2.add(10.043);
//		itemsNeedAdd.add("场均助攻"); avg1.add(1.0); avg2.add(2.159);
//		itemsNeedAdd.add("场均篮板"); avg1.add(4.4); avg2.add(4.469);
//		itemsNeedAdd.add("三分％"); 	avg1.add(30.0); avg2.add(34.5);
//		itemsNeedAdd.add("罚球％");	avg1.add(78.4); avg2.add(74.3);
//	
//		jf.add(new VSContentPanel(itemsNeedAdd,avg1,avg2,true));
//		jf.setVisible(true);
	}

	
}
