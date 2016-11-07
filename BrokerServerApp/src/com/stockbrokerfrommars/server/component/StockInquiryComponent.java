package com.stockbrokerfrommars.server.component;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import com.batchfrommars.component.ComponentII;


public class StockInquiryComponent extends ComponentII {

	private Date businessStartTime;
	private Date businessEndTime;
	private LinkedList<String> watchingList;
	

	@Override
	protected void onInit() {
		super.onInit();
		setBusinessTime();
		setWatchingList();
	}

	

	@Override
	protected void act() {
		while (checkBusinessTime()) {
			
		}
	}

	private boolean checkBusinessTime() {
		// check if the current time is between businessStartTime and
		// businessEndTime
		if (getCurrentTime().after(businessStartTime) && getCurrentTime().before(businessEndTime)) {
			return true;
		} else {
			return false;
		}
	}

	private Date getCurrentTime() {
		Date date = new Date();
		return date;
	}

	private void setBusinessTime() {
		// set businessStartTime and businessEndTime
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 25);
		calendar.set(Calendar.SECOND, 0);

		businessStartTime = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, 16);

		businessEndTime = calendar.getTime();

	}
	
	private void setWatchingList() {
		
		
	}

}
