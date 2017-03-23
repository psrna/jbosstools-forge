/*******************************************************************************
 * Copyright (c) 2016-2017 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.forge.ui.bot.test.util;

/**
 * Scaffolding types enumeration
 * @author Jan Richter
 *
 */
public enum ScaffoldType {
	FACES("Faces"), ANGULARJS("AngularJS");

	private final String name;

	private ScaffoldType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
