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

package com.liferay.blade.util;

import com.liferay.blade.api.ProgressMonitor;

/**
 * @author Gregory Amerson
 */
public class NullProgressMonitor implements ProgressMonitor {

	@Override
	public void beginTask(String taskName, int totalWork) {
	}

	@Override
	public void done() {
	}

	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void setTaskName(String taskName) {
	}

	@Override
	public void worked(int work) {
	}

}