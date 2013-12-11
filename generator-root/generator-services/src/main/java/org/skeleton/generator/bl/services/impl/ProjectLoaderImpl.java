package org.skeleton.generator.bl.services.impl;

import javax.annotation.Resource;

import org.skeleton.generator.bc.factory.model.interfaces.ProjectFactory;
import org.skeleton.generator.bl.services.interfaces.ProjectLoader;
import org.skeleton.generator.model.metadata.ProjectMetaData;
import org.skeleton.generator.model.om.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ProjectLoaderImpl implements ProjectLoader {

	/*
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProjectLoader.class);
	
	/*
	 * properties injected by spring
	 */
	@Resource(name="javaProjectFactory")
	ProjectFactory projectFactory;
	
	/*
	 * (non-Javadoc)
	 * @see com.skeleton.generator.bl.services.interfaces.ProjectLoader#loadProject(java.lang.String)
	 */
	@Override
	public Project loadProject(ProjectMetaData projectMetaData) {
		
		logger.info("start building project : " + projectMetaData.getProjectName());
		Project project = projectFactory.buildProject(projectMetaData);
		logger.info("end building project : " + projectMetaData.getProjectName());
		return project;
	}

	
}
