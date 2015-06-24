package presentation.gravity;

import java.awt.Color;

import javax.swing.*;

public class GroundPanel extends JPanel implements IGround{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GroundPanel(){
		this.setLayout(null);
		this.setSize(1280, 700);
		this.setLocation(0, 0);
		this.setBackground(Color.DARK_GRAY);
	}

}
