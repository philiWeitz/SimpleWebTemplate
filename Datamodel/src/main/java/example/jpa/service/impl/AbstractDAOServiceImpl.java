package example.jpa.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import example.jpa.model.AbstractEntity;

public abstract class AbstractDAOServiceImpl<T extends AbstractEntity> {
	private static Logger LOG = LogManager
			.getLogger(AbstractDAOServiceImpl.class);
	
	private EntityManager em;

	
	public AbstractDAOServiceImpl() {
		em = Persistence.createEntityManagerFactory("hello-world")
				.createEntityManager();
	}

	public T saveOrUpdate(T item) {
		T result = null;

		try {
			em.getTransaction().begin();

			if (null == item.getId()) {
				// new item
				em.persist(item);
				result = item;
			} else {
				// update item
				result = em.merge(item);
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while saving \"" + item.getClass() + "\"", e);
		}

		return result;
	}

	public T getById(Long id) {
		T result = null;

		try {
			em.getTransaction().begin();
			result = em.find(getGenericClass(), id);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while getting \"" + result.getClass() + "\" (id: "
					+ id + ")", e);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		List<T> results = Collections.EMPTY_LIST;

		try {
			em.getTransaction().begin();

			Query query = em.createQuery("SELECT e FROM "
					+ getGenericClass().getSimpleName() + " e");
			results = query.getResultList();

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while getting result list for \""
					+ getGenericClass() + "\"", e);
		}

		return results;
	}

	public void delete(T item) {
		delete(item.getId());
	}

	public void delete(Long id) {
		try {
			T item = getById(id);

			if (null != item) {			
				em.getTransaction().begin();
				em.remove(item);
				em.getTransaction().commit();				
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while deleting by id: \"" + id + "\"", e);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<T> getGenericClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
