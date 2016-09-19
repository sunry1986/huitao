package com.huitao.dao.factory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class DaoFactory {
    private  static DaoFactory instance;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private DaoFactory(){};
    public static DaoFactory getInstance(){
    	if(instance==null){
    		  synchronized(DaoFactory.class){
    			   if(instance==null){
    				   instance=new DaoFactory(); 
    			   }
    		  }
    	}
    	
    	return instance;
    }
    /**
	 * 
	 * @param serviceName
	 * @return
	 */
	public <T> T getService(String serviceName) {
		T service = (T)context.getBean(serviceName);
		return service;
	}
}
