package org.sklsft.generator.skeletons.core.commands.database.oracle;

import java.io.File;
import java.io.IOException;

import org.sklsft.generator.bc.resolvers.DatabaseHandlerDiscovery;
import org.sklsft.generator.model.domain.database.Column;
import org.sklsft.generator.model.domain.database.Table;
import org.sklsft.generator.model.metadata.DataType;
import org.sklsft.generator.model.metadata.IdGeneratorType;
import org.sklsft.generator.skeletons.commands.impl.typed.SqlFileWriteCommand;
import org.sklsft.generator.skeletons.core.database.OracleHandler;

public class OracleTableDefinitionFileWriteCommand extends SqlFileWriteCommand {

	private Table table;
	private String sequenceName;
	

	/*
	 * constructor
	 */
	public OracleTableDefinitionFileWriteCommand(Table table) {

		super(table.myPackage.model.project.workspaceFolder + File.separator + DatabaseHandlerDiscovery.getBuildScriptFolder(OracleHandler.NAME) + File.separator + "1" + File.separator + table.myPackage.name.toUpperCase().replace(".", File.separator), table.originalName);

		this.table = table;
		this.sequenceName = table.name + "_id_seq";
	}

	@Override
	public void writeContent() throws IOException {
		
		createTable();
		
		if (table.myPackage.model.project.audited) {
			createAuditTable();
		}

		writeNotOverridableContent();

		skipLine();
	}

	private void createTable() {
		
		writeLine("-- create table --");
		writeLine("CREATE TABLE " + table.name);
		writeLine("(");
		write("ID " + getOracleType(table.idType));

		for (Column column:table.columns) {
			writeLine(",");
			write(column.name + " " + getOracleType(column.dataType));
			if (column.nullable) {
				write(" NULL");
			} else {
				write(" NOT NULL");
			}
		}

		writeLine(",");
		
		if (table.cardinality > 0) {
			write("CONSTRAINT UC_" + table.name + " UNIQUE (" + this.table.columns.get(0).name);
			for (int i = 1; i < this.table.cardinality; i++) {
				write("," + this.table.columns.get(i).name);
			}
			writeLine(")");
			write("USING INDEX (CREATE INDEX UC_" + table.name + " ON " + table.name + "(" + this.table.columns.get(0).name);
			for (int i = 1; i < this.table.cardinality; i++) {
				write("," + this.table.columns.get(i).name);
			}
			writeLine(")),"); // TABLESPACE " + table.myPackage.model.project.projectName.toUpperCase() + "_IND)");
		}
		writeLine("CONSTRAINT PK_" + table.name + " PRIMARY KEY (ID)");
		writeLine("USING INDEX (CREATE INDEX PK_" + table.name + " ON " + table.name + "(ID))"); // TABLESPACE " + table.myPackage.model.project.projectName.toUpperCase() + "_IND)");

		writeLine(")"); // TABLESPACE " + table.myPackage.model.project.projectName.toUpperCase() + "_TBL");
		writeLine("/");
		skipLine();

		if (table.idGeneratorType.equals(IdGeneratorType.SEQUENCE)) {
			writeLine("-- create sequence --");
			writeLine("CREATE SEQUENCE " + sequenceName + " MINVALUE 0 NOMAXVALUE START WITH 0 INCREMENT BY 1 NOCYCLE");
			writeLine("/");
			skipLine();
		}
	}
	
	/*
	 * create audit table
	 */
	private void createAuditTable()
    {
		
        writeLine("-- create audit table --");
        writeLine("CREATE TABLE " + table.name + "_AUD");
        writeLine("(");
        writeLine("ID int NOT NULL,");
        writeLine("REV int NOT NULL,");
        writeLine("REVTYPE smallint NOT NULL,");

        for (Column column:table.columns) {
            writeLine(column.name + " " + getOracleType(column.dataType) + " NULL,");
        }

        writeLine("CONSTRAINT PK_" + table.name + "_AUD PRIMARY KEY (ID, REV),");
        writeLine("CONSTRAINT FK_" + table.name + "_AUD FOREIGN KEY (REV)");
        writeLine("REFERENCES AUDITENTITY (ID)");
        writeLine(")"); // TABLESPACE " + table.myPackage.model.project.projectName.toUpperCase() + "_AUD");
        writeLine("/");
        skipLine();
        
        writeLine("CREATE INDEX FK_" + table.name + "_AUD ON " + this.table.name + "_AUD(REV)");
        writeLine("/");
        skipLine();
    }
	
	
	private String getOracleType(DataType type) {
		switch (type) {
			case TEXT:
				return "CLOB";
	
			case STRING:
				return "VARCHAR2(255)";
	
			case SHORT:
				return "NUMBER(5,0)";
				
			case INTEGER:
				return "NUMBER(10,0)";
			
			case LONG:
				return "NUMBER(19,0)";
	
			case DOUBLE:
				return "FLOAT(24)";
				
			case BIG_DECIMAL:
				return "NUMBER";
	
			case DATE:
				return "DATE";
			
			case DATETIME:
				return "TIMESTAMP";
	
			case BOOLEAN:
				return "NUMBER(1,0)";
	
			default:
				throw new IllegalArgumentException("Unhandled data type " + this);
		}
	}
}
