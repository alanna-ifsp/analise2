package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cliente;
import model.Conta;
import model.ContaEspecial;
import model.ContaPoupanca;

public class ContaDAO {

	EntityManager em = DAO.conexao();

	public Conta getById(Integer id) {
		return em.find(Conta.class, id);
	}
	
	public List<Conta> getAll()
	{
		return em.createQuery("SELECT c FROM Conta c").getResultList();
	}
	
	public List<ContaEspecial> getAllContaEspecial()
	{
		return em.createQuery("SELECT c FROM ContaEspecial c").getResultList();
	}
	
	public ContaEspecial getByIdEspecial(Integer id)
	{
		return em.find(ContaEspecial.class, id);
	}
	public List<Conta> getByClientId(Cliente cliente)
	{
		Query query = em.createQuery("SELECT c FROM Conta c WHERE cliente = :cliente");
		query.setParameter("cliente",cliente);
		return (List<Conta>)query.getResultList();
	}

	public ContaPoupanca  persist(ContaPoupanca conta) {
		ContaPoupanca contaAtualizada;
		em.getTransaction().begin();
		contaAtualizada = em.merge(conta);
		em.getTransaction().commit();
		return contaAtualizada;
	}

	public ContaEspecial persist(ContaEspecial conta) {
		ContaEspecial contaAtualizada;
		em.getTransaction().begin();
		contaAtualizada = em.merge(conta);
		em.getTransaction().commit();
		return contaAtualizada;
	}

	public Conta persist(Conta conta) {
		Conta contaAtualizada;
		em.getTransaction().begin();
		contaAtualizada = em.merge(conta);
		em.getTransaction().commit();
		return contaAtualizada;
	}

}
