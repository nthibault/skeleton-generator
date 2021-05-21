package org.sklsft.generator.skeletons.jsf.commands.presentation.primefaces;

import org.sklsft.generator.model.domain.business.Bean;
import org.sklsft.generator.model.domain.ui.ViewProperty;
import org.sklsft.generator.model.metadata.DataType;
import org.sklsft.generator.model.metadata.SelectionMode;
import org.sklsft.generator.skeletons.commands.impl.typed.XhtmlFileWriteCommand;

public abstract class PrimefacesXhtmlFileWriteCommand extends XhtmlFileWriteCommand {

	public PrimefacesXhtmlFileWriteCommand(String folderName, String fileName) {
		super(folderName, fileName);
	}

	protected void writeListComponent(ViewProperty property, Bean bean) {
		switch (property.dataType) {
		case BOOLEAN:
			if (property.nullable) {
				writeLine("<h:outputText value=\"#{"+ bean.objectName + "." + property.name + "}\"/>");
			} else {
				writeLine("<h:selectBooleanCheckbox value=\"#{" + bean.objectName + "." + property.name + "}\" disabled=\"true\"/>");
			}
			break;
			
		case DATE:
			writeLine("<h:outputText value=\"#{" + bean.objectName + "." + property.name + "}\" converter=\"localDateConverter\">");
			writeLine("</h:outputText>");

			break;

		case DATETIME:
			writeLine("<h:outputText value=\"#{" + bean.objectName + "." + property.name + "}\">");			
			writeLine("<f:convertDateTime pattern=\"yyyy-MM-dd HH:mm:ss\"/>");
			writeLine("</h:outputText>");

			break;

		case DOUBLE:
			writeLine("<h:outputText value=\"#{" + bean.objectName + "." + property.name + "}\">");
			writeLine("<f:convertNumber pattern=\"#,##0.########\"/>");
			writeLine("</h:outputText>");
			break;
			
		case BIG_DECIMAL:
			writeLine("<h:outputText value=\"#{" + bean.objectName + "." + property.name + "}\">");			
			writeLine("<f:convertNumber pattern=\"#,##0.########\"/>");
			writeLine("</h:outputText>");
			break;

		case SHORT:
		case INTEGER:
		case LONG:
			writeLine("<h:outputText value=\"#{" + bean.objectName + "." + property.name + "}\">");
			writeLine("<f:convertNumber pattern=\"#,##0\"/>");
			writeLine("</h:outputText>");
			break;

		case STRING:
			writeLine("<h:outputText value=\"#{" + bean.objectName + "." + property.name + "}\"/>");
			break;

		case TEXT:
			writeLine("<pre class=\"truncated-text\"");
			writeLine(" data-toggle=\"tooltip\"");
			writeLine(" data-html=\"true\"");
			writeLine(" data-title='&lt;pre&gt;#{" + bean.objectName + "." + property.name + "}&lt;/pre&gt;'");
			writeLine(" data-placement=\"bottom\"");
			writeLine(" data-trigger=\"click\">");
			writeLine("#{" + bean.objectName + "." + property.name + "}");
			writeLine("</pre>");
			break;
		}
	}
	
	
	protected void writeInput(ViewProperty property, Bean bean){
		writeInput("", property, bean);
	}
	
	protected void writeInput(String prefix, ViewProperty property, Bean bean){
		
		writeLine("<div class=\"col-xs-12\">");
		
		if (!property.dataType.equals(DataType.BOOLEAN)) {
            writeLine("<label>#{i18n." + bean.objectName + property.capName + "}</label>");
		}
		
		if (property.selectableBean != null) {
			if (property.selectableBean.selectionBehavior.selectionMode.equals(SelectionMode.DROPDOWN_OPTIONS)) {
				writeCombobox(prefix, property, bean);
			} else {
				writeAutocomplete(prefix, property, bean);
			}
		} else {
		
			switch (property.dataType) {
				case BOOLEAN:
					writeBooleanInput(prefix, property, bean);
					break;
				case DATE:
					writeDateInput(prefix, property, bean);
					break;
				case DATETIME:
					writeDateTimeInput(prefix, property, bean);
					break;
				case DOUBLE:
					writeDoubleInput(prefix, property, bean);
					break;
				case BIG_DECIMAL:
					writeBigDecimalInput(prefix, property, bean);
					break;
				case SHORT:
				case INTEGER:
				case LONG:
					writeLongInput(prefix, property, bean);
					break;
				case STRING:
					writeStringInput(prefix, property, bean);
					break;
				case TEXT:
					writeTextInput(prefix, property, bean);
					break;
			}
		}
		
		if (!property.dataType.equals(DataType.BOOLEAN)) {
			writeLine("<h:message for=\"" + prefix + bean.objectName + property.capName + "\" styleClass=\"detail-error-message\"/>");
		}
		
		writeLine("</div>");
        skipLine();
	}
	
