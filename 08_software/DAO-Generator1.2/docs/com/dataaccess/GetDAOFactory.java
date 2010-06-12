package com.dataaccess;
import com.dataaccess.factory.DAOFactory;
import com.dataaccess.factory.DAOFactoryImpl;

public class GetDAOFactory {
	public static DAOFactory getFactory(){
		return new DAOFactoryImpl();
	}
	
}