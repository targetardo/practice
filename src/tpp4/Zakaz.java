package tpp4;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ZAKAZ database table.
 * 
 */
@Entity
@NamedQuery(name="Zakaz.findAll", query="SELECT z FROM Zakaz z")
public class Zakaz implements Serializable,IModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="IDCLIENT")
	private Client client;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="IDPRODUCT")
	private Product product;

	public Zakaz() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	@Override
	public String toString() {
		return "Zakaz [id=" + id + ", date=" + date + ", client=" + client + ", product=" + product + "]";
	}

	public String[] getTableHeaders() {
		return new String[]{"Id","Date","Client","Product" };
	}

	public Object[] getTableRowData() {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s = myFormat.format(date);
		return new Object[]{id,s,client,product};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Zakaz other = (Zakaz) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public void updateWith(Object mask) {
	}

}