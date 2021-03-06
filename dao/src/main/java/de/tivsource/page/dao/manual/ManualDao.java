/**
 * 
 */
package de.tivsource.page.dao.manual;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tivsource.page.entity.manual.Manual;

/**
 * @author Marc Michele
 *
 */
@Stateless
public class ManualDao implements ManualDaoLocal {

    /*
     * Statischer Logger der Klasse.
     */
    private static final Logger LOGGER = LogManager.getLogger(ManualDao.class);

    /*
     * EntityManager der Dao Klasse.
     */
    @PersistenceContext
    private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see de.tivsource.page.dao.manual.ManualDaoLocal#save(de.tivsource.page.entity.manual.Manual)
	 */
	@Override
	public void save(Manual manual) {
        LOGGER.info("save(Manual manual) aufgerufen");
        entityManager.persist(manual);
	}

	/* (non-Javadoc)
	 * @see de.tivsource.page.dao.manual.ManualDaoLocal#merge(de.tivsource.page.entity.manual.Manual)
	 */
	@Override
	public void merge(Manual manual) {
        LOGGER.info("merge(Manual manual) aufgerufen");
        entityManager.merge(manual);
	}

	/* (non-Javadoc)
	 * @see de.tivsource.page.dao.manual.ManualDaoLocal#delete(de.tivsource.page.entity.manual.Manual)
	 */
	@Override
	public void delete(Manual manual) {
		entityManager.remove(entityManager.find(Manual.class, manual.getUuid()));
	}

	/* (non-Javadoc)
	 * @see de.tivsource.page.dao.manual.ManualDaoLocal#isManualUrl(java.lang.String)
	 */
	@Override
	public Boolean isManualUuid(String uuid) {
        Query query = entityManager.createQuery("select m from Manual m where m.uuid = ?1 and m.visible = 'Y' order by m.uuid asc");
        query.setParameter("1", uuid);
        return (query.getResultList().size() > 0 ? true : false);
	}

	/* (non-Javadoc)
	 * @see de.tivsource.page.dao.manual.ManualDaoLocal#findByUuid(java.lang.String)
	 */
	@Override
	public Manual findByUuid(String uuid) {
		return entityManager.find(Manual.class, uuid);
	}

	/* (non-Javadoc)
	 * @see de.tivsource.page.dao.manual.ManualDaoLocal#findAll(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Manual> findAll(Integer start, Integer max) {
        Query query = entityManager.createQuery("from Manual m");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see de.tivsource.page.dao.manual.ManualDaoLocal#findAll(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Manual> findAll(Integer start, Integer max, String field, String order) {
        String queryString = "SELECT DISTINCT m FROM Manual m JOIN m.descriptionMap dm WHERE dm.language = 'DE' ORDER BY ";
        queryString = queryString + field + " " + order;
        Query query = entityManager.createQuery(queryString);
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see de.tivsource.page.dao.manual.ManualDaoLocal#countAll()
	 */
	@Override
	public Integer countAll() {
        Query query = entityManager.createQuery("Select Count(m) from Manual m");
        return Integer.parseInt(query.getSingleResult().toString());
	}

}// Ende class
