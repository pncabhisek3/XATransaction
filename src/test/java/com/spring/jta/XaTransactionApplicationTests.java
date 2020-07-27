package com.spring.jta;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.jta.bean.HdfcAccount;
import com.spring.jta.bean.SbiAccount;
import com.spring.jta.dao.BankDao;
import com.spring.jta.service.TransactionService;

@SpringBootTest
class XaTransactionApplicationTests {

	@Autowired
	BankDao<SbiAccount> si;

	@Autowired
	BankDao<HdfcAccount> hi;

	@Autowired
	TransactionService ts;

//	@Test
//	void testt() throws Exception {
//		SbiAccount sa = si.getOne(13);
//		sa.setAmount(sa.getAmount().add(new BigDecimal(1)));
//		si.update(sa);
//		System.out.println("Sbi account updated id=" + sa.get_id());
//	}
//
//	@Test
//	void testtt() throws Exception {
//		HdfcAccount ha = hi.getOne(2);
//		ha.setAmount(ha.getAmount().subtract(new BigDecimal(1)));
//		hi.update(ha);
//		System.out.println("Sbi account updated id=" + ha.get_id());
//	}

	@Test
	void testService() throws Exception {
//		ts.creditFromSbi2Hdfc(si.getOne(1), hi.getOne(1), new BigDecimal(1));
		ts.creditFromHdfc2Sbi(hi.getOne(1), si.getOne(1), new BigDecimal(1));
	}

}
