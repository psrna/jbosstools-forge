/**
 * Copyright (c) Red Hat, Inc., contributors and others 2013 - 2014. All rights reserved
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.tools.forge.ui.internal.ext.control;

import org.jboss.forge.addon.ui.input.InputComponent;

/**
 * This component only renders any undefined input as a textbox.
 * 
 * 
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 * 
 */
public class FallbackTextBoxControlBuilder extends TextBoxControlBuilder {
	@Override
	public boolean handles(InputComponent<?, ?> input) {
		return true;
	}
}
