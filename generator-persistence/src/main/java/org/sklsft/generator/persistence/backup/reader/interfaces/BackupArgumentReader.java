package org.sklsft.generator.persistence.backup.reader.interfaces;

import org.sklsft.generator.model.exception.ReadBackupFailureException;
import org.sklsft.generator.persistence.backup.reader.model.BackupArguments;

/**
 * This class provides a representation of Backup data through a List of object arrays<br/>
 * Depending on the implementation, the data will be fetched from a database query, a csv file, ...
 * @author Nicolas Thibault
 *
 */
public interface BackupArgumentReader {

	BackupArguments readBackupArgs(String backupFilePath) throws ReadBackupFailureException;
}
