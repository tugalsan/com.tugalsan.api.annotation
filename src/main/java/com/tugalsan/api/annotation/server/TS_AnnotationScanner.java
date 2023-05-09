package com.tugalsan.api.annotation.server;

import java.io.File;
import java.lang.ModuleLayer.Controller;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class TS_AnnotationScanner {
 
    private String packageName;
 
    public TS_AnnotationScanner(String packageName) {
        this.packageName = packageName;
    }
 
    public static List<String> scanPackage(String packageName) throws Exception {
        List<String> mappings = new ArrayList<String>();
        ClassLoader classLoader = TS_AnnotationScanner.class.getClassLoader();
        File pkgFile = new File(classLoader.getResource(packageName.replace('.', '/')).getFile());
 
        if (pkgFile.exists()) {
            for (File file : pkgFile.listFiles()) {
                String fileName = file.getName();
 
                if (file.isDirectory()) {
                    mappings.addAll(scanPackage(packageName + (packageName.isEmpty()?"":".") + fileName));
                } else if (fileName.endsWith(".class")) {
                    String className = packageName + '.' + fileName.substring(0, fileName.indexOf('.'));
                    mappings.addAll(getMappings(className));
                }
            }
        }
        return mappings;
    }
 
    private static List<String> getMappings(String className) throws Exception {
        List<String> values = new ArrayList<String>();
        Class cls = Class.forName(className);
 
        if (cls.isAnnotationPresent(Controller.class) || cls.isAnnotationPresent(RestController.class)) {
            Annotation[] annotationArray = cls.getAnnotationsByType(RequestMapping.class);
 
            if (annotationArray.length < 1) {
                values.addAll(scanMethods(cls, ""));
            }
            for (Annotation annotation : cls.getAnnotationsByType(RequestMapping.class)) {
                Class<? extends Annotation> type = annotation.annotationType();
                for (Method classMethod : type.getDeclaredMethods()) {
                    if (classMethod.getName().equals("value")) {
                        String[] valueArray = (String[])classMethod.invoke(annotation, null);
                        for (String rootPath : valueArray) {
                            values.addAll(scanMethods(cls, rootPath));
                        }
                    }
                }
            }
        }
 
        return values;
    }
 
    private static List<String> scanMethods(Class cls, String rootPath) {
        List<String> values = new ArrayList<>();
        for (Method method : cls.getMethods()) {
            RequestMapping mapping = method.getAnnotation(RequestMapping.class);
 
            if (mapping != null) {
                for (String mappingValue : mapping.value()) {
                    for (RequestMethod requestMethod : mapping.method()) {
                        String entry = cls.getCanonicalName() + "." + method.getName() + "(): " + rootPath + mappingValue + " " + requestMethod;
                        values.add(entry);
                    }
                }
            }
        }
 
        return values;
    }
 
    public void run() {
        try {
            for (String path : scanPackage(this.packageName)) {
                System.out.println(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new TS_AnnotationScanner("controllers").run();
    }
}