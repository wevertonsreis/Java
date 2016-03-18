package br.com.caelum.estoque.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 
 * @author Weverton Reis
 *
 */
public class DateAdapter extends XmlAdapter<String, Date> {
	
	private String pattern = "dd/MM/yyyy";
	
	/**
	 * 
	 * @param dataString
	 * @return
	 * @throws Exception
	 */
	@Override
	public Date unmarshal(String dataString) throws Exception {
		return new SimpleDateFormat(pattern).parse(dataString);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@Override
	public String marshal(Date data) throws Exception {
		return new SimpleDateFormat(pattern).format(data);
	}

}
