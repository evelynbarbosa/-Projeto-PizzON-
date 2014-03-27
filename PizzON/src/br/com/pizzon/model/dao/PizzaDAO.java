package br.com.pizzon.model.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pizzon.model.bean.Pizza;
@SuppressWarnings("unchecked")
public class PizzaDAO {

	private EntityManager entityManager;
	
	public PizzaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Pizza pizza){
		entityManager.persist(pizza);
	}
	public void alterar(Pizza pizza){
		entityManager.merge(pizza);
	}
	public void excluir(Pizza pizza){
		entityManager.remove(entityManager.merge(pizza));
	}
	
	public Pizza consultar(Long id){
		return entityManager.getReference(Pizza.class, id);
	}
	
	public List<Pizza> listar(){
		String jpql = "Select p from Pizza p order by nome";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
