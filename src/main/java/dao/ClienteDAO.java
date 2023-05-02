package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.Cliente;

public class ClienteDAO {

	EntityManager em = DAO.conexao();

	public Cliente getById(Integer id) {
		return em.find(Cliente.class, id);
	}

	public List<Cliente> getAll() {
		return em.createQuery("SELECT c FROM Cliente c").getResultList();
	}

	public void persist(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}
}
