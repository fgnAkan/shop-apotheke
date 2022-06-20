import org.junit.Assert;
import org.junit.Test;
import api.LoginApi;
import java.util.Arrays;
import java.util.List;
import utils.Common;


public class LoginTests {
	LoginApi login=new LoginApi();
	 @Test
	    public void loginWithExistingTenantsSucces() {
		 String postBody=Common.readJsonFile("/src/test/resources/loginInfoSucces");
		 String actualToken="eyJhbGciOiJSUzM4NCJ9.ewogICJzdWIiOiAiMTExOTg5OTM0IiwKICAicm9sZSI6ICJSRUdJU1RF UkVEIiwKICAidGVuYW50IjogImNvbS1xcyIsCiAgInR5cGUiOiAiQUNDRVNTIiwKICAia2V5VmVyc 2lvbiI6ICJjMjQ1MzVlOC0yMzQyLTQ2N2QtODVmYy1jNmJkYWIxZDY4MmUiLAogICJleHAiOiA5Nj A1ODg1MDc3LAogICJleHBSZWZyZXNoIjogIjIwMjItMTEtMTlUMTU6MTY6MTcuODY2WiIsCiAgImp 0aSI6ICI1NzljZjk5OC1kNjBkLTQ2YTktOWNlNi1iYmE5ODJjY2YzZDMiLAogICJpc3MiOiAiYXV0 aC5yZWR0ZWNsYWIuY29tIiwKICAiaWF0IjogMTYwNTc5ODY3Nwp9";

		 List<String> listTenant = Arrays.asList("at","ch","com","fr","es","it");
		 for(int i=0;i<listTenant.size();i++){
			 login.callApi(listTenant.get(i),postBody);
			Assert.assertEquals(Common.jsonPathQuery(login.getReturnedText(),"token"),actualToken);
			 Assert.assertEquals(Common.jsonPathQuery(login.getReturnedText(),"tokenType"),"bearer");
			 Assert.assertEquals(login.getStatusCode(), 201);
		 }
	    }
	@Test
	public void loginWithNonExistingTenantFail() {
		String postBody=Common.readJsonFile("/src/test/resources/loginInfoSucces");
			login.callApi("xx",postBody);
			Assert.assertEquals(login.getStatusCode(), 404);
	}
	@Test
	public void loginWithoutPasswordFail() {
		String postBody=Common.readJsonFile("/src/test/resources/loginInfoFail");

		List<String> listTenant = Arrays.asList("at","ch","com","fr","es","it");
		for(int i=0;i<listTenant.size();i++){
			login.callApi(listTenant.get(i),postBody);
			Assert.assertEquals(Common.jsonPathQuery(login.getReturnedText(),"error"),"Bad Request");
			Assert.assertEquals(Common.jsonPathQuery(login.getReturnedText(),"message"),"Validation Exception");
			Assert.assertEquals(Common.jsonPathQuery(login.getReturnedText(),"invalidParams.get(0).field"),"password");
			Assert.assertEquals(Common.jsonPathQuery(login.getReturnedText(),"invalidParams.get(0).key"),"parameter.error.empty");
			Assert.assertEquals(login.getStatusCode(), 400);
		}
	}

	@Test
	public void loginTimeOutFail() {
		String postBody=Common.readJsonFile("/src/test/resources/loginInfoSucces");
			login.callApi("timeOut",postBody);
			Assert.assertEquals(Common.jsonPathQuery(login.getReturnedText(),"error"),"Gateway timed out");
			Assert.assertEquals(Common.jsonPathQuery(login.getReturnedText(),"message"),"Connection to service timed out");
			Assert.assertEquals(login.getStatusCode(), 504);
		}
	}
