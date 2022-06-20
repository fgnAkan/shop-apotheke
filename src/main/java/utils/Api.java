package utils;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Api {
	public Map<String,String> pathParams;
	private Object body;
	private int statusCode;
	private String method;
	private String returnedText;
	private Response response;
	private Response executeApi(String url) {
	    RestAssured.baseURI="http://localhost:7103";
		RequestSpecification request=RestAssured.given();
		request.pathParams(pathParams);
		if(body!=null) {
			request.body(body);
		}
		if(method.equals("POST")) {
		return request.post(url);
		}
		else if(method.equals("GET")) {	
			return request.get(url);
			}
		return null;
	}
	public void execute(String url) {
		response=executeApi(url);
		statusCode=response.getStatusCode();
		returnedText=response.getBody().asString();
	}
}
