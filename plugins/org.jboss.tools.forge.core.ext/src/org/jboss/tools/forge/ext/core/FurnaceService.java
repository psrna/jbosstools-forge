/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.tools.forge.ext.core;

import org.jboss.forge.addon.convert.ConverterFactory;
import org.jboss.forge.furnace.ContainerStatus;
import org.jboss.forge.furnace.Furnace;
import org.jboss.forge.furnace.addons.AddonRegistry;
import org.jboss.forge.furnace.lock.LockManager;
import org.jboss.forge.furnace.services.ExportedInstance;

/**
 * This is a singleton for the {@link Forge} class.
 * 
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 * 
 */

public enum FurnaceService {
	INSTANCE;

	private transient Furnace forge;
	private ConverterFactory converterFactory;

	private FurnaceService() {
	}

	public void setFurnace(Furnace forge) {
		this.forge = forge;
	}

	public void start(final ClassLoader loader) {
		forge.startAsync(loader);
	}

	public AddonRegistry getAddonRegistry() {
		return forge.getAddonRegistry();
	}

	public void stop() {
		forge.stop();
	}

	public ContainerStatus getContainerStatus() {
		return (forge == null) ? ContainerStatus.STOPPED : forge.getStatus();
	}

	public ConverterFactory getConverterFactory() {
		if (converterFactory == null) {
			converterFactory = lookup(ConverterFactory.class);
		}
		return converterFactory;
	}

	public <S> S lookup(Class<S> service) {
		ExportedInstance<S> exportedInstance = null;
		if (forge != null) {
			exportedInstance = forge.getAddonRegistry().getExportedInstance(
					service);
		}
		return (exportedInstance == null) ? null : exportedInstance.get();
	}

	public LockManager getLockManager() {
		return forge.getLockManager();
	}
}
