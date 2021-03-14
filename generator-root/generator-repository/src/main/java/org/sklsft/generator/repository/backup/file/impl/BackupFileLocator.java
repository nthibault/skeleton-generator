package org.sklsft.generator.repository.backup.file.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sklsft.generator.model.domain.database.Table;
import org.sklsft.generator.model.metadata.PersistenceMode;
import org.springframework.stereotype.Component;

@Component
public class BackupFileLocator {

	public PersistenceMode resolvePersistenceModeOrNull(String backupPath, int step, Table table) {
		if (existsFileForType(backupPath, step, table, PersistenceMode.CMD)) {
			return PersistenceMode.CMD;
		} else if (existsFileForType(backupPath, step, table, PersistenceMode.XML)) {
			return PersistenceMode.XML;
		} else if (existsFileForType(backupPath, step, table, PersistenceMode.CSV)) {
			return PersistenceMode.CSV;
		} else if (existsFileForType(backupPath, step, table, PersistenceMode.TXT)) {
			return PersistenceMode.TXT;
		} else {
			return null;
		}
	}

	public String getBackupFilePath(String backupPath, int step, Table table, PersistenceMode mode) {
		return getPathPrefix(backupPath, step, table) + mode.getExtension();
	}

	
	public boolean existsFileForTable(String backupPath, int maxStep, Table table) {
		for (int step = 1; step <= maxStep; step++) {
			for (PersistenceMode mode : PersistenceMode.values()) {
				if (existsFileForType(backupPath, step, table, mode)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean existsFileForType(String backupPath, int step, Table table, PersistenceMode type) {

		String backupFilePath = getBackupFilePath(backupPath, step, table, type);
		Path path = Paths.get(backupFilePath);

		return Files.exists(path);
	}

	private String getPathPrefix(String backupPath, int step, Table table) {
		return backupPath + File.separator + step + File.separator
				+ table.myPackage.name.toUpperCase().replace(".", File.separator) + File.separator + table.originalName;
	}
}
