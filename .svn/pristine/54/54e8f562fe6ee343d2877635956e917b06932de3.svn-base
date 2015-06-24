package presentation.figure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class RadarFigure extends JLabel{
	private Color[] colors = {new Color(180, 30, 30), new Color(30, 180, 30), new Color(30, 30, 180)};
	private int currentColorIndex = 0;
	
	JLabel figureLabel;
	JLabel legendLabel;
	
	BackgroundLabel backgroundLabel;
	public boolean single = true;
//	LinesLabel linesLabel;
	private int attriCount = 11;//几个属性
	private int levelCount = 3;//几种等级
	int size = 100;			//半径
	
	private ArrayList<LinesLabel> labels = new ArrayList<RadarFigure.LinesLabel>();

	private ArrayList<Boolean> readyToDrawLines = new ArrayList<Boolean>();
	private boolean readyToDrawSpoke = false;

	//
	public RadarFigure(int attriCount, int levelCount, int size){
		this.attriCount = attriCount;
		this.levelCount = levelCount;
		this.size = size;
		this.initialise();
	}
	
	public RadarFigure(){
		this.initialise();
	}
	
	public RadarFigure(int size){
		this.size = size;
		initialise();
	}
	
	private void setFigurePart(){
		figureLabel = new JLabel();
		figureLabel.setBounds(0, 0, 2*size+30, 2*size);
		figureLabel.setLayout(null);
		this.add(figureLabel);
	}
	
	private void setLegendPart(){
		int height = 2*size;
		int width = 2*(int)(0.4*size);
		legendLabel = new JLabel();
		legendLabel.setBounds(2*size+30, 0, width, height);
//		legendLabel.setOpaque(true);
		legendLabel.setBackground(new Color(200, 200, 255));
		legendLabel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		this.add(legendLabel);
	
	}
	
	private void initialise(){
		this.setSize((int)(2.8*size), 2*size);
		this.setLayout(null);

		setFigurePart();
		setLegendPart();
		this.setBackgroundLabel();
//		this.setLinesLabel();
	}
	
	private void setBackgroundLabel(){
		backgroundLabel = new BackgroundLabel((int) (size));
		backgroundLabel.setLayout(null);
//		backgroundLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		backgroundLabel.setLocation(0, 0);
		figureLabel.add(backgroundLabel);
	}
	
	private void setLinesLabel(){
		for(int i=0;i<labels.size();i++){
			LinesLabel linesLabel = labels.get(i);
			linesLabel.setLocation(0, 0);
			backgroundLabel.add(linesLabel);
			
			
			linesLabel.createPoints();
			linesLabel.repaint();
		}
		
	
	}
	
	public void setValues(int labelIndex, Attribute attri){
		if(labels.size()-1<labelIndex){
			labels.add(new LinesLabel(labelIndex, size, colors[currentColorIndex]));
			currentColorIndex++;
		}
		labels.get(labelIndex).addAttri(attri);
		
		if(labels.size()>1){
			single = false;
		}
	}
	
	public void drawFigure(ArrayList<String> names){
		for(int i=0;i<labels.size();i++){
			ColorAndDescriptionLabel label = new ColorAndDescriptionLabel(i, names.get(i));
			legendLabel.add(label);
		}
		setLinesLabel();
	}
	
	public static void main(String args[]){
		JFrame f = new JFrame();
		f.setBounds(200, 200, 700, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		RadarFigure figure = new RadarFigure(11, 3, 160);
		figure.setLocation(20, 20);
		
		for(int i=0;i<3;i++){
			for(int j=0;j<11;j++){
				figure.setValues(i, new Attribute("mock"+j, 100, Math.random(), j));
			}
		}
		
		figure.drawFigure(null);
	
		f.add(figure);
		f.setVisible(true);
	}


class BackgroundLabel extends JLabel{
	private int R;
	
	private int arc1 = 0;
	private int arc2 = 0;
	private int r1 = 33;
	private int r2 = 66;
	private int r3 = 99;
	
	private Timer timer;
	
	public BackgroundLabel(int size){
		this.setSize(2*size+30, 2*size);
		this.R = size;
		this.r1 = (int)(R/3);
		this.r2 = (int)(2*R/3);
		this.r3 = (int)(3*R/3);
		
		paintDynamicLevel();
	}

	@Override
	public void paintComponent(Graphics g) {
//		super.paint(g);
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		paintLevel(g2d, 30);
		arc1 += 10;
		arc2 -= 10;
		g2d.setColor(new Color(20, 20, 20, 30));
//		g2d.drawArc(R-r1, R-r1, 2*r1, 2*r1, 0, arc1);
//		g2d.drawArc(R-r2, R-r2, 2*r2, 2*r2, 0, arc2);
//		g2d.drawArc(R-r3, R-r3, 2*r3, 2*r3, 0, arc1);

		g2d.fillArc(R-r1, R-r1, 2*r1, 2*r1, 0, arc1);
		g2d.fillArc(R-r2, R-r2, 2*r2, 2*r2, 0, arc2);
		g2d.fillArc(R-r3, R-r3, 2*r3, 2*r3, 0, arc1);
		
//		System.out.println("paint");
	}
	
	private void paintLevel(Graphics2D g2d, int r){
//		g2d.drawRoundRect(R, R, width, height, arcWidth, arcHeight);
		g2d.setColor(Color.black);
//		g2d.drawOval(R-r, R-r, r, r);
		g2d.drawArc(R-r, R-r, r, r, 0, 300);
		
	}
	
	public void paintDynamicLevel(){
//		java.util.Timer timer = new java.util.Timer();
//		timer.schedule(new DrawCircleTask(g2d, 0), 0, 1000);
		timer = new Timer(20, new DrawCircleAction());
		timer.start();
	}
	
	class DrawCircleAction implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			repaint();
//			paintComponent(getGraphics());
//			System.out.println("e");
			if(arc1>360){
//				timer.stop();
				readyToDrawSpoke = true;
//				repaint();
			}
		}
		
	}

	

}

