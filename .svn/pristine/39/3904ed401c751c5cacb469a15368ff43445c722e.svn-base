package presentation.diagram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blservice.chartsblservice.ChartBLService;
import businesslogic.statsbl.ChartBL;
import presentation.common.GeneralComboBox;
import presentation.diagram.FiveMan.chooserListener;
import vo.AttriOption;

public class Position extends Diagram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<String> chooses = new ArrayList<String>();
	GeneralComboBox chooser;
	JPanel positionPanel;
	
	ArrayList<JPanel> charts;
	
	boolean isteam;
	int teamID;
	int season;
	
	
	ChartBLService chartbl = new ChartBL();
	
	public Position(int teamID, int season, boolean isTeam) {
		super("     位置分析");
		this.isteam = isTeam;
		this.teamID = teamID;
		this.season = season;
		initialise();
	}
	
	private void initialise(){
		setChooses();
		setChooserComboBox();
		setPositionDiagram();
	}
	
	private void setChooses(){
		//投篮数，有效命中率，罚球数，近距离投篮比例，篮板，助攻，失误，盖帽，犯规数，得分，PER
		chooses.clear();
		chooses.add("属性(默认PER)");
		chooses.add ("投篮数");
		chooses.add("有效命中率");
		chooses.add("罚球数");
		chooses.add("近距离投篮比例");
		chooses.add("篮板");
		chooses.add("助攻");
		chooses.add("失误");
		chooses.add("盖帽");
		chooses.add("犯规数");
		chooses.add("得分");
	}
	public void setChooserComboBox(){
		chooser = new GeneralComboBox(chooses);
		chooser.setsize(200, 40);
		chooser.setlocation(new Point(1000,10));
		chooser.addItemListener(new chooserListener());
		titleLabel.add(chooser);
		this.updateUI();
	} 
	
	class chooserListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			int choosed = e.getStateChange();
			if(choosed == ItemEvent.SELECTED){
			
				Position.this.setVisible(false);
				if(positionPanel != null){
					remove(positionPanel);
				}
				setPositionDiagram();
				Position.this.setVisible(true);
			}
		}
	}
	
	public static AttriOption judgeAttri(String selectedItem){
		switch(selectedItem){
		//投篮数，有效命中率，罚球数，近距离投篮比例，篮板，助攻，失误，盖帽，犯规数，得分，PER
		case "投篮数":return AttriOption.position_fga;
		case "有效命中率":return AttriOption.position_efg;
		case "罚球数":return AttriOption.position_fta;
		case "近距离投篮比例":return AttriOption.position_ifg;
		case "篮板":return AttriOption.fiveMan_reb;
		case "助攻":return AttriOption.position_ast;
		case "失误":return AttriOption.position_to;
		case "盖帽":return AttriOption.position_blk;
		case "犯规数":return AttriOption.position_pf;
		case "得分":return AttriOption.position_pts;
		case "属性(默认PER)":return AttriOption.position_per;
		default:			 return null;
		}
	}
	
	public void setPositionDiagram(){
		positionPanel = new JPanel();
		charts = chartbl.position(teamID,season,judgeAttri(chooser.getSelectedItem().toString()),isteam);
		positionPanel.setLayout(new GridLayout());
		positionPanel.setBackground(Color.WHITE);
		positionPanel.setOpaque(true);
		
		positionPanel.setBounds(0,60,1280, 325);
		
		charts.get(0).setPreferredSize(new Dimension(640,320));
		charts.get(1).setPreferredSize(new Dimension(640,320));
		
		positionPanel.add(charts.get(0));
		positionPanel.add(charts.get(1));
		
		this.add(positionPanel);
	}
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new Position(1,14,true));
		jf.setVisible(true);
	}
}
