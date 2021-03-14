package org.sklsft.generator.repository.backup.file.interfaces;

import java.io.IOException;

import org.sklsft.generator.exception.InvalidFileException;
import org.sklsft.generator.repository.backup.file.model.TextDelimitedFile;

/**
 * provides two methodes to :
 * <li>read a csv file as a list of string arrays
 * <li>write a csv file from a list of string arrays
 * @author Nicolas Thibault
 *
 */
public interface TextDelimitedFileParser {

	TextDelimitedFile readData(String filePath) throws IOException, InvalidFileException;
	
}
