package com.spring.jta.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "chainedTx")
public class GlobalTransactionConfig {

	/**
	 * Why two 'ChainedTransactionManager' configuration, because the order of  'PlatformTransactionManager(PTM)'
	 * matters. And when PTM2 fails rollback through @Transacitonal(value="chainedTx") is possible on PTM1.
	 * 
	 * 1. So for 'chainedTx1'; As per 'ChainedTransactionManager' parameterized constructor arguments
	 * if 'hdfcTx' fails 'sbiTx' gets rolled-back. But not vice-versa.
	 * 
	 * 2. And for 'chainedTx2'; As per 'ChainedTransactionManager' parameterized constructor arguments
	 * if 'sbiTx' fails 'hdfcTx' gets rolled-back. But not vice-versa.
	 * 
	 */
	
	@Bean("chainedTx1")
	public ChainedTransactionManager chainedTx1(@Qualifier("sbiTM") PlatformTransactionManager sbiTx,
			@Qualifier("hdfcTM") PlatformTransactionManager hdfcTx) {
		return new ChainedTransactionManager(sbiTx, hdfcTx);
	}
	
	@Bean("chainedTx2")
	public ChainedTransactionManager chainedTx2(@Qualifier("sbiTM") PlatformTransactionManager sbiTx,
			@Qualifier("hdfcTM") PlatformTransactionManager hdfcTx) {
		return new ChainedTransactionManager(hdfcTx, sbiTx);
	}


}
