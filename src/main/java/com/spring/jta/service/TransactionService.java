package com.spring.jta.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jta.bean.HdfcAccount;
import com.spring.jta.bean.SbiAccount;
import com.spring.jta.dao.BankDao;

@Service
public class TransactionService {

	@Autowired
	BankDao<SbiAccount> si;

	@Autowired
	BankDao<HdfcAccount> hi;

	@Transactional(value = "chainedTx1", rollbackFor = Exception.class)
	public void creditFromSbi2Hdfc(SbiAccount sbi, HdfcAccount hdfc, BigDecimal amt) throws Exception {
		if (null == sbi || null == hdfc || null == amt || amt.compareTo(new BigDecimal(0)) <= 0)
			return;
		// Debit amt from SBI.
		System.out.println("SBI before Tx:" + sbi);
		sbi.setAmount(sbi.getAmount().subtract(amt));
		si.update(sbi);
		System.out.println("SBI after Tx:" + sbi);

		// Credit amt to HDFC.
		System.out.println("HDFC before Tx:" + hdfc);
		hdfc.setAmount(hdfc.getAmount().add(new BigDecimal(100000)));
		hi.update(hdfc);
		System.out.println("HDFC after Tx:" + hdfc);
	}
	
	@Transactional(value = "chainedTx2", rollbackFor = Exception.class)
	public void creditFromHdfc2Sbi(HdfcAccount hdfc, SbiAccount sbi, BigDecimal amt) throws Exception {
		if (null == sbi || null == hdfc || null == amt || amt.compareTo(new BigDecimal(0)) <= 0)
			return;
		
		// Debit amt from HDFC.
		System.out.println("HDFC before Tx:" + hdfc);
		hdfc.setAmount(hdfc.getAmount().subtract(amt));
		hi.update(hdfc);
		System.out.println("HDFC after Tx:" + hdfc);
		
		// Credit amt to SBI.
		System.out.println("SBI before Tx:" + sbi);
		sbi.setAmount(sbi.getAmount().add(new BigDecimal(100000)));
		si.update(sbi);
		System.out.println("SBI after Tx:" + sbi);
	}

}
