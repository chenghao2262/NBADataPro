package presentation.diagram;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Diagram extends JPanel{

	/**
	 * 球队：五人组合（赛季，属性包括：all）
	 * 		|投篮分析（efg，ast，pts）
	 * 		|投篮时间分析(efg，ast，pts)
	 * 		攻防对比(season)
	 * 		失误犯规分析(season)
	 * 		位置分析(见enum)
	 * 		演化历程（待定）
	 * 		胜负分析 雷达图（bobo）
	 * 
	 * 球员：五人组合
	 * 		|投篮分析（isclutch关键时刻；efg，ast，pts）
	 * 		|投篮时间分析(isclutch关键时刻；efg，ast，pts)
	 * 		位置分析(见enum)
	 * 		在场统计（有8个）
	 * 		｜失误统计（isclutch）除一张扇形图外有列表数据
	 * 	    ｜传球统计（助攻）
	 * 		|盖帽统计	（）
	 * 		演化历程（待定）
	 * 		
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel titleLabel;
	String type;

	Color entered = new Color(30,80,140);
	Color pressed = new Color(42,109,183);
	Color exicted= new Color(26,70,122);

	public Diagram(String type){
		this.type = type;
		this.setLayout(null);
		this.setBounds(0, 265,1280,430);
		this.setBackground(Color.WHITE);
		setTitle();
	}
	
	public void setTitle(){
		titleLabel = new JLabel(" "+ type,JLabel.LEADING);
		titleLabel.setFont(new Font("Dialog",1,25));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(entered);
		titleLabel.setOpaque(true);
		titleLabel.setBounds(0,0,1280,60);
		this.add(titleLabel);
		this.updateUI();
	}
	
	public void settitlt(String title){
		titleLabel.setText(title);
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new Diagram("Hawks"));
		jf.setVisible(true);
	}

}
