package reporterService.dao;


	import java.lang.reflect.Field;
	import java.math.BigDecimal;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Timestamp;
	import java.util.*;

	
	public class ReflectionExHelper {
	    public static void loadObjectIntoMap(Object object, Map<String, Object> map)
	            throws Exception {
	        if (map != null) {
//	           Class<?> zclass = object.getClass();
	            List<Field> allFields = getFields(object);
	            for (Field field : allFields) {
	                field.setAccessible(true);
	                String columnName = field.getName();
	                try {
	                    Object value = field.get(object);
	                    Class<?> type = field.getType();

	                    if (isPrimitive(type)) {//check primitive type(Point 5)
	                        Class<?> boxed = boxPrimitiveClass(type);//box if primitive(Point 6)
	                        value = boxed.cast(value);
	                    }
	                    map.put(columnName, value);
	                    // System.out.printf("%s - %s \n",columnName,value );
	                } catch (Exception ex) {
	                    //   ex.printStackTrace();
	                    continue;
	                }
	            }
	        }
	    }

	    public static void convertObjectIntoObject(Object targetObj, Object desObj) throws IllegalArgumentException, IllegalAccessException {
	        List<Field> targetFields = getFields(targetObj);
	        List<Field> desFields = getFields(desObj);
	        for (Field targetField : targetFields) {
	            targetField.setAccessible(true);
	            for (Field desField : desFields) {
	                desField.setAccessible(true);
	                if (desField.getName().equals(targetField.getName())) {
	                    Object value = targetField.get(targetObj);
//	                    Class<?> type = targetField.getType();
//	                    if (isPrimitive(type)) {//check primitive type(Point 5)
//	                        Class<?> boxed = boxPrimitiveClass(type);//box if primitive(Point 6)
//	                        value = boxed.cast(value);
//	                    }
	                    desField.set(desObj, value);
	                }
	            }

	        }
	    }

	    public static void loadObjIntoVector(Object object, Vector vec) throws IllegalArgumentException, IllegalAccessException {
	        List<Field> allFields = getFields(object);
	        for (Field field : allFields) {
	            field.setAccessible(true);
	            Object value = field.get(object);
	            vec.add(value);
	        }
	    }

	    public static void loadResultSetIntoMapProperties(ResultSet rst, Object object, Map<String, Object> mapProperties)
	            throws IllegalArgumentException, IllegalAccessException, SQLException {
	        List<Field> allFields = getFields(object);

	        for (Field field : allFields) {
	            field.setAccessible(true);
	            String columnName = field.getName();
	            try {
	                Object value = rst.getObject(columnName);
	                Class<?> type = field.getType();
	                if (isPrimitive(type)) {//check primitive type(Point 5)
	                    Class<?> boxed = boxPrimitiveClass(type);//box if primitive(Point 6)
	                    value = boxed.cast(value);
	                }
	                mapProperties.put(columnName, value);
	            } catch (Exception ex) {
	                //  ex.printStackTrace();
	                continue;
	            }
	        }
	    }

	    public static void loadResultSetIntoObject(ResultSet rst, Object object)
	            throws IllegalArgumentException, IllegalAccessException, SQLException {
	        List<Field> allFields = getFields(object);

	        for (Field field : allFields) {
	            field.setAccessible(true);
	            String columnName = field.getName();
	            try {
	                Object value = rst.getObject(columnName);
	                Class<?> type = field.getType();
	                if (isPrimitive(type)) {//check primitive type(Point 5)
	                    Class<?> boxed = boxPrimitiveClass(type);//box if primitive(Point 6)
	                    value = boxed.cast(value);
	                } else if (type == java.sql.Date.class) {
	                    if (value != null) {
	                        Timestamp ts = (Timestamp) value;
	                        field.set(object, new java.sql.Date(ts.getTime()));
	                    } else {
	                        field.set(object, null);
	                    }

	                    continue;
	                }
	                field.set(object, value);
	            } catch (Exception ex) {
	                //   ex.printStackTrace();
	                //  throw ex;
	                continue;
	            }
	        }
	    }

	    public static void loadResultSetIntoRowData(ResultSet rst, Object displayObj, List rowData)
	            throws IllegalArgumentException, IllegalAccessException, SQLException {

	        List<Field> allFields = getFields(displayObj);

	        for (Field field : allFields) {
	            field.setAccessible(true);
	            String columnName = field.getName();
	            try {
	                Object value = rst.getObject(columnName);
	                Class<?> type = field.getType();
	                if (isPrimitive(type)) {//check primitive type(Point 5)
	                    Class<?> boxed = boxPrimitiveClass(type);//box if primitive(Point 6)
	                    value = boxed.cast(value);
	                }

	                rowData.add(value);
	                // field.set(object, value);
	            } catch (Exception ex) {
	                //  ex.printStackTrace();
	                continue;
	            }
	        }
	    }

	    public static boolean isPrimitive(Class<?> type) {
	        return (type == int.class || type == long.class || type == double.class || type == float.class
	                || type == boolean.class || type == byte.class || type == char.class || type == short.class);
	    }

	    public static Class<?> boxPrimitiveClass(Class<?> type) {
	        if (type == int.class) {
	            return Integer.class;
	        } else if (type == long.class) {
	            return Long.class;
	        } else if (type == double.class) {
	            return Double.class;
	        } else if (type == float.class) {
	            return Float.class;
	        } else if (type == boolean.class) {
	            return Boolean.class;
	        } else if (type == byte.class) {
	            return Byte.class;
	        } else if (type == char.class) {
	            return Character.class;
	        } else if (type == short.class) {
	            return Short.class;
	        } else {
	            String string = "class '" + type.getName() + "' is not a primitive";
	            throw new IllegalArgumentException(string);
	        }
	    }

	    private boolean isCurrencyField(Class<?> type) {
	        boolean result = false;
	        String name = type.getName();
	        if (!name.contains("price")) {

	        } else {
	            if (type == BigDecimal.class) {
	                result = true;
	            }
	        }
	        return result;
	    }

	    public static Map<String, Object> reflectObjectToMap(Object obj) {
	        Map< String, Object> map
	                = new HashMap< String, Object>();

//	        Field[] baseFields = obj.getClass().getSuperclass().getDeclaredFields();
//	        Field[] fields = obj.getClass().getDeclaredFields();
//	        Field[] allFields = new Field[baseFields.length + fields.length];
//	        Arrays.setAll(allFields, i ->
//	                (i < baseFields.length ? baseFields[i] : fields[i - baseFields.length]));
	        List<Field> allFields = getFields(obj);

	        for (Field field : allFields) {
	            try {
	                String fName = field.getName();
	                if (fName.substring(0, 1) != "p") {
	                    fName = String.format("p_%s", field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
	                }
	                field.setAccessible(true);
	                Object value = field.get(obj);
//	                if(value!=null){
//	                    System.out.println(fName+" - "+ value.getClass().getName());
//	                }
	                map.put(fName, value);
	            } catch (IllegalAccessException e) {
	                //   e.printStackTrace();
	                continue;
	            }
	        }

	        return map;
	    }

//	    private static Field[] getFields(Object obj){
//	        Class<?> zclass = obj.getClass();
//	        Field[] baseFields = zclass.getSuperclass().getDeclaredFields();
//	        Field[] fields = zclass.getDeclaredFields();
//	        Field[] allFields = new Field[baseFields.length + fields.length];
//	        Arrays.setAll(allFields, i ->
//	                (i < baseFields.length ? baseFields[i] : fields[i - baseFields.length]));
//	        return allFields;
//	    }
//	    private static Field[] getFields(Object obj,Field[] derivedFields){
//	        Field[] allFields = null;
	//
//	        Class<?> zclass = obj.getClass();
//	        Field[] fields = zclass.getDeclaredFields();
//	        Class<?> superClass = zclass.getSuperclass();
	//
//	        if(!"java.lang.Object".toLowerCase().equals(superClass.getCanonicalName().toLowerCase())){
//	            Field[] superFields = getFields(superClass,superClass.getDeclaredFields());
//	            allFields = mergeFields(fields,superFields);
//	        }else{
//	            allFields = null;
//	        }
//	        return allFields;
//	    }
	    public static List<Field> getFields(Object obj) {
	        Class<?> clazz = obj.getClass();
	        return getAllFieldsRec(clazz, new ArrayList<Field>());
	    }

	    private static List<Field> getAllFieldsRec(Class clazz, List<Field> list) {
	        Class superClazz = clazz.getSuperclass();
	        if (superClazz != null) {
	            getAllFieldsRec(superClazz, list);
	        }
	        list.addAll(Arrays.asList(clazz.getDeclaredFields()));
	        return list;
	    }

	    private static Field[] mergeFields(Field[] fields1, Field[] fields2) {
	        if (fields1 == null && fields2 == null) {
	            return null;
	        } else if (fields1 == null) {
	            return fields2;
	        } else if (fields2 == null) {
	            return fields1;
	        }
	        Field[] allFields = new Field[fields1.length + fields2.length];
	        Arrays.setAll(allFields, i
	                -> (i < fields1.length ? fields1[i] : fields2[i - fields1.length]));
	        return allFields;
	    }
}
