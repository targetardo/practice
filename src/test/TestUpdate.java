package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tpp4.Client;

public class TestUpdate {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();
		// Виводимо список студентів до зміни ім'я студента у якого id=1
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":" + ((Client) s).getFirstname());
		// Транзакція заміни ім'я студента
		em.getTransaction().begin();
		Client st = em.find(Client.class, 1);
		st.setFirstname("Алла");
		em.getTransaction().commit();
		// Виводимо список студентів після зміни ім'я студента у якого id=1
		System.out.println("Client list after update");
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":"+ ((Client) s).getFirstname());
	}
}
