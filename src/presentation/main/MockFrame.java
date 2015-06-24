package presentation.main;

import javax.swing.JFrame;

public class MockFrame extends JFrame {

	private static MockFrame frame;
	
	private MockFrame(){
		this.setSize(1280, 700);
		
	}
	
	public static MockFrame getFrame(){
		if(frame == null){
			frame = new MockFrame();
		}else{
			
		}
		
		return frame;
	}
	
	
}
