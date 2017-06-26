package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tpp4.Client;

public class TestParameter {

public static  void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select m from Client m where m.Firstname = ?1");
		q.setParameter(1, "Козак І.П.");
		List<Client>list = q.getResultList();
		for (Client mark : list) {
			System.out.println(mark.getFirstname()
							+":"+mark.getLastname()
							+"->" +mark.getEmail());
		}
		}


}
