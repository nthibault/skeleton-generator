package org.skeleton.generator.bl.services.impl;

import org.skeleton.generator.bl.services.interfaces.ProjectLoader;
import org.skeleton.generator.bl.services.interfaces.ProjectMetaDataService;
import org.skeleton.generator.exception.ConfigurationReadException;
import org.skeleton.generator.model.metadata.ColumnMetaData;
import org.skeleton.generator.model.metadata.PackageMetaData;
import org.skeleton.generator.model.metadata.ProjectMetaData;
import org.skeleton.generator.model.metadata.TableMetaData;
import org.skeleton.generator.repository.dao.metadata.interfaces.ProjectMetaDataDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMetaDataServiceImpl implements ProjectMetaDataService {

	/*
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProjectLoader.class);

	/*
	 * properties injected by spring
	 */
	@Autowired
	ProjectMetaDataDao projectMetaDataDao;

	public ProjectMetaData loadProjectMetaData(String folderPath) throws ConfigurationReadException{
		logger.info("start reading meta data");
		ProjectMetaData projectMetaData = projectMetaDataDao.loadProjectMetaData(folderPath);
		logger.info("end reading meta data");
		
		return projectMetaData;
	}
	
	

	@Override
	public void insertPackageMetaData(PackageMetaData packageMetaData, int index, ProjectMetaData projectMetaData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertTableMetaData(TableMetaData tableMetaData, int index, PackageMetaData packageMetaData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertColumnMetaData(ColumnMetaData columnMetaData, int index, TableMetaData tableMetaData) {
		// TODO Auto-generated method stub
		
	}
}
