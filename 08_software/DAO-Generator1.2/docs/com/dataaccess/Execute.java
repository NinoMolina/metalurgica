
package com.dataaccess;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.dataaccess.dao.DeptDAO;
import com.dataaccess.dao.EmpDAO;
import com.dataaccess.exception.DeptException;
import com.dataaccess.exception.EmpException;
import com.dataaccess.factory.DAOFactory;
import com.dataaccess.vo.Dept;
import com.dataaccess.vo.Emp;
import com.dataaccess.vo.EmpPK;


public class Execute {
	
		public static void main(String args[]){
			Connection con = new DBConnect().connect();
			DAOFactory daofactory = GetDAOFactory.getFactory();
			EmpDAO empdao= daofactory.createEmpDAO();
			DeptDAO deptdao = daofactory.createDeptDAO();
			Dept dept = new Dept(1L,"Computer Science","JNU");
			Emp employee = new Emp(1L, "Doctor", new Timestamp(123756565L),1L);
			try{
					con.setAutoCommit(false);
					deptdao.insert(dept,con);
					empdao.insert(employee, con);
				
					con.commit();
					EmpPK empPK = new EmpPK(1L);
					System.out.println("Emp Name " + empdao.findByPrimaryKey(empPK,con).getName());
					System.out.println("Emp Date of Birth " + empdao.findByPrimaryKey(empPK,con).getBirthdate());
					System.out.println("Emp id " + empdao.findByPrimaryKey(empPK,con).getEmployeeid());
					Emp[] emp = empdao.findByName("Doctor",con);
					for(int i =0; i < emp.length; i++)
						System.out.println("Emp id " +emp[i].getEmployeeid()); 
					empdao.delete(empPK, con);
					con.commit();
					con.close();
			}catch(EmpException ee){System.out.println(ee + "  " + employee.toString());}
			catch(DeptException ee){System.out.println(ee + "  " + dept.toString());}
			catch(SQLException sqle){System.out.println(sqle + "  " + employee.toString());}
			
		}

}