package org.sklsft.generator.bc.file.command.impl.conf.test;

import java.io.File;

import org.sklsft.generator.bc.file.command.impl.templatized.ProjectTemplatizedFileWriteCommand;
import org.sklsft.generator.model.domain.Project;
import org.sklsft.generator.model.metadata.FileType;

public class JUnitDataInitializerFileWriteCommand extends ProjectTemplatizedFileWriteCommand {

	public JUnitDataInitializerFileWriteCommand(Project project) {
		super(project.workspaceFolder + File.separator + project.projectName + "-test/src/main/java/" + project.model.junitDataPackageName.replace(".", File.separator), "JUnitDataInitializer", FileType.JAVA, project);
	}

}
