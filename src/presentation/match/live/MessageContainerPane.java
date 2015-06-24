package presentation.match.live;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class MessageContainerPane extends JPanel{

	public static int height = 500;
	private int lineCount = 0;
	
	public MessageContainerPane(){
//		mockMessage();
		this.initialise();
	}
	
	public void initialise(){
		this.setPreferredSize(new Dimension(1190, height));
		this.setBackground(Color.white);
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 0, 0);
		this.setLayout(layout);
	}
	
	public void appendMessage(Message message){
		message.setPreferredSize(new Dimension(1200, 40));
		this.add(message);
		lineCount++;
		if((lineCount*40)>height){
			height = lineCount*40;
			this.setPreferredSize(new Dimension(1190, height));
		}
		this.repaint();
	}
	
	
	
//	private void mockMessage(){
//		for(int i=0;i<16;i++){
//			MessageLabel l1 = new MessageLabel("12:23", "lskadjfla森拉克毒奶粉历史课的发就仨的了发就撒丝路宽带放假拉山口的就发skdjf啊三度空间法拉第13242", "20:123", "skldajf");
//			this.appendMessage(i+"", "sakldjflsakdjflkasdjflkasjdflkjasdlkfjsaldkjflaksd啊丝路宽带解放路可撒的就发", "23:23", null);
//			
//		}
//	}
}