	private void writeCombobox(String prefix, ViewProperty property, Bean bean){
		
		write("<h:selectOneMenu id=\"" + prefix + bean.objectName
				+ property.capName + "\" styleClass=\"form-control\" value=\"#{form." + property.name + "}\"");
				
		if (!property.editable) {
			write(" disabled=\"true\"");
		}
		
		writeLine(">");

		writeLine("<f:selectItem itemValue=\"#{null}\" itemLabel=\"\"/>");
		writeLine("<f:selectItems value=\"#{commonView." + property.selectableBean.objectName + "Options}\"");
		writeLine("var=\"option\" itemValue=\"#{option.key}\" itemLabel=\"#{option.label}\"/>");
		writeLine("</h:selectOneMenu>");
	}
	
	private void writeAutocomplete(String prefix, ViewProperty property, Bean bean){
		
		writeLine("<p:autoComplete id=\"" + prefix + bean.objectName
				+ property.capName + "\" styleClass=\"form-control\" value=\"#{form." + property.name + "}\"");
				
		if (!property.editable) {
			write(" disabled=\"true\"");
		}
		
		writeLine(" completeMethod=\"#{commonController.search" + property.selectableBean.className + "Options}\"/>");
	}
	
	
	private void writeStringInput(String prefix, ViewProperty property, Bean bean){
		write("<h:inputText id=\"" + prefix + bean.objectName
				+ property.capName + "\" styleClass=\"form-control\" value=\"#{form." + property.name + "}\"");
		
		if (!property.editable) {
			write(" disabled=\"true\"");
		}
		writeLine("/>");
	}
	
	private void writeTextInput(String prefix, ViewProperty property, Bean bean){
		write("<h:inputTextarea id=\"" + prefix + bean.objectName
				+ property.capName + "\" styleClass=\"form-control\" rows=\"10\" value=\"#{form." + property.name + "}\"");
		
		if (!property.editable) {
			write(" disabled=\"true\"");
		}
		writeLine("/>");
	}
	
	private void writeBooleanInput(String prefix, ViewProperty property, Bean bean){
		writeLine("<div class=\"checkbox\">");
		writeLine("<label>");
		writeLine("<h:selectBooleanCheckbox id=\"" + prefix 
				+ bean.objectName + property.capName + "\" value=\"#{form." + property.name + "}\"");
		if (property.editable) {
			writeLine("readonly=\"false\" disabled=\"false\"/>");			
		} else {
			writeLine("readonly=\"true\" disabled=\"true\"/>");
		}
		writeLine("#{i18n." + bean.objectName + property.capName + "}");
		writeLine("</label>");
		writeLine("</div>");
	}
	
	private void writeDoubleInput(String prefix, ViewProperty property, Bean bean){
		write("<h:inputText id=\"" + prefix + bean.objectName
				+ property.capName + "\" styleClass=\"form-control\" value=\"#{form."
				+ property.name + "}\"");
		
		if (!property.editable) {
			write(" disabled=\"true\"");
		}
		writeLine(">");		
		writeLine("<f:convertNumber pattern=\"#,##0.########\"/>");
		writeLine("</h:inputText>");
	}
	
	private void writeBigDecimalInput(String prefix, ViewProperty property, Bean bean){
		write("<h:inputText id=\"" + prefix + bean.objectName
				+ property.capName + "\" styleClass=\"form-control\" value=\"#{form."
				+ property.name + "}\"");
		
		if (!property.editable) {
			write(" disabled=\"true\"");
		}
		writeLine(">");		
		writeLine("<f:convertNumber pattern=\"#,##0.########\"/>");
		writeLine("</h:inputText>");
	}
	
	private void writeLongInput(String prefix, ViewProperty property, Bean bean){
		write("<h:inputText id=\"" + prefix + bean.objectName
				+ property.capName + "\" styleClass=\"form-control\" value=\"#{form." + property.name + "}\"");
				
		if (!property.editable) {
			skipLine();
			write("disabled=\"true\"");
		}
		
		writeLine(">");
		writeLine("<f:convertNumber integerOnly=\"true\" pattern=\"#,##0\"/>");
		writeLine("</h:inputText>");
	}
	
	private void writeDateInput(String prefix, ViewProperty property, Bean bean){
		writeLine("<p:calendar id=\"" + prefix + bean.objectName + property.capName +
				"\" class=\"form-control date-picker\" value=\"#{form." + property.name + "}\"");

		write(" pattern=\"yyyy-MM-dd\" mask=\"true\" navigator=\"true\"");
				
		if (!property.editable) {
			skipLine();
			write("disabled=\"true\"");
		}
		writeLine("/>");
		
	}
	
