/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.ide.project.core.modules.fragment;

import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.modeling.ProgressMonitor;
import org.eclipse.sapphire.modeling.Status;
import org.eclipse.sapphire.modeling.annotations.DelegateImplementation;
import org.eclipse.sapphire.modeling.annotations.Derived;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.annotations.Service;

/**
 * @author Terry Jia
 */
public interface NewModuleFragmentFilesOp extends NewModuleFragmentOp {

	public ElementType TYPE = new ElementType(NewModuleFragmentFilesOp.class);

	@DelegateImplementation(NewModuleFragmentFilesOpMethods.class)
	@Override
	public Status execute(ProgressMonitor monitor);

	public Value<String> getHostOsgiBundle();

	public Value<String> getProjectName();

	public void setHostOsgiBundle(String value);

	public void setProjectName(String value);

	@Derived
	@Label(standard = "Host OSGi Bundle")
	@Required
	@Service(impl = HostOSGiBundleDefaultValueService.class)
	public ValueProperty PROP_HOST_OSGI_BUNDLE = new ValueProperty(TYPE, "HostOsgiBundle");

	@Label(standard = "project name")
	@Required
	@Service(impl = NewModuleFragmentProjectNameDefaultValueService.class)
	@Service(impl = NewModuleFragmentProjectNamePossibleService.class)
	@Service(impl = NewModuleFragmentProjectValidationService.class)
	public ValueProperty PROP_PROJECT_NAME = new ValueProperty(TYPE, "ProjectName");

}