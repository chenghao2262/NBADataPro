package presentation.common;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class SeasonComboBox extends JComboBox<String>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeasonComboBox(){
		initialise();
	}

	private void initialise(){
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(null);
		this.setSize(150, 40);
		
		this.addItem("赛季");
		this.addItem("2014-2015");
		this.addItem("2013-2014");
		this.addItem("2012-2013");
		this.addItem("2011-2012");
		this.addItem("2010-2011");
		this.addItem("2009-2010");

		this.setLocation(880, 50);
		this.setSelectedIndex(0);

	}
	
	public void setlocation(Point p){
		this.setLocation(p);
	}
		
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new SeasonComboBox());
		jf.setVisible(true);
	}
	
}
