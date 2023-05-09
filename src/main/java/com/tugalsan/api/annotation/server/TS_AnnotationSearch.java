package com.tugalsan.api.annotation.server;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TS_AnnotationSearch {

//    public static void main(String[] args) {
//        sniffPackage("controllers").stream().forEach(path -> System.out.println(path));
//    }

    public static File getResourceDirectory(Class clazz, String packageName) {
        return new File(clazz.getClassLoader().getResource(packageName.replace('.', '/')).getFile());
    }

    public static String getFileNameLabel(File file) {
        var fileName = file.getName();
        return fileName.substring(0, fileName.indexOf('.'));
    }

    public static boolean isFileClass(File file) {
        return file.getName().endsWith(".class");
    }

//    public static List<String> sniffPackage(String packageName) {
//        var mappings = new ArrayList<String>();
//        var pkgFile = getResourceDirectory(TS_AnnotationSearch.class, packageName);
//        if (!pkgFile.exists()) {
//            return mappings;
//        }
//        Arrays.stream(pkgFile.listFiles()).forEach(file -> {
//            if (file.isDirectory()) {
//                var subPackage = packageName + "." + file.getName();
//                mappings.addAll(sniffPackage(subPackage));
//            } else if (isFileClass(file)) {
//                var className = packageName + '.' + getFileNameLabel(file);
//                mappings.addAll(sniffClass(className));
//            }
//        });
//        return mappings;
//    }
//
//    private static List<String> sniffClass(String className, List<Class> annotationsToFind) {
//        var values = new ArrayList<String>();
//        try {
//            var cls = Class.forName(className);
//            if (annotationsToFind.stream().filter(a -> cls.isAnnotationPresent(a)).findAny().isPresent()) {
//                Annotation[] annotationArray = cls.getAnnotationsByType(RequestMapping.class);
//
//                if (annotationArray.length < 1) {
//                    values.addAll(scanMethods(cls, ""));
//                }
//                for (Annotation annotation : cls.getAnnotationsByType(RequestMapping.class)) {
//                    Class<? extends Annotation> type = annotation.annotationType();
//                    for (Method classMethod : type.getDeclaredMethods()) {
//                        if (classMethod.getName().equals("value")) {
//                            String[] valueArray = (String[]) classMethod.invoke(annotation, null);
//                            for (String rootPath : valueArray) {
//                                values.addAll(scanMethods(cls, rootPath));
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return values;
//    }
//    
//    
//
//    private static List<String> scanMethods(Class cls, String rootPath) {
//        List<String> values = new ArrayList<>();
//        for (Method method : cls.getMethods()) {
//            RequestMapping mapping = method.getAnnotation(RequestMapping.class);
//
//            if (mapping != null) {
//                for (String mappingValue : mapping.value()) {
//                    for (RequestMethod requestMethod : mapping.method()) {
//                        String entry = cls.getCanonicalName() + "." + method.getName() + "(): " + rootPath + mappingValue + " " + requestMethod;
//                        values.add(entry);
//                    }
//                }
//            }
//        }
//
//        return values;
//    }
}
