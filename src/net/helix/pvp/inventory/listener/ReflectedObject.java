package net.helix.pvp.inventory.listener;
 
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
 
public class ReflectedObject { // class made by retrodaredevil. Feel free to use in your plugins without permission.
 
    private Object o;
    
    public ReflectedObject(Object o){
        this.o = o;
    }
    public Object toObject(){
        return o;
    }
    public Class<?> getObjectClass(){
        return o.getClass();
    }
    public String getName(){
        return o.getClass().getTypeName();
    }
    public String getFullName(){
        return o.getClass().getName();
    }
    public String getPackageName(){
        return o.getClass().getPackage().getName();
    }
    public void setField(String field, Object o){
        Field f = null;
        try{
            f = this.getField(field);
        } catch(NoSuchFieldException e){
            e.printStackTrace();
        } catch(SecurityException e){
            e.printStackTrace();
        }
        if(f == null){
            return;
        }
        f.setAccessible(true);
        try {
            f.set(this.o, o);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public ReflectedObject get(String field){
        Field f = null;
        try{
            f = this.getField(field);
        } catch(NoSuchFieldException e){
            return null;
        } catch(SecurityException e){
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            return new ReflectedObject(f.get(this.o));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean hasField(String field){
        try{
            this.getField(field);
        }catch(NoSuchFieldException | SecurityException e){
            return false;
        }
        return true;
    }
    private Field getField(String field) throws NoSuchFieldException, SecurityException{
        return o.getClass().getDeclaredField(field);
    }
    
    public Method getMethod(String name, boolean ignoreCase){
        return new Method(name, o, ignoreCase, o.getClass());
    }
    public Method getMethod(String name){
        return this.getMethod(name, false);
    }
    
    
    
    public static class Method{
        
        private String name;
        private Object instance;
        private boolean ignoreCase;
        private Class<?> c;
        
        private Method(String name, Object instance, boolean ignoreCase, Class<?> c){
            this.name = name;
            this.instance = instance;
            this.ignoreCase = ignoreCase;
            this.c = c;
        }
        public boolean isValid(){
            return this.getMethod() != null;
        }
        public ReflectedObject invoke(Object... variables){
            java.lang.reflect.Method m = this.getMethod();
            m.setAccessible(true);
            try {
                return new ReflectedObject(m.invoke(instance, variables));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                return null;
            }
        }
        public String getName(){
            return name;
        }
        public Object getInstance(){
            return instance;
        }
        private java.lang.reflect.Method getMethod(){
            for(java.lang.reflect.Method m : c.getMethods()){
                if(this.ignoreCase){
                    if(m.getName().equalsIgnoreCase(name)){
                        return m;
                    }
                } else {
                    if(m.getName().equals(name)){
                        return m;
                    }
                    
                }
            }
            return null;
        }
    }
    public static void setStaticField(Class<?> c, String name, Object set){
        Field f = null;
        try{
            f = c.getDeclaredField(name);
        } catch(NoSuchFieldException e){
            e.printStackTrace();
        } catch(SecurityException e){
            e.printStackTrace();
        }
        if(f == null){
            throw new IllegalArgumentException("Error while getting the field '" + name + "'");
        }
        f.setAccessible(true);
        
        try{
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, modifiersField.getInt(f) - Modifier.FINAL);
        } catch(SecurityException e){
            System.out.println("A security manager may be preventing you from setting this field.");
            e.printStackTrace();
        } catch(IllegalAccessException | IllegalArgumentException | NoSuchFieldException e){
            e.printStackTrace();
        }
        
        try {
            f.set(null, set);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static Object getFromStaticField(Class<?> c, String name){
        Field f = null;
        try{
            f = c.getDeclaredField(name);
        } catch(NoSuchFieldException e){
            e.printStackTrace();
        } catch(SecurityException e){
            e.printStackTrace();
        }
        if(f == null){
            return null;
        }
        f.setAccessible(true);
        
        try{
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, modifiersField.getInt(f) - Modifier.FINAL);
        } catch(SecurityException e){
            System.out.println("A security manager may be preventing you from setting this field.");
            e.printStackTrace();
        } catch(IllegalAccessException | IllegalArgumentException | NoSuchFieldException e){
            e.printStackTrace();
        }
        
        try {
            return f.get(null);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    
    
    
}
 
 
//  public static Method getMethodFromStaticField(Class<?> c, String name, boolean ignoreCase){
//      return new Method("name", null, ignoreCase, c);
//  }
//  public static Method getMethodFromStaticField(Class<?> c, String name){
//      return getMethodFromStaticField(c, name, false);
//  }
//  public static Class<?> getClassFromString(String pack, String name){
//      return getClassFromString(pack + "." + name);
//  }
//  public static Class<?> getClassFromString(String whole){
//      try {
//          return Class.forName(whole);
//      } catch (ClassNotFoundException e) {
//          e.printStackTrace();
//      }
//      return null;
//  }