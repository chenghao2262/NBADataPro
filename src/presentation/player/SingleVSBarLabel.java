package presentation.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import presentation.common.DynamicBar;
import presentation.main.Mainframe;
import presentation.main.MockFrame;
import presentation.player.vs.MockSelectionLabel;


public class SingleVSBarLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	private Popup popup;
	
	double avg1;
	double avg2;
	
	int avg;
	int varians;
	
	DynamicBar local;
	DynamicBar another;
	JLabel itemLabel;
	
	static Color blue = new Color(14,105,176);
	static Color grey = Color.GRAY;
	
	int limit;//最高分
	int length;//bar条长度
	
	String[] items = {"场均得分","场均篮板","场均助攻","三分％","罚球％"};
	int[] playerLim = {40,20,30,1,1};
	int[] TeamLim = {150,100,80,1,1};
	
	public SingleVSBarLabel(double avg1,double avg2,String item,int length,boolean isPlayer, int avg, int varians){
		this.avg1 = avg1;
		this.avg2 = avg2;
		this.avg = avg;
		this.varians = varians;
		this.length = length;
		if(isPlayer)
			TeamLim = playerLim;
//		this.setLayout(new GridLayout(1,2,0,20));
		this.setLayout(null);
//		this.setBounds(0, 0, 1280,60);
		this.setLocation(50, 50);
		this.setPreferredSize(new Dimension(1280,60));
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setLayout(null);
		this.setVisible(true);
		setLimit(item);
		setBars(avg1,avg2);
		setItem(item);
		
//		popup = PopupFactory.getSharedInstance().getPopup(MockFrame.getFrame(), new MockSelectionLabel(), 0, 300);
	}
	
	public SingleVSBarLabel(double avg1,double avg2,String item,int length,boolean isPlayer){
		this.avg1 = avg1;
		this.avg2 = avg2;
		this.length = length;
		if(isPlayer)
			TeamLim = playerLim;
//		this.setLayout(new GridLayout(1,2,0,20));
		this.setLayout(null);
//		this.setBounds(0, 0, 1280,60);
		this.setLocation(50, 50);
		this.setPreferredSize(new Dimension(1280,60));
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setLayout(null);
		this.setVisible(true);
		setLimit(item);
		setBars(avg1,avg2);
		setItem(item);
	}

	
	private void setLimit(String item){
		for(int i = 0; i < 5; i++){
			if(items[i].equals(item)){
				this.limit = TeamLim[i];
				System.out.println(item+" "+limit);
			}
		}
	}

	private void setBars(double avg1, double avg2){
		System.out.println(avg1+" "+avg2);
		if(avg1 > avg2){
			local = new DynamicBar(blue,this.limit,avg1,this.length);
			another = new DynamicBar(grey,this.limit,avg2,this.length);
		}else if(avg1 == avg2){
			local = new DynamicBar(grey,this.limit,avg1,this.length);
			another = new DynamicBar(grey,this.limit,avg2,this.length);
		}else if(avg1 < avg2){
			local = new DynamicBar(grey,this.limit,avg1,this.length);
			another = new DynamicBar(blue,this.limit,avg2,this.length);
		}
		local.setValueAndDirection(DynamicBar.LEFT);
		another.setValueAndDirection(DynamicBar.RIGHT);
		
		local.setLocation(20, 5);
		another.setLocation(700, 5);

		this.add(local);
		this.add(another);
		
		local.showOut();
		another.showOut();
	}
	
	private void setItem(String item){
		ItemLabel itemLabel = new ItemLabel(item);
//		itemLabel.changeStable(avg, varians);
		this.add(itemLabel);
	}
		
	public static void main(String[] args){
//		JFrame jf = MockFrame.getFrame();
//		jf.setLayout(null);
//		SingleVSBarLabel bar = new SingleVSBarLabel(20.9,30.0,"场均得分",430, true);
//		bar.setBounds(0, 100, 1280, 60);
//		jf.add(bar);
//		jf.setVisible(true);
//		
//		Popup popup = PopupFactory.getSharedInstance().getPopup(jf, new MockSelectionLabel(), 0, 500);
//		popup.show();
//		
//		
//		MockSelectionLabel label = new MockSelectionLabel();
//		jf.add(label);
	}

	


}
