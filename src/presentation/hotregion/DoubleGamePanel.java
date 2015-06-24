package presentation.hotregion;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DoubleGamePanel extends JPanel implements MouseMotionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int width;
	int height;

	RegionObserver observer;
	
	ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>(); // 原图片组件
	ArrayList<BufferedImage> imageList_highlight = new ArrayList<BufferedImage>(); // 高亮图片组件
	ArrayList<BufferedImage> imageToDraw = new ArrayList<BufferedImage>(); // 待绘图片组件

	int currentHighLight; // 当前高亮的组件1~14，如果为0则表示没有高亮

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (BufferedImage each : imageToDraw) {
//			g.drawImage(each, 0, 0, this);
			g.drawImage(each, 0, 0, 510, 504, null);

		}
	}

	public DoubleGamePanel(int width, int height) {

		this.setLayout(null);
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		// 锟斤拷取图片

		try {
			for (int i = 1; i <= 14; i++) {
				// 取原始图片，并且存入待绘组件列表
				String path = "image" + File.separator + "q" + i + ".png";
				BufferedImage temp = ImageIO.read(new File(path));
				imageList.add(temp);
				imageToDraw.add(temp);

				// 读取高亮图片
				path = "image" + File.separator + "p" + i + ".png";
				imageList_highlight.add(ImageIO.read(new File(path)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setObserver(RegionObserver observer){
		this.observer = observer;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		int ix = (int) (e.getX() / 0.74);
		int iy = (int) (e.getY() / 0.74);
		int index = 0; // 储存匹配到的图片序号
		for (BufferedImage each : imageList) {
			index++;
			int alpha = 0;
			try{
				alpha = (each.getRGB(ix, iy) >> 24) & 0xff;
			}catch (ArrayIndexOutOfBoundsException exception){
				
			}
			if (alpha != 0) {
				// 首先恢复之前高亮的部分
				if (currentHighLight != 0) {
					imageToDraw.set(currentHighLight - 1,
							imageList.get(currentHighLight - 1));
				}
				// 修改当前高亮部分序号
				currentHighLight = index;
				imageToDraw.set(index - 1, imageList_highlight.get(index - 1));
				repaint();
				
				//更改对应区域描述
				observer.changeContent(index);
				break;
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		imageToDraw.clear();
		for(BufferedImage each : imageList){
			imageToDraw.add(each);
		}
		repaint();
		observer.changeContent(0);
//		System.out.println("***********exited");
	}
}