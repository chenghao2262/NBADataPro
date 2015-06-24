package presentation.common;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class GeneralComboBox extends JComboBox<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> chooses = new ArrayList<String>();
	
	public GeneralComboBox(ArrayList<String> items){
		this.chooses = items;
		initialise();
	}

	private void initialise(){
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(null);
		this.setSize(150, 40);
		for(String item : chooses){
			this.addItem(item);
		}		
		this.setLocation(880, 50);
		this.setSelectedIndex(0);
	}
	
	public void setlocation(Point p){
		this.setLocation(p);
	}
	
	public void setsize(int width, int height){
		this.setSize(width, height);
	}

}
