package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 生成目标目录所有jar包下的class文件的TXT文件
 *
 * @Description
 * @Since 2020-10-14
 */
public final class JarUtil {
    public static final String JAR_FOLDER = "W:\\workspaces\\tjin\\library\\alllib";//jar包所在目录
    public final static String DESKTOP = "E:\\desktop\\class.txt";//包含所有【class】的文件输出目录

    public static void main(String[] args) throws IOException {
        final Map<String, Set> map = getJarFiles(JAR_FOLDER);
        writeFile(map.get("CLASS"), DESKTOP);
    }

    public final static Set<JarEntry> getJarClasses(String jarName) throws IOException {
        Set<JarEntry> set = new HashSet();
        final JarFile jarFile = new JarFile(jarName);
        final Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            final JarEntry jarEntry = entries.nextElement();
            if (jarEntry.toString().endsWith(".class")) {
                set.add(jarEntry);
            }
        }
//        System.out.println("共【"+set.size()+"】个class文件：");
//        for (JarEntry e : set) {
//            System.out.println(e);
//        }
        return set;

    }

    public final static Map<String, Set> getJarFiles(String folder) throws IOException {
        Map<String, Set> result = new HashMap<>(2);
        Set<File> jarSet = new HashSet(1000);
        Set<JarEntry> classSet = new HashSet(100000);
        final File file = new File(folder);
        final File[] files = file.listFiles();
        int sum = 0;
        for (File f : files) {
            if (f.isFile()) {
                jarSet.add(f);
                final Set<JarEntry> jarClasses = getJarClasses(f.toString());
                classSet.addAll(jarClasses);
                sum += jarClasses.size();
            }
        }
        System.out.println("共【" + jarSet.size() + "】个jar文件：");
        for (File f : jarSet) {
            System.out.println(f);
        }
        System.out.println("共【" + sum + "】个class文件：");
        result.put("JAR", jarSet);
        result.put("CLASS", classSet);
        return result;
    }

    public final static void writeFile(Set set, String desFile) throws IOException {
        long t1 = System.currentTimeMillis();
        System.out.println("开始写入...");
        final File file = new File(desFile);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        final FileWriter fileWriter = new FileWriter(file);
        for (Object f : set) {
            fileWriter.write(f.toString() + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
        System.out.println("写入完成,耗时【" + (System.currentTimeMillis() - t1) + "】");
    }
}
