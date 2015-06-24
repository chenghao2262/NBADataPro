package presentation.match.live;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import presentation.table.RowPane;
import presentation.table.TablePane;

public class LiveTablePane extends TablePane{

	public LiveTablePane(ArrayList<ArrayList<String>> datas, String[] columns,
			ArrayList<Integer> wi, int x, int y, int w, int sh, int h,
			boolean hasI, boolean whetherRank, int season) {
		super(datas, columns, wi, x, y, w, sh, h, hasI, whetherRank, season);
		// TODO Auto-generated constructor stub
	}

	protected void getRows(final ArrayList<ArrayList<String>> rowInfos){
		
		SwingWorker<ArrayList<RowPane>, Void> worker = new SwingWorker<ArrayList<RowPane>, Void>() {

			protected ArrayList<RowPane> doInBackground() throws Exception {
				ArrayList<RowPane> rows = new ArrayList<RowPane>();
				
				if(firstV.equals("")){
					for(int i=0;i<rowInfos.size();i++){
						RowPane row = new LiveRowPane(rowInfos.get(i).get(3),i+1,hasIndex,width,height);		
						row.addDatas_pic(rowInfos.get(i),wid);
						rows.add(row);
					}
				}
				else{
					System.out.println("impossible for liveTable");
				}
//					for(int i=0;i<rowInfos.size();i++){
//						RowPane row = new LiveRowPane(i+1,hasIndex,width,height);		
//						row.addDatas(rowInfos.get(i),wid);
//						rows.add(row);
//					}
				return rows;
				
			}
			
			protected void done(){
				try {
					ArrayList<RowPane> rows = get();
					setContent(rows);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		
		worker.execute();
		
	}
}
