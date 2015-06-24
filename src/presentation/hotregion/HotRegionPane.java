package presentation.hotregion;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentation.common.TF;

public class HotRegionPane extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DoubleGamePanel map;
	DiscriptionPane dis;
	
	
	public HotRegionPane(int id, boolean isPlayer, int season){
		initialise(id, isPlayer, season);
		
	}
	
	private void initialise(int id, boolean isPlayer, int season){
		this.setSize(1280, 420);//former height = 504
		this.setLocation(0, 255);
		this.setLayout(null);
		this.setBackground(new Color(44, 44, 120));
		setDiscription(id, isPlayer, season);
		setMap();
	}
	
	
	
	
	private void setMap(){
		map = new DoubleGamePanel(510, 415);
		map.setObserver(dis);
		map.setLocation(0, 5);
		map.setVisible(true);
		map.setOpaque(true);
		this.add(map);
	}
	//啊
	private void setDiscription(int id, boolean isPlayer, int season){
		dis = new DiscriptionPane(id, isPlayer, season);
		dis.setLocation(515, 5);//
		this.add(dis);
	}
	
	public static void main(String args[]){
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1280, 700);
		HotRegionPane p = new HotRegionPane(0,  true, 14);
//		p.setBackground(Color.black);
		p.setLocation(0, 255);
		f.add(p);
		f.setVisible(true);
		
		
	}

	

}
