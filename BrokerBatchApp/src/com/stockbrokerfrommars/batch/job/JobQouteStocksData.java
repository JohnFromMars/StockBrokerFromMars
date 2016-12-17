package com.stockbrokerfrommars.batch.job;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.batchfrommars.file.FileInformation;
import com.batchfrommars.file.LogUtil;
import com.batchfrommars.file.PhysicalFile;
import com.stockbrokerfrommars.batch.component.qoutestocksdata.StepInquiringStocksData;

public class JobQouteStocksData {

	private final static String FILE_NAME = "stockId.txt";
	private final static String UPDATE_FAIL = "updateFail.txt";
	private static ResourceBundle resource;
	private static Logger logger;
	private static FileInformation input;
	private static FileInformation output;
	private static StepInquiringStocksData stepInquiring;

	public static void main(String[] args) {
		setPrpoerties();

		logger.info("------ JobQouteStocksData started -------");
		stepInquiring.activate();

		try {
			stepInquiring.join();
			logger.info("------ JobQouteStocksData completed -------");

		} catch (InterruptedException e) {
			logger.warning(e.getStackTrace()[0].toString());
			logger.warning(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void setPrpoerties() {
		setResource();
		setLogger();
		setInput();
		setOutput();
		setStepInquiring();
	}

	public static void setResource() {
		JobQouteStocksData.resource = ResourceBundle.getBundle("com.stockbrokerfrommars.batch.config.job");
	}

	public static void setLogger() {
		JobQouteStocksData.logger = LogUtil.setLogger("JobQouteStocksData", resource.getString("dev.qouteStockData.log"), Logger.getLogger("qouteStockData"));
	}

	public static void setInput() {
		JobQouteStocksData.input = new PhysicalFile(PhysicalFile.INPUT, resource.getString("dev.getAllStocksId.stocksIdData") + FILE_NAME, "UTF8", false);
	}

	public static void setOutput() {
		JobQouteStocksData.output = new PhysicalFile(PhysicalFile.OUTPUT, resource.getString("dev.qouteStockData.failUpdateData") + UPDATE_FAIL, "UTF8", false);
	}

	public static void setStepInquiring() {
		JobQouteStocksData.stepInquiring = new StepInquiringStocksData();
		stepInquiring.addInputFileInformation(input);
		stepInquiring.addOutputFileInformation(output);
	}

}
