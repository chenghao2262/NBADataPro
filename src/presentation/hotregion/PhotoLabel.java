package presentation.hotregion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


//@author qhb
/*类的使用说明：
 * 1. 传入构造函数的参数是是此label的大小
 * 2. 必须调用一下两种方法之一：
 * 		setImage(Image image)
 * 		setImage(String path) , path 是图片路径
 * 		否则图片不加载
 * 3. 图片是按比例缩放的
 * 4. 图片只能被缩放，不能扩大*/

public class PhotoLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image image;
	boolean full = false;
	
	PhotoLabel(Dimension preferredDimension){
		this.setBackground(Color.LIGHT_GRAY);
//		this.setOpaque(true);
		this.setPreferredSize(preferredDimension);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.repaint();
	}
	
	PhotoLabel(Dimension preferredDimension, boolean full){
		this.setBackground(Color.LIGHT_GRAY);
//		this.setOpaque(true);
		this.setPreferredSize(preferredDimension);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.repaint();
		
		full = true;
	}
	
	public void setImage(Image image){
		this.image = image;
	}
	
	public void setImage(ImageIcon icon){
		this.image = icon.getImage();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(image!=null){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if(!full){
				paintPhoto(g2d);
//				System.out.println("resource");
			}else{
				paintFullSize(g2d);
//				System.out.println("full");
			}
		}
	}
	
	public void setImage(String path){
		try {
//			ImageIo.r
			String[] temp = path.split("\\."); 
//			System.out.println("** "+temp[temp.length-1]);
			if(path.contains(".svg")){
//				System.out.println("contains svg");
//				System.out.println("*read svg");
//				image = ImageIO.read(TestSVG.convertToPng(path, "temp.png"));
			}else{
				image = ImageIO.read(new File(path));
			}
			
//			if(temp[temp.length-1] == "svg" || temp[temp.length-1] == "SVG"){
//				System.out.println("*read svg");
//				image = ImageIO.read(TestSVG.convertToPng(path, "temp.png"));
//			}else{
//				image = ImageIO.read(new File(path));
//			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void setSvgImage(String path){
		try {
			System.out.println("start");
			PipedInputStream in = new PipedInputStream();
//			SvgToPng.trans("ATL.svg", in);
			image = ImageIO.read(in);
			System.out.println("end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void paintPhoto(Graphics2D g){
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		
		int labelWidth = this.getSize().width;
		int labelHeight = this.getSize().height;
		
		double wh = (imageWidth+0.0)/imageHeight;
		double hw = (imageHeight+0.0)/imageWidth;
		
		int imageWidthBasedOnHeight = (int) (wh * labelHeight);
		int imageHeightBasedOnWidth = (int) (hw * labelWidth);
		
		int width = 0;//缩放后的图片宽度
		int height = 0;//缩放后的图片高度
		if(imageWidthBasedOnHeight > labelWidth){
			width = labelWidth;
			height = imageHeightBasedOnWidth;
		}else{
			width = imageWidthBasedOnHeight;
			height = labelHeight;
		}
		
//		System.out.println(imageWidth+" "+imageHeight);
//		System.out.println(width +" "+height);
		g.drawImage(image, 0, 0, width, height, null);
	}

	private void paintFullSize(Graphics2D g){
		int labelWidth = this.getSize().width;
		int labelHeight = this.getSize().height;
		System.out.println("full: "+this.getSize());
		g.drawImage(image, 0, 0, labelWidth, labelHeight, null);
	}
	
	public static void main(String args[]){
		JFrame f = new JFrame();
		PhotoLabel l = new PhotoLabel(new Dimension(100, 100));
//		l.setImage("basketball.png");
		l.setSvgImage("ATL.svg");
		f.add(l);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
//		SvgToPng.changeSvgToJpg("temp.png", "ATL.svg");
	}
}
