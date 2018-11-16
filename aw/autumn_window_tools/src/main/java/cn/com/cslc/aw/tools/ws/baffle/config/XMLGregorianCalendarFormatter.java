package cn.com.cslc.aw.tools.ws.baffle.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import cn.com.cslc.aw.tools.core.common.utils.DateUtils;

@Component
public class XMLGregorianCalendarFormatter implements Formatter<XMLGregorianCalendar>{

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public String print(XMLGregorianCalendar arg0, Locale arg1) {
		Date date = DateUtils.xmlDate2Date(arg0);
		return sdf.format(date);
	}

	@Override
	public XMLGregorianCalendar parse(String arg0, Locale arg1) throws ParseException {
		try {
			Date date = sdf.parse(arg0);
			return DateUtils.dateToXmlDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}