package be.gerard.general.service.dao;

import be.gerard.core.interface_v1.db.dao.DAO;
import be.gerard.general.service.model.ApplicationDetailRecord;

/**
 * ApplicationDAO
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface ApplicationDetailDAO extends DAO<ApplicationDetailRecord, Long> {

    ApplicationDetailRecord findByKey(String key);

}
