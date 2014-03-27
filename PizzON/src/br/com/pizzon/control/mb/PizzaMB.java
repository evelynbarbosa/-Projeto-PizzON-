package br.com.pizzon.control.mb;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.pizzon.model.bean.Pizza;
import br.com.pizzon.model.dao.JPAUtil;
import br.com.pizzon.model.dao.PizzaDAO;

@ViewScoped
@ManagedBean
public class PizzaMB {
	//Atributos devem ser iniciados
	private Pizza pizza = new Pizza();
	
	public Pizza getPizza() {
		return pizza;
	}
	
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	//Atributo que guarda a colecao produtos armazenados em BD
	public List<Pizza> listaPizza = new ArrayList<Pizza>();
	
	public List<Pizza> getListaPizza() {
		return listaPizza;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarPizza(){
		EntityManager em = JPAUtil.getEntityManager();
		PizzaDAO dao = new PizzaDAO(em);
		listaPizza = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		PizzaDAO dao = new PizzaDAO(em);
		em.getTransaction().begin();
		dao.excluir(pizza);
		em.getTransaction().commit();
		em.close();
		carregarPizza();
	}

	public void salvar(){
		EntityManager em = JPAUtil.getEntityManager();
		PizzaDAO dao = new PizzaDAO(em);
		em.getTransaction().begin();
		pizza.setDataCadastro(Calendar.getInstance());
		if(pizza.getId()!=null){
			dao.alterar(pizza);
		}else{
			dao.cadastrar(pizza);
		}
		em.getTransaction().commit();
		em.close();
		pizza  = new Pizza();
		carregarPizza();
	}
}
