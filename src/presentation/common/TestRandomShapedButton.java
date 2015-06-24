package presentation.common;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestRandomShapedButton extends RandomShapedButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestRandomShapedButton(){
		super();
		this.setBounds(0, 0, 100, 100);
		ImageIcon icon = new ImageIcon("0.png");
		this.setIcon(icon);
		System.out.println(icon == null);
		this.repaint();
	}

	@Override
	public void mouseMoveIn() {
		System.out.println("in");
	}

	@Override
	public void mouseMoveOut() {
		System.out.println("out");
	}

	@Override
	public void mouseClicked() {
		System.out.println("click");
	}
	
	public static void main(String args[]){
		JFrame f = new JFrame();
		f.getContentPane().setLayout(null);
		TestRandomShapedButton button = new TestRandomShapedButton();
		f.getContentPane().add(button);
		button.setLocation(0, 0);
		f.setSize(500, 800);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		JLabel l = new JLabel();
//		l.setIcon(new ImageIcon("0.png"));
//		l.setBounds(0, 200, 100, 100);
		
	}

}
