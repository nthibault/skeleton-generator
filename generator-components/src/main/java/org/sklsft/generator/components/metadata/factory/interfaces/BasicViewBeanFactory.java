package org.sklsft.generator.components.metadata.factory.interfaces;

import org.sklsft.generator.model.domain.business.Bean;
import org.sklsft.generator.model.domain.business.OneToMany;
import org.sklsft.generator.model.domain.ui.BasicViewBean;

public interface BasicViewBeanFactory {

	BasicViewBean getBasicViewBean(Bean bean);
	
	BasicViewBean getBasicViewBean(OneToMany oneToMany);
}