class LinesLabel extends JLabel{
	private Color color = new Color(10, 10, 200);
	private int index;
	private int max;
	private ArrayList<Attribute> attris;
	private ArrayList<Point> points;//数学坐标
	private ArrayList<Point> pointsToDraw;
	private ArrayList<Point> tempPoints;
	private ArrayList<ArrayList<Point>> memoryPoints;
	private ArrayList<Double> ks;
	private ArrayList<Double> arcs;
	
	private int dr;
	private int dx;
	private boolean pointsArrived;
	
	
	public LinesLabel(int index, int max, Color color){
		readyToDrawLines.add(new Boolean(false));
		this.index = index;
		this.color = color;
		this.max = max;
		this.setSize(2*max+30, 2*max);
		attris = new ArrayList<Attribute>();
		points = new ArrayList<Point>();
		pointsToDraw = new ArrayList<Point>();
		ks = new ArrayList<Double>();
		arcs = new ArrayList<Double>();
		tempPoints = new ArrayList<Point>();
		memoryPoints = new ArrayList<ArrayList<Point>>();
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.setLayout(null);
	}
	
	public void addAttri(Attribute attri){
		attris.add(attri);
	}
	
	public void setAttris(ArrayList<Attribute> attris){
		this.attris = attris;
	}
	
	private int getAttriCount(){
		return attris.size();
	}
	
	public void createPoints(){
		int n = getAttriCount();
//		System.out.println(n);
		for(int i=0;i<n;i++){
			double arc = (i+1)*(360/n);
//			int k = (int) Math.atan(arc);
			int y = (int) (attris.get(i).getValue()*max * Math.sin(2*Math.PI*arc/360));
			int x = (int) (attris.get(i).getValue()*max * Math.cos(2*Math.PI*arc/360));
			ks.add((y+0.0)/x);
			points.add(new Point(x, y));
			arcs.add(arc);
			tempPoints.add(new Point(0, 0));	
//			System.out.println(arc);
		}
//		System.out.println(ks);
//		System.out.println(points);
//		System.out.println(Math.sin(Math.PI/6));
		readyToDrawLines.set(index, true);
	}
	
	public void paint(Graphics g){
//		this.getParent().paint(g);
		
		
		if(readyToDrawLines.get(index) && readyToDrawSpoke){
			if(dr > max){
				this.paintSpoke(g, dr);
			}else{
				dr+=1;
//				System.out.println(dr);
				this.paintSpoke(g, dr);
			}
			
//			if(pointsArrived){
//				this.paintPoints(g);
//			}else{
//				
//			}
//			this.paintPoints(g);
			
			if(dx > max){
				this.linkPoints(g, dx);
				this.paintPoints(g);
			}else{
				dx+=1;
				this.linkPoints(g, dx);
			}
//			this.linkPoints(g);
			
		}
	}
	
	private void paintSpoke(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		Stroke stroke = g2d.getStroke();
		g2d.setStroke(new BasicStroke(0.5f));
//		System.out.println("----------------------");
		for(int i=0;i<ks.size();i++){
//			g2d.drawLine(max, max, max + (int)(max*Math.cos(Math.atan(ks.get(i)))), 
//					max - (int)(max*Math.sin(Math.atan(ks.get(i)))));
////			System.out.println(ks.get(i));
//			System.out.println(((double)(Math.cos(Math.atan(ks.get(i))))+"  "+((int)(max*Math.sin(2*Math.PI*Math.atan(ks.get(i))/360)))));
			g2d.drawLine(max, max, (int)(max + Math.cos(Math.PI*2*arcs.get(i)/360)*max), (int)(max - Math.sin(Math.PI*2*arcs.get(i)/360)*max));
//			System.out.println((int)(max + Math.sin(Math.PI*2*arcs.get(i)/360)*max));
		}
//		System.out.println("----------------------");
		g2d.setStroke(stroke);
	}
	
