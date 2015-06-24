package presentation.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class VSLegend extends JLabel{

	public VSLegend(){
		this.initialise();
	}
	
	private void initialise(){
		this.setSize(120, 200);
		this.setLayout(new GridLayout(5, 0));
		this.setBorder(BorderFactory.createTitledBorder("图例"));

		this.add(new VSLegendItem("■", "均值差估计", Color.red));
		this.add(new VSLegendItem("■", "方差比估计", Color.blue));
		this.add(new VSLegendItem("↑", "更高", Color.black));
		this.add(new VSLegendItem("-", "无法比较", Color.black));
		this.add(new VSLegendItem("↓", "更低", Color.black));

	}
}


class VSLegendItem extends JLabel{
	
	public VSLegendItem(String sign, String text, Color color){
		this.setLayout(null);
		
		setSign(sign, color);
		setDes(text, color);
	}
	
	private void setSign(String sign, Color color){
		JLabel l = new JLabel();
		l.setForeground(color);
		l.setBounds(0, 0, 30, 30);
		l.setHorizontalAlignment(JLabel.LEADING);
		l.setText(sign);
		l.setFont(new Font("微软雅黑", Font.BOLD, 30));
		this.add(l);
	}
	
	private void setDes(String text, Color color){
		JLabel l = new JLabel();
		l.setBounds(30, 0, 90, 30);
		l.setForeground(color);
		l.setHorizontalAlignment(JLabel.LEADING);
		l.setText(text);
		l.setFont(new Font("微软雅黑", Font.BOLD, 15));
		this.add(l);
	}
}