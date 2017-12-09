package com.imooc;

import java.lang.reflect.*;

/**
 * Created by Administrator on 2017/12/9.
 * 反射相关操作
 */
public class ReflectDemo {
    public static void main(String[] args){

        try {
            //获取需要反射类的class
            Class c1=Person.class;
            Class c2=Class.forName("com.imooc.Person");
            Class c3=new Person().getClass();
            System.out.println(c1==c2);
            System.out.println(c1==c3);
            //读取字段
            Field field=c1.getDeclaredField("name");
            System.out.println(field.getName());
            System.out.println(field.getType().getName());
            Field[] fields=c1.getDeclaredFields();
            for(Field f:fields){
                System.out.println(f.getName());
                System.out.println(f.getType().getName());
            }
            //读取构造方法
            Constructor[] constructors=c1.getConstructors();
            System.out.println("==================");
            for(Constructor constructor:constructors){
                System.out.println(constructor.getName());
                Type[] types=constructor.getGenericParameterTypes();
                for(Type type:types){
                    System.out.println(type.getTypeName());
                }

            }
            System.out.println("==================");
            //读取方法(包括从父类继承的方法,不包括所有私有方法)
            Method[] methods=c1.getMethods();
            for (Method method:methods){
                System.out.println("方法名:"+method.getName());
                System.out.println("方法返回值"+method.getReturnType().getName());
                Parameter[] parameters= method.getParameters();
                for(Parameter parameter:parameters){
                    System.out.println("参数类型"+parameter.getType().getName());
                }
                System.out.println("*******************");
            }
            System.out.println("++++++++++++++++++++++++++");
            //读取方法(仅有自己声明的方法,包括私有方法)
            methods=c1.getDeclaredMethods();
            for (Method method:methods){
                System.out.println("方法名:"+method.getName());
                System.out.println("方法返回值"+method.getReturnType().getName());
                System.out.println("方法修饰符"+method.getModifiers());
                Parameter[] parameters= method.getParameters();
                for(Parameter parameter:parameters){
                    System.out.println("参数类型"+parameter.getType().getName());
                }
                System.out.println("*******************");
            }
            System.out.println("=====方法调用=======");
            //通过反射调用方法
            methods=c1.getDeclaredMethods();
            Person person=(Person) c1.newInstance();
            for (Method method:methods){
                if(!"say".equals(method.getName())){
                    continue;
                }
                int paramterCount=method.getParameterCount();
                if(paramterCount==0){
                    Object obj=method.invoke(person);
                    System.out.println(obj==null);
                }else if(paramterCount==1){
                    Object obj=method.invoke(person,new Object[]{"one paramter"});
                    System.out.println(obj);
                }else{
                    //不加上这一句,是不可以访问私有方法的
                    method.setAccessible(true);
                    Object obj=method.invoke(person,new Object[]{"one paramter",12});
                    System.out.println(obj);
                }

            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (NoSuchFieldException e1){
            e1.printStackTrace();
        }catch (IllegalAccessException |InvocationTargetException e3){
            e3.printStackTrace();
        }catch (InstantiationException e4){
            e4.printStackTrace();
        }



    }
}
