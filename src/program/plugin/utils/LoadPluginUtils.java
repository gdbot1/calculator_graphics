package program.plugin.utils;

import program.plugin.Configurable;
import program.plugin.Configure;
import program.plugin.PluginLoader;
import program.plugin.exception.FileIsNotDirectory;
import program.version.VersionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class LoadPluginUtils {
    public static Configure bootstrapPlugin(File directory, int version) throws FileIsNotDirectory, IOException, ReflectiveOperationException {
        if (!directory.exists()) { //File exist
            throw new FileNotFoundException("File '" + directory.getPath() + "' was not found.");
        }

        if (!directory.isDirectory()) { //File is directory
            throw new FileIsNotDirectory("File '" + directory.getPath() + "' is not a directory.");
        }

        File[] files = directory.listFiles(); //Files inside

        if (files == null) { //If files can't be received
            throw new UnknownError("Couldn't open the folder.");
        }

        Configure configure = new Configure();

        for (File file : files) { //Run through files
            PluginLoader<Configurable> pluginLoader = new PluginLoader<>(new URL[]{file.toURI().toURL()}, Configurable.class);

            Class<? extends Configurable> raw_instance = pluginLoader.getPlugin("program.Plugin", Configurable.class);

            Configurable instance = pluginLoader.instancePlugin(raw_instance);

            short required_global_version = VersionUtils.getGlobalVersion(instance.getVersion());
            short global_version = VersionUtils.getGlobalVersion(version);

            if (required_global_version != global_version) { //Check plugin version
                System.out.println("Plugin '" + file.getPath() + "' can't be opened on build: '" + global_version + "', required: '" + required_global_version + "'.");
            }
            else { //Run plugin
                instance.configure(configure, version);
            }
            pluginLoader.getClassLoader().close(); //Close plugin's loader
        }

        return configure; //Return result
    }
}
