/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator Developed by akcess(www.akcess.in)
* Date of code generation: Thu Nov 03 23:45:41 PST 2005
*
* DAO-Generator Version 1.1
*
*/
package com.dataaccess.factory;
import java.io.Serializable;
import java.sql.Connection;
import com.dataaccess.dao.*;
import com.dataaccess.impl.*;
public interface DAOFactory
{
	public DeptDAO createDeptDAO();

	public EmpDAO createEmpDAO();

}
