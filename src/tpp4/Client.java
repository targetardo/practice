package tpp4;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CLIENT database table.
 * 
 */
@Entity
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c ORDER BY c.firstname")
public class Client implements Serializable,IModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String firstname;

	private String lastname;

	

	private String sex;

	private String telephone;

	//bi-directional many-to-one association to Zakaz
	@OneToMany(mappedBy="client")
	private List<Zakaz> zakazs;

	public Client() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Zakaz> getZakazs() {
		return this.zakazs;
	}

	public void setZakazs(List<Zakaz> zakazs) {
		this.zakazs = zakazs;
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", sex=" + sex + ", telephone=" + telephone + "]";
	}
	
	public Zakaz addZakaz(Zakaz zakaz) {
		getZakazs().add(zakaz);
		zakaz.setClient(this);

		return zakaz;
	}

	public Zakaz removeZakaz(Zakaz zakaz) {
		getZakazs().remove(zakaz);
		zakaz.setClient(null);

		return zakaz;
	}
	public String[] getTableHeaders() {
		return new String[]{"Id","Sex","Firstname","Lastname","Email","Telephone" };
	}

	public Object[] getTableRowData(){
		return new Object[]{id,sex,firstname,lastname,email,telephone};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
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
		Client other = (Client) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}

	@Override
	public void updateWith(Object mask) {
		Client obj = (Client) mask;
		sex = obj.getSex();
		lastname = obj.getFirstname();
	    firstname = obj.getLastname();
		email = obj.getEmail();
		telephone = obj.getTelephone();
	}
}