package com.stockbrokerfrommars.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stockbrokerfrommars.web.bean.TxDetail;
import com.stockbrokerfrommars.web.service.TxDetailService;

@Controller
public class TxDetailController {
	private TxDetailService TxDetailService;

	@RequestMapping(value="/finddetail",method=RequestMethod.GET)
	public String showTxDetail(Model model) {
		List<TxDetail> details = TxDetailService.getTxDetails();
		
		model.addAttribute("txdetails", details);

		return "txdetails";
	}

	@Autowired
	public void setTxDetailService(TxDetailService txDetailService) {
		TxDetailService = txDetailService;
	}

}
