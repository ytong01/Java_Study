package com.rose.javassist;

import javassist.*;

import java.io.IOException;

public class JavassistMain {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {

        ClassPool classPool = ClassPool.getDefault();
        CtClass clazz = classPool.makeClass("com.rose.javassist.JavassistTest");

        StringBuffer sb = null;

        CtField prop = new CtField(classPool.get("java.lang.String"), "prop", clazz);
        prop.setModifiers(Modifier.PRIVATE);

        clazz.addMethod(CtNewMethod.setter("setProp", prop));
        clazz.addMethod(CtNewMethod.getter("getProp", prop));

        clazz.addField(prop, CtField.Initializer.constant("rose"));
        CtConstructor constructor = new CtConstructor(new CtClass[]{}, clazz);

        sb = new StringBuffer();
        sb.append("{\n prop = \"rose\"; \n}");
        constructor.setBody(sb.toString());
        clazz.addConstructor(constructor);

        CtMethod method = new CtMethod(CtClass.voidType, "execute", new CtClass[]{}, clazz);
        method.setModifiers(Modifier.PUBLIC);
        sb.setLength(0);
        sb.append("{\n System.out.println(\"do something...\" + prop);\n}");
        method.setBody(sb.toString());
        clazz.addMethod(method);
        clazz.writeFile("");

    }
}
