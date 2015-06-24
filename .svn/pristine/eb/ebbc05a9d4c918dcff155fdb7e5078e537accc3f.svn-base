package presentation.diagram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class ON_OFF extends Diagram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int ID;
	int season;
	static int type = 0;
	
	ArrayList<String> chooses = new ArrayList<String>();
	GeneralComboBox chooser;
	
	ArrayList<JPanel> on_offPanels = new ArrayList<JPanel>();
	JPanel onOff;
	
	ChartBLService chartbl = new ChartBL();
	PlayerBLService playerbl = new PlayerBL_stub();
	
	public ON_OFF(int playerID,int season) {
		super("     在场统计");
		this.ID = playerID;
		this.season = season;
		initialise();
	}

	void initialise(){
		setChooses();
		setChooserComboBox();
		setOnOffPanel();
	}
	
	public void setChooses(){
		chooses.clear();
		chooses.add("属性(攻防－得分－有效命中％)");
		chooses.add ("攻防－得分－有效命中％");
		chooses.add("助攻－盖帽－篮板");
		chooses.add("罚球－失误－犯规");
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
				ON_OFF.this.setVisible(false);
				if(onOff != null){
					remove(onOff);
				}
				ON_OFF.type = judgeChoosed(e.getItem().toString());
				setOnOffPanel();
				ON_OFF.this.setVisible(true);
			}
		}
	}
	
	int judgeChoosed(String selected){
		switch(selected){
		case"属性(攻防－得分－有效命中％)":return 0;
		case"攻防－得分－有效命中％":return 0;
		case"助攻－盖帽－篮板":return 1;
		case"罚球－失误－犯规":return 2;
			default:return 0;
		}
	}
	
	public void setOnOffPanel(){
		onOff = new JPanel();
		onOff.setBounds(20,60,1200,345);
		onOff.setBackground(Color.WHITE);
		onOff.setOpaque(true);
		onOff.setLayout(new FlowLayout());
		
		on_offPanels = chartbl.on_off(ID, season);
		switch(type){
		case 0:	
			JPanel jp0 = on_offPanels.get(0);
			JPanel jp1 = on_offPanels.get(1);
			JPanel jp2 = on_offPanels.get(2);
			jp0.setPreferredSize(new Dimension(320,340));
			jp1.setPreferredSize(new Dimension(320,340));
			jp2.setPreferredSize(new Dimension(320,340));
			onOff.add(jp0);
			onOff.add(jp1);
			onOff.add(jp2);
		case 1:
			JPanel jp3 = on_offPanels.get(3);
			JPanel jp4 = on_offPanels.get(4);
			JPanel jp5 = on_offPanels.get(5);
			jp3.setPreferredSize(new Dimension(320,340));
			jp4.setPreferredSize(new Dimension(320,340));
			jp5.setPreferredSize(new Dimension(320,340));
			onOff.add(jp3);
			onOff.add(jp4);
			onOff.add(jp5);
		case 2:
			JPanel jp6 = on_offPanels.get(6);
			JPanel jp7 = on_offPanels.get(7);
			JPanel jp8 = on_offPanels.get(8);
			jp6.setPreferredSize(new Dimension(320,340));
			jp7.setPreferredSize(new Dimension(320,340));
			jp8.setPreferredSize(new Dimension(320,340));
			onOff.add(jp6);
			onOff.add(jp7);
			onOff.add(jp8);
		}
		this.add(onOff);
	}

	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new ON_OFF(1,2014));
		jf.setVisible(true);
	}	
}
