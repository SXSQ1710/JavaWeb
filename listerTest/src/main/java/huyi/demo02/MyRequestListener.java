package huyi.demo02;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class MyRequestListener implements ServletRequestListener{
	private Integer count  = 0;

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		if(request.getRequestURI().endsWith("onlineCount.jsp")) {
			count++;
			sre.getServletContext().setAttribute("count", count);
		}
	}
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	

}
