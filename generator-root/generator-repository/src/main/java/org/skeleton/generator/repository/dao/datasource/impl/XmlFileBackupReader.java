package org.skeleton.generator.repository.dao.datasource.impl;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.skeleton.generator.exception.DataSourceNotFoundException;
import org.skeleton.generator.exception.ReadBackupFailureException;
import org.skeleton.generator.model.om.Table;
import org.skeleton.generator.repository.dao.datasource.interfaces.BackupReader;
import org.skeleton.generator.repository.dao.datasource.interfaces.InputSourceProvider;


public class XmlFileBackupReader implements BackupReader {
	
	/*
	 * properties
	 */
	private String backupFilePath;
	private Table table;
	private XmlFileSourceAndScriptReader xmlFileScriptAndSourceReader;
	private InputSourceProvider inputSourceProvider;
	private SourceAndScriptBackupReader sourceAndScriptBackupReader;
	
	/*
	 * constructor
	 */
	public XmlFileBackupReader(Table table, String backupFilePath, InputSourceProvider inputSourceProvider) {
		this.table = table;
		this.backupFilePath = backupFilePath;
		this.xmlFileScriptAndSourceReader = new XmlFileSourceAndScriptReader();
		this.inputSourceProvider = inputSourceProvider;
	}

	@Override
	public List<Object[]> readBackupArgs() throws ReadBackupFailureException {

		SourceAndScript sourceAndScript;
		DataSource inputSource;
		
		try {
			sourceAndScript = xmlFileScriptAndSourceReader.readScript(backupFilePath);
		} catch (IOException e) {
			throw new ReadBackupFailureException("Failed to read source and script for table : " + table.name,e);
		}
	
		try {
			inputSource = inputSourceProvider.getDataSource(sourceAndScript.getSource());
		} catch (DataSourceNotFoundException e) {
			throw new ReadBackupFailureException("Invalid backup source for table : " + table.name, e);			
		}
		
		sourceAndScriptBackupReader = new SourceAndScriptBackupReader(table, sourceAndScript.getScript(), inputSource);
		
		return sourceAndScriptBackupReader.readBackupArgs();

	}

}