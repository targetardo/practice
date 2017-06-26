package tpp4;

public interface IModel {
	public String[] getTableHeaders();
	public Object[] getTableRowData();
	int getId();
	void updateWith(Object mask);
	public void setId(int id);


}

