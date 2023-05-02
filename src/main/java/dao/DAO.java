package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {

	public static EntityManager conexao() {
		EntityManager em;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("banco-persistence");
		em = factory.createEntityManager();
		return em;
	}

}
