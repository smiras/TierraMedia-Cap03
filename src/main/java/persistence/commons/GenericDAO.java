package persistence.commons;

import java.util.LinkedList;


public interface GenericDAO<T> {

	public T find(Integer id);
	public LinkedList<T> findAll();
	public int countAll();
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
}
