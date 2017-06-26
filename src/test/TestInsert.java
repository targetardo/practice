package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tpp4.Client;

public class TestInsert {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();
		String sql = "insert into CLIENT(FIRSTNAME) values(" + "'NewName'" + ")";
		Query q = em.createNativeQuery(sql);
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":" + ((Client) s).getFirstname());
		em.getTransaction().begin();
			q.executeUpdate();
		em.getTransaction().commit();
		System.out.println("Client list after update");
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":"+ ((Client) s).getFirstname());
	}
	
}
