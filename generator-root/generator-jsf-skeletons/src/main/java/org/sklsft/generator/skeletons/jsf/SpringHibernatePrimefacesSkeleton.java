package org.sklsft.generator.skeletons.jsf;

import java.util.ArrayList;
import java.util.List;

import org.sklsft.generator.bc.resolvers.DatabaseHandlerDiscovery;
import org.sklsft.generator.model.domain.Project;
import org.sklsft.generator.skeletons.Skeleton;
import org.sklsft.generator.skeletons.core.layers.ApiLayer;
import org.sklsft.generator.skeletons.core.layers.BusinessComponentLayer;
import org.sklsft.generator.skeletons.core.layers.HibernateBusinessModelLayer;
import org.sklsft.generator.skeletons.core.layers.HibernateDaoLayer;
import org.sklsft.generator.skeletons.core.layers.JunitLayer;
import org.sklsft.generator.skeletons.core.layers.PopulatorLayer;
import org.sklsft.generator.skeletons.core.layers.ServiceLayer;
import org.sklsft.generator.skeletons.database.DatabaseHandler;
import org.sklsft.generator.skeletons.jsf.layers.JsfControllerLayer;
import org.sklsft.generator.skeletons.jsf.layers.JsfModelLayer;
import org.sklsft.generator.skeletons.jsf.layers.PrimefacesPresentationLayer;
import org.sklsft.generator.skeletons.jsf.layers.PrimefacesRootLayer;
import org.sklsft.generator.skeletons.layers.Layer;
import org.sklsft.generator.skeletons.rest.layers.SpringRestClientLayer;
import org.sklsft.generator.skeletons.rest.layers.SpringRestControllerLayer;


public class SpringHibernatePrimefacesSkeleton implements Skeleton {

	@Override
	public String getName() {
		return "SPRING_HIBERNATE_PRIMEFACES";
	}
	
	@Override
	public List<Layer> getLayers(Project project) {
		List<Layer> layers = new ArrayList<>();
	
		for (DatabaseHandler handler:DatabaseHandlerDiscovery.handlers) {
			layers.add(handler.getLayer());
		}
		layers.add(new PrimefacesRootLayer());
		layers.add(new ApiLayer());
		layers.add(new HibernateBusinessModelLayer());
		layers.add(new HibernateDaoLayer());
		layers.add(new BusinessComponentLayer());
		layers.add(new ServiceLayer());
		layers.add(new SpringRestControllerLayer());
		layers.add(new SpringRestClientLayer());
		layers.add(new JsfModelLayer());
		layers.add(new JsfControllerLayer());
		layers.add(new PrimefacesPresentationLayer());
		layers.add(new PopulatorLayer());
		layers.add(new JunitLayer());
		
		return layers;
	}	
}
