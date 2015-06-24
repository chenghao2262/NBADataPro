package presentation.hotregion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TeamsMapsPanel extends JPanel implements MouseMotionListener {

	int width;
	int height;

	ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>(); // ԭͼƬ���?
	ArrayList<BufferedImage> imageList_highlight = new ArrayList<BufferedImage>(); // ����ͼƬ���?
	ArrayList<BufferedImage> imageToDraw = new ArrayList<BufferedImage>(); // ����ͼƬ���?

	int currentHighLight; // ��ǰ���������?1~14������?0���ʾû�и���?

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (BufferedImage each : imageToDraw) {
			g.drawImage(each, 0, 0, this);

		}
	}

	public TeamsMapsPanel(int width, int height) {

		this.setLayout(null);
		this.addMouseMotionListener(this);
		this.setBackground(Color.black);
		// ��ȡͼƬ

		try {
			for (int i = 1; i <= 29; i++) {
				// ��ȡԭʼͼƬ�����Ҵ����������б�
				String path = "image" + File.separator + "z" + i + ".png";
				BufferedImage temp = ImageIO.read(new File(path));
				imageList.add(temp);
				imageToDraw.add(temp);

				// ��ȡ����ͼƬ
				path = "image" + File.separator + "x" + i + ".png";
				imageList_highlight.add(ImageIO.read(new File(path)));
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO �Զ����ɵķ������?
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		int ix = e.getX();
		int iy = e.getY();
		int index = 0; // ����ƥ�䵽��ͼƬ���?

		for (BufferedImage each : imageList) {
			index++;
			int alpha = (each.getRGB(ix, iy) >> 24) & 0xff;
			if (alpha != 0) {
				// ���Ȼָ�֮ǰ�����Ĳ��� if(currentHighLight!=0){
				if (currentHighLight != 0) {
					imageToDraw.set(currentHighLight - 1,
							imageList.get(currentHighLight - 1));
				}

				// �޸ĵ�ǰ�����������?
				currentHighLight = index;
				imageToDraw.set(index - 1, imageList_highlight.get(index - 1));
				repaint();
				break;
			}

		}

	}
}