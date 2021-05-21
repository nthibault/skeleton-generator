package org.sklsft.generator.skeletons.jsf.commands.controller;

import java.io.File;
import java.io.IOException;

import org.sklsft.generator.model.domain.business.Bean;
import org.sklsft.generator.skeletons.commands.impl.typed.JavaFileWriteCommand;

public class JsfListControllerFileWriteCommand extends JavaFileWriteCommand {

	private Bean bean;

	public JsfListControllerFileWriteCommand(Bean bean) {
		super(bean.myPackage.model.project.workspaceFolder + File.separator + bean.myPackage.model.webappArtefactName + File.separator + bean.myPackage.model.javaSourcesFolder + File.separator
				+ bean.myPackage.listControllerPackageName.replace(".", File.separator), bean.listControllerClassName);

		this.bean = bean;
	}

	@Override
	protected void fetchSpecificImports() {
		
		javaImports.add("import org.springframework.stereotype.Component;");
        javaImports.add("import org.springframework.context.annotation.Scope;");
        javaImports.add("import org.springframework.web.context.WebApplicationContext;");
        javaImports.add("import " + this.bean.myPackage.baseListControllerPackageName + "." + this.bean.baseListControllerClassName + ";");
	}

	@Override
	protected void writeContent() throws IOException {

		writeLine("package " + this.bean.myPackage.listControllerPackageName + ";");
		skipLine();
        
        writeImports();
        skipLine();

        writeLine("/**");
        writeLine(" * auto generated list controller class file");
        writeLine(" * <br/>write modifications between specific code marks");
        writeLine(" * <br/>processed by skeleton-generator");
        writeLine(" */");
        writeLine("@Component");
        writeLine("@Scope(value=WebApplicationContext.SCOPE_REQUEST)");
        writeLine("public class " + this.bean.listControllerClassName + " extends  " + this.bean.baseListControllerClassName + " {");
        skipLine();

        writeNotOverridableContent();

        writeLine("}");
		
	}
}
