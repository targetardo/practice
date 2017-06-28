package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tpp4.Client;

public class test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();
		String sql = "alter table ZAKAZ add  IDPRODUCT3 integer, add foreign key (IDPRODUCT3)";
		Query q = em.createNativeQuery(sql);
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
		System.out.println("Client list after update");
	}

}
