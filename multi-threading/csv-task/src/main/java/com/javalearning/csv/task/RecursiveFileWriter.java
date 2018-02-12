package com.javalearning.csv.task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

/**
 * @author pradyumnm
 * The purpose of the class is to write files by using Fork and Join utility provided in Java concurrency framework
 */
public class RecursiveFileWriter extends RecursiveAction {
	
	private int SEQUENTIAL_THRESHOLD;
	private List<String> data;
	int fileNo = 0;

	public RecursiveFileWriter(List<String> data, int i, int lineLimit) {
		this.data = data;
		this.fileNo = i;
		this.SEQUENTIAL_THRESHOLD= lineLimit;
	}


	@Override
	protected void compute() {
		if (data.size() <= SEQUENTIAL_THRESHOLD) { 
			computeDirectly();
		} else { 
			
			int mid = data.size() - SEQUENTIAL_THRESHOLD;
			RecursiveFileWriter firstSubtask = new RecursiveFileWriter(data.subList(0, mid), data.size() / SEQUENTIAL_THRESHOLD,SEQUENTIAL_THRESHOLD);
			RecursiveFileWriter secondSubtask = new RecursiveFileWriter(data.subList(mid, data.size()), data.size() / SEQUENTIAL_THRESHOLD + 1,SEQUENTIAL_THRESHOLD);

			invokeAll(firstSubtask, secondSubtask);
		}

	}
	

	private void computeDirectly() {
		BufferedWriter fw;
		try {
			fw = new BufferedWriter(
					new FileWriter("/home/pradyumnm/Downloads/tsk/" + "tsk" + Integer.toString(fileNo) + ".csv"));
			for (int i = 0; i < data.size(); i++) {
				fw.write(data.get(i));
				fw.newLine();
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
