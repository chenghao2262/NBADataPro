package presentation.match.live;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.main.Mainframe;
import presentation.match.MatchAnalyseTopPanel;
import presentation.table.RowPane;

public class LiveRowPane extends RowPane {
	
	private String liveID;

	public LiveRowPane(String liveID, int index, boolean hasIndex, int width, int h) {
		super(index, hasIndex, width, h);
		this.liveID = liveID;
		// TODO Auto-generated constructor stub
	}

	public void addData(String data,int width){		
		JLabel block = new JLabel(data,JLabel.CENTER);
		block.setPreferredSize(new Dimension(width, height));
		block.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				Mainframe.getFrame().changeContent((new MatchLiveTopPanel(liveID)));
			}
		
		});
		this.add(block);
	}
}
