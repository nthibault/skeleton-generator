package org.sklsft.generator.model.util.folder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FolderUtil {

	public static int resolveMaxStep(String rootPath) {
		int maxStep = 0;
		while (Files.exists(Paths.get(rootPath + File.separator + String.valueOf(maxStep+1)))) {
			maxStep++;
		}
		return maxStep;
	}
	
}
