package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tpp4.Client;

public class TestDeleteID {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();em.getTransaction().begin();
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":" + ((Client) s).getFirstname());
		//em.getTransaction().begin();
		Client st = em.find(Client.class, 12);
		em.remove(st);
		em.getTransaction().commit();
		System.out.println("Client list after update");
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":"+ ((Client) s).getFirstname());
	}

}
