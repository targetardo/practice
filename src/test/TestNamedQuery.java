package test;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tpp4.Client;
import tpp4.Zakaz;

public class TestNamedQuery {

	public static void main(String[] args) {
		
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
			EntityManager em = emf.createEntityManager();
			Query q = em.createNamedQuery("Client.findAll");
			List<Client>list = q.getResultList();
			for (Client mark : list) {
				System.out.println(mark.getFirstname()
						+":"+mark.getLastname()
						+"->" +mark.getEmail());
			}
		}


}
