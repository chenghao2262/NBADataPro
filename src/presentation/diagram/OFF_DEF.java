package presentation.diagram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blservice.chartsblservice.ChartBLService;
import blservice.playerblservice.PlayerBLService;
import businesslogic.playerbl.PlayerBL_stub;
import businesslogic.statsbl.ChartBL;

public class OFF_DEF extends Diagram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int ID;
	int season;
	
	JPanel off_DefPanel;
	ArrayList<JPanel> off_defResultPanels = new ArrayList<JPanel>();
	
	ChartBLService chartbl = new ChartBL();
	PlayerBLService playerbl = new PlayerBL_stub();
	
	public OFF_DEF(int teamID,int season) {
		super("     攻防对比");
		this.ID = teamID;
		this.season = season;
		initialise();
	}

	private void initialise(){
		setOFF_DEFDiagram();
	}
	
	public void setOFF_DEFDiagram(){
		off_defResultPanels = chartbl.off_def(ID, season);
		off_DefPanel = new JPanel();
		FlowLayout fl = new FlowLayout();
		fl.setHgap(100);
		off_DefPanel.setLayout(fl);
		off_DefPanel.setBackground(Color.WHITE);
		off_DefPanel.setOpaque(true);
		off_DefPanel.setBounds(0, 60, 1280, 340);
		
		JPanel off = off_defResultPanels.get(0);
		JPanel def = off_defResultPanels.get(1);
		off.setPreferredSize(new Dimension(360,320));
		def.setPreferredSize(new Dimension(720,320));
		off_DefPanel.add(off);		
		off_DefPanel.add(def);
		this.add(off_DefPanel);
		repaint();
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new OFF_DEF(1,2014));
		jf.setVisible(true);
	}

}
