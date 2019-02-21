package Tool;

import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author ldk
 *
 */
public class Tool {
	
	public static String toPercentage(float n, int digits){
	    return String.format("%."+digits+"f",n*100)+"%";
	}
	
}