package com.spring.jta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jta.bean.HdfcAccount;

@Repository("hdfcImpl")
@Transactional(value = "hdfcTM", rollbackFor = Exception.class)
public class HdfcImpl implements BankDao<HdfcAccount> {

	@PersistenceContext(unitName = "hdfcPU")
	@Qualifier("hdfcEM")
	EntityManager em;

	@Override
	public void insert(HdfcAccount obj) throws Exception {
		em.persist(obj);
	}

	@Override
	public void update(HdfcAccount obj) throws Exception {
		em.merge(obj);
	}

	@Override
	public void delete(HdfcAccount obj) throws Exception {
		em.remove(obj);
	}

	@Override
	public void deleteById(Integer _id) throws Exception {
		em.remove(getOne(_id));
	}

	@Override
	public HdfcAccount getOne(Integer _id) throws Exception {
		return em.find(HdfcAccount.class, _id);
	}

	@Override
	public List<HdfcAccount> getAll() throws Exception {
		CriteriaQuery<HdfcAccount> criteria = em.getCriteriaBuilder().createQuery(HdfcAccount.class);
		criteria.select(criteria.from(HdfcAccount.class));
		List<HdfcAccount> results = em.createQuery(criteria).getResultList();
		return results;
	}
}
