package com.klef.jfsd.HQL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String empName;
  private String empDesig;
  private Double empSal;

  public Employee() {
   
  }

  public Employee(int id, String empName, String empDesig, Double empSal) {
    super();
    this.id = id;
    this.empName = empName;
    this.empDesig = empDesig;
    this.empSal = empSal;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getEmpDesig() {
    return empDesig;
  }

  public void setEmpDesig(String empDesig) {
    this.empDesig = empDesig;
  }

  public Double getEmpSal() {
    return empSal;
  }

  public void setEmpSal(double i) {
    this.empSal = i;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", empName=" + empName + ", empDesig=" + empDesig + ", empSal=" + empSal + "]";
  }

public String getempSalary() {
	// TODO Auto-generated method stub
	return null;
}

}