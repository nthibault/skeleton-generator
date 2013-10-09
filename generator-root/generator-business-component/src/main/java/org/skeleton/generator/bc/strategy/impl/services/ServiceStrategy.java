package org.skeleton.generator.bc.strategy.impl.services;

import org.skeleton.generator.bc.command.file.impl.java.services.BaseServiceImplFileWriteCommand;
import org.skeleton.generator.bc.command.file.impl.java.services.BaseServiceInterfaceFileWriteCommand;
import org.skeleton.generator.bc.command.file.impl.java.services.ServiceImplFileWriteCommand;
import org.skeleton.generator.bc.command.file.impl.java.services.ServiceInterfaceFileWriteCommand;
import org.skeleton.generator.bc.executor.FileWriteCommandTreeNode;
import org.skeleton.generator.bc.strategy.interfaces.LayerStrategy;
import org.skeleton.generator.model.om.Bean;
import org.skeleton.generator.model.om.Package;
import org.skeleton.generator.model.om.Project;

public class ServiceStrategy implements LayerStrategy {

	@Override
	public FileWriteCommandTreeNode getLayerNode(Project project) {

		FileWriteCommandTreeNode serviceLayerTreeNode = new FileWriteCommandTreeNode("Services Layer");

		FileWriteCommandTreeNode baseServiceTreeNode = new FileWriteCommandTreeNode("Base Services");
		serviceLayerTreeNode.add(baseServiceTreeNode);

		for (Package myPackage : project.model.packageList) {
			FileWriteCommandTreeNode packageTreeNode = new FileWriteCommandTreeNode(myPackage.name);
			baseServiceTreeNode.add(packageTreeNode);

			FileWriteCommandTreeNode interfacesTreeNode = new FileWriteCommandTreeNode("interfaces");
			packageTreeNode.add(interfacesTreeNode);
			
			for (Bean bean : myPackage.beanList) {
				if (!bean.isComponent) {
					FileWriteCommandTreeNode beanTreeNode = new FileWriteCommandTreeNode(new BaseServiceInterfaceFileWriteCommand(bean), bean.baseDaoInterfaceName);
					interfacesTreeNode.add(beanTreeNode);
				}
			}

			FileWriteCommandTreeNode implTreeNode = new FileWriteCommandTreeNode("impl");
			packageTreeNode.add(implTreeNode);
			
			for (Bean bean : myPackage.beanList) {
				if (!bean.isComponent) {
					FileWriteCommandTreeNode beanTreeNode = new FileWriteCommandTreeNode(new BaseServiceImplFileWriteCommand(bean), bean.baseDaoClassName);
					implTreeNode.add(beanTreeNode);
				}
			}
		}

		FileWriteCommandTreeNode serviceTreeNode = new FileWriteCommandTreeNode("Services");
		serviceLayerTreeNode.add(serviceTreeNode);

		for (Package myPackage : project.model.packageList) {
			FileWriteCommandTreeNode packageTreeNode = new FileWriteCommandTreeNode(myPackage.name);
			baseServiceTreeNode.add(packageTreeNode);

			FileWriteCommandTreeNode interfacesTreeNode = new FileWriteCommandTreeNode("interfaces");
			packageTreeNode.add(interfacesTreeNode);

			for (Bean bean : myPackage.beanList) {
				if (!bean.isComponent) {
					FileWriteCommandTreeNode beanTreeNode = new FileWriteCommandTreeNode(new ServiceInterfaceFileWriteCommand(bean), bean.daoInterfaceName);
					interfacesTreeNode.add(beanTreeNode);
				}
			}

			FileWriteCommandTreeNode implTreeNode = new FileWriteCommandTreeNode("impl");
			packageTreeNode.add(implTreeNode);
			
			for (Bean bean : myPackage.beanList) {
				if (!bean.isComponent) {
					FileWriteCommandTreeNode beanTreeNode = new FileWriteCommandTreeNode(new ServiceImplFileWriteCommand(bean), bean.daoClassName);
					implTreeNode.add(beanTreeNode);
				}
			}
		}
		
		return serviceLayerTreeNode;
	}
}