package dao;

import java.util.List;
import javax.persistence.EntityManager;
import models.Reserva;

public class ReservaDAO {
	
	private EntityManager em;
	
	public ReservaDAO(EntityManager em) {
		this.em = em;
	}

	//METHODS**********************************************************************************
	public void save(Reserva reserva) {
		try {
			em.persist(reserva);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(int id) {
		try {
			System.out.println("hola");
			Reserva reserva = em.find(Reserva.class, id);
			em.remove(reserva);
			System.out.println("adios");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Reserva getByID(int id) {
		return em.find(Reserva.class, id);
	}
	
	public List<Reserva> getAll() {
		String jpql = "SELECT R FROM Reserva AS R";
		return em.createQuery(jpql, Reserva.class).getResultList();
	}
}
