package tpp4;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p ORDER BY p.name")
public class Product implements Serializable,IModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String brend;

	private String name;

	private double price;

	//bi-directional many-to-one association to Zakaz
	@OneToMany(mappedBy="product")
	private List<Zakaz> zakazs;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrend() {
		return this.brend;
	}

	public void setBrend(String brend) {
		this.brend = brend;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Zakaz> getZakazs() {
		return this.zakazs;
	}

	public void setZakazs(List<Zakaz> zakazs) {
		this.zakazs = zakazs;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", brend=" + brend + ", name=" + name + ", price=" + price + "]";
	}

	public Zakaz addZakaz(Zakaz zakaz) {
		getZakazs().add(zakaz);
		zakaz.setProduct(this);

		return zakaz;
	}

	public Zakaz removeZakaz(Zakaz zakaz) {
		getZakazs().remove(zakaz);
		zakaz.setProduct(null);

		return zakaz;
	}
	
	public String[] getTableHeaders() {
		return new String[]{"Id","Name","Brend","Price"};
	}

	public Object[] getTableRowData() {
		return new Object[]{id,name,brend,price};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brend == null) ? 0 : brend.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (brend == null) {
			if (other.brend != null)
				return false;
		} else if (!brend.equals(other.brend))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	@Override
	public void updateWith(Object mask) {
		Product obj = (Product) mask;
		name = obj.getName();
		brend = obj.getBrend();
		price = obj.getPrice();
	}

}