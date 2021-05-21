package org.sklsft.generator.skeletons.jsf.commands.root;

import org.sklsft.generator.model.domain.Project;
import org.sklsft.generator.model.metadata.files.FileType;
import org.sklsft.generator.skeletons.commands.impl.templatized.ProjectTemplatizedFileWriteCommand;

public class PrimefacesRootPomFileWriteCommand extends ProjectTemplatizedFileWriteCommand {

	public PrimefacesRootPomFileWriteCommand(Project project) {
		super(project.workspaceFolder, "pom", FileType.XML, project);
	}

}
