package my.maven.mvc.portlet.commands;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import my.maven.mvc.portlet.constants.MyMavenMVCPortletKeys;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + MyMavenMVCPortletKeys.MyMavenMVC,
				"mvc.command.name=/state/get"
		},service = MVCRenderCommand.class)

public class GetStateMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		PortletURL backURL = renderResponse.createRenderURL();
		renderRequest.setAttribute("backURL",backURL.toString());
		
		try {
            System.out.println("RENDER COMMAND CLASS");
			URL url = new URL("https://alloyui.com/io/data/states.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			InputStream is = conn.getInputStream();
			final int bufferSize = 1024;
			final char[] buffer = new char[bufferSize];
			final StringBuilder out = new StringBuilder();
			Reader in = new InputStreamReader(is, "UTF-8");
			for (; ; ) {
			    int rsz = in.read(buffer, 0, buffer.length);
			    if (rsz < 0)
			        break;
			    out.append(buffer, 0, rsz);
			}
			
			String output = out.toString();
			System.out.println("Output from Server here.... \n");
			if (output != null) {
				System.out.println(output);
				 try {
					JSONObject myObject = JSONFactoryUtil.createJSONObject(output);
					System.out.println("states json "+myObject.get("states"));
					Iterator<String> keyIterator = myObject.keys();
					String key = null;
					while(keyIterator.hasNext())
					{
					    key = keyIterator.next();
					    JSONArray jsonArray = myObject.getJSONArray(key);
						renderRequest.setAttribute(key,jsonArray);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					return "/error.jsp";
				}
	            	
	            }
			
	          System.out.println("States Json : "+renderRequest.getAttribute("states"));
	          //System.out.println("City: "+renderRequest.getAttribute("city"));
			
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();
			return "/error.jsp";

		  } catch (IOException e) {

			e.printStackTrace();
			return "/error.jsp";

		  }
		return "/view.jsp";
	}

}
