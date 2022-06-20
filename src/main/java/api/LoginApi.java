package api;
import lombok.Getter;
import lombok.Setter;
import utils.Api;
import java.util.HashMap;
import java.util.Map;
import static api.Url.Login.loginUrl;
@Getter
@Setter
public class LoginApi {
	Api api=new Api();
	private String returnedText;
	private int statusCode;
	private String response;
	public void callApi(String tenant,String body) {
	 Map<String,String> pathParams=new HashMap();
	 pathParams.put("tenant", tenant);
	 api.setPathParams(pathParams);
	 api.setMethod("POST");
	 api.setBody(body);
	 api.execute(loginUrl);
	 this.setStatusCode(api.getStatusCode()) ;
	 this.setReturnedText(api.getReturnedText());
	}

}
