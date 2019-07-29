package com.eric.web.method;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	public static String bean2Json(Object obj) throws IOException {
		StringWriter sw = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
		mapper.writeValue(gen, obj);
		gen.close();
		return sw.toString();
	}
 
	public static <T> T json2Bean(String jsonStr, Class<T> objClass)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonStr, objClass);
	}
	public static <T> List<T> json2BeanList(String jsonStr, Class<T> objClass)
			throws JsonParseException, JsonMappingException, IOException {
		
		JavaType javaType = getCollectionType(ArrayList.class, objClass); 
		
		@SuppressWarnings("unchecked")
		List<T> lst =  (List<T>)mapper.readValue(jsonStr, javaType);
		return lst;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
	        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	   }   
	 
}
