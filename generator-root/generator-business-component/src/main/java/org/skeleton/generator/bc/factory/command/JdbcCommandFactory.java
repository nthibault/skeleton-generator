package org.skeleton.generator.bc.factory.command;

import javax.sql.DataSource;

import org.skeleton.generator.model.om.Table;
import org.skeleton.generator.repository.dao.jdbc.interfaces.JdbcCommand;

public interface JdbcCommandFactory {

	JdbcCommand getCommand(DataSource dataSource, Table table, Object[] args);

}