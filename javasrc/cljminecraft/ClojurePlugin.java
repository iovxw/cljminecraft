package cljminecraft;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ClojurePlugin extends JavaPlugin {
	static {
		/*
		 * this should only be executed for cljminecraft(the main not any children) plugin,
		 * and it is so if children have a depend on cljminecraft
		 * bukkit will then make sure cljminecraft is loaded before them
		 */
		ClassLoader previous = Thread.currentThread().getContextClassLoader();
		final ClassLoader parentClassLoader = ClojurePlugin.class.getClassLoader();
		Thread.currentThread().setContextClassLoader(parentClassLoader);
		try {
			Bukkit.getConsoleSender().sendMessage(
				ChatColor.DARK_GREEN + "===[" +
				ChatColor.DARK_BLUE + " Clojure init " +
				ChatColor.DARK_GREEN + "]===");

			clojure.lang.DynamicClassLoader newCL = (clojure.lang.DynamicClassLoader)AccessController
				.doPrivileged(new PrivilegedAction<Object>()
					{
						@Override
						public Object run()
						{
							assert parentClassLoader == ClojurePlugin.class.getClassLoader();
							assert this.getClass().getClassLoader() == ClojurePlugin.class.getClassLoader();
							return(new clojure.lang.DynamicClassLoader(parentClassLoader));
						}
					});
			assert !clojure.lang.Compiler.LOADER.isBound();
			/* wow this really isn't bound even after `reload` also nothing remains pushed */

			/*
			 * Addresses NullPointerException with clojure 1.5.
			 * See http://dev.clojure.org/jira/browse/CLJ-1172
			 * Note that there should be a better way to do this...
			 */
			// clojure.lang.RT.init();
			clojure.lang.RT.baseLoader();
			clojure.lang.Var.pushThreadBindings(clojure.lang.RT.map(clojure.lang.Compiler.LOADER, newCL));
			clojure.lang.Var.intern(clojure.lang.RT.CLOJURE_NS,
						 clojure.lang.Symbol.intern("*warn-on-reflection*"),
						 clojure.lang.RT.F, true);

			clojure.lang.Var.intern(clojure.lang.RT.CLOJURE_NS,
						 clojure.lang.Symbol.intern("*use-context-classloader*"),
						 clojure.lang.RT.F, true);
		} finally {
			Thread.currentThread().setContextClassLoader(previous);
		}
	}
}
