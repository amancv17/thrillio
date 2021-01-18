package thrillio.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpConnect {

	public static String download(String sourceUrl) throws MalformedURLException, URISyntaxException {
	try {
		{
			System.out.println("Downloading :" + sourceUrl);
			URL url = new URI(sourceUrl).toURL();
			
			try {
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				int responceCode = con.getResponseCode();
				
				if(responceCode >= 200 && responceCode < 300) {
					return IOUtils.read(con.getInputStream());
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	
}catch(Exception e) {
	e.printStackTrace();
}
	return sourceUrl;
	}
}
