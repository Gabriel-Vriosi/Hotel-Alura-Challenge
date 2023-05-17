package dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import models.Huesped;

public class HuespedDAO {
	
	private EntityManager em;
	
	public HuespedDAO(EntityManager em) {
		this.em = em;
	}

	//METHODS**********************************************************************************
	public void save(Huesped huesped) {
		try {
			em.persist(huesped);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(int id) {
		try {
			Huesped huesped = em.find(Huesped.class, id);
			em.remove(huesped);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Huesped getByID(int id) throws SQLException{
		return em.find(Huesped.class, id);
	}
	
	public List<Huesped> getByApellido(String surname) throws SQLException{
		String jpql = "SELECT h " + 
					  "FROM Huesped h "+
					  "WHERE h.apellido = :surname "+
					  "ORDER BY h.nombre";
		
		return em.createQuery(jpql, Huesped.class)
				.setParameter("surname", surname)
				.getResultList();
	}
	
	public List<Huesped> getAll() throws SQLException{
		String jpql = "SELECT H FROM Huesped AS H";
		return em.createQuery(jpql, Huesped.class).getResultList();
	}
	
	public List<Object[]> getWithReservation() throws SQLException{
		String jpql =
				"SELECT h.id, h.nombre, h.apellido, h.fechaNacimiento, h.nacionalidad, " + 
				"h.telefono, r.id " +
				"FROM Huesped h, Reserva r " + 
				"WHERE h.id = r.huesped " +
				"ORDER BY h.id";
		return em.createQuery(jpql, Object[].class).getResultList();
	}
}