	private void paintSpoke(Graphics g, int r){

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		Stroke stroke = g2d.getStroke();
		g2d.setStroke(new BasicStroke(0.5f));
		for(int i=0;i<ks.size();i++){
			int x = (int)(max + Math.cos(Math.PI*2*arcs.get(i)/360)*r);
			int y = (int)(max - Math.sin(Math.PI*2*arcs.get(i)/360)*r);
			g2d.drawLine(max, max, x, y);
			g2d.setColor(color.BLACK);
			g2d.drawString(attris.get(i).getName(),(int)(max + Math.cos(Math.PI*2*arcs.get(i)/360)*max*0.9), 
					(int)(max - Math.sin(Math.PI*2*arcs.get(i)/360)*max*0.9));
			g2d.setColor(color.white);
		}
		g2d.setStroke(stroke);
	}
	
	
	private void paintPoints(Graphics g){
//		System.out.println("p");
		for(int i=0;i<points.size();i++){
			int x = points.get(i).x + max;
			int y = max - points.get(i).y;
//			if(y<0){
//				y = -1*y+max;
//			}else{
//				y = max - y;
//			}
//			System.out.println("("+x+","+y+")");
			g.setColor(color);
			pointsToDraw.add(new Point(x, y));
			g.fillRect(x-4, y-4, 8, 8);
			g.setFont(new Font(Font.DIALOG, Font.ITALIC, 10));
			g.drawString(attris.get(i).getAbsoluteValue()+"", x+8, y+8);
		}
	}
	
	private void linkPoints(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(0.5f));
		g2d.setColor(Color.DARK_GRAY);
		for(int i=0;i<pointsToDraw.size()-1;i++){
			Point p1 = pointsToDraw.get(i);
			Point p2 = pointsToDraw.get(i+1);
			g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void linkPoints(Graphics g, int dr){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.setColor(new Color((int)(color.getRed()*0.5), (int)(color.getGreen()*0.5), (int)( color.getBlue()*0.5)));
		
		pointsArrived = true;
		for(int i=0;i<tempPoints.size();i++){
			if(Math.abs(tempPoints.get(i).x-points.get(i).x)<=1 && Math.abs(tempPoints.get(i).y-tempPoints.get(i).y)<=1)	{
				//tempPoint到达位置
				tempPoints.set(i, new Point(points.get(i)));
			}else{
//				tempPoints.get(i).x = dx;
//				tempPoints.get(i).y = (int) (dx * ks.get(i)));
//				System.out.println(tempPoints.get(i)+" : "+points.get(i));
				Point lastPoint = tempPoints.get(i);
				Point newPoint = new Point((int) (dr*Math.cos(2*Math.PI*arcs.get(i)/360)), (int) (dr *Math.sin(2*Math.PI*arcs.get(i)/360)));
//				if(lastPoint.equals(newPoint)){
//					tempPoints.set(i, points.get(i));
//				}else{
//					tempPoints.set(i, newPoint);
//				}
				tempPoints.set(i, newPoint);
				pointsArrived = false;
			}
		}
		
		if(!pointsArrived){
//
			memoryPoints.add((ArrayList<Point>) tempPoints.clone());
		}

//		System.out.println(tempPoints);
		for(int j=memoryPoints.size()-1;j<memoryPoints.size();j++){
			ArrayList<Point> temp = memoryPoints.get(j);
			for(int i=0;i<temp.size()-1;i++){
				Point p1 = temp.get(i);
				Point p2 = temp.get(i+1);
				g2d.drawLine(max+p1.x, max-p1.y, max+p2.x, max-p2.y);
			}
			Point p1 = temp.get(0);
			Point p2 = temp.get(tempPoints.size()-1);
			g2d.drawLine(max+p1.x, max-p1.y, max+p2.x, max-p2.y);
		}
		
	}
}




class ColorAndDescriptionLabel extends JLabel{
	public ColorAndDescriptionLabel(int colorIndex, String name){
		this.setPreferredSize(new Dimension(legendLabel.getWidth(), (int)(legendLabel.getWidth()*0.3)));
		this.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));
		this.setVerticalAlignment(JLabel.CENTER);
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setColor(colorIndex);
		this.setDescription(name);
	}
	
	private void setColor(int index){
		JLabel colorLabel = new JLabel();
		colorLabel.setOpaque(true);
		colorLabel.setPreferredSize(new Dimension((int)(legendLabel.getWidth()*0.1), (int)(legendLabel.getWidth()*0.1)));
		colorLabel.setBackground(colors[index]);
		this.add(colorLabel);
	}
	
	private void setDescription(String name){

		JLabel desLabel = new JLabel();
		desLabel.setPreferredSize(new Dimension((int)(legendLabel.getWidth()*0.7), (int)(legendLabel.getWidth()*0.4)));
		desLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		desLabel.setText(name);
		this.add(desLabel);
	}
}
}