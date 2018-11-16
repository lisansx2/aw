package cn.com.cslc.aw.tools.ws.baffle.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cn.com.cslc.aw.tools.core.common.utils.DateUtils;

//@Component
public class StringToXMLGregorianCalendarConverter implements Converter<String, XMLGregorianCalendar>{

	@Override
	public XMLGregorianCalendar convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date;
			date = sdf.parse(source);
			return DateUtils.dateToXmlDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}