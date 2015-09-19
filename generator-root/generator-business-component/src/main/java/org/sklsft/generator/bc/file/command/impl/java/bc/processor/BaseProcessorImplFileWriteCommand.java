package org.sklsft.generator.bc.file.command.impl.java.bc.processor;

import java.io.File;
import java.io.IOException;

import org.sklsft.generator.bc.file.command.impl.java.JavaFileWriteCommand;
import org.sklsft.generator.model.om.Bean;
import org.sklsft.generator.model.om.OneToManyComponent;
import org.sklsft.generator.model.om.UniqueComponent;

public class BaseProcessorImplFileWriteCommand extends JavaFileWriteCommand {

	private Bean bean;

	/*
	 * constructor
	 */
	public BaseProcessorImplFileWriteCommand(Bean bean) {
		super(bean.myPackage.model.project.workspaceFolder + File.separator + bean.myPackage.model.project.projectName + "-business-component\\src\\main\\java\\"
				+ bean.myPackage.baseProcessorImplPackageName.replace(".", "\\"), bean.baseProcessorClassName);

		this.bean = bean;
	}

	@Override
	protected void fetchSpecificImports() {

		javaImports.add("import org.springframework.beans.factory.annotation.Autowired;");
		javaImports.add("import " + this.bean.myPackage.omPackageName + "." + this.bean.className + ";");
        javaImports.add("import " + this.bean.myPackage.ovPackageName + "." + this.bean.viewClassName + ";");
        javaImports.add("import " + this.bean.myPackage.DAOInterfacePackageName + "." + this.bean.daoInterfaceName + ";");
        
        for (UniqueComponent uniqueComponent : this.bean.uniqueComponentList)
        {
            Bean currentBean = uniqueComponent.referenceBean;
            javaImports.add("import " + currentBean.myPackage.omPackageName + "." + currentBean.className + ";");
            javaImports.add("import " + currentBean.myPackage.ovPackageName + "." + currentBean.viewClassName + ";");
        }

        for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList)
        {
            Bean currentBean = oneToManyComponent.referenceBean;
            javaImports.add("import " + currentBean.myPackage.omPackageName + "." + currentBean.className + ";");
            javaImports.add("import " + currentBean.myPackage.ovPackageName + "." + currentBean.viewClassName + ";");
        }		
	}

	@Override
	protected void writeContent() throws IOException {

		writeLine("package " + this.bean.myPackage.baseProcessorImplPackageName + ";");
        skipLine();
        
        writeImports();
        skipLine();

        writeLine("/**");
        writeLine(" * auto generated base processor class file");
        writeLine(" * <br/>no modification should be done to this file");
		writeLine(" * <br/>processed by skeleton-generator");
        writeLine(" */");

        writeLine("public class " + this.bean.baseProcessorClassName + " {");
        skipLine();

        writeLine("/*"); 
        writeLine(" * properties injected by spring");
        writeLine(" */");
        
        writeLine("@Autowired");
        writeLine("protected " + this.bean.daoInterfaceName + " " + this.bean.daoObjectName + ";");
        skipLine();
        
        writeLine("/**");
        writeLine(" * process save");
        writeLine(" */");
        writeLine("public Long save(" + this.bean.className + " " + this.bean.objectName + ") {");
        writeLine("return " + this.bean.daoObjectName + ".save" + this.bean.className + "(" + this.bean.objectName + ");");
        writeLine("}");
        skipLine();

        for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList)
        {
        	Bean currentBean = oneToManyComponent.referenceBean;
        	writeLine("/**");
            writeLine(" * process save one to many component " + currentBean.className);
            writeLine(" */");
            writeLine("public void save" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + "," + this.bean.className + " " + this.bean.objectName + ") {");
            writeLine(this.bean.daoObjectName + ".save" + currentBean.className + "(" + this.bean.objectName + ", " + currentBean.objectName + ");");
            writeLine("}");
            skipLine();
        }

        writeLine("/**");
        writeLine(" * process update");
        writeLine(" */");
        writeLine("public void update(" + this.bean.className + " " + this.bean.objectName + ") {");
        writeLine("}");
        skipLine();

        for (UniqueComponent uniqueComponent : this.bean.uniqueComponentList)
        {
            Bean currentBean = uniqueComponent.referenceBean;
            writeLine("/**");
            writeLine(" * process update unique component " + currentBean.className);
            writeLine(" */");
            writeLine("public void update" + currentBean.className + "(" + this.bean.className + " " + this.bean.objectName + ") {");
            writeLine("}");
            skipLine();
        }

        for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList)
        {
            Bean currentBean = oneToManyComponent.referenceBean;
            writeLine("/**");
            writeLine(" * process update one to many component " + currentBean.className);
            writeLine(" */");
            writeLine("public void update" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + ") {");
            writeLine("}");
            skipLine();
        }

        writeLine("/**");
        writeLine(" * process delete");
        writeLine(" */");
        writeLine("public void delete(" + this.bean.className + " " + this.bean.objectName + ") {");
        writeLine(this.bean.daoObjectName + ".delete" + this.bean.className + "(" + this.bean.objectName + ");");
        writeLine("}");
        skipLine();

        for (OneToManyComponent oneToManyComponent : this.bean.oneToManyComponentList)
        {
            Bean currentBean = oneToManyComponent.referenceBean;
            writeLine("/**");
            writeLine(" * process delete one to many component " + currentBean.className);
            writeLine(" */");
            writeLine("public void delete" + currentBean.className + "(" + currentBean.className + " " + currentBean.objectName + ") {");
            writeLine(this.bean.daoObjectName + ".delete" + currentBean.className + "(" + currentBean.objectName + ");");
            writeLine("}");
            skipLine();
        }

        writeLine("}");

    }
}