package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import mail.TestSendMail;
import tpp4.*;

public class JPAController implements IController {
	EntityManagerFactory emf =Persistence.createEntityManagerFactory("tpp4");
	public List getObjectList(Class clazz) {
		EntityManager em = emf.createEntityManager();
		// Ôîðìóºìî ³ì'ÿ ³ìåíîâàíîãî çàïèòó äëÿ çàäàíîãî êëàñó
		String queryName = clazz.getSimpleName() + "." + "findAll";
		// Îòðèìóºìî ïåðåë³ê çàïèñ³â òàáëèö³ çàäàíîãî êëàñó
		List list = em.createNamedQuery(queryName).getResultList();
		em.close();
		return list;
	}

@Override
	public TableModel getModel(String className) {
		try {
			Class clazz = Class.forName("tpp4." + className);
			// Îòðèìóºìî çàãîëîâîê òàáëèö³
			IModel obj = (IModel) clazz.newInstance();
			String[] header = obj.getTableHeaders();
			// Îòðèìóºìî ñïèñîê îá'ºêò³â
			List list = getObjectList(clazz);
			if (list == null || list.size() == 0)
				return new DefaultTableModel(null, header);
			// Ñòâîðþºìî ìàñèâ ïîòð³áíîãî ðîçì³ðó
			Object[][] array = new Object[list.size()][header.length];
			// Íàïîâíþºìî ìàñèâ
			int i = 0;
			for (Object s : list)
				array[i++] = ((IModel) s).getTableRowData();
			// Ïîâåðòàºìî ìîäåëü
			return new DefaultTableModel(array, header);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean exist(IModel obj) {
		Class clazz = obj.getClass();
		// Îòðèìóºìî ïåðåë³ê çàïèñ³â òàáëèö³ çàäàíîãî êëàñó
		List list = getObjectList(clazz);
		if (list != null&&list.size() != 0)
			for (Object current : list)
				if (current.equals(obj))
					return true;
		return false;
	}

	@Override
	public void createDB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Object obj) {
		Class clazz = obj.getClass();
		if(exist((IModel) obj)) return;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();
		Query q = null;
		if (obj instanceof Client) {
			q = em.createNativeQuery(
	"insert into CLIENT(SEX,FIRSTNAME,LASTNAME,EMAIL,TELEPHONE) values(?1,?2,?3,?4,?5)");
			q.setParameter(1, ((Client) obj).getSex());
			q.setParameter(2, ((Client) obj).getFirstname());
			q.setParameter(3, ((Client) obj).getLastname());
			q.setParameter(4, ((Client) obj).getEmail());
			q.setParameter(5, ((Client) obj).getTelephone());
		} else if (obj instanceof Product) {
			q = em.createNativeQuery(
	"insert into PRODUCT(NAME,BREND, PRICE) values(?1,?2,?3)");
			q.setParameter(1, ((Product)obj).getName());
			q.setParameter(2, ((Product)obj).getBrend());
			q.setParameter(3, ((Product)obj).getPrice());
		} else if (obj instanceof Zakaz) {
			q = em.createNativeQuery(
	"insert into ZAKAZ(IDPRODUCT, IDCLIENT,DATE)" + "values(?1,?2,?3)");
			q.setParameter(1, ((Zakaz)obj).getProduct().getId());
			q.setParameter(2, ((Zakaz)obj).getClient().getId());
			q.setParameter(3, ((Zakaz) obj).getDate());
		}
		if(q==null)return;
		em.getTransaction().begin();
			q.executeUpdate();
		em.getTransaction().commit();
		TestSendMail send = new TestSendMail();
		send.sender();  
	}


	@Override
	public void edit(int id, Object obj) {
			Class clazz = obj.getClass();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			IModel obj1 = (IModel) em.find(clazz, id);
			obj1.updateWith(obj);
			em.persist(obj1);
			em.getTransaction().commit();
			TestSendMail send = new TestSendMail();
			send.sender();   
	}

	@Override
	public void delete(int id, String className) {
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = Class.forName("tpp4." + className);
			Object delObj = em.find(clazz, id);
			em.getTransaction().begin();
			em.remove(delObj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			TestSendMail send = new TestSendMail();
			send.sender();   
			em.close();
		}
	}


	@Override
	public TableModel doQuery1() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select CLIENT.LASTNAME as CLIENT, ZAKAZ.DATE"
				+ " from ZAKAZ,CLIENT"
				+ " where ZAKAZ.IDCLIENT = CLIENT.ID");
		List<Object[]>list = q.getResultList();
		String[][] arr = new String[list.size()][4];
		int i = 0;
		for (Object[] z : list) {
			
			arr[i][0] = (String)z[0];
			arr[i++][1] = String.valueOf(z[1]);
		}
		DefaultTableModel model = new DefaultTableModel(arr,
		new String[] { "Lastname","Date" });
		return model;
	}
			
	@Override
	public TableModel doQuery2() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpp4");
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("SELECT m FROM Product m where m.price>2500");
			//q.setParameter(1, 60);
			List<Product>list = q.getResultList();
			String[][] arr = new String[list.size()][4];
			int i = 0;
			for (Product m : list) {
				arr[i][0] = m.getName();
				arr[i][1] = m.getBrend();
				arr[i++][2] = String.valueOf(m.getPrice());
			}
			DefaultTableModel model = new DefaultTableModel(arr,
		new String[] { "Name", "Brend", "Price" });
			return model;
		}
	public void operateObject(IModel obj, int operation) {
		if(operation==1){
			add(obj);
		}
		else if(operation==2){
			edit(obj.getId(),obj);
		}
		else if(operation==4){
			delete(obj.getId(),obj.getClass().getSimpleName());
		}
	}
	}
