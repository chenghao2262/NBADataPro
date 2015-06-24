package presentation.diagram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blservice.chartsblservice.ChartBLService;
import blservice.playerblservice.PlayerBLService;
import businesslogic.playerbl.PlayerBL_stub;
import businesslogic.statsbl.ChartBL;
import presentation.common.GeneralComboBox;

public class Evolution extends Diagram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<String> chooses = new ArrayList<String>();
	GeneralComboBox chooser;
	
	ArrayList<String> name;
	boolean isteam;
	
	JPanel evolutionPanel;
	
	public static String selectedItem = "属性(默认得分)";
	
	ChartBLService chartbl = new ChartBL();
	PlayerBLService playerbl = new PlayerBL_stub();

	public Evolution(ArrayList<String> NAME,boolean isTeam) {
		super("     演化历程");
		this.name = NAME;
		this.isteam = isTeam;
		initialise();
	}

	void initialise(){
//		if(isteam){
//			selectedItem = "属性(默认得分)";
//		}else{
//			selectedItem = "属性(默认胜率)";
//		}
		setChooses();
		setChooserComboBox();
		setEvolutionPanel();
	}
	
		
	public void setChooses(){
		chooses.clear();
		if(isteam){
			chooses.add("属性(默认得分)");
			chooses.add ("助攻");
			chooses.add("盖帽");
			chooses.add("防守数");
			chooses.add("效率");
			chooses.add("失误数");
			chooses.add("犯规数");
			chooses.add("进攻数");
			chooses.add("罚球命中率数");
			chooses.add("得分");
			chooses.add("篮板数");
			chooses.add("首发次数");
			chooses.add("抢断数");
			chooses.add("两双");
			chooses.add("三分命中率数");
		}else{
			chooses.add("属性(默认得分)");
			chooses.add("助攻数");
			chooses.add("盖帽数");
			chooses.add("防守篮板数");
			chooses.add("失误数");
			chooses.add("进攻篮板数");
			chooses.add("罚球命中率");
			chooses.add("得分");
			chooses.add("篮板数");
			chooses.add("投篮命中率");
			chooses.add("抢断数");
			chooses.add("三分命中率");
		}
	}
				
	public static String judgeAttri(String selectedItem){
		switch(selectedItem){
		case "属性(默认得分)":return "point";
		case "助攻数":return "assist";
		case "盖帽数":return "blockShot";
		case "防守篮板数":return "defendRebound";
		case "失误数":return "fault";
		case "犯规数":return "foul";
		case "进攻篮板数":return "offendRebound";
		case "罚球命中率":return "penalty";
		case "得分":return "point";
		case "篮板数":return "rebound";
		case "投篮命中率":return "shot";
		case "抢断数":return "steal";
		case "三分命中率":return "three";
		case "防守数":return "defend";
		case "效率":return "efficiency";
		case "进攻数":return "offend";
		case "首发次数":return "start";
		case "两双":return "doubleTwo";
			default: return "point";
		}
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
				System.out.println(e.getItem());
				Evolution.selectedItem = e.getItem().toString();
				Evolution.this.setVisible(false);
				if(evolutionPanel != null){
					remove(evolutionPanel);
				}
				setEvolutionPanel();
				Evolution.this.setVisible(true);
			}
		}
	}
	
	void setEvolutionPanel(){
		evolutionPanel = new JPanel();
//		JPanel jp = chartbl.evolution(name,judgeAttri(chooser.getSelectedItem().toString()),isteam);
		System.out.println(name+judgeAttri(selectedItem)+isteam);

		
		JPanel jp = chartbl.evolution(name,judgeAttri(selectedItem),isteam);
		
//		System.out.println(name+judgeAttri(selectedItem)+isteam);


		evolutionPanel.setBackground(Color.WHITE);
		evolutionPanel.setOpaque(true);
		
		evolutionPanel.setBounds(100,60, 1000, 325);
		jp.setPreferredSize(new Dimension(600,320));
			
		evolutionPanel.add(jp);
		this.add(evolutionPanel);
	}
	
	public static void main(String[] args){
		ArrayList<String> name = new ArrayList<String>();
		name.add("1");		name.add("2");
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new Evolution(name,false));
		jf.setVisible(true);
	}	

}
