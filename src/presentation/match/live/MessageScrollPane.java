package presentation.match.live;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.*;

public class MessageScrollPane extends JScrollPane implements Scrollable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MessageContainerPane container;
	Timer timer;
	JScrollBar bar;
	
	ArrayList<Message> messages;
	
	private int value;

	public MessageScrollPane(MessageContainerPane container){
		this.setViewportView(container);
		this.container = container;
		this.getVerticalScrollBar().setUnitIncrement(20);
		this.setBounds(0, 30, 1200, 450);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		MessageScrollPane.this.getVerticalScrollBar().setAutoscrolls(false);
//		this.doLayout();
		messages = new ArrayList<Message>();

		bar = MessageScrollPane.this.getVerticalScrollBar();
//		bar.addAdjustmentListener(l);
	}
	
	private void toBottom(){
		if(timer != null){
			timer.stop();
		}
		timer = new Timer(20, new Action());
		timer.start();
//		int containerHeight = container.getPreferredSize().height;
		
	}
	

	public void appendMessage(String time, String hostMessage, String scores, String guestMessage){
		Message message = new MessageLabel(time, hostMessage, scores, guestMessage);
		if(ScrollLockLabel.state == false){
			messages.add(message);
		}else{
			container.appendMessage(message);
		}
		this.getVerticalScrollBar().setValue(0);
	}
	
	public void appendPeriod(int period, String scores){
		Message message = new PeriodMessageLabel(period, scores);//new MessageLabel(time, hostMessage, scores, guestMessage);
		if(ScrollLockLabel.state == false){
			messages.add(message);
		}else{
			container.appendMessage(message);
		}
	}
	
	class Action implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JScrollBar bar = MessageScrollPane.this.getVerticalScrollBar();
			bar.setValue(bar.getValue()+5);
			if(bar.getValue()>bar.getMaximum()){
				bar.setValue(bar.getMaximum());
				timer.stop();
			}
		}
		
	}
	
	class AjustListener implements AdjustmentListener{

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public void update() {
		if(ScrollLockLabel.state){
//			System.out.println("bottom");
			for(int i=0;i<messages.size();i++){
				container.appendMessage(messages.get(i));
			}
			messages.clear();
			toBottom();
		}else{
			if(timer != null){
				timer.stop();
			}
//			MessageScrollPane.this.getVerticalScrollBar().set
		}
//		bar.setValue((int)(Math.random()*bar.getMaximum()));
		
	}
	
}
