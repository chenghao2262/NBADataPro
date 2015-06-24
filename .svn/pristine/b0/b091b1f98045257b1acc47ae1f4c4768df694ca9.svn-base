package presentation.diagram;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentation.common.SelectLabel;
import presentation.player.playerDetail.PlayerMiddlePanel;
import presentation.team.teamDetail.TeamMiddlePanel;

public class DiagramList extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> classification = new ArrayList<String>();
	ArrayList<SelectLabel> selectedGroups = new ArrayList<SelectLabel>();
	JPanel observer;//可能是TeamMiddlePanel/PlayerMiddlePanel
	public static String item;
		
	public DiagramList(ArrayList<String> items,Point loc,int len){
		this.classification = items;
		GridLayout gl = new GridLayout(items.size(),1,0,1);
		this.setLayout(gl);
		this.setSize(len,50*items.size());
		this.setLocation(loc);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		addDiagramClassifications();
		this.setVisible(true);
	}

	
	private void addDiagramClassifications(){
		selectedGroups.clear();
		for(String item : classification){
			SelectLabel sl = new SelectLabel(item,new Point(181,50));
			sl.setIsPopup();
			selectedGroups.add(sl);
			sl.addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent e) {
					DiagramList.item = item;
					setSelectedGroups(sl);
					if(observer.getClass() == TeamMiddlePanel.class){
						((TeamMiddlePanel) observer).updateDiagram(item);
//						System.out.println("GOT THE TEA MMIDDLEPANEL LISTENER");
					}else{
						((PlayerMiddlePanel)observer).updateDiagram(item);
					}
				}
			});
			this.add(sl);
		}
	}
	
	public void setSelectedGroups(SelectLabel s){
		for(SelectLabel sl : selectedGroups){
			if(sl != s){
				sl.setBackground(Color.black);
				sl.isSelected = false;
			}else{
				sl.isSelected = true;
				sl.setBackground(Color.gray);
			}
		}
	}

	
	public void register(JPanel diagram){
		observer = diagram;
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		ArrayList<String> items = new ArrayList<String>();
		items.add("五人组合");
		items.add("五人组合");
		items.add("五人组合");
		items.add("五人组合");
		jf.add(new DiagramList(items,new Point(0,0),181));
		jf.setVisible(true);
	}


}
