package tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext)getJspContext();
		JspWriter out=pc.getOut();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		out.print(sdf.format(date));
	}
	
}
