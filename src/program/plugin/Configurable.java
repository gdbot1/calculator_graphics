package program.plugin;

import java.net.URLClassLoader;
import java.util.HashMap;

public interface Configurable {
    HashMap<String, URLClassLoader> configure(Configure configure, int version);

    int getVersion();
}
