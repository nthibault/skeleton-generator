package org.skeleton.generator.bc.command.file.impl.java.bc;

import java.io.File;
import java.io.IOException;

import org.skeleton.generator.bc.command.file.impl.java.JavaFileWriteCommand;
import org.skeleton.generator.model.om.Bean;

public class MapperImplFileWriteCommand extends JavaFileWriteCommand {

	private Bean bean;

	/*
	 * constructor
	 */
	public MapperImplFileWriteCommand(Bean bean) {
		super(bean.myPackage.model.project.workspaceFolder + File.separator + bean.myPackage.model.project.projectName + "-business-component\\src\\main\\java\\"
				+ bean.myPackage.mapperImplPackageName.replace(".", "\\"), bean.mapperClassName);

		this.bean = bean;
	}

	@Override
	protected void fetchSpecificImports() {

		javaImports.add("import org.springframework.stereotype.Component;");
		javaImports.add("import " + this.bean.myPackage.mapperInterfacePackageName + "." + this.bean.mapperInterfaceName + ";");
        javaImports.add("import " + this.bean.myPackage.baseMapperImplPackageName + "." + this.bean.baseMapperClassName + ";");
		
	}

	@Override
	protected void writeContent() throws IOException {

		writeLine("package " + this.bean.myPackage.mapperImplPackageName + ";");
        skipLine();
        
        writeImports();
        skipLine();

        writeLine("/**");
        writeLine(" * auto generated mapper class file");
        writeLine(" * <br/>write modifications between specific code marks");
        writeLine(" * <br/>processed by skeleton-generator");
        writeLine(" */");
        skipLine();

        writeLine("@Component");
        writeLine("public class " + this.bean.mapperClassName + " extends " + this.bean.baseMapperClassName + " implements " + bean.mapperInterfaceName + " {");

        this.writeNotOverridableContent();

        writeLine("}");
	}
}