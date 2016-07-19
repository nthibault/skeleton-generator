package org.sklsft.generator.bc.file.command.impl.java.model;

import java.io.File;
import java.io.IOException;

import org.sklsft.generator.bc.file.command.impl.java.JavaFileWriteCommand;
import org.sklsft.generator.model.domain.business.Bean;
import org.sklsft.generator.model.domain.business.Property;


public class FullViewBeanFileWriteCommand extends JavaFileWriteCommand {

	private Bean bean;
	/*
	 * constructor
	 */
	public FullViewBeanFileWriteCommand(Bean bean) {
        
		super(bean.myPackage.model.project.workspaceFolder + File.separator + bean.myPackage.model.project.projectName + "-api" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + bean.myPackage.ovPackageName.replace(".",File.separator), bean.fullViewBean.className);
		
		this.bean = bean;
		
	}
	
	@Override
	protected void fetchSpecificImports() {
		javaImports.add("import java.util.Date;");
        javaImports.add("import java.io.Serializable;");
        javaImports.add("import javax.validation.constraints.NotNull;");
	}
	
	
	@Override
	protected void writeContent() throws IOException {

		writeLine("package " + bean.myPackage.ovPackageName + ";");
        skipLine();

        writeImports();
        skipLine();

        writeLine("/**");
        writeLine(" * auto generated view bean class file");
        writeLine(" * <br/>basic representation of what is going to be considered as model in MVC patterns");
        writeLine(" * <br/>write modifications between specific code marks");
        writeLine(" * <br/>processed by skeleton-generator");
        writeLine(" */");
        writeLine("public class " + this.bean.fullViewBean.className + " implements Serializable {");
        skipLine();

        writeLine("private static final long serialVersionUID = 1L;");
        skipLine();

        createProperties();
        createGettersAndSetters();
        writeNotOverridableContent();
        
        writeLine("}");

    }

    private void createProperties()
    {
        writeLine("/*");
        writeLine(" * properties");
        writeLine(" */");
        writeLine("private Long id;");

        for (Property property:this.bean.fullViewBean.properties) {
        	if (!property.nullable) {
        		writeLine("@NotNull");
//        		if (property.dataType.equals(DataType.STRING) || property.dataType.equals(DataType.TEXT)) {
//        			writeLine("@NotEmpty");
//        		}
        	}
        	writeLine("private " + property.beanDataType + " " + property.name + ";");
        }
        skipLine();

    }

    private void createGettersAndSetters()
    {
        writeLine("/*");
        writeLine(" * getters and setters");
        writeLine(" */");
        writeLine("public Long getId() {");
        writeLine("return this.id;");
        writeLine("}");
        skipLine();
        
        writeLine("public void setId(Long id) {");
        writeLine("this.id = id;");
        writeLine("}");
        skipLine();

        for (Property property:this.bean.fullViewBean.properties) {
            writeLine("public " + property.beanDataType + " get" + property.capName + "() {");
            writeLine("return this." + property.name + ";");
            writeLine("}");
            skipLine();
            
            writeLine("public void set" + property.capName + "(" + property.beanDataType + " " + property.name + ") {");
            writeLine("this." + property.name + " = " + property.name + ";");
            writeLine("}");
            skipLine();
        }
        skipLine();
	}
}