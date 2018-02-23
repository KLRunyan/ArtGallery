//Kristen Runyan
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.GalleryItem;

public class GalleryItemHelper {
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ArtGalleryRunyan");
	
	public void insertItem(GalleryItem toAdd) {
		// TODO Auto-generated method stub
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();
	}

	public List<GalleryItem> showAllItems() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<GalleryItem> allResults = em.createQuery("select li from GalleryItem li", GalleryItem.class);
		List<GalleryItem> allItems = allResults.getResultList();
		em.close();
		return allItems;
	}

	public void deleteItem(GalleryItem toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GalleryItem> typedQuery = em.createQuery("select li from GalleryItem li where li.title = :selectedTitle and li.artistName = :selectedArtistName and li.media = :selectedMedia and li.year = :selectedYear and li.value = :selectedValue", GalleryItem.class);
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedArtistName", toDelete.getArtistName());
		typedQuery.setParameter("selectedMedia", toDelete.getMedia());
		typedQuery.setParameter("selectedYear", toDelete.getYear());
		typedQuery.setParameter("selectedValue", toDelete.getValue());
		typedQuery.setMaxResults(1);
		GalleryItem result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public GalleryItem searchForItemById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		GalleryItem foundItem = em.find(GalleryItem.class, tempId);
		em.close();
		return foundItem;
}
	public void updateItem(GalleryItem itemToUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(itemToUpdate);
		em.getTransaction().commit();
		em.close();
}
}
