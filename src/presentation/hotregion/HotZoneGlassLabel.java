package presentation.hotregion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.Position;

public class HotZoneGlassLabel extends JLabel{
	ArrayList<ZoneLabel> zones;

	public HotZoneGlassLabel(){
		initialise();
	}
	
	private void initialise(){
		this.setName("hot zone");
		this.setLayout(null);
		this.setSize(510, 415);
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		zones = new ArrayList<ZoneLabel>();
		
		setZones();
	}
	
	public void changeValue(ArrayList<Double> values){
		for(int i=0;i<zones.size();i++){
			ZoneLabel zone = zones.get(i);
			zone.setText(values.get(i)+"%");
		}
	}
	
	public void makeBig(int index){
		for(ZoneLabel zone : zones){
			zone.makeNormal();
		}
		
		if(index == 0){
			
		}else{
			zones.get(index-1).makeBig();
			System.out.println("index: "+(index+1));
		}
		this.repaint();
	}
	
	private void setZones(){
		Point[] positions = {
				
new Point(10, 20),new Point(45, 100),new Point(90, 20),new Point(215, 20),new Point(330, 20),new Point(390, 100),new Point(415, 20), 
									
											new Point(215, 180), 
				
						new Point(100, 210), new Point(215, 240), new Point(330, 210), 
				
						new Point(30, 330), new Point(215, 350), new Point(410, 330), };
		for(int i=0;i<14;i++){
			ZoneLabel zone = new ZoneLabel();
			zones.add(zone);
			zone.setLocation(positions[i]);
			this.add(zone);
		}
	}
}

class ZoneLabel extends JLabel {
	public ZoneLabel(){
		initialise();
	}
	
	private void initialise(){
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setSize(80, 40);
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setFont(new Font("微软雅黑", Font.BOLD, 17));
		this.setForeground(Color.GRAY);
	}
	
	public void makeBig(){
		this.setForeground(Color.black);
		this.setFont(new Font("微软雅黑", Font.BOLD, 21));
	}
	
	public void makeNormal(){
		this.setForeground(Color.GRAY);
		this.setFont(new Font("微软雅黑", Font.BOLD, 17));
	}
}