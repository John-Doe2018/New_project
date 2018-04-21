package com.tcs.dummyproject.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.kirat.solutions.util.FileInfoPropertyReader;

public class helloWorldService {
	@GET
	@Path("sayHello")
	public String getConfigurationParameterValue() {
		return "Hello World";
	}
	
	@GET
	@Path("getMasterJson")
	public String getMasterJson() {
		
	    String filePath = FileInfoPropertyReader.getInstance().getString("masterjson.file.path");
	    
		return filePath;  
	}
	
	public String readJson(String filePath){
		/*byte[] jsonData = Files.;
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		BinderList emp = objectMapper.readValue(jsonData, BinderList.class);
		
		System.out.println("Employee Object\n"+emp);
		
		//convert Object to json string
		BinderList emp1 = createEmployee();
		//configure Object mapper for pretty print
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		//writing to console, can write to any output stream such as file
		StringWriter stringEmp = new StringWriter();
		objectMapper.writeValue(stringEmp, emp1);
		System.out.println("Employee JSON is\n"+stringEmp);
	}*/
		return filePath;
		
		
	}
	
	
}
