package org.jboss.tools.forge.aesh.f2;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.jboss.forge.addon.ui.UICommand;
import org.jboss.forge.furnace.addons.AddonRegistry;
import org.jboss.forge.furnace.services.ExportedInstance;
import org.jboss.tools.forge.ext.core.FurnaceService;

public class F2Starter {
	
	public static void start() {
		try {
			if (!FurnaceService.INSTANCE.getContainerStatus().isStarted()) {
				ProgressMonitorDialog pmd = new ProgressMonitorDialog(null);
				pmd.run(true, false, new IRunnableWithProgress() {				
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException,
							InterruptedException {
						String taskName = "Please wait while Forge 2 is started.";
						monitor.beginTask(taskName, IProgressMonitor.UNKNOWN);
						FurnaceService.INSTANCE.waitUntilContainerIsStarted();
						// hack to make progress monitor stick until all commands are loaded
						getAllCandidatesAsMap();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, UICommand> getAllCandidatesAsMap() {
		Map<String, UICommand> result = new TreeMap<String, UICommand>();
		AddonRegistry addonRegistry = FurnaceService.INSTANCE
				.getAddonRegistry();
		Set<ExportedInstance<UICommand>> exportedInstances = addonRegistry
				.getExportedInstances(UICommand.class);
		for (ExportedInstance<UICommand> instance : exportedInstances) {
			instance.get();
		}
		return result;
	}

}