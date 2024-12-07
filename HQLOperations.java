package com.klef.jfsd.HQL;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import jakarta.transaction.Transaction;



public class HQLOperations {
  public static void main(String[] args) {
    HQLOperations operations = new HQLOperations();
     operations.addEmployee();
    //operations.displayallemployeeComplterObj();
    // operations.displayallemployeeComplterObj();
     //operations.aggregateFunctions();
    //operations.displayEmployeesPartialobj();
    // operations.updatePositionalParams();
    // operations.updateNamedParams();
    // operations.deletePositional();
    // operations.deleteNamedParams();
    //operations.HqlDemo();
    //operations.pagination();
  }

  public void addEmployee() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    org.hibernate.Transaction tx = session.beginTransaction();

    Employee emp = new Employee();
    emp.setEmpDesig("KLEF");
    emp.setEmpName("Eswar");
    emp.setEmpSal(45000);

    session.persist(emp);

    tx.commit();
    System.out.println("employee added successfully");

    session.close();
    sf.close();
  }

  public void displayallemployeeComplterObj() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    String hql = "from Employee";
    Query<Employee> qry = session.createQuery(hql, Employee.class);
    List<Employee> emps = qry.getResultList();
    System.out.println("Total Employee" + emps.size());
    for (Employee e : emps) {
      System.out.println("ID : " + e.getId());
      System.out.println("Name : " + e.getEmpName());
      System.out.println("Designation : " + e.getEmpDesig());
      System.out.println("Salary : " + e.getEmpSal());
    }
    session.close();
    sf.close();
  }

  public void displayEmployeesPartialobj() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    String hql = "select id,empName,empDesig from Employee";
    // e is an alias or reference type employee

    Query<Object[]> qry = session.createQuery(hql, Object[].class);
    List<Object[]> emps = qry.getResultList();

    System.out.println("total employees" + emps.size());

    for (Object[] obj : emps) {
      System.out.println("ID : " + obj[0]);
      System.out.println("Name : " + obj[1]);
      System.out.println("Designation : " + obj[2]);

    }
    session.close();
    sf.close();

  }

  public void aggregateFunctions() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    String sql = "select count(*) from Employee";
    Query<Long> qrl1 = session.createQuery(sql, Long.class);
    long count = qrl1.getSingleResult();
    System.out.println("total employees: " + count);

    String sql1 = "select sum(empSal) from Employee";
    Query<Double> qrl2 = session.createQuery(sql1, Double.class);
    Double count1 = qrl2.getSingleResult();
    System.out.println("total employees  sal sum: " + count1);

    String sql2 = "select avg(empSal) from Employee";
    Query<Double> qrl3 = session.createQuery(sql2, Double.class);
    Double count2 = qrl3.getSingleResult();
    System.out.println("total employees average is : " + count2);

    String sql3 = "select max(empSal) from Employee";
    Query<Double> qrl4 = session.createQuery(sql3, Double.class);
    Double count3 = qrl4.getSingleResult();
    System.out.println("employees  maxium sal is : " + count3);
    String sql4 = "select min(empSal) from Employee";
    Query<Double> qrl5 = session.createQuery(sql4, Double.class);
    Double count4 = qrl5.getSingleResult();
    System.out.println("employees  minimum sal is : " + count4);

    session.close();
    sf.close();
  }

  public void updatePositionalParams() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();
    org.hibernate.Transaction tx = session.beginTransaction();
    String Hql = "update Employee set empName=?1,empSal=?2 where id=?3";
    MutationQuery qry = session.createMutationQuery(Hql);

    qry.setParameter(1, "King");
    qry.setParameter(2, 90000);
    qry.setParameter(3, 1);

    int n = qry.executeUpdate();
    if (n > 0) {
      System.out.println("Employee updated successfully");
    }

    else {
      System.out.println("Employee ID not found");
    }
    tx.commit();
    session.close();
    sf.close();
  }

  public void updateNamedParams() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();
    org.hibernate.Transaction tx = session.beginTransaction();
    String Hql = "update Employee set empName=:v1,empSal=:v2 where id=:v3";
    MutationQuery qry = session.createMutationQuery(Hql);

    qry.setParameter("v1", "Teju");
    qry.setParameter("v2", 990000);
    qry.setParameter("v3", 1);

    int n = qry.executeUpdate();
    if (n > 0) {
      System.out.println("Employee updated successfully");
    }

    else {
      System.out.println("Employee ID not found");
    }
    tx.commit();
    session.close();
    sf.close();
  }

  public void deletePositional() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();
    org.hibernate.Transaction tx = session.beginTransaction();
    String Hql = "delete from Employee where id=?1";

    Scanner sc = new Scanner(System.in);
    System.out.println("enter the id of the employee to delete");
    int eid = sc.nextInt();
    MutationQuery qry = session.createMutationQuery(Hql);

    qry.setParameter(1, eid);

    int n = qry.executeUpdate();
    if (n > 0) {
      System.out.println("Employee deleted successfully");
    }

    else {
      System.out.println("Employee ID not found");
    }

    tx.commit();
    session.close();
    sf.close();
  }

  public void deleteNamedParams() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();
    org.hibernate.Transaction tx = session.beginTransaction();
    String Hql = "delete from where id=:v1";
    MutationQuery qry = session.createMutationQuery(Hql);
    Scanner s = new Scanner(System.in);
    System.out.println("enter the person id to delete");
    int id = s.nextInt();
    qry.setParameter("v1", id);

    int n = qry.executeUpdate();
    if (n > 0) {
      System.out.println("Employee deleted successfully");
    }

    else {
      System.out.println("Employee ID not found");
    }
    tx.commit();
    session.close();
    sf.close();
  }

  // Display Employee objects based on designation(Professor) and salary should be
  // grate than or equal to 10000

  public void HqlDemo() {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    String hql = "from Employee where empDesig = ?1 and empSal >=?2";

    Query<Employee> qry = session.createQuery(hql, Employee.class);
    qry.setParameter(1, "prof");
    qry.setParameter(2, 10000);
    List<Employee> emps = qry.getResultList();
    System.out.println("Total Employee" + emps.size());
    for (Employee e : emps) {
      System.out.println("ID : " + e.getId());
      System.out.println("Name : " + e.getEmpName());
      System.out.println("Designation : " + e.getEmpDesig());
      System.out.println("Salary : " + e.getEmpSal());
    }
    session.close();
    sf.close();
  }
  
  
  public void pagination()
  {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");
    
    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();
   
    String hql = "from Employee";
    Query<Employee> qry = session.createQuery(hql, Employee.class);
    
    qry.setFirstResult(2);
    qry.setMaxResults(5);
    
    List<Employee> emps =  qry.getResultList();
    
    
    System.out.println("Total Employees="+emps.size());
    
    for( Employee e :emps)
    {
      System.out.println("ID:"+e.getId());
      System.out.println("Name:"+e.getEmpName());
      System.out.println("Designation:"+e.getEmpDesig());
      System.out.println("Salary:"+e.getempSalary());
    }
    session.close();
    sf.close();  
  }
}