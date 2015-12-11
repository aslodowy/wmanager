package com.dalroy.wm.dataaccess;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
//import javax.transaction.NotSupportedException;
//import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.dalroy.wm.entities.*;
import com.dalroy.wm.helpers.Password;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN) 
public class DataAccessBean {
	
	@EJB
	Password pwd;
	@PersistenceContext(unitName = "WorkersManager")
    private EntityManager em;
	@Resource 
	UserTransaction uTx;
	
	public DataAccessBean() {}
	
	
	public List<Worker> findWorkers(String name, String lastName, String minSalary, String maxSalary, String minAge, String maxAge, String minyearEmployed, String maxyearEmployed, String position, String sex) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Worker> query = builder.createQuery(Worker.class);
		Root<Worker> worker = query.from(Worker.class);
		query.select(worker);
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		Predicate namePredicate, lastNamePredicate, minSalaryPredicate, maxSalaryPredicate, minAgePredicate, maxAgePredicate, minyearEmployedPredicate, maxyearEmployedPredicate, positionPredicate, sexPredicate;
		
		
		if (this.notNullAndEmpty(name)) {
			namePredicate = builder.like(builder.upper(worker.<String>get("name")), "%"+name.toUpperCase()+"%");
			predicateList.add(namePredicate);
		}
		
		
		if (this.notNullAndEmpty(lastName)) {
			lastNamePredicate = builder.like(builder.upper(worker.<String>get("lastName")), "%"+lastName.toUpperCase()+"%");
			predicateList.add(lastNamePredicate);
		}
		
		if (this.notNullAndEmpty(position)) {
			positionPredicate = builder.like(builder.upper(worker.<String>get("position")), "%"+position.toUpperCase()+"%");
			predicateList.add(positionPredicate);
		}
		
		if (this.notNullAndEmpty(minyearEmployed)) {
			minyearEmployedPredicate = builder.ge(worker.<Integer>get("yearEmployed"), Integer.parseInt(minyearEmployed));
			predicateList.add(minyearEmployedPredicate);
		}
		
			if (this.notNullAndEmpty(maxyearEmployed)) {
			maxyearEmployedPredicate = builder.le(worker.<Integer>get("yearEmployed"), Integer.parseInt(maxyearEmployed));
			predicateList.add(maxyearEmployedPredicate);
		}
		
		if (this.notNullAndEmpty(minAge)) {
			minAgePredicate = builder.ge(worker.<Integer>get("age"), Integer.parseInt(minAge));
			predicateList.add(minAgePredicate);
		}
		
		if (this.notNullAndEmpty(maxAge)) {
			maxAgePredicate = builder.le(worker.<Integer>get("age"), Integer.parseInt(maxAge));
			predicateList.add(maxAgePredicate);
		}
		
		if (this.notNullAndEmpty(minSalary)) {
			minSalaryPredicate = builder.ge(worker.<Double>get("salary"), Double.parseDouble(minSalary));
			predicateList.add(minSalaryPredicate);
		}
		
		if (this.notNullAndEmpty(maxSalary)) {
			maxSalaryPredicate = builder.le(worker.<Double>get("salary"), Double.parseDouble(maxSalary));
			predicateList.add(maxSalaryPredicate);
		}
		
		if (this.notNullAndEmpty(sex)) {
			sexPredicate = builder.equal(worker.<String>get("sex"), sex);
			predicateList.add(sexPredicate);
		} 
			
		Predicate[] predicates = new Predicate[predicateList.size()];
		predicateList.toArray(predicates);
		
		for (int i =0; i<predicates.length; i++) System.out.println(predicates[i].toString());
		query.where(predicates);
		
