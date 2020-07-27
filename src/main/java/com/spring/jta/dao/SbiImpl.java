package com.spring.jta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jta.bean.SbiAccount;

@Repository("sbiImpl")
@Transactional(value = "sbiTM", rollbackFor = Exception.class)
public class SbiImpl implements BankDao<SbiAccount> {

	@PersistenceContext(unitName = "sbiPU")
	@Qualifier("sbiEM")
	EntityManager em;

	@Override
	public void insert(SbiAccount obj) throws Exception {
		em.persist(obj);
	}

	@Override
	public void update(SbiAccount obj) throws Exception {
		em.merge(obj);
	}

	@Override
	public void delete(SbiAccount obj) throws Exception {
		em.remove(obj);
	}

	@Override
	public void deleteById(Integer _id) throws Exception {
		em.remove(getOne(_id));
	}

	@Override
	public SbiAccount getOne(Integer _id) throws Exception {
		return em.find(SbiAccount.class, _id);
	}

	@Override
	public List<SbiAccount> getAll() throws Exception {
		CriteriaQuery<SbiAccount> criteria = em.getCriteriaBuilder().createQuery(SbiAccount.class);
		criteria.select(criteria.from(SbiAccount.class));
		List<SbiAccount> results = em.createQuery(criteria).getResultList();
		return results;
	}

}
