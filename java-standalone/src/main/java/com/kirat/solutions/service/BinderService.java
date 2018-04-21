package com.kirat.solutions.service;

import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.transform.TransformerException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.parser.ParseException;

import com.kirat.solutions.domain.BinderList;
import com.kirat.solutions.domain.CreateBinderRequest;
import com.kirat.solutions.domain.CreateBinderResponse;
import com.kirat.solutions.domain.DeleteBookRequest;
import com.kirat.solutions.domain.UpdateBinderRequest;
import com.kirat.solutions.processor.TransformationProcessor;
import com.kirat.solutions.processor.UpdateMasterJson;

public class BinderService {

	
	@POST
	@Path("create")
	public CreateBinderResponse createBinder(CreateBinderRequest createBinderRequest) throws TransformerException, JsonParseException, JsonMappingException, IOException, ParseException {
		CreateBinderResponse createBinderResponse = new CreateBinderResponse();
		String htmlContent = createBinderRequest.getHtmlContent();
		BinderList listOfBinderObj;
		TransformationProcessor transformationProcessor = new TransformationProcessor();
		listOfBinderObj = transformationProcessor.processHtmlToBinderXml(htmlContent);
		
		//append in MasterJson 
		UpdateMasterJson updateMasterJson = new UpdateMasterJson();
		updateMasterJson.prepareMasterJson(listOfBinderObj);
		createBinderResponse.setSuccessMsg("Binder Successfully Created.");
		return createBinderResponse;
	}
	
	@POST
	@Path("update")
	public String updateBinder(UpdateBinderRequest updateBinderRequest) throws TransformerException {
		
		
		//append in MasterJson 
		String s = "Serice was run Successfully";
		return s;
	}
	
	
	@POST
	@Path("delete")
	public String deleteBinder(DeleteBookRequest deleteBookRequest) throws TransformerException {
		
		
		//append in MasterJson 
		String s = "Serice was run Successfully";
		return s;
	}
	
}
