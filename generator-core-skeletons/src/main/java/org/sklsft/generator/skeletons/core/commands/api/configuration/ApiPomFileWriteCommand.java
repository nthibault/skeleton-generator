package org.sklsft.generator.skeletons.core.commands.api.configuration;

import java.io.File;

import org.sklsft.generator.model.domain.Project;
import org.sklsft.generator.model.metadata.files.FileType;
import org.sklsft.generator.skeletons.commands.impl.templatized.ProjectTemplatizedFileWriteCommand;

public class ApiPomFileWriteCommand extends ProjectTemplatizedFileWriteCommand {

	public ApiPomFileWriteCommand(Project project) {
		super(project.workspaceFolder + File.separator + project.model.apiArtefactName, "pom", FileType.XML, project);
	}
}