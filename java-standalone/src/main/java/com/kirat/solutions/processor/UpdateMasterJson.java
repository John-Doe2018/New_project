package com.kirat.solutions.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.kirat.solutions.domain.BinderList;
import com.kirat.solutions.util.FileInfoPropertyReader;
import com.kirat.solutions.util.ReadJsonUtil;

public class UpdateMasterJson {

	@SuppressWarnings("unchecked")
	public void prepareMasterJson(BinderList binderObject) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		JSONObject superObj = new JSONObject();
		JSONObject parentObj = new JSONObject();

		boolean isSameName = false;

		//getting the master Json File path
		String filePath = FileInfoPropertyReader.getInstance().getString("masterjson.file.path");
		//Check any binder with same name already present or not
		File tmpDir = new File(filePath);
		boolean isFile = tmpDir.exists();
		if(isFile) {
			isSameName = ReadJsonUtil.CheckBinderWithSameName(filePath,binderObject.getName());
		}else {
			if(isFile) {
				JSONObject array = (JSONObject) parser.parse(new FileReader("D:\\test.JSON"));
				JSONArray jsonArray = (JSONArray) array.get("BookList");
				//Add the new object to existing
			}
			if(!isSameName) {
				obj.put("Name", binderObject.getName());
				obj.put("Classification",binderObject.getClassification());
				obj.put("Path", "D:\\files\\xmlFile.xml");
				superObj.put(binderObject.getName(), obj);
				JSONArray binderList = new JSONArray();
				binderList.add(superObj);
				parentObj.put("BookList", binderList);
				try {
					FileWriter jsonFile = new FileWriter("D:\\test.json");
					jsonFile.write(parentObj.toJSONString());
					jsonFile.flush();
					jsonFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				//throw Exception e
			}
		}

	}
}
