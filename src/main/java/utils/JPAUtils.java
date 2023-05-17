package utils;

import javax.persistence.*;

public class JPAUtils {

	private static EntityManagerFactory FACTORY =  Persistence.createEntityManagerFactory("hotelAluraPU");
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
