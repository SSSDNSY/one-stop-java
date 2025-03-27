package basic;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pengzh
 * @desc
 * @since 2025-02-20
 */
public class Str2ClassTest {


    public static void main(String[] args) throws Exception {
        // 1. 准备源代码字符串
        String className = "HelloWorld";
        String sourceCode = "public class HelloWorld {" + "    public void sayHello() {" + "        System.out.println(\"Hello, World! Str2ClassTest\");" + "    }" + "}";

        // 2. 编译源代码
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        // 使用内存中的 JavaFileObject
        JavaFileObject file = new JavaSourceFromString(className, sourceCode);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);

        // 编译选项
        List<String> options = new ArrayList<>();
        options.add("-classpath");
        options.add(System.getProperty("java.class.path"));

        // 编译
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, options, null, compilationUnits);
        boolean success = task.call();
        if (success) {
            System.out.println("Compilation succeeded.");
        } else {
            // 输出编译错误
            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                System.out.println(diagnostic.getMessage(null));
            }
            return;
        }

        // 3. 加载编译后的类
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File("").toURI().toURL()});
        Class<?> cls = Class.forName(className, true, classLoader);

        // 4. 实例化并执行方法
        Object instance = cls.getDeclaredConstructor().newInstance();
        cls.getMethod("sayHello").invoke(instance);
    }
}

// 自定义的 JavaFileObject，用于在内存中存储源代码
class JavaSourceFromString extends SimpleJavaFileObject {
    final String code;

    JavaSourceFromString(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return code;
    }
}

