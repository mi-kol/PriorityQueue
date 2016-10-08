import java.awt.Color;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.awt.BasicStroke;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.util.Comparator;

import lib.SamplePQueue;

/*
 * 
 * You are not expected to understand all of the code in this class. 
 * 	All that is expected of you is to use the tester to graph some data,
 * 	and, optionally, use the validateStructure method to verify your classes work
 * 
 */

public class Tester extends ApplicationFrame {

	private static ArrayList<XYSeries> data = new ArrayList<XYSeries>();

	public static void vailidateStructure(Class c, int iterations) throws Exception {
		// Test the class to make sure its an instance of queue
		Object typeCheck = c.newInstance();
		if (!(typeCheck instanceof PQueue)) {
			throw new Exception("Type must be a Queue");
		}

		PQueue<Integer> userPq = (PQueue<Integer>) c.newInstance();
		SamplePQueue<Integer> systemPq = new SamplePQueue<Integer>();
		
		for (int i = 0; i < iterations; i++) {
			int elm = (int) (Math.random() * 100 + 1);
			int p = (int) (Math.random() * 5 + 1);
			
			userPq.enqueue(elm, p);
			systemPq.enqueue(elm, p);
		}

		for (int i = 0; i < iterations; i++) {
			int e0 = userPq.dequeue();
			int e1 = systemPq.dequeue();
			
			if(e0 != e1){
				throw new Exception("Invalid Datastructure: " + c.getName());
			}
		}
	}

	public static XYDataset timeStructure(Class c, int start, int end,
			int interval) throws Exception {
		// Test the class to make sure its an instance of queue
		Object typeCheck = c.newInstance();
		if (!(typeCheck instanceof PQueue)) {
			throw new Exception("Type must be a Queue");
		}

		// Initialize array of numbers to test
		int[] nums = new int[(end - start) / interval];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = start + (i * interval);
		}

		XYSeries enqSeries = new XYSeries(c.getName() + " Enqueue");
		XYSeries deqSeries = new XYSeries(c.getName() + " Dequeue");

		for (int i = 0; i < nums.length; i++) {

			PQueue<Integer> q = (PQueue<Integer>) c.newInstance();

			long startTime = System.currentTimeMillis();
			for (int n = 0; n < nums[i]; n++) {
				int elm = (int) (Math.random() * 100 + 1);
				int p = (int) (Math.random() * 5 + 1);
				q.enqueue(elm, p);
			}
			long endTime = System.currentTimeMillis();
			enqSeries.add(nums[i], endTime - startTime);

			startTime = System.currentTimeMillis();
			while (q.size() > 0) {
				q.dequeue();
			}
			endTime = System.currentTimeMillis();
			deqSeries.add(nums[i], endTime - startTime);
		}

		XYSeriesCollection dataSet = new XYSeriesCollection();

		dataSet.addSeries(enqSeries);
		dataSet.addSeries(deqSeries);
		data.add(enqSeries);
		data.add(deqSeries);

		return dataSet;
	}

	public Tester(Class c, int start, int end, int interval) throws Exception {
		super("Lab2 Graph");

		String chartTitle = c.getName() + " Chart"; 
		XYDataset dataset = timeStructure(c, start, end, interval);
		
		JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle,
				"N", "Time (milliseconds)", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		final XYPlot plot = xylineChart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, Color.YELLOW);
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		setContentPane(chartPanel);

		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}

	public static void main(String[] args) throws Exception {

		Tester t1 = new Tester(UnsortedArrayPriorityQueue.class, 1000, 30000, 3000);
		
		// Arguments for the test: Class to test, start n, end n, interval n
		// 		Example:
		// Tester t1 = new Tester(UnsortedPQueue.class, 1000, 30000, 3000);
		
	}
}