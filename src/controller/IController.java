package controller;

import javax.swing.table.TableModel;

public interface IController{

	void createDB();

	TableModel getModel(String className);
	
	public void add(Object obj);
	
	public void edit(int id, Object obj);
	
	public void delete(int id, String className);
	
	public TableModel doQuery1();

	public TableModel doQuery2();
}