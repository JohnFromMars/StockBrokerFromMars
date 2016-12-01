package com.stockbrokerfrommars.server.component;

import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.batchfrommars.component.BatchComponentII;
import com.batchfrommars.file.LogUtil;
import com.stockbrokerfrommars.server.bean.DecisionOrder;

public class DecisionComponent extends BatchComponentII {
	private static final int DECISION_QUE = 0;
	private Logger logger;
	private ResourceBundle resource;
	private int count = 0;

	@Override
	protected void onInit() {
		super.onInit();
		resource = ResourceBundle.getBundle("com.stockbrokerfrommars.server.config.serverapp");
		logger = LogUtil.setLogger("DecisionComponent", resource.getString("dev.app.log"), Logger.getLogger("DecisionComponent"));
		logger.info(" ==== DecisionComponent started ====");
	}

	@Override
	protected LinkedList<String> excuteProcess(LinkedList<String> inputList) {

		if (inputList.get(DECISION_QUE) != null) {
			DecisionOrder decisionOrder = new DecisionOrder();
			decisionOrder.parse(inputList.get(DECISION_QUE));
			System.out.println("decision com " + decisionOrder);
			count++;
		}

		return null;
	}

	@Override
	protected void onFinish() {
		super.onFinish();
		logger.info("---decesion report---");
		logger.info("decesion total comand reicved : " + count);
		logger.info("---------------------");
		logger.info(" ==== DecisionComponent compeleted ====");
	}
}
