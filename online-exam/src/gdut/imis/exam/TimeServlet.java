package gdut.imis.exam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.swing.Timer;

public class TimeServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		//定时器，每隔1秒打印时间
		Timer time = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(LocalDateTime.now());
			}
			
		});
		time.start();
	}
}
