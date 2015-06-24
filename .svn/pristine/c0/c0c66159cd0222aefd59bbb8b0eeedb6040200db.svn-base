package businesslogic.statsbl;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

import presentation.figure.Attribute;
import presentation.figure.RadarFigure;

public class Charts {
	Color[] colors;
	
	private void colorInit(){
		colors = new Color[]{new Color(160, 160, 255),new Color(128, 128, 223),new Color(96, 96, 191),new Color(64, 64, 128)};
	}
	
	//投篮分析犯规失误分析饼图
	public  ChartPanel PieChart(double[] pct, String[] sec,String title){
		colorInit();
		DefaultPieDataset dpd = new DefaultPieDataset();
	    
		for(int i=0;i<pct.length;i++)
			dpd.setValue(sec[i], pct[i]);
	
	    JFreeChart chart = ChartFactory.createPieChart(title,dpd, true, false, false);
	    
	    PiePlot plot = (PiePlot) chart.getPlot();
		for(int i=0;i<pct.length;i++)
			plot.setSectionPaint(sec[i], colors[i]);
	
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}({2} percent)"));
	    plot.setBackgroundAlpha(0f);
	    plot.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE);
	    
	    plot.setLabelBackgroundPaint(null);//标签背景颜色

	    plot.setLabelOutlinePaint(null);//标签边框颜色

	    plot.setLabelShadowPaint(null);//标签阴影颜色

	    ChartPanel cp = new ChartPanel(chart);
	    return cp;
	    
	}
	//演化例程气泡图
	public ChartPanel bubbleChart(ArrayList<double[][]> datas, ArrayList<String> names,int years){
		DefaultXYZDataset dataset = new DefaultXYZDataset(); 
		for(int i=0;i<datas.size();i++)
			dataset.addSeries(names.get(i), datas.get(i));
		JFreeChart chart = ChartFactory.createBubbleChart(  
			"Data of year 2009-2015", // chart title      
			"Year", // domain axis label      
			"Value", // range axis label      
			dataset, // data      
			PlotOrientation.VERTICAL, // orientation      
			true, // include legend      
			false, // tooltips      
			false // urls      
			);  
		
		 XYPlot xyplot = ( XYPlot )chart.getPlot( );                 
	     xyplot.setBackgroundAlpha(0f);
	     xyplot.setForegroundAlpha( 0.5F );
	     
	     XYItemRenderer xyitemrenderer = xyplot.getRenderer( );
	     xyitemrenderer.setBaseSeriesVisibleInLegend(false); 	 
	     for(int i=0;i<datas.size();i++)
	    	 xyitemrenderer.setSeriesPaint( i , Color.LIGHT_GRAY );  
//	      NumberAxis numberaxis = ( NumberAxis )xyplot.getDomainAxis( );                 
//	      numberaxis.setLowerMargin( 0.2 );                 
//	      numberaxis.setUpperMargin( 0.5 );                 
//	      NumberAxis numberaxis1 = ( NumberAxis )xyplot.getRangeAxis( );                 
//	      numberaxis1.setLowerMargin( 0.8 );                 
//	      numberaxis1.setUpperMargin( 0.9 );
	    
	    XYSeriesCollection  linedataset = new XYSeriesCollection (); 
	    for(int i=0;i<datas.size();i++){
	    	XYSeries line = new XYSeries(names.get(i));          
	    	for(int j=0;j<years;j++){
	    		line.add(datas.get(i)[1][j],datas.get(i)[0][j]);
	    	}
	    linedataset.addSeries(line);
	    }
	    xyplot.setDataset(1, dataset);// 放XY图数据
	    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
	    renderer.setSeriesPaint( 0 , Color.blue );

	    renderer.setBaseSeriesVisibleInLegend(true); 	      
	    xyplot.setRenderer(1, renderer ); 
	    xyplot.setBackgroundAlpha(0f);
	    xyplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		ChartPanel cp = new ChartPanel(chart);
	    return cp;
	}
	//演化例程折线图
	public ChartPanel lineChart(ArrayList<double[]> datas, ArrayList<String> names){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		for(int i=0;i<datas.size();i++){
			for(int j=0;j<datas.get(i).length;j++){
				dataset.addValue(datas.get(i)[j], names.get(i), 2009+j+"");
			}
		}
		JFreeChart chart = ChartFactory.createLineChart(  
			"Data of year 2009-2015", // chart title      
			"Year", // domain axis label      
			"Value", // range axis label      
			dataset, // data      
			PlotOrientation.VERTICAL, // orientation      
			true, // include legend      
			true, // tooltips      
			false // urls      
			);  
		
		CategoryPlot plot = chart.getCategoryPlot();  
        plot.setBackgroundAlpha(0f);
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) plot.getRenderer();//数据点    
        lineandshaperenderer.setBaseShapesVisible(true);  //series 点（即数据点）可见  
        lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  //显示数据点的数据     
        lineandshaperenderer.setBaseItemLabelsVisible(true);  //显示折线图点上的数据

        
		ChartPanel cp = new ChartPanel(chart);
	    return cp;
	}
	//攻防对比柱状图
	public ChartPanel OffDefColumn(double[][] data,String title,String[] columnkeys){
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				new String[]{"Off","Def"},columnkeys,data);

        JFreeChart chart = ChartFactory.createBarChart( 
                title, // 图表标题
                "",
                "",
                 dataset, // 数据集
                 PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                 true,  // 是否显示图例(对于简单的柱状图必须是 false)
                 false, // 是否生成工具
                 false  // 是否生成 URL 链接
                 ); 
        CategoryPlot plot = chart.getCategoryPlot();  
        plot.setBackgroundAlpha(0f);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.yellow);
        renderer.setSeriesPaint(1, Color.black);
        renderer.setBaseItemLabelsVisible(true);
        // 设置条目标签生成器
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        // 设置条目标签显示的位置,outline表示在条目区域外,baseline_center表示基于基线且居中
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        renderer.setMaximumBarWidth(0.5);

        
        ChartPanel cp = new ChartPanel(chart);
	    return cp;
		
	}
	//position product 堆积柱状图
	public ChartPanel posPER(double[][] datas, String title,String[] rowkeys, String[] columnkeys){
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowkeys,columnkeys,datas);
		
		JFreeChart chart = ChartFactory.createStackedBarChart( 
                title, // 图表标题
                "",
                "",
                 dataset, // 数据集
                 PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                 true,  // 是否显示图例(对于简单的柱状图必须是 false)
                 false, // 是否生成工具
                 false  // 是否生成 URL 链接
                 ); 
        CategoryPlot plot = chart.getCategoryPlot();  
        plot.setBackgroundAlpha(0f);
        StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.GRAY);
        renderer.setSeriesPaint(1, Color.LIGHT_GRAY);
        
        // 设置条目标签生成器
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        // 设置条目标签显示的位置,outline表示在条目区域外,baseline_center表示基于基线且居中
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        renderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE6, TextAnchor.BASELINE_CENTER));
        
        CategoryAxis categoryAxis=plot.getDomainAxis();
        if(columnkeys[0].length()>20)
        	categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//分类标签以45度倾斜
        //customBarRenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
        renderer.setBaseItemLabelsVisible(true);
		ChartPanel cp = new ChartPanel(chart);
		return cp;
	}
	//on/off stats
	public ChartPanel OnOff(double[][] data,String title,String[] columnkeys){
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				new String[]{"On","Off","Net"},columnkeys,data);

        JFreeChart chart = ChartFactory.createBarChart( 
                title, // 图表标题
                "",
                "",
                 dataset, // 数据集
                 PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                 true,  // 是否显示图例(对于简单的柱状图必须是 false)
                 false, // 是否生成工具
                 false  // 是否生成 URL 链接
                 ); 
        CategoryPlot plot = chart.getCategoryPlot();  
        plot.setBackgroundAlpha(0f);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.yellow);
        renderer.setSeriesPaint(1, Color.black);
        renderer.setSeriesPaint(2, Color.gray);
        renderer.setBaseItemLabelsVisible(true);
        // 设置条目标签生成器
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        // 设置条目标签显示的位置,outline表示在条目区域外,baseline_center表示基于基线且居中
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        renderer.setMaximumBarWidth(0.5);

        
        ChartPanel cp = new ChartPanel(chart);
	    return cp;
		
	}
	//w-l profiles 雷达图
	public JLabel radarChart(double[][]datas,double shrinkage){
		double enlarge = 1.0/(1.0-shrinkage);
		System.out.print(shrinkage+" "+enlarge);
		RadarFigure chart = new RadarFigure(11,3,160);
		String[] labels = new String[]{"Pts For","Pts Agn","Net Pts","Off eFG","Def eFG",
				"FTA Net","Reb","Turnover","Ast","Blk","Pace"};
		ArrayList<String> legend = new ArrayList<String>();
		legend.add("good");
		legend.add("average");
		legend.add("poor");
		for(int i=0;i<3;i++){
			for(int j=0;j<11;j++){
				chart.setValues(i, new Attribute(labels[j],datas[i][j],(datas[i][j]-shrinkage)*enlarge, j));
			}
		}
		chart.drawFigure(legend);
		return chart; 
	}
	
}
