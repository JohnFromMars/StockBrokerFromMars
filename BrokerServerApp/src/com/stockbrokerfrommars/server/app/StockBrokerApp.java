package com.stockbrokerfrommars.server.app;

import com.batchfrommars.file.FileInformation;
import com.batchfrommars.file.TemporaryFile;
import com.stockbrokerfrommars.server.component.DecisionComponent;
import com.stockbrokerfrommars.server.component.StockInquiryComponent;
import com.stockbrokerfrommars.server.component.TransactionComponent;

public class StockBrokerApp {
	private static StockInquiryComponent stockInquiry = new StockInquiryComponent();
	private static DecisionComponent decision = new DecisionComponent();
	private static TransactionComponent transaction = new TransactionComponent();
	

	private static FileInformation dbOue = new TemporaryFile();
	private static FileInformation txQue = new TemporaryFile();
	private static FileInformation decisionQue = new TemporaryFile();

	public static void main(String[] args) {
		setProperties();
		startApp();

	}

	private static void startApp() {
		stockInquiry.start();
		decision.start();
		transaction.start();
		
	}

	private static void setProperties() {
		stockInquiry.addOutputFileInformation(decisionQue);

		decision.addInputFileInformation(decisionQue);
		decision.addOutputFileInformation(txQue);
		decision.addLastComponent(stockInquiry);

		transaction.addInputFileInformation(txQue);
		transaction.addOutputFileInformation(dbOue);
		transaction.addLastComponent(decision);

		
	}

}
