package com.stockbrokerfrommars.server.app;

import com.batchfrommars.file.FileInformation;
import com.batchfrommars.file.TemporaryFile;
import com.stockbrokerfrommars.server.component.DatabaseComponent;
import com.stockbrokerfrommars.server.component.DecisionComponent;
import com.stockbrokerfrommars.server.component.StockInquiryComponent;
import com.stockbrokerfrommars.server.component.TransactionComponent;

public class StockBroker {

	public static void main(String[] args) throws InterruptedException {

		StockInquiryComponent stockInquiry = new StockInquiryComponent();
		DatabaseComponent database = new DatabaseComponent();
		DecisionComponent decision = new DecisionComponent();
		TransactionComponent transaction = new TransactionComponent();

		FileInformation dbQueue = new TemporaryFile();
		FileInformation txQueue = new TemporaryFile();
		FileInformation decisionQueue = new TemporaryFile();

		setProperties(stockInquiry, database, decision, transaction, dbQueue, txQueue, decisionQueue);
		mainProcess(stockInquiry, database, decision, transaction);
		
		

	}


	private static void mainProcess(StockInquiryComponent stockInquiry, DatabaseComponent database, DecisionComponent decision, TransactionComponent transaction) throws InterruptedException {
		if(isAuthorize()){
			stockInquiry.start();
			database.start();
			decision.start();
			transaction.start();
			
			stockInquiry.join();
			database.join();
			decision.join();
			transaction.join();
		}
	}

	
	private static void setProperties(StockInquiryComponent stockInquiry, DatabaseComponent database, DecisionComponent decision, TransactionComponent transaction, FileInformation dbQueue,
			FileInformation txQueue, FileInformation decisionQueue) {
		stockInquiry.addOutputFileInformation(dbQueue, decisionQueue);

		decision.addInputFileInformation(decisionQueue);
		decision.addOutputFileInformation(txQueue);
		decision.addLastComponent(stockInquiry);

		database.addInputFileInformation(dbQueue);
		database.addLastComponent(stockInquiry, transaction);

		transaction.addInputFileInformation(txQueue);
		transaction.addOutputFileInformation(dbQueue);
		transaction.addLastComponent(decision);
	}
	
	private static boolean isAuthorize(){
		return true;
	}

}
