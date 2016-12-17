package com.stockbrokerfrommars.batch.job;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.batchfrommars.file.FileInformation;
import com.batchfrommars.file.LogUtil;
import com.batchfrommars.file.PhysicalFile;
import com.stockbrokerfrommars.batch.component.getallstocksid.StepGetStocksId;

/**
 * 
 * @author Yj
 *
 */
public class JobGetAllStockId {
	
	private static FileInformation input;
	private static FileInformation output;
	private static ResourceBundle resource;
	private static Logger logger;
	static StepGetStocksId getDataComponent;

	public static void main(String[] args) {
		
		setProperties();
		
		logger.info("------ JobGetAllStockId started ------");
		getDataComponent.start();
		
		try {
			getDataComponent.join();
		} catch (InterruptedException e) {
			logger.warning(e.getStackTrace()[0].toString());
			logger.warning(e.getMessage());
			e.printStackTrace();
		}
		logger.info("------ JobGetAllStockId completed ------");

	}
	
	private static void setProperties(){
		setResource();
		setLogger();
		setInput();
		setOutput();
		setGetDataComponent();
	}

	private static void setLogger() {
		logger = LogUtil.setLogger("JobGetAllStockId", resource.getString("dev.getAllStocksId.log"), Logger.getLogger("JobGetAllStockId"));

	}

	private static void setResource() {
		resource = ResourceBundle.getBundle("com.stockbrokerfrommars.batch.config.job");

	}

	static void setInput() {
		input = new PhysicalFile(PhysicalFile.INPUT, resource.getString("dev.getAllStocksId.urlPath"), "UTF8", false);
	}

	static void setOutput() {
		output = new PhysicalFile(PhysicalFile.OUTPUT, resource.getString("dev.getAllStocksId.stocksIdData") + "stockId.txt", "UTF8", false);
	}

	static void setGetDataComponent() {
		getDataComponent = new StepGetStocksId();
		getDataComponent.addInputFileInformation(input);
		getDataComponent.addOutputFileInformation(output);
	}

}
