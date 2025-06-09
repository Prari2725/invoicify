package Invoicify.repository;

import java.util.Collection;

import Invoicify.entity.Role;
import Invoicify.entity.User;

public interface UserRepository <T extends User>{
	//basic crud op

	T create(T data);
	
	Collection<T> list(int page,int pageSize);
	T get(Long id);
	T update (T data);
	Boolean delete(Long id);
	
	
	//more complex operation
	void addRoleToUser(long userId,String roleName);
	
	
	
	
	
}
