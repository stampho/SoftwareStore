package hu.sed.prf.softwarestore.controller.core;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;

public abstract class AbstractEntityAction<EntityType, ID extends Serializable>
		implements Serializable {

	private static final long serialVersionUID = -4887946822448840701L;

	@Inject
	private Logger logger;

	private ID id;

	private EntityType entity;

	private Class<EntityType> entityClass;

	public AbstractEntityAction() {
		// Nothing to do here.
	}

	public AbstractEntityAction(Class<EntityType> entityClass) {
		this.entityClass = entityClass;
	}

	public void load() {
		if (null == id) {
			try {
				entity = entityClass.newInstance();
				afterCreation();
			} catch (InstantiationException e) {
				logger.severe("Cannot instantiate entity.");
			} catch (IllegalAccessException e) {
				logger.severe("Illegal access during instantiation.");
			}
		} else {
			entity = getEntityDao().findEntity(id);
		}
	}

	protected void afterCreation() {
		// Nothing to do here.
	}

	public String persist() {
		getEntityDao().saveOrUpdate(getEntity());
		getEntityDao().flush();
		return getNavigationTargetAfterPersist();
	}

	protected void beforeDeletion(EntityType entityToDelete) {
		// Nothing to do here.
	}

	public void delete(ID identifier) {
		EntityType entityToDelete = getEntityDao().findEntity(identifier);
		beforeDeletion(entityToDelete);
		getEntityDao().delete(entityToDelete);
		getEntityDao().flush();
	}

	protected abstract AbstractGenericDAO<EntityType, ID> getEntityDao();

	protected abstract String getNavigationTargetAfterPersist();

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public EntityType getEntity() {
		return entity;
	}

	public void loadById(ID id) {
		setId(id);
		load();
	}

	public void reset() {
		this.entity = null;
		load();
	}

}

