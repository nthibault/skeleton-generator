package org.sklsft.generator.repository.backup.datasource.interfaces;

import org.apache.commons.dbcp.BasicDataSource;
import org.sklsft.generator.exception.DataSourceNotFoundException;

/**
 * This class is used in your /data-model/datasource-context.xml<br/>
 * given a name, it gives a declared {@link BasicDataSource} that can be accessed to fecth data that will further be injected in your project datasource
 * @author Nicolas Thibault
 *
 */
public interface InputDataSourceProvider {

	public BasicDataSource getDataSource(String dataSourceName) throws DataSourceNotFoundException;

}