		List<Worker> resultList = em.createQuery(query).getResultList();
		if (resultList.isEmpty()) System.out.println("pusta lista kurwisyny");
		return resultList;
		
	} 

	public void addWorker(Worker worker) throws Exception {
		uTx.begin();
		em.persist(worker);
		uTx.commit();
	}
	
	public void addSection(Section sct) throws Exception {
		uTx.begin();
		em.persist(sct);
		uTx.commit();
	}
	
	public void updateSection(int id, Section section) throws Exception {
		Section current = em.find(Section.class, id);
		current.setName(section.getName());
		current.setNumberOfWorkers(current.getNumberOfWorkers());
		uTx.begin();
		em.merge(current);
		uTx.commit();
	}
	
	public void changeWorkerPosition(int id, String position) {
		Worker worker = em.find(Worker.class, id);
		em.getTransaction().begin();
		worker.setPosition(position);
		em.getTransaction().commit();
	}
	
	public void updateWorker(int id, Worker worker) throws Exception {
		Worker current = em.find(Worker.class, id);
		Section section = em.find(Section.class, worker.getSectionId());
		current.setName(worker.getName());
		current.setLastName(worker.getLastName());
		current.setAge(worker.getAge());
		current.setPosition(worker.getPosition());
		current.setYearEmployed(worker.getYearEmployed());
		current.setSection(section);
		current.setSalary(worker.getSalary());
		uTx.begin();
		em.merge(current);
		uTx.commit();
	}
	
	public void deleteWorker(int id) throws Exception {
		uTx.begin();
		em.remove(em.find(Worker.class, id));
		uTx.commit();
	}
	
//	public List<Worker> getAllWorkers() {
		
	//}
	
	public Worker getSpecificWorker(int id) {
		Worker worker = em.find(Worker.class, id);
		return worker;
	}
	
	public Section getSpecificSection(int id) {
		Section section = em.find(Section.class, id);
		return section;
	}
	
	public List<Worker> getAllOfSpecificSex(String sex) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Worker> query = cb.createQuery(Worker.class);
		Root<Worker> worker = query.from(Worker.class);
		query.select(worker);
		query.where(cb.equal(worker.get("sex"), sex));
		TypedQuery<Worker> qr = em.createQuery(query);
		return qr.getResultList();
	}
	
	public List<Worker> getWorkersFromSection(Section section) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Worker> query = cb.createQuery(Worker.class);
		Root<Worker> worker = query.from(Worker.class);
		query.select(worker);
		query.where(cb.equal(worker.get("section").get("id"), section.getId()));
		TypedQuery<Worker> qr = em.createQuery(query);
		return qr.getResultList();
	}
	
	public List<Worker> getAllWorkers() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Worker> cq = cb.createQuery(Worker.class);
		Root<Worker> worker = cq.from(Worker.class);
		cq.select(worker);
		TypedQuery<Worker> query = em.createQuery(cq);
		return query.getResultList();
	}
	
	public List<Section> getSections() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Section> cq = cb.createQuery(Section.class);
		Root<Section> section = cq.from(Section.class);
		cq.select(section);
		TypedQuery<Section> query = em.createQuery(cq);
		return query.getResultList();
	}
	
	 public User getUser(String username) throws NoResultException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> user = query.from(User.class);
		query.select(user);
		//ParameterExpression<String> param = cb.parameter(String.class);
		query.where(cb.equal(user.get("username"), username));
		TypedQuery<User> qr = em.createQuery(query);
		return qr.getSingleResult();
	} 
		 
	public void createUser(User user) throws Exception {
		uTx.begin();
		String hash = pwd.getHash(user.getPassword());
		String salt = pwd.getSalt();
		user.setPassword(pwd.getPassword(hash, salt));
		user.setSalt(salt);
		em.persist(user);
		uTx.commit();
	}
	
	public void updateUser(int id, User user) throws Exception {
		User current = em.find(User.class, id);
		current.setRole(user.getRole());
		current.setUsername(user.getUsername());
		String hash = pwd.getHash(user.getPassword());
		String salt = pwd.getSalt();
		current.setPassword(pwd.getPassword(hash, salt));
		current.setSalt(salt);
		uTx.begin();
		em.merge(current);
		uTx.commit();
	}
	
	private boolean notNullAndEmpty(Object obj) {
		return (obj != null && !obj.toString().isEmpty() );
	}
}
