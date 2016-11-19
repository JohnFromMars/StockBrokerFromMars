package com.stockbrokerfrommars.batch.job;

import java.util.ResourceBundle;

import com.batchfrommars.file.FileInformation;
import com.batchfrommars.file.PhysicalFile;
import com.stockbrokerfrommars.batch.component.getallstocksid.JobGetAllStocksIdGetData;

/**
 * 
 * @author Yj
 *
 */
public class JobGetAllStockId {
	private static ResourceBundle resource;
	private static FileInformation input;
	private static FileInformation output;
	private static JobGetAllStocksIdGetData getDataComponent;

	public static void main(String[] args) {
		setResource();
		setInput();
		setOutput();
		setGetDataComponent();
		getDataComponent.start();

	}

	private static void setResource() {
		resource = ResourceBundle.getBundle("com.stockbrokerfrommars.batch.config.getAllStocksId");
	}

	private static void setInput() {
		input = new PhysicalFile(PhysicalFile.INPUT, resource.getString("dev.getAllStocksId.urlPath"), "UTF8", false);
	}

	private static void setOutput() {
		output = new PhysicalFile(PhysicalFile.OUTPUT, resource.getString("dev.getAllStocksId.stocksIdData") + "stockId.txt", "UTF8", false);
	}

	private static void setGetDataComponent() {
		getDataComponent = new JobGetAllStocksIdGetData();
		getDataComponent.addInputFileInformation(input);
		getDataComponent.addOutputFileInformation(output);
	}

}
