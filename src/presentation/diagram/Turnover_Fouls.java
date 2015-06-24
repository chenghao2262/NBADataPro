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

public class Turnover_Fouls extends Diagram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int ID;
	int season;
	
	JPanel turnover_foulsfPanel;
	ArrayList<JPanel> turnover_foulsResultPanels = new ArrayList<JPanel>();
	
	ChartBLService chartbl = new ChartBL();
	PlayerBLService playerbl = new PlayerBL_stub();
	
	
	public Turnover_Fouls(int teamID,int season) {
		super("     失误犯规分析");
		this.ID = teamID;
		this.season = season;
		initialise();
	}
	
	void initialise(){
		setTurnover_Fouls();
	}

	void setTurnover_Fouls(){
		turnover_foulsResultPanels = chartbl.turnover_fouls(ID, season); 
		turnover_foulsfPanel = new JPanel();
		FlowLayout fl = new FlowLayout();
		fl.setHgap(30);
		turnover_foulsfPanel.setLayout(fl);
		turnover_foulsfPanel.setBackground(Color.WHITE);
		turnover_foulsfPanel.setOpaque(true);
		turnover_foulsfPanel.setBounds(0, 60, 1280, 340);
		
		JPanel self_t = turnover_foulsResultPanels.get(0);
		JPanel opp_t = turnover_foulsResultPanels.get(1);
		JPanel self_f = turnover_foulsResultPanels.get(2);
		JPanel opp_f = turnover_foulsResultPanels.get(3);
		
		self_t.setPreferredSize(new Dimension(280,320));
		opp_t.setPreferredSize(new Dimension(280,320));
		self_f.setPreferredSize(new Dimension(280,320));
		opp_f.setPreferredSize(new Dimension(280,320));
		
		turnover_foulsfPanel.add(self_t);		
		turnover_foulsfPanel.add(opp_t);
		turnover_foulsfPanel.add(self_f);		
		turnover_foulsfPanel.add(opp_f);

		this.add(turnover_foulsfPanel);
		repaint();
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new Turnover_Fouls(1,2014));
		jf.setVisible(true);
	}
}
