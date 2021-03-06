package presentation.player.playerDetail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import businesslogic.playerbl.RegressionItem;

public class RegressionLabel extends JLabel{
	PartLabel positiveLabel;
	PartLabel negativeLabel;
	
	public RegressionLabel(){
		initialise();
	}
	
	private void initialise(){
		this.setLayout(new GridLayout(1, 2, 2, 2));
		this.setSize(150, 170);
		this.setOpaque(true);
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.setVisible(true);
		
		setPositive();
		setNegative();
	}
	
	public void setValues(ArrayList<ArrayList<RegressionItem>> lists){
		int random = (int) (4*Math.random());
		if(random == 0){
			lists = null;
		}else if(random >0 ){
			String[] strs = {"篮板","助攻","抢断","盖帽","失误","犯规"};
			int[]	order = {1,2,3,4,5,6};
			lists = new ArrayList<ArrayList<RegressionItem>>();
			
				for(int i=0;i<9;i++){
					int ex = (int)(Math.random()*6);
					int temp = order[ex];
					order[ex] = order[0];
					order[0] = temp;
				}
				
				int fakeSize = (int) (6*Math.random());

				ArrayList<RegressionItem> list1 = new ArrayList<RegressionItem>();
				for(int t=0;t<fakeSize;t++){
					RegressionItem item = new RegressionItem(strs[t], 0.0);
					list1.add(item);
				}
				lists.add(list1);
			
				ArrayList<RegressionItem> list2 = new ArrayList<RegressionItem>();
				for(int t=fakeSize;t<strs.length;t++){
					RegressionItem item = new RegressionItem(strs[t], 0.0);
					list2.add(item);
				}
				lists.add(list2);
			
			
			
		}
		
		if(lists != null){
			positiveLabel.setValues(lists.get(0));
			negativeLabel.setValues(lists.get(1));
		}else{
//			System.out.println("*****相关性 传入空");
			
			this.setVisible(false);
		}
	}
	
	private void setPositive(){
		positiveLabel = new PartLabel("正相关");
		this.add(positiveLabel);
	}
	
	private void setNegative(){
		negativeLabel = new PartLabel("负相关");
		this.add(negativeLabel);
	}
	
//	private 
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setBounds(20, 20, 400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RegressionLabel r = new RegressionLabel();
		f.add(r);
		
		ArrayList<ArrayList<RegressionItem>> lists = new ArrayList<ArrayList<RegressionItem>>();
		for(int j=0;j<2;j++){
			ArrayList<RegressionItem> list = new ArrayList<RegressionItem>();
			for(int i=0;i<7;i++){
				RegressionItem item = new RegressionItem("mock", 2.0);
				list.add(item);
			}
			lists.add(list);
		}
		
		r.setValues(lists);
		f.repaint();
		f.setVisible(true);
	}

}

class PartLabel extends JLabel{

	private boolean isPositive;
	public PartLabel(String text){
		setHeader(text);
		if(text.equals("正相关")){
			isPositive = true;
		}else{
			isPositive = false;
		}
		initialise();
	}
	
	private void setHeader(String text){
		JLabel l = new JLabel();
		l.setPreferredSize(new Dimension(74, 30));
		l.setOpaque(true);
		l.setBackground(Color.GRAY);
		l.setText(text);
		l.setHorizontalAlignment(JLabel.CENTER);
		this.add(l);
	}
	
	public void setValues(ArrayList<RegressionItem> items){
		if(items.size() == 0){
			
		}else{
			
			if(isPositive){
				for(int i=0;i<items.size();i++){
					RegressionItem item = items.get(i);
					String str = item.attri;
					JLabel l = new JLabel();
					l.setHorizontalAlignment(JLabel.CENTER);
					l.setOpaque(true);
					l.setBackground(Color.LIGHT_GRAY);
					l.setText(str);
					this.add(l);
					
				}
			}else{
				for(int i=items.size()-1;i>=0;i--){
					RegressionItem item = items.get(i);
					String str = item.attri;
					JLabel l = new JLabel();
					l.setOpaque(true);
					l.setBackground(Color.LIGHT_GRAY);
					l.setHorizontalAlignment(JLabel.CENTER);
					l.setText(str);
					this.add(l);
					
				}
			}
			
		}
	}
	
	private void initialise(){
//		this.setBackground(Color.GRAY);
//		this.setOpaque(true);
		this.setLayout(new GridLayout(0, 1, 2, 2));
	}
}