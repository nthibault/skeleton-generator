package org.sklsft.generator.bc.build;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.sklsft.generator.bc.resolvers.DatabaseHandlerDiscovery;
import org.sklsft.generator.exception.InvalidFileException;
import org.sklsft.generator.model.domain.database.Table;
import org.sklsft.generator.repository.backup.file.impl.SimpleScriptFileReaderImpl;
import org.sklsft.generator.repository.backup.file.interfaces.SimpleScriptFileReader;
import org.sklsft.generator.repository.build.JdbcRawCommand;


public class TableBuilder {

	/*
	 * properties
	 */
	private Table table;
	private DataSource dataSource;
	private SimpleScriptFileReader scriptFileReader;
	private int step;
	
	/*
	 * constructor
	 */
	public TableBuilder(Table table, DataSource dataSource, int step) {
		this.table = table;
		this.dataSource = dataSource;
		this.scriptFileReader = new SimpleScriptFileReaderImpl();
		this.step = step;
	}
	

	public void buildTable() throws IOException, InvalidFileException, SQLException {
		
		String scriptFilePath = table.myPackage.model.project.workspaceFolder + File.separator + DatabaseHandlerDiscovery.getBuildScriptFolder(table.myPackage.model.project.databaseEngine) + File.separator + step + File.separator + table.myPackage.name.toUpperCase().replace(".", File.separator) + File.separator + table.originalName + ".sql";
		
		String script = scriptFileReader.readScript(scriptFilePath);
			
		new JdbcRawCommand(dataSource, script).execute();
	}
}