	private void writeDateTimeInput(String prefix, ViewProperty property, Bean bean){
		writeLine("<p:calendar id=\"" + prefix + bean.objectName + property.capName +
				"\" class=\"form-control date-picker\" value=\"#{form." + property.name + "}\"");

		write(" pattern=\"yyyy-MM-dd HH:mm:ss\" mask=\"true\" navigator=\"true\"");
				
		if (!property.editable) {
			skipLine();
			write("disabled=\"true\"");
		}
		writeLine("/>");
		
	}
	
	
	protected void writeFilter(ViewProperty property, Bean bean, Bean parentBean) {
		
		writeLine("<label>#{i18n." + bean.objectName + property.capName + "}</label>");
		
		String scrollForm = parentBean!=null?parentBean.detailViewObjectName + "." + bean.objectName + "ScrollForm":bean.listViewObjectName + ".scrollForm";
		String refreshMethod = parentBean!=null?parentBean.detailControllerObjectName + ".refresh" + bean.className + "List":bean.listControllerObjectName + ".refresh";
		
		switch (property.dataType) {
			case STRING:
			case TEXT:
				writeLine("<h:inputText");
				writeLine("value=\"#{" + scrollForm + ".filter." + property.name + "}\"");
				writeLine("styleClass=\"form-control\">");
				writeLine("<p:ajax event=\"keyup\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</h:inputText>");			
				break;
				
			case DATE:				
				writeLine("<p:calendar value=\"#{" + scrollForm + ".filter." + property.name + "MinValue}\"");
				writeLine("class=\"form-control date-picker\"");
				writeLine("pattern=\"yyyy-MM-dd\" mask=\"true\" navigator=\"true\">");
				writeLine("<p:ajax event=\"dateSelect\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</p:calendar>");
				writeLine("<p:calendar value=\"#{" + scrollForm + ".filter." + property.name + "MaxValue}\"");
				writeLine("class=\"form-control date-picker\"");
				writeLine("pattern=\"yyyy-MM-dd\" mask=\"true\" navigator=\"true\">");
				writeLine("<p:ajax event=\"dateSelect\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</p:calendar>");
				break;
				
			case DATETIME:				
				writeLine("<p:calendar value=\"#{" + scrollForm + ".filter." + property.name + "MinValue}\"");
				writeLine("class=\"form-control date-picker\"");
				writeLine("pattern=\"yyyy-MM-dd HH:mm:ss\" mask=\"true\" navigator=\"true\">");
				writeLine("<p:ajax event=\"dateSelect\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</p:calendar>");
				writeLine("<p:calendar value=\"#{" + scrollForm + ".filter." + property.name + "MaxValue}\"");
				writeLine("class=\"form-control date-picker\"");
				writeLine("pattern=\"yyyy-MM-dd HH:mm:ss\" mask=\"true\" navigator=\"true\">");
				writeLine("<p:ajax event=\"dateSelect\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</p:calendar>");
				break;
				
			case DOUBLE:
				
				writeLine("<h:inputText value=\"#{" + scrollForm + ".filter." + property.name + "MinValue}\"");
				writeLine("styleClass=\"form-control\">");
				writeLine("<p:ajax event=\"keyup\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</h:inputText>");
				
				writeLine("<h:inputText value=\"#{" + scrollForm + ".filter." + property.name + "MaxValue}\"");
				writeLine("styleClass=\"form-control\">");
				writeLine("<p:ajax event=\"keyup\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</h:inputText>");
				break;
				
			case BIG_DECIMAL:
				
				writeLine("<h:inputText value=\"#{" + scrollForm + ".filter." + property.name + "MinValue}\"");
				writeLine("styleClass=\"form-control\">");
				writeLine("<p:ajax event=\"keyup\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</h:inputText>");
				
				writeLine("<h:inputText value=\"#{" + scrollForm + ".filter." + property.name + "MaxValue}\"");
				writeLine("styleClass=\"form-control\">");
				writeLine("<p:ajax event=\"keyup\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</h:inputText>");
				break;
			
			case SHORT:
			case INTEGER:
			case LONG:
				writeLine("<h:inputText value=\"#{" + scrollForm + ".filter." + property.name + "MinValue}\"");
				writeLine("styleClass=\"form-control\">");
				writeLine("<f:convertNumber integerOnly=\"true\" pattern=\"#,##0\"/>");
				writeLine("<p:ajax event=\"keyup\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</h:inputText>");
				
				writeLine("<h:inputText value=\"#{" + scrollForm + ".filter." + property.name + "MaxValue}\"");
				writeLine("styleClass=\"form-control\">");
				writeLine("<f:convertNumber integerOnly=\"true\" pattern=\"#,##0\"/>");
				writeLine("<p:ajax event=\"keyup\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</h:inputText>");
				
				break;
				
			case BOOLEAN:
				
				writeLine("<h:selectOneMenu value=\"#{" + scrollForm + ".filter." + property.name + "}\" styleClass=\"form-control\">");
				writeLine("<f:selectItem itemLabel=\"\" itemValue=\"#{null}\"></f:selectItem>");
				writeLine("<f:selectItem itemLabel=\"#{i18n.trueLabel}\" itemValue=\"#{true}\"></f:selectItem>");
				writeLine("<f:selectItem itemLabel=\"#{i18n.falseLabel}\" itemValue=\"#{false}\"></f:selectItem>");
				writeLine("<p:ajax event=\"change\" update=\"resultsPanelGroup, sizePanelGroup\" listener=\"#{" + refreshMethod + "}\"/>");
				writeLine("</h:selectOneMenu>");
				
				break;

		}
	}
	
	protected void writeFilter(ViewProperty property, Bean bean) {
		writeFilter(property, bean, null);
	}
}
