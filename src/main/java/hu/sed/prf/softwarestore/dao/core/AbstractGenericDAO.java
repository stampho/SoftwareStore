package hu.sed.prf.softwarestore.dao.core;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

/**
 * <p>
 * This class represents the generic hibernate data access object.
 * </p>
 * <p>
 * https://code.google.com/p/hibernate-generic-dao/
 * </p>
 * 
 * @param <EntityType> the persistent class type
 * @param <ID> the identify type to get many query
 */
@SuppressWarnings("unchecked")
public abstract class AbstractGenericDAO<EntityType, ID extends Serializable> implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/** The persistent class. */
	private final Class<EntityType> persistentClass;

	/** The Hibernate session. */
	private Session session;

	/** The entity manager. */
	@Inject
	private EntityManager entityManager;

	/**
	 * @param persistentClass set the persistentClass
	 */
	protected AbstractGenericDAO(final Class<EntityType> persistentClass) {
		this(persistentClass, null);
	}

	/**
	 * The constructor set the persistent class and the session.
	 * 
	 * @param persistentClass set the persistentClass
	 * @param session set the session
	 */
	protected AbstractGenericDAO(final Class<EntityType> persistentClass, final Session session) {
		this.persistentClass = persistentClass;
		this.session = session;
	}

	/**
	 * @return the hibernate session, generally belongs to the entity manager.
	 */
	protected Session getSession() {

		// If no entity manager, we want to use the manually set session.
		if (session != null) {
			return session;
		}

		// If the entity manager is used, necessary to get the current session.
		final Session delegatedSession = (Session) entityManager.getDelegate();

		// Throws an exception if no session is set.
		if (delegatedSession == null) {
			throw new IllegalStateException("Session has not been set on DAO before usage");
		}
		return delegatedSession;
	}

	/**
	 * @return the entityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @return the persistent class
	 */
	public Class<EntityType> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * Find an entity by id.
	 * 
	 * @param identifier by identifier
	 * @return the entity if it exists, null if not
	 */
	public EntityType findEntity(final ID identifier) {
		return (EntityType) getSession().get(getPersistentClass(), identifier);
	}

	/**
	 * Use carefully. This method returns all the items.
	 * 
	 * @return list of elements
	 */
	public List<EntityType> list() {
		return getSession().createCriteria(getPersistentClass()).list();
	}

	/**
	 * Make persistent a T type entity.
	 * 
	 * @param entity the entity
	 * @return the persisted entity
	 */
	public EntityType saveOrUpdate(final EntityType entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * Save an entity immediately into to the data store, no matter its in or out of the transaction.
	 * 
	 * @param entity the entity be saved
	 * @return the saved entity identify
	 */
	public ID save(final EntityType entity) {
		return (ID) this.getSession().save(entity);
	}

	/**
	 * Make persistent an entity if it wasn't and update immediately.
	 * 
	 * @param entity the entity be updated
	 */
	public void update(final EntityType entity) {
		this.getSession().update(entity);
	}

	/**
	 * Make a transient instance persistent.
	 * 
	 * @param entity a transient instance to be made persistent
	 */
	public void persist(final EntityType entity) {
		this.getSession().persist(entity);
	}

	/**
	 * Copy the state of the given object onto the persistent object with the same identifier. If there is no persistent instance currently
	 * associated with the session, it will be loaded. Return the persistent instance. If the given instance is unsaved, save a copy of and
	 * return it as a newly persistent instance. The given instance does not become associated with the session
	 * 
	 * @param entity a detached instance with state to be copied
	 * @return an updated persistent instance
	 */
	public EntityType merge(final EntityType entity) {
		return (EntityType) this.getSession().merge(entity);
	}

	/**
	 * Delete an entity from the data store.
	 * 
	 * @param entity the entity will be deleted
	 */
	public void delete(final EntityType entity) {
		getSession().delete(entity);
	}

	/**
	 * This method says that the entity is session contained or not.
	 * 
	 * @param entity the entity will be queried
	 * @return true if the entity is session contained
	 */
	public boolean isSessionContained(final EntityType entity) {
		return this.getSession().contains(entity);
	}

	/**
	 * Force this hibernate session to flush. Must be called at the end of a unit of work, before committing the transaction and closing the
	 * session.
	 */
	public void flush() {
		getSession().flush();
	}

	/**
	 * Completely clear the hibernate session.
	 */
	public void clear() {
		getSession().clear();
	}

	/**
	 * Remove this instance from the session cache. Changes to the instance will not be synchronized with the database. This operation
	 * cascades to associated instances if the association is mapped with cascade="evict".
	 * 
	 * @param entity a persistent instance
	 */
	public void evict(final EntityType entity) {
		getSession().evict(entity);
	}

	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement long-running
	 * sessions that span many business tasks.
	 * 
	 * @param entity - the entity
	 */
	public void refresh(final EntityType entity) {
		getSession().refresh(entity);
	}

	/**
	 * Retrieve the <b>reference</b> for a given ID - so no query is executed. Use the entity manager, so if the dao is initialized with
	 * session it throw a {@link NullPointerException}
	 * 
	 * @param identifier - type is ID
	 * @return null if there is no such element otherwise the reference
	 */
	public EntityType getReference(final ID identifier) {
		return getEntityManager().getReference(getPersistentClass(), identifier);
	}

	/**
	 * Retrieves the identifier value of specified entity.
	 * 
	 * @param entity for get the ID
	 * @return identifier value of entity
	 */
	public ID getEntityIdentifier(final EntityType entity) {
		return (ID) this.getSession().getIdentifier(entity);
	}

}