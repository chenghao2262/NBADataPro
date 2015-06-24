package presentation.match.live;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class LiveDriver {
	
	int i = 0;
	
	public void go(){
//		JFrame f = new JFrame();
//		f.setLayout(null);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		MatchLiveTopPanel p = new MatchLiveTopPanel(1);
//		
//		f.add(p);
//		f.setBounds(50, 50, 1280, 700);
//		f.setVisible(true);
//		
//		Timer timer = new Timer(1000, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				p.appendMessage((int)(Math.random()*100)+"", "aslkfjaslkdjfalsdnfaksldjfasldkfj加啊历史课打飞机拉可三的就发三了的可", 
//						"39:30", "skdjflaskd");
//				i++;
//				if(i==20){
//					((Timer) e.getSource()).stop();
//				}
//			
//			}
//		});
//		
//		timer.start();
	}

	public static void main(String args[]){
		LiveDriver driver = new LiveDriver();
		driver.go();
	}
}
