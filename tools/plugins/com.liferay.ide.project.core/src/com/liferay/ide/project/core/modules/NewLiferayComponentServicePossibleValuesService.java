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

package com.liferay.ide.project.core.modules;

import com.liferay.ide.project.core.ProjectCore;
import com.liferay.ide.project.core.util.TargetPlatformUtil;

import java.util.Set;

import org.eclipse.sapphire.FilteredListener;
import org.eclipse.sapphire.Listener;
import org.eclipse.sapphire.PossibleValuesService;
import org.eclipse.sapphire.PropertyContentEvent;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.modeling.Status;

/**
 * @author Simon Jiang
 * @author Lovett Li
 */
public class NewLiferayComponentServicePossibleValuesService extends PossibleValuesService {

	@Override
	public void dispose() {
		NewLiferayComponentOp op = _op();

		if (_listener != null) {
			op.property(NewLiferayComponentOp.PROP_COMPONENT_CLASS_TEMPLATE_NAME).detach(_listener);

			_listener = null;
		}

		super.dispose();
	}

	@Override
	public Status problem(Value<?> value) {
		return Status.createOkStatus();
	}

	@Override
	protected void compute(Set<String> values) {
		NewLiferayComponentOp op = _op();

		IComponentTemplate<NewLiferayComponentOp> componentTemplate = op.getComponentClassTemplateName().content(true);

		String template = componentTemplate.getShortName();

		if (template.equals("ServiceHook")) {
			try {
				ServiceContainer allServicesWrapper = TargetPlatformUtil.getServiceWrapperList();

				values.addAll(allServicesWrapper.getServiceList());
			}
			catch (Exception e) {
				ProjectCore.logError("Get service wrapper list error.", e);
			}
		}
		else if (template.equals("service")) {
			try {
				ServiceContainer allServices = TargetPlatformUtil.getServicesList();

				values.addAll(allServices.getServiceList());
			}
			catch (Exception e) {
				ProjectCore.logError("Get services list error. ", e);
			}
		}
	}

	protected void initPossibleValuesService() {
		_listener = new FilteredListener<PropertyContentEvent>() {

			@Override
			protected void handleTypedEvent(PropertyContentEvent event) {
				refresh();
			}

		};

		NewLiferayComponentOp op = _op();

		op.property(NewLiferayComponentOp.PROP_COMPONENT_CLASS_TEMPLATE_NAME).attach(_listener);
	}

	private NewLiferayComponentOp _op() {
		return context(NewLiferayComponentOp.class);
	}

	private Listener _listener;

}