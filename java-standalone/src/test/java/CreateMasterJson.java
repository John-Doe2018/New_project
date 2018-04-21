import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateMasterJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String htmlContent = "[{\"id\": 1,\"name\": \"root1\",\"classification\": \"Math&\",\"children\": [{\"id\": 2,\"name\": \"child1\",\"path\": \"E:/abc.pdf\",\"type\": \"Application/PDF\",\"version\": \"1.0\",\"note\":\"This is a note\"}]}]";
		
		/*JSONObject obj = new JSONObject();
        obj.put("Name", binderObject.getName());
        obj.put("Classification",binderObject.getClassification());
        obj.put("Path", "D:\\files\\xmlFile.xml");

        JSONArray binderList = new JSONArray();
        binderList.add(obj);
        try {
	        FileWriter jsonFile = new FileWriter("D:\\test.json");
	        jsonFile.write(obj.toJSONString());
	        jsonFile.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
	}

}
