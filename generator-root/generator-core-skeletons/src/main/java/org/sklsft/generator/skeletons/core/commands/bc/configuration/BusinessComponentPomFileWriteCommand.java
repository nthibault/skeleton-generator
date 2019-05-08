package org.sklsft.generator.skeletons.core.commands.bc.configuration;

import java.io.File;

import org.sklsft.generator.model.domain.Project;
import org.sklsft.generator.model.metadata.files.FileType;
import org.sklsft.generator.skeletons.commands.impl.templatized.ProjectTemplatizedFileWriteCommand;

public class BusinessComponentPomFileWriteCommand extends ProjectTemplatizedFileWriteCommand {

	public BusinessComponentPomFileWriteCommand(Project project) {
		super(project.workspaceFolder + File.separator + project.projectName + "-business-component", "pom", FileType.XML, project);
	}

}
