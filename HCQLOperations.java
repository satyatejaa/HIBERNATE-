package com.klef.jfsd.HCQL;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HCQLOperations {
	public static void main(String args[]) {
		HCQLOperations operations = new HCQLOperations();
		// operations.addFaculty();
		// operations.restrictionsdemo();
		// operations.orderdemo();
		//operations.aggregatefunctions();
		 operations.hcqldemo(); // use case
	}

	public void addFaculty() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();

		Transaction t = session.beginTransaction();

		Faculty faculty = new Faculty();
		faculty.setId(102);
		faculty.setName("EP");
		faculty.setGender("MALE");
		faculty.setDepartment("CSE");
		faculty.setSalary(50000);
		faculty.setContactno("7898164546");

		session.persist(faculty);
		t.commit();

		System.out.println("faculty Added Successfully");

		session.close();
		sf.close();
	}

	public void restrictionsdemo() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Faculty> cq = cb.createQuery(Faculty.class);
		// from Faculty; [Complete Object]
		Root<Faculty> root = cq.from(Faculty.class);

		// equals

		// cq.select(root).where(cb.equal(root.get("department"), "CSE"));
		// cq.select(root).where(cb.between(root.get("salary"),10000, 30000));
		cq.select(root).where(cb.ge(root.get("salary"), 10000));

		List<Faculty> facultylist = session.createQuery(cq).getResultList();
		System.out.println("Students Count=" + facultylist.size());
		for (Faculty faculty : facultylist) {

			System.out.println(facultylist.toString());
		}
		session.close();
		sf.close();
	}

	public void orderdemo() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		Root<Student> root = cq.from(Student.class);

		// ascending order based on age
		// cq.orderBy(cb.asc(root.get("age")));

		// descending order based on name
		cq.orderBy(cb.desc(root.get("name")));

		System.out.println("****Order by Demo****");
		List<Student> students = session.createQuery(cq).getResultList();
		System.out.println("Students Count=" + students.size());
		for (Student s : students) {
			System.out.println(s.toString());
		}

		session.close();
		sf.close();
	}

	public void hcqldemo() { // usecase

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Faculty> cq = cb.createQuery(Faculty.class);
		// from Faculty; [Complete Object]
		Root<Faculty> root = cq.from(Faculty.class);

		// criteria
		cq.select(root).where(cb.greaterThan(root.get("salary"), 30000));
		// order by
		cq.orderBy(cb.desc(root.get("salary")));

		List<Faculty> facultylist = session.createQuery(cq).getResultList();
		System.out.println("Students Count=" + facultylist.size());
		for (Faculty faculty : facultylist) {

			System.out.println(facultylist.toString());
		}
		session.close();
		sf.close();

	}

	private void aggregatefunctions() {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();

		/*
		 * CriteriaBuilder cb = session.getCriteriaBuilder(); CriteriaQuery<Long> cq =
		 * cb.createQuery(Long.class); Root<Faculty> root = cq.from(Faculty.class);
		 * cq.select(cb.count(root.get("name"))); Long count =
		 * session.createQuery(cq).getSingleResult(); System.out.println(count);
		 */

		
		  
		  CriteriaBuilder cb = session.getCriteriaBuilder(); 
		  CriteriaQuery<Double> cq = cb.createQuery(Double.class); 
		  Root<Faculty> root = cq.from(Faculty.class);
		  cq.multiselect(cb.sum(root.get("salary"))); 
		  Double sum = session.createQuery(cq).getSingleResult(); 
		  System.out.println(sum);
		
		 
			

		session.close();
		sf.close();
	}

}