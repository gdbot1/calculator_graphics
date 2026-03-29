package program.plugin;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader<T> {
    protected URLClassLoader classLoader;

    public PluginLoader() {

    }

    public PluginLoader(URL[] jar, Class<T> clazz) {
        loadClassLoader(jar, clazz);
    }

    public PluginLoader(URLClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    protected URLClassLoader findClassLoader(URL[] jar, Class<T> clazz) {
        return new URLClassLoader(jar, clazz.getClassLoader());
    }

    protected Class<?> getRawPluginEntry(URLClassLoader classLoader, String entry) throws ClassNotFoundException {
        return classLoader.loadClass(entry);
    }

    protected Class<? extends T> boilPlugin(Class<?> raw, Class<T> clazz) throws ClassCastException {
        return raw.asSubclass(clazz);
    }

    public void loadClassLoader(URL[] jar, Class<T> clazz) {
        this.classLoader = findClassLoader(jar, clazz);
    }

    public URLClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Class<? extends T> getPlugin(String entry, Class<T> clazz) throws ClassNotFoundException {
        Class<?> raw = getRawPluginEntry(this.classLoader, entry);

        return boilPlugin(raw, clazz);
    }

    public T instancePlugin(Class<? extends T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz.getDeclaredConstructor().newInstance();
    }
}
