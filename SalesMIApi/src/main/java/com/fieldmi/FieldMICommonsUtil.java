package com.fieldmi;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.fieldmi.FieldMILogger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRSaver;

public class FieldMICommonsUtil {

	private static Properties fileProperties = new Properties();

	static {

		try {

			fileProperties.load(FieldMICommonsUtil.class.getClassLoader().getResourceAsStream("/application.properties"));
		} catch (Exception e) {

			FieldMILogger.error(FieldMICommonsUtil.class.getName(), e);
		}
	}
	
	public static Properties getFileProperties() {
		return fileProperties;
	}
	
	public String partitionToFetch( String fromDate, String toDate ) {
		
		String partitionToFetch = "";
		if (fromDate != null && toDate != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {

				Date fDate = sdf.parse(fromDate);
				Calendar cFrom = Calendar.getInstance();
				cFrom.setTime(fDate);
				int fromMonth = cFrom.get(Calendar.MONTH) + 1;

				Date tDate = sdf.parse(toDate);
				Calendar cTo = Calendar.getInstance();
				cTo.setTime(tDate);
				int toMonth = cTo.get(Calendar.MONTH) + 1;

				int diff = toMonth - fromMonth;
				if ((diff) > 0) {

					partitionToFetch = " PARTITION(";
					for (int mtCnt = 0; mtCnt <= diff; mtCnt++) {

						if (mtCnt != 0)
							partitionToFetch = partitionToFetch + ",";

						if (fromMonth != 12)
							partitionToFetch = partitionToFetch + "p" + fromMonth;
						else
							partitionToFetch = partitionToFetch + "p0";

						fromMonth++;
					}
					partitionToFetch = partitionToFetch + ")";
				} else if (diff < 0) {

					int mtCnt = 0;
					partitionToFetch = " PARTITION(";
					
					//add all months till december
					while(fromMonth != 13) {
						
						if (mtCnt != 0)
							partitionToFetch = partitionToFetch + ",";
						
						if (fromMonth != 12)
							partitionToFetch = partitionToFetch + "p" + fromMonth;
						else
							partitionToFetch = partitionToFetch + "p0";
						
						mtCnt++;
						fromMonth++;
						
					}
					fromMonth = 1;
					//add all months post december till toMonth
					while(fromMonth <= toMonth) {
						
						if (mtCnt != 0)
							partitionToFetch = partitionToFetch + ",";
						
						if (fromMonth != 12)
							partitionToFetch = partitionToFetch + "p" + fromMonth;
						else
							partitionToFetch = partitionToFetch + "p0";
						
						mtCnt++;
						fromMonth++;
						
					}
					
					partitionToFetch = partitionToFetch + ")";
				} else if (diff == 0) {

					if (fromMonth != 12)
						partitionToFetch = " PARTITION(p" + fromMonth + ")";
					else
						partitionToFetch = " PARTITION(p0)";
				}

			} catch (ParseException e1) {
			}
		}
		
		return partitionToFetch;
	}
	
	public String partitionTofecthUsingSplit( String fromDate ) {
		
		String partitionToFetch = "";
		String[] monthSplitter = fromDate.split("-");
		if( monthSplitter.length == 3 ) {
		
			int fromMonth = Integer.parseInt(monthSplitter[1]);
			if( fromMonth !=12 )
				partitionToFetch = " PARTITION(p" + fromMonth + ")";
			else
				partitionToFetch = " PARTITION(p0)";
			
		}
		
		return partitionToFetch;
	}
	
	public static void main(String args[]) {
		
		FieldMICommonsUtil utils = new FieldMICommonsUtil();
		utils.generateJasper();
		
	}
	
	public void generateJasper() {
		
		InputStream employeeReportStream = this.getClass().getResourceAsStream("/SalesOrderwithoutDiscount.jrxml");
		try {
			JasperReport jasperReport
			  = JasperCompileManager.compileReport(employeeReportStream);
			JRSaver.saveObject(jasperReport, new File("/Users/parag/Documents/fieldmi/salesmidev/maven/SalesMIWebServices/src/main/resources/SalesOrderwithoutDiscount.jasper"));
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
