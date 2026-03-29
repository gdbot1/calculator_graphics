package program.version;

public class VersionUtils {
    public static short getGlobalVersion(int version) {
        return (short) (version >> 16);
    }

    public static short getLocalVersion(int version) {
        return (short) version;
    }

    public static int getVersion(short global_version, short local_version) {
        return (global_version << 16) | local_version;
    }

    public static int getVersion(int global_version, int local_version) {
        return ((short)global_version << 16) | (short)local_version;
    }
}
