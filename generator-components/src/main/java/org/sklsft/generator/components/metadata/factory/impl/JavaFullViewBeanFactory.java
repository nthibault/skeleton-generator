package org.sklsft.generator.components.metadata.factory.impl;

import org.sklsft.generator.components.metadata.factory.interfaces.FullViewBeanFactory;
import org.sklsft.generator.model.domain.business.Bean;
import org.sklsft.generator.model.domain.business.OneToMany;
import org.sklsft.generator.model.domain.ui.FullViewBean;
import org.springframework.stereotype.Component;

@Component("javaFullViewBeanFactory")
public class JavaFullViewBeanFactory implements FullViewBeanFactory {

	@Override
	public FullViewBean getFullViewBean(Bean bean) {
				
		FullViewBean fullViewBean = new FullViewBean();
		
		fullViewBean.className = bean.className + "FullView";
		fullViewBean.objectName = bean.objectName + "FullView";
		
		fullViewBean.baseMapperClassName = fullViewBean.className + "BaseMapper";
		fullViewBean.mapperClassName = fullViewBean.className + "Mapper";
		fullViewBean.mapperObjectName = fullViewBean.objectName + "Mapper";
		
		return fullViewBean;
	}
	
	
	@Override
	public FullViewBean getFullViewBean(OneToMany oneToMany) {
		
		FullViewBean fullViewBean = new FullViewBean();
		
		Bean bean = oneToMany.referenceBean;
		
		fullViewBean.className = bean.className + "FullView";
		fullViewBean.objectName = bean.objectName + "FullView";
		
		fullViewBean.baseMapperClassName = fullViewBean.className + "BaseMapper";
		fullViewBean.mapperClassName = fullViewBean.className + "Mapper";
		fullViewBean.mapperObjectName = fullViewBean.objectName + "Mapper";
		
		return fullViewBean;
	}
}
