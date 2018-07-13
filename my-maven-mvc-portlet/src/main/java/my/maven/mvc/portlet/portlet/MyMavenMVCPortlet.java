package my.maven.mvc.portlet.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import my.maven.mvc.portlet.constants.MyMavenMVCPortletKeys;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.kartik",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-js=/js/main.js",
		"javax.portlet.display-name=my-maven-mvc-portlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MyMavenMVCPortletKeys.MyMavenMVC,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MyMavenMVCPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
        System.out.println("MyMavenMVCPortlet DO VIEW Called");
		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		renderRequest.setAttribute(
			"user",themeDisplay.getUser());
		System.out.println("MyMavenMVCPortlet DO VIEW END");
		super.doView(renderRequest, renderResponse);
	}
	
//	public void serveResource(ResourceRequest resourceRequest,
//			ResourceResponse resourceResponse) throws IOException,
//			PortletException {
//		
//		resourceResponse.setContentType("text/json");
//		PrintWriter out = resourceResponse.getWriter();
//		out.println("AUI Ajax call is performed");
//		out.flush();
//		super.serveResource(resourceRequest, resourceResponse);
//		
//	}
	
//	public void getStates(ActionRequest actionRequest, ActionResponse res)
//		    throws IOException, PortletException, PortalException, SystemException, JSONException {
//		        System.out.println("getStates");
//		         String getURL="https://alloyui.com/io/data/states.json";
//		          HttpClient client = new DefaultHttpClient();	
//		          HttpGet request = new HttpGet(getURL);
//		          request.addHeader("accept", "application/json");
//		          HttpResponse response = client.execute(request);
//		          
//		            
//				 StringBuffer sB = new StringBuffer();
//				// Get the response
//		         BufferedReader brd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//		         String jsonString  = "";
//		         while ((jsonString  = brd.readLine()) != null) {
//		        	 sB.append(jsonString );
//		           System.out.println("jsonString:::::"+jsonString ); 
//		         }         
//		         
//		         JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
//		         JSONObject myObject = JSONFactoryUtil.createJSONObject(jsonString );
//		         jsonArray.put(myObject);
//				//System.out.println("path json "+myObject.get("path"));
//					Iterator<String> keyIterator = myObject.keys();
//					String key = null;
//					while(keyIterator.hasNext())
//					{
//					    key = keyIterator.next();
//					    actionRequest.setAttribute(
//								key,myObject.get(key));
//					}
//		         } 
}