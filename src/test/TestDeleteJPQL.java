package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tpp4.Client;

public class TestDeleteJPQL {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();em.getTransaction().begin();
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":" + ((Client) s).getFirstname());
		em.createQuery("DELETE FROM Client s WHERE s.firstname = ?1").setParameter(1,"NewName").executeUpdate();
		em.getTransaction().commit();
		System.out.println("Client list after update");
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":"+ ((Client) s).getFirstname());
	}
}
