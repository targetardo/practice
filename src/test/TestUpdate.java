package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tpp4.Client;

public class TestUpdate {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();
		// �������� ������ �������� �� ���� ��'� �������� � ����� id=1
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":" + ((Client) s).getFirstname());
		// ���������� ����� ��'� ��������
		em.getTransaction().begin();
		Client st = em.find(Client.class, 1);
		st.setFirstname("����");
		em.getTransaction().commit();
		// �������� ������ �������� ���� ���� ��'� �������� � ����� id=1
		System.out.println("Client list after update");
		for (Object s : em.createNamedQuery("Client.findAll").getResultList())
			System.out.println(((Client) s).getId() + ":"+ ((Client) s).getFirstname());
	}
}
