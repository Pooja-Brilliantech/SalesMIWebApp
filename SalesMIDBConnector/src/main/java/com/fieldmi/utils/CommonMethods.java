package com.fieldmi.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fieldmi.FieldMILogger;
import com.fieldmi.service.FMSalesMasterService;
import com.fieldmi.service.FMUserService;
import com.fieldmi.service.FMWorkflowConfigurationService;
import com.fieldmi.service.WorkFlowAuditLogService;
import com.fieldmi.service.WorkFlowTaskStatusService;
import com.fieldmi.stubs.WsCustomerDetails;
import com.fieldmi.stubs.WsExpense;
import com.fieldmi.stubs.WsFetchSalesOrg;
import com.softtantra.salesapp.pojo.CategoryMaster;
import com.softtantra.salesapp.pojo.Citys;
import com.softtantra.salesapp.pojo.Country;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.CustomerImportRecords;
import com.softtantra.salesapp.pojo.CustomerTargetRecords;
import com.softtantra.salesapp.pojo.CustomerTargetRecordsDetails;
import com.softtantra.salesapp.pojo.DistributorDetails;
import com.softtantra.salesapp.pojo.District;
import com.softtantra.salesapp.pojo.EmailConfiguration;
import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.Route;
import com.softtantra.salesapp.pojo.SalesDetails;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.salesapp.pojo.SalesOrderImportRecords;
import com.softtantra.salesapp.pojo.States;
import com.softtantra.salesapp.pojo.Taluka;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.WorkFlowConfiguration;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;
import com.softtantra.salesapp.pojo.ZoneWiseStates;
import com.softtantra.servicemi.pojo.BusinessSegment;
import com.softtantra.servicemi.pojo.SM_ManageVehicles;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.WsInfluencerDetails;

public class CommonMethods {

	private static Properties fileProperties = new Properties();
	
	private static String platform = null;

	static {

		try {

			fileProperties.load(CommonMethods.class.getClassLoader().getResourceAsStream("/application.properties"));
			platform = fileProperties.getProperty("platform");
		} catch (Exception e) {

			FieldMILogger.error(CommonMethods.class.getName(), e);
		}
	}
	
	public static S3Operations getS3OperationClient( ) {
		
		if ( platform.equalsIgnoreCase("AWS") ) {
			
			return new AWSS3Operations();
		} else {
			
			return new GoogleS3Operations();
		}
	}

	public static Properties getFileProperties() {
		return fileProperties;
	}

	public List quaryListPaginationForSalesOrg(Session session, String query, WsFetchSalesOrg wsFetchSalesOrgInput)
			throws Exception {

		List list = null;

		int pageSize = 500;// Integer.parseInt(wsdispather.getPageSize());
		int pageNumber = wsFetchSalesOrgInput.getPage_no();

		try {
			if (pageNumber == 0) {
				Query q = session.createSQLQuery(query.toString());
				list = q.setMaxResults(pageSize).list();

				return list;
			} else {
				Query q = session.createSQLQuery(query.toString());
				q = q.setFirstResult(pageSize * (pageNumber - 1));
				list = q.setMaxResults(pageSize).list();

				return list;
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}
	}

	public int getTotalPagesCountSql(Session session, String query) throws Exception {

		try {
			// FieldMILogger.debug(this.getClass().getName(),"total count query "+query);
			int count = 0;
			List list = null;
			Query q = session.createSQLQuery(query);
			list = q.list();

			for (Object object : list) {
				count = Integer.parseInt(object + "");
			}

			return count;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}
	}

	public List quaryListPagination(Session session, String query, int pageNo, int pageSize) throws Exception {

		List list = null;

		try {

			Query q = session.createQuery(query);
			q = q.setFirstResult(pageSize * (pageNo - 1));
			list = q.setMaxResults(pageSize).list();

			return list;

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}
	}

	public List quarySQLListPagination(Session session, String query, int pageNo, int pageSize) throws Exception {

		List list = null;

		try {

			Query q = session.createSQLQuery(query);
			q = q.setFirstResult(pageSize * (pageNo - 1));
			list = q.setMaxResults(pageSize).list();

			return list;

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}
	}

	public List<Integer> getUserHirachyWise(int company_id, int intValue, Session session, String country, String state,
			String city, String divId) {

		try {

			List<User> uList = new ArrayList<>();
			List<User> uListnew = new ArrayList<>();

			List<Integer> uListnew1 = new ArrayList<>();
			int u_id = 0;

			String sqlCountry = "";
			String sqlState = "";
			String sqlCity = "";
			String sqlDiv = "";
			if (country != null && !"".equals(country)) {
				sqlCountry = " and country_id=" + country + " ";
			}
			if (state != null && !"".equals(state)) {
				sqlState = " and new_state=" + state + " ";
			}
			if (city != null && !"".equals(city)) {
				sqlCity = " and new_city=" + city + " ";
			}
			if (divId != null && !"".equals(divId)) {
				sqlDiv = " and division_id=" + divId + " ";
			}

			Query q = session.createSQLQuery(
					"select user_id,first_name,last_name,reporting_manager_id from user_details where company_id="
							+ company_id + sqlCountry + sqlState + sqlCity + sqlDiv
							+ " and status=1 order by first_name asc");
			List<User> alllist = q.list();

			for (Object obj : alllist) {
				Object[] o = (Object[]) obj;

				User u = new User();
				u.setUser_id(Integer.parseInt(o[0] + ""));
				u.setFirst_name(o[1] + " " + o[2]);
				u.setReporting_manager_id(Integer.parseInt(o[3] + ""));
				uList.add(u);
			}

			for (int j = 0; j < uList.size(); j++) {

				if (uList.get(j).getUser_id() == intValue) {
					uListnew.add(uList.get(j));

				}
				if (uList.get(j).getReporting_manager_id() == intValue) {
					if (uListnew != null) {
						boolean flag = true;
						for (int m = 0; m < uListnew.size(); m++) {
							if (uListnew.get(m).getUser_id() == uList.get(j).getUser_id()) {
								flag = false;
								break;
							}
						}
						if (flag) {
							uListnew.add(uList.get(j));
						}
					}
				}
			}

			for (int k = 0; k < uListnew.size(); k++) {
				u_id = uListnew.get(k).getUser_id();
				for (int j = 0; j < uList.size(); j++) {

					if (uList.get(j).getReporting_manager_id() == u_id) {
						if (uListnew != null) {

							boolean flag = true;
							for (int m = 0; m < uListnew.size(); m++) {
								if (uListnew.get(m).getUser_id() == uList.get(j).getUser_id()) {
									// FieldMILogger.debug(this.getClass().getName(),"uList.get(j).getUser_id()"+uList.get(j).getFirst_name()+"
									// "+uList.get(j).getLast_name());
									flag = false;
									break;
								}
							}
							if (flag) {
								uListnew.add(uList.get(j));
							}

						}
					}
				}

			}
			for (int k = 0; k < uListnew.size(); k++) {
				uListnew1.add(uListnew.get(k).getUser_id());

			}

			return uListnew1;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}
	}

	// Hibernate query for count the records
	public int getTotalPagesCountHql(Session session, String query) throws Exception {

		try {
			// FieldMILogger.debug(this.getClass().getName(),"total count query "+query);
			int count = 0;
			List list = null;
			Query q = session.createQuery(query);
			list = q.list();

			for (Object object : list) {
				count = Integer.parseInt(object + "");
			}

			return count;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}
	}

	public List<SM_ManageVehicles> paginationForVehicleMaster(Session session, int pageNo, String route,
			long company_id) throws Exception {

		List<SM_ManageVehicles> list = null;

		int pageSize = 1000;// Integer.parseInt(wsdispather.getPageSize());
		int pageNumber = pageNo;

		String subQuery = "";
		subQuery = "From SM_ManageVehicles where route='" + route + "' and company_id=" + company_id;
		try {

			if (pageNo == 0) {
				Query q = session.createQuery(subQuery.toString());
				list = q.list();

				return list;
			} else {
				Query q1 = session.createQuery(subQuery.toString());
				q1 = q1.setFirstResult(pageSize * (pageNumber - 1));
				list = q1.setMaxResults(pageSize).list();
				return list;
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}

	}

	public List quaryListForPagination(Session session, String query, Credentials credentials) {
		// TODO Auto-generated method stub
		List list = null;

		int pageSize = 20;
		int pageNumber = credentials.getPage_no();

		try {
			Query q = session.createQuery(query.toString());
			q = q.setFirstResult(pageSize * (pageNumber - 1));
			list = q.setMaxResults(pageSize).list();
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}
		return list;
	}

	public Workbook exportExcelTemplate(List<ExcelColumnDefinition> columnDefList, String xlsFileName,
			Workbook workbook) {

		if (workbook == null)
			workbook = new XSSFWorkbook();
		HashMap<String, Sheet> nameVsSheet = new HashMap<>();

		// create user sheet columns
		CellStyle headerStyle = workbook.createCellStyle();
		Font headerFont = workbook.createFont();
		headerFont.setColor(IndexedColors.WHITE.getIndex());

		// Setting Background color
		headerStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFont(headerFont);

		for (ExcelColumnDefinition colmnDef : columnDefList) {

			Sheet sheet = null;
			if (nameVsSheet.containsKey(colmnDef.getSheetName()))
				sheet = nameVsSheet.get(colmnDef.getSheetName());
			else {

				sheet = workbook.createSheet(colmnDef.getSheetName());
				sheet.createRow(0);
				sheet.setDefaultColumnWidth(30);
				nameVsSheet.put(colmnDef.getSheetName(), sheet);
			}

			Cell cell = sheet.getRow(0).createCell(colmnDef.getColumnNo());
			cell.setCellValue(colmnDef.getColumnName());
			cell.setCellStyle(headerStyle);
			if (colmnDef.getRefDataList().size() > 0 || colmnDef.getRefDependentLov().size() > 0)
				fillHiddenSheets(workbook, colmnDef, nameVsSheet);
		}

		if (xlsFileName != null) {

			try {
				// this Writes the workbook gfgcontribute
				FileOutputStream out = new FileOutputStream(new File(xlsFileName));
				workbook.write(out);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return workbook;

	}

	private void fillHiddenSheets(Workbook workbook, ExcelColumnDefinition colmnDef,
			HashMap<String, Sheet> nameVsSheet) {

		if (colmnDef.getRefDataList().size() > 0 || colmnDef.getRefDependentLov().size() > 0) {

			Sheet mainSheet = nameVsSheet.get(colmnDef.getSheetName());
			if (!nameVsSheet.containsKey(colmnDef.getRefSheetName())) {

				Sheet refSheet = workbook.createSheet(colmnDef.getRefSheetName());
				refSheet.setDefaultColumnWidth(30);
				nameVsSheet.put(colmnDef.getRefSheetName(), refSheet);

				if (colmnDef.getRefDataList().size() > 0) {

					for (int i = 0; i < colmnDef.getRefDataList().size(); i++) {

						refSheet.createRow(i).createCell(0).setCellValue(colmnDef.getRefDataList().get(i));
					}
					Name namedCell = workbook.createName();
					namedCell.setNameName(colmnDef.getRefSheetName() + "Name");
					String reference = colmnDef.getRefSheetName() + "!$A$1:$A$" + colmnDef.getRefDataList().size();
					namedCell.setRefersToFormula(reference);

					workbook.setSheetHidden(colmnDef.getRefSheetNo(), true);

					DataValidationHelper mealdvHelper = mainSheet.getDataValidationHelper();

					// Instead of creating a potentially long constraint formula, use a named range
					String namedRange = colmnDef.getRefSheetName() + "Name"; // Name of the named range
					DataValidationConstraint dvConstraint = mealdvHelper.createFormulaListConstraint(namedRange);

					CellRangeAddressList rangeList = new CellRangeAddressList(1, 10000, colmnDef.getColumnNo(), colmnDef.getColumnNo());
					DataValidation categoryDataValidation = mealdvHelper.createValidation(dvConstraint, rangeList);

					// Set data validation attributes
					categoryDataValidation.setShowErrorBox(true);
					categoryDataValidation.setSuppressDropDownArrow(true);
					categoryDataValidation.setShowPromptBox(true);

					// Add the validation to the main sheet
					mainSheet.addValidationData(categoryDataValidation);

				} else if (colmnDef.getRefDependentLov().size() > 0) {

					Row row;
					Name namedRange;
					String colLetter;
					String reference;
					String clmnName = colmnDef.getColumnName();
					int clmnNo = 0;
					for (String key : colmnDef.getRefDependentLov().keySet()) {

						int rowNum = 0;
						row = refSheet.getRow(rowNum);
						if (row == null)
							row = refSheet.createRow(rowNum);
						rowNum++;
						row.createCell(clmnNo).setCellValue(key);

						List<String> items = colmnDef.getRefDependentLov().get(key);
						for (String item : items) {
							row = refSheet.getRow(rowNum);
							if (row == null)
								row = refSheet.createRow(rowNum);
							rowNum++;
							row.createCell(clmnNo).setCellValue(item);
						}
						// create names for the item list constraints, each named from the current key
						colLetter = CellReference.convertNumToColString(clmnNo);
						namedRange = workbook.createName();
						Pattern pattern = Pattern.compile(".*\\d.*");
						Pattern pattern2 = Pattern.compile("^[^_\\s].*$");

						// Create a matcher with the string
						Matcher matcher = pattern.matcher(key);
						Matcher matcher2 = pattern2.matcher(key);

						// Check if the string contains any digit character
						boolean containsDigit = matcher.matches();
						boolean contains_Orletter = matcher2.matches();

						if (containsDigit || !contains_Orletter) {
							continue;
						}
						namedRange.setNameName(key);
						reference = colmnDef.getRefSheetName() + "!$" + colLetter + "$2:$" + colLetter + "$" + rowNum;
						namedRange.setRefersToFormula(reference);
						clmnNo++;
					}

					// create name for Categories list constraint
					colLetter = CellReference.convertNumToColString((clmnNo - 1));
					namedRange = workbook.createName();
					namedRange.setNameName(clmnName.replace(" ", "").trim() + "Name");
					reference = colmnDef.getRefSheetName() + "!$A$1:$" + colLetter + "$1";
					namedRange.setRefersToFormula(reference);

					workbook.setSheetHidden(colmnDef.getRefSheetNo(), true);

					colLetter = CellReference.convertNumToColString(colmnDef.getColumnNo());
					String dependentCollLetter = CellReference
							.convertNumToColString(colmnDef.getDependentColumnDef().get(0).getColumnNo());
					mainSheet.setActiveCell(new CellAddress(colLetter + "2"));

					// data validations
					DataValidationHelper dvHelper = mainSheet.getDataValidationHelper();
					// data validation for categories in A2:
					String constraint = "=IF($" + dependentCollLetter + "2=\"\"," + clmnName.replace(" ", "").trim()
							+ "Name,INDIRECT(" + "\"Nothing\"" + "))";
					DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(constraint);
					CellRangeAddressList addressList = new CellRangeAddressList(1, 10000, colmnDef.getColumnNo(),
							colmnDef.getColumnNo());
					DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
					validation.setShowErrorBox(true);
					validation.setSuppressDropDownArrow(true);
					validation.setShowPromptBox(true);
					mainSheet.addValidationData(validation);

					// data validation for items of the selected category in B2:

					dvConstraint = dvHelper.createFormulaListConstraint("INDIRECT($" + colLetter + "2)");
					List<ExcelColumnDefinition> dependeList = colmnDef.getDependentColumnDef();
					for (ExcelColumnDefinition dependentColumn : dependeList) {

						addressList = new CellRangeAddressList(1, 10000, dependentColumn.getColumnNo(),
								dependentColumn.getColumnNo());
						validation = dvHelper.createValidation(dvConstraint, addressList);
						validation.setShowErrorBox(true);
						validation.setSuppressDropDownArrow(true);
						validation.setShowPromptBox(true);
						mainSheet.addValidationData(validation);
					}
				}
			}
		}
	}

	public static void exportExcel(String[] args) {

		CommonMethods cm = new CommonMethods();

		String xlsFileName = "/Users/parag/Downloads/Users_Template.xlsx";

		String userSheetName = "User Details";
		String rolesSheetName = "Roles";
		String companyLocationSheetName = "CompanyLocations";
		String buSheetName = "BusinessUnits";
		String divSheetName = "Divsions";
		String circlesSheetName = "Circles";
		String zonesSheetName = "Zones";
		String shiftNameSheetName = "ShiftNames";

		List<String> userSheetClmns = new ArrayList<String>();
		userSheetClmns.add("Email");
		userSheetClmns.add("User Login");
		userSheetClmns.add("Employee Code");
		userSheetClmns.add("First Name");
		userSheetClmns.add("Last Name");
		userSheetClmns.add("Password");
		userSheetClmns.add("Mobile No.");
		userSheetClmns.add("Home Location");
		userSheetClmns.add("Date of Birth");
		userSheetClmns.add("Date of Joining");
		userSheetClmns.add("Blood Group");
		userSheetClmns.add("Bank A/C Number");
		userSheetClmns.add("IFSC Code");
		userSheetClmns.add("Vendor Code");
		userSheetClmns.add("Weekly Off");
		userSheetClmns.add("Role");
		userSheetClmns.add("Reporting Role");
		userSheetClmns.add("Manager Email");
		userSheetClmns.add("Shift Name");
		userSheetClmns.add("Company Location");
		userSheetClmns.add("Business Unit");
		userSheetClmns.add("Division");
		userSheetClmns.add("Circle");
		userSheetClmns.add("Zone");

		int clmn = 0;
		List<ExcelColumnDefinition> columnDefList = new ArrayList<>();
		for (String clmnName : userSheetClmns) {

			ExcelColumnDefinition excelColumnDef = new ExcelColumnDefinition();
			excelColumnDef.setColumnName(clmnName);
			excelColumnDef.setColumnNo(clmn++);
			excelColumnDef.setSheetName(userSheetName);
			columnDefList.add(excelColumnDef);
		}

		List<String> roles = new ArrayList();
		roles.add("Manager");
		roles.add("System Admin");
		columnDefList.get(15).setRefDataList(roles);
		columnDefList.get(15).setRefSheetNo(1);
		columnDefList.get(15).setRefSheetName(rolesSheetName);
		columnDefList.get(16).setRefDataList(roles);
		columnDefList.get(16).setRefSheetNo(1);
		columnDefList.get(16).setRefSheetName(rolesSheetName);

		List<String> shiftNames = new ArrayList();
		shiftNames.add("Morning");
		shiftNames.add("Night");
		columnDefList.get(18).setRefDataList(shiftNames);
		columnDefList.get(18).setRefSheetNo(2);
		columnDefList.get(18).setRefSheetName(shiftNameSheetName);

		List<String> compantLocations = new ArrayList();
		compantLocations.add("Pune");
		compantLocations.add("Mumbai");
		columnDefList.get(19).setRefDataList(compantLocations);
		columnDefList.get(19).setRefSheetNo(3);
		columnDefList.get(19).setRefSheetName(companyLocationSheetName);

		List<String> buUnits = new ArrayList();
		buUnits.add("Steel");
		buUnits.add("Retail");
		columnDefList.get(20).setRefDataList(buUnits);
		columnDefList.get(20).setRefSheetNo(4);
		columnDefList.get(20).setRefSheetName(buSheetName);

		List<String> divisions = new ArrayList();
		divisions.add("RnD");
		divisions.add("QV");
		columnDefList.get(21).setRefDataList(divisions);
		columnDefList.get(21).setRefSheetNo(5);
		columnDefList.get(21).setRefSheetName(divSheetName);

		List<String> circles = new ArrayList();
		circles.add("Maharashtra Circle");
		circles.add("Karnataka Circle");
		columnDefList.get(22).setRefDataList(circles);
		columnDefList.get(22).setRefSheetNo(6);
		columnDefList.get(22).setRefSheetName(circlesSheetName);

		List<String> zones = new ArrayList();
		zones.add("North Zone");
		zones.add("West Zone");
		columnDefList.get(23).setRefDataList(zones);
		columnDefList.get(23).setRefSheetNo(7);
		columnDefList.get(23).setRefSheetName(zonesSheetName);

		cm.exportExcelTemplate(columnDefList, xlsFileName, null);
	}

	public static void updatePassword(String[] args) {

		try {

			String xlsFileName = "/Users/parag/Documents/UserLogin.xlsx";

			Workbook workBook = new XSSFWorkbook(new File(xlsFileName));
			Sheet sheet = workBook.getSheetAt(0);

			int currRow = 0;
			for (Row row : sheet) {

				if (currRow == 0) {

					currRow++;
					continue;
				}
				DataFormatter formatter = new DataFormatter();
				String password = formatter.formatCellValue(row.getCell(4));

				if (password == null || password.trim().length() == 0)
					password = "Sales@1234";

				row.getCell(4).setCellValue(password);

				currRow++;
			}

			FileOutputStream outFile = new FileOutputStream(new File(xlsFileName));
			workBook.write(outFile);
			outFile.close();
			workBook.close();

		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	private boolean containsPattern(String pattern, String password) {

		if (password.matches(pattern))
			return true;
		else
			return false;
	}

	public static boolean uploadMultiplefiles(String objectKey, byte byteArray[]) {
		try {
			String bucketName = fileProperties.getProperty("bucketName");
			S3Operations s3Operatons = getS3OperationClient();
			boolean result = s3Operatons.addFilesToS3(bucketName, objectKey, new ByteArrayInputStream(byteArray),
					byteArray.length);
			if (result)
				return true;
			else
				return false;
		} catch (Exception e) {
			throw e;
		}
	}

	public Properties configureEmailProperties(Properties props, EmailConfiguration emailConfiguration) {

		props.put("mail.smtp.auth", "true");
		if (emailConfiguration.getIsSSL() == 0) {
			props.put("mail.smtp.starttls.enable", "false");
		} else {

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "true");
		}
		props.put("mail.smtp.host", emailConfiguration.getHost_name());
		props.put("mail.smtp.port", "" + emailConfiguration.getPort_no());

		return props;
	}

	public String checkIfMandatoryColumnAreEmpty(DataFormatter fmt, Row row, List<String> clmNames,
			List<String> mandateClmnNames) {

		StringBuilder messageBuffer = new StringBuilder();
		int count = 0;
		for (String clmName : mandateClmnNames) {

			if (row.getCell(clmName.indexOf(clmName)) != null
					&& fmt.formatCellValue(row.getCell(clmName.indexOf(clmName))).trim().length() == 0) {
				if (count == 0)
					messageBuffer.append("Missing value for ");

				messageBuffer.append(clmName).append(", ");
			}
			if (clmName.equals("Email")) {

				String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
				Pattern pattern = Pattern.compile(regex);
				String email = fmt.formatCellValue(row.getCell(clmName.indexOf(clmName))).trim();
				Matcher matcher = pattern.matcher(email);
				if (!matcher.matches())
					messageBuffer.append("Invalid email ").append(email);
			}

		}

		return messageBuffer.toString();
	}

	public void recommendPricingWorkflow(int sales_master_id, int c_id, String comment, String custom_field_date,
			int u_id, FMSalesMasterService fmSalesMasterService, FMUserService userService,
			FMWorkflowConfigurationService fmWorkflowConfigurationService,
			WorkFlowAuditLogService workFlowAuditLogService, WorkFlowTaskStatusService workFlowTaskStatusService) {

		SalesMIDBUtils dbUtils = new SalesMIDBUtils();
		// first approve his own orders.
		List<WorkFlowTasksStatus> workTaskStatusDetailList = workFlowTaskStatusService.getSalesOrderTasksBasedOnStatus(
				c_id, sales_master_id, SalesMIDBUtils.PRICING_ISSUE_TASK, SalesMIDBUtils.PENDING,
				SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
		for (WorkFlowTasksStatus workFlowTasksStatus : workTaskStatusDetailList) {

			dbUtils.updateWorkflowObject(workFlowTasksStatus, workFlowTaskStatusService, workFlowAuditLogService,
					fmWorkflowConfigurationService, SalesMIDBUtils.APPROVED, u_id, c_id, comment, custom_field_date,
					true, SalesMIDBUtils.ALL);
		}

		// recommend remaining by checking the condition
		workTaskStatusDetailList = workFlowTaskStatusService.getSalesOrderTasksBasedOnStatus(c_id, sales_master_id,
				SalesMIDBUtils.PRICING_ISSUE_TASK, SalesMIDBUtils.RECOMMEND, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
		List<WorkFlowConfiguration> workflowConfigurationList = fmWorkflowConfigurationService.getWorkflowConfigs(
				SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME, SalesMIDBUtils.PRICING_ISSUE_TASK, c_id, SalesMIDBUtils.ALL);
		if (workflowConfigurationList.get(0).getApproval_condition().equals(SalesMIDBUtils.ALLOWED_DISCOUNT)) {

			recommendRemainingSalesDetails(userService, fmSalesMasterService, fmWorkflowConfigurationService,
					workFlowAuditLogService, workFlowTaskStatusService, workTaskStatusDetailList, u_id, c_id, comment,
					custom_field_date);
		} else {

			for (WorkFlowTasksStatus workFlowTasksStatus : workTaskStatusDetailList) {

				dbUtils.updateWorkflowObject(workFlowTasksStatus, workFlowTaskStatusService, workFlowAuditLogService,
						fmWorkflowConfigurationService, SalesMIDBUtils.PENDING, u_id, c_id, comment, custom_field_date,
						false, SalesMIDBUtils.ALL);
			}
		}
	}

	private void recommendRemainingSalesDetails(FMUserService userService, FMSalesMasterService fmSalesMasterService,
			FMWorkflowConfigurationService fmWorkflowConfigurationService,
			WorkFlowAuditLogService workFlowAuditLogService, WorkFlowTaskStatusService workFlowTaskStatusService,
			List<WorkFlowTasksStatus> workTaskStatusDetailList, int u_id, int c_id, String comment,
			String custom_field_date) {

		if (workTaskStatusDetailList != null && workTaskStatusDetailList.size() > 0) {

			SalesMIDBUtils dbUtils = new SalesMIDBUtils();
			List<String> salesDetailsIdList = new ArrayList<String>();
			for (WorkFlowTasksStatus workFlowTasksStatus : workTaskStatusDetailList) {

				salesDetailsIdList.add("" + workFlowTasksStatus.getSales_details_id());
			}

			List<SalesDetails> salesDetails = fmSalesMasterService.getSalesDetailsList(salesDetailsIdList);
			Iterator<SalesDetails> listItr = salesDetails.iterator();
			HashMap<String, Double> salesDetailsVsDiscount = new HashMap<>();
			HashMap<String, Double> salesDetailsVsProductStock = new HashMap<>();
			while (listItr.hasNext()) {

				SalesDetails salesDetail = listItr.next();
				salesDetailsVsDiscount.put("" + salesDetail.getSales_details_id(), salesDetail.getDiscount());
				salesDetailsVsProductStock.put("" + salesDetail.getSales_details_id(), salesDetail.getProduct_stock());
			}

			User currUser = userService.getUserInfoForEdit(u_id, c_id);
			if (currUser != null && currUser.getReporting_manager_id() != 0) {

				User reportingManager = userService.getUserInfoForEdit(currUser.getReporting_manager_id(), c_id);
				Iterator<WorkFlowTasksStatus> workFlowItr = workTaskStatusDetailList.iterator();
				while (workFlowItr.hasNext()) {

					WorkFlowTasksStatus workFlowTasksStatus = workFlowItr.next();

					if (salesDetailsVsDiscount.containsKey("" + workFlowTasksStatus.getSales_details_id())) {

						double givenSalesDetailDiscount = salesDetailsVsDiscount
								.get("" + workFlowTasksStatus.getSales_details_id());
						double stock = salesDetailsVsProductStock.get("" + workFlowTasksStatus.getSales_details_id());
						double usertotalAllowedDiscount = currUser.getAllowed_discount() * stock;
						double rptMgrtotalAllowedDiscount = reportingManager.getAllowed_discount() * stock;

						String status = SalesMIDBUtils.RECOMMEND;
						if (givenSalesDetailDiscount > usertotalAllowedDiscount
								&& givenSalesDetailDiscount <= rptMgrtotalAllowedDiscount) {

							// set pending if the approval for upper level is within limits and above curr
							// user
							status = SalesMIDBUtils.PENDING;
						}

						dbUtils.updateWorkflowObject(workFlowTasksStatus, workFlowTaskStatusService,
								workFlowAuditLogService, fmWorkflowConfigurationService, status, u_id, c_id, comment,
								custom_field_date, false, SalesMIDBUtils.ALL);
						workFlowItr.remove();
					}
				}
			}

		}
	}

	public List<WsExpense> expenseList(int c_id, Session session, String next_approver_id, String fromDate,
			String toDate, int u_id, String sqlCountry, String sqlState, String sqlCity, String sqlUser,
			String sqlUserName) {

		List<WsExpense> eList = new ArrayList<>();
		try {
			String sqlDate = "";

			String query = "";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (fromDate != null && toDate != null) {
				sqlDate = " AND  DATE(e.expense_claim_date) BETWEEN '" + fromDate + "'  AND '" + toDate + "'";
			}

			if (u_id == 0) {

				query = "select e.expense_master_id,concat(u.first_name,' ',u.last_name) as username,"
						+ "e.total,e.created_date,IFNULL(e.comment,'-'),e.status,e.user_id,u.country_id,isDefault,"
						+ "expense_claim_date,approvedamount,approver_id,e.approveRejectStatus,u.reporting_manager_id"
						+ " from user_details u join expense_master e  on  e.user_id=u.user_id  where e.status<>0"
						+ " and e.company_id=" + c_id
						+ " and e.expense_master_id in (select distinct(w.sales_master_id) from work_flow_task_status w where w.company_id="
						+ c_id + " and w.workflow_name='" + SalesMIDBUtils.EXPENSE_WORKFLOW_NAME + "' and w.task_name='"
						+ SalesMIDBUtils.EXPENSE_ISSUE_TASK + "' and w.next_approver_id in " + next_approver_id + " ) "
						+ sqlDate + " " + sqlCountry + " " + sqlState + " " + sqlCity + " " + sqlUser + " "
						+ sqlUserName + "order by e.created_date desc";
			} else {

				query = "select e.expense_master_id,concat(u.first_name,' ',u.last_name) as username,"
						+ "e.total,e.created_date,IFNULL(e.comment,'-'),e.status,e.user_id,u.country_id,isDefault,"
						+ "expense_claim_date, approvedamount,approver_id,e.approveRejectStatus,u.reporting_manager_id"
						+ " from user_details u join expense_master e  on  e.user_id=u.user_id  where e.status<>0"
						+ " and e.company_id=" + c_id
						+ " and e.expense_master_id in (select distinct(w.sales_master_id) from work_flow_task_status w where w.company_id="
						+ c_id + " and w.workflow_name='" + SalesMIDBUtils.EXPENSE_WORKFLOW_NAME + "' and w.task_name='"
						+ SalesMIDBUtils.EXPENSE_ISSUE_TASK + "') " + sqlDate + " " + sqlCountry + " " + sqlState + " "
						+ sqlCity + " " + sqlUser + " " + sqlUserName + " and e.created_by=" + u_id
						+ " order by e.created_date desc";

			}

			Query q = session.createSQLQuery(query);

			List<WsExpense> alllist = q.list();

			for (Object obj : alllist) {
				Object[] o = (Object[]) obj;

				WsExpense u = new WsExpense();
				u.setExpense_master_id((int) o[0]);
				u.setUserName((String) o[1]);
				u.setTotal(BigDecimal.valueOf(Double.parseDouble(o[2] + "")));
				u.setUser_id((int) o[6]);
				u.setComment((String) o[4]);
				u.setAppAmount(BigDecimal.valueOf(Double.parseDouble(o[10] + "")));
				u.setCreated_date(format.format((Date) o[3]));

				eList.add(u);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return eList;

	}

	public boolean checkIfCoumnMisMatch(List<String> productSheetClmns, Row firstrow) {

		if (firstrow.getLastCellNum() == productSheetClmns.size()) {

			int clmnNo = 0;
			for (String columnName : productSheetClmns) {

				if (!firstrow.getCell(clmnNo).getStringCellValue().equals(columnName))
					return true;

				clmnNo++;
			}
		} else {

			return true;
		}

		return false;

	}

	public static double roundDouble(double d, int places) {

		BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
		bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
		return bigDecimal.doubleValue();
	}

	public static float roundFloat(float f, int places) {

		BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
		bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
		return bigDecimal.floatValue();
	}

	public String partitionToFetch(String fromDate, String toDate) {

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

					// add all months till december
					while (fromMonth != 13) {

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
					// add all months post december till toMonth
					while (fromMonth <= toMonth) {

						if (mtCnt != 0)
							partitionToFetch = partitionToFetch + ",";

						if (fromMonth != 12)
							partitionToFetch = partitionToFetch + "p" + fromMonth;
						else
							partitionToFetch = partitionToFetch + "p0";

						mtCnt++;
						fromMonth++;

					}

					partitionToFetch = partitionToFetch + ") ";
				} else if (diff == 0) {

					if (fromMonth != 12)
						partitionToFetch = " PARTITION(p" + fromMonth + ") ";
					else
						partitionToFetch = " PARTITION(p0) ";
				}

			} catch (ParseException e1) {
			}
		}

		return partitionToFetch;
	}

	public String partitionTofecthUsingSplit(String fromDate) {

		String partitionToFetch = "";
		String[] monthSplitter = fromDate.split("-");
		if (monthSplitter.length == 3) {

			int fromMonth = Integer.parseInt(monthSplitter[1]);
			if (fromMonth != 12)
				partitionToFetch = " PARTITION(p" + fromMonth + ")";
			else
				partitionToFetch = " PARTITION(p0)";

		}

		return partitionToFetch;
	}

	public void importCustomers(Session session, int company_id, int u_id, List<WsCustomerDetails> customerListToImport,
			List<CustomerDetails> customerList, List<CustomerImportRecords> importMessagesList,
			StringBuilder importMessage) throws Exception {

		try {

			FieldMILogger.info(this.getClass().getName(), "Customer List Size from SAP:" + customerListToImport.size());

			HashMap<String, Object> defaultValueMap = getDefaultValues(session, company_id);
			for (WsCustomerDetails sfdcCustomerData : customerListToImport) {

				CustomerDetails customerDetails = null;
				importMessage = new StringBuilder();
				boolean customerExists = false;
				Query<CustomerDetails> queryCustomer = session.createQuery("From CustomerDetails where code='"
						+ sfdcCustomerData.getCode() + "' and company_id=" + company_id, CustomerDetails.class);
				List<CustomerDetails> fieldMICustomerList = queryCustomer.list();
				if (fieldMICustomerList != null && fieldMICustomerList.size() > 0) {

					customerExists = true;
					// customerDetails.setCustomer_id(Integer.parseInt(""+fieldMICustomerList.get(0)));
					customerDetails = fieldMICustomerList.get(0);
				} else {

					customerDetails = new CustomerDetails();
				}

				customerDetails.setCode(sfdcCustomerData.getCode());
				customerDetails.setCustomer_name(sfdcCustomerData.getCustomer_name());
				customerDetails.setCustomerAccountGroup(sfdcCustomerData.getCustomerAccountGroup());
				customerDetails.setCompany_id(company_id);
				customerDetails.setCreated_by(u_id);
				customerDetails.setUpdated_by(u_id);
				customerDetails.setStatus(1);
				customerDetails.setGst_no(sfdcCustomerData.getGst_no());
				customerDetails.setPan_card(sfdcCustomerData.getPan_card());
				customerDetails.setAddress(sfdcCustomerData.getAddress());
				customerDetails.setBilling_address(sfdcCustomerData.getBilling_address());
				customerDetails.setPincode(sfdcCustomerData.getPincode());
				customerDetails.setMobile(sfdcCustomerData.getMobile());
				customerDetails.setEmail(sfdcCustomerData.getEmail());
				customerDetails.setCredit_limit(0);
				customerDetails.setCredit_limit(0);
				customerDetails.setClassType(0);
				customerDetails.setStoreType(0);
				customerDetails.setLocation_no(0);
				customerDetails.setExportedToSAP(1);
				customerDetails.setSendToSAP(2);
				customerDetails.setCustomer_type(sfdcCustomerData.getCustomerType());

				// set Business Segment
				if (sfdcCustomerData.getBsegment() != null) {

					Query<BusinessSegment> queryBsegment = session.createQuery(
							"From BusinessSegment where upper(bsegment_name)='"
									+ sfdcCustomerData.getBsegment().toUpperCase() + "' and company_id=" + company_id,
							BusinessSegment.class);
					List<BusinessSegment> fieldMIBsegmentList = queryBsegment.list();

					if (fieldMIBsegmentList != null && fieldMIBsegmentList.size() > 0) {

						customerDetails.setbSegmentId(fieldMIBsegmentList.get(0).getBsegment_id());
					}
				} else {
					customerDetails.setbSegmentId(0);
				}

				if (sfdcCustomerData.getCountry_name() == null || sfdcCustomerData.getCountry_name().length() == 0) {

					importMessage.append("Country with name ").append(sfdcCustomerData.getCountry_name())
							.append(" not found. Country set to India.");
					sfdcCustomerData.setCountry_name("INDIA");
				}

				if (sfdcCustomerData.getStateId() == null || sfdcCustomerData.getStateId().length() == 0) {

					importMessage.append("State with name ").append(sfdcCustomerData.getStateId())
							.append(" not found. State set to Maharashtra");
					sfdcCustomerData.setStateId("MAHARASHTRA");
				}

				if (sfdcCustomerData.getCityId() == null || sfdcCustomerData.getCityId().length() == 0) {

					importMessage.append("City with name ").append(sfdcCustomerData.getCityId())
							.append(" not found. City set to Pune");
					sfdcCustomerData.setCityId("PUNE");
				}
				
				if (sfdcCustomerData.getDistributor_name() == null || sfdcCustomerData.getDistributor_name().length() == 0) {

					importMessage.append("Distributor with name ").append(sfdcCustomerData.getDistributor_name())
							.append(" not found. Distributor set to DEFAULT");
					sfdcCustomerData.setDistributor_name("DEFAULT");
				}
				
				if (sfdcCustomerData.getRoute_name() == null || sfdcCustomerData.getRoute_name().length() == 0) {

					importMessage.append("Route with name ").append(sfdcCustomerData.getRoute_name())
							.append(" not found. Distributor and Route set to DEFAULT");
					sfdcCustomerData.setDistributor_name("DEFAULT");
					sfdcCustomerData.setRoute_name("DEFAULT");
				}

				// set Country
				Query<Country> queryCountry = session.createQuery("From Country where upper(country_name)='"
						+ sfdcCustomerData.getCountry_name().toUpperCase() + "'", Country.class);
				List<Country> fieldMICountryList = queryCountry.list();
				if (fieldMICountryList != null && fieldMICountryList.size() > 0) {

					customerDetails.setCountry_id(fieldMICountryList.get(0).getCountry_id());
				} else {
					
					customerDetails.setCountry_id((Integer)defaultValueMap.get("COUNTRY"));
					importMessage.append("Country with name ").append(sfdcCustomerData.getCountry_name())
							.append(" not found. Country set to India.");
				}

				// set state
				Query<States> queryState = session.createQuery(
						"From States where upper(state_name)='" + sfdcCustomerData.getStateId().toUpperCase()
								+ "' and country_id=" + customerDetails.getCountry_id(),
						States.class);
				List<States> fieldMIStateList = queryState.list();
				if (fieldMIStateList != null && fieldMIStateList.size() > 0) {

					customerDetails.setNew_state(fieldMIStateList.get(0).getState_id());
				} else {

					customerDetails.setNew_state((Integer)defaultValueMap.get("STATE"));
					importMessage.append("State with name ").append(sfdcCustomerData.getStateId())
							.append(" not found. State set to Maharashtra.");
				}

				// set zone
				Query<ZoneWiseStates> queryZone = session.createQuery("From ZoneWiseStates where stateId = "
						+ customerDetails.getNew_state() + " and company_id=" + company_id, ZoneWiseStates.class);
				List<ZoneWiseStates> fieldMIZoneList = queryZone.list();
				if (fieldMIZoneList != null && fieldMIZoneList.size() > 0) {

					customerDetails.setZone_id(fieldMIZoneList.get(0).getZoneId());
				} else {

					customerDetails.setZone_id(0);
					importMessage.append("Zone with name ").append(sfdcCustomerData.getZoneName())
							.append(" not found. Zone set to 0");
				}

				// set city, district code and taluka code
				Query<Citys> queryCitys = session
						.createQuery("From Citys where upper(city_name)='" + sfdcCustomerData.getCityId().toUpperCase()
								+ "' and state_id=" + customerDetails.getNew_state(), Citys.class);
				List<Citys> fieldMICitysList = queryCitys.list();
				if (fieldMICitysList != null && fieldMICitysList.size() > 0) {

					customerDetails.setNew_city(fieldMICitysList.get(0).getCity_id());
					Query<District> queryDistrict = session.createQuery(
							"From District where district_id=" + fieldMICitysList.get(0).getDistrict_id(),
							District.class);
					District fieldMIDistrict = queryDistrict.uniqueResult();
					if (fieldMIDistrict != null) {

						customerDetails.setDistrict(fieldMIDistrict.getDistrictCode());
					}

					Query<Taluka> queryTaluka = session.createQuery(
							"From Taluka where taluka_id=" + fieldMICitysList.get(0).getTaluka_id(), Taluka.class);
					Taluka fieldMITaluka = queryTaluka.uniqueResult();
					if (fieldMITaluka != null) {

						customerDetails.setTown(fieldMITaluka.getTalukaCode());
					}

				} else {

					customerDetails.setNew_city((Integer)defaultValueMap.get("CITY"));
					customerDetails.setDistrict((String) defaultValueMap.get("DISTRICT"));
					customerDetails.setTown((String) defaultValueMap.get("TALUKA"));
					
					importMessage.append("City with name ").append(sfdcCustomerData.getCityId())
							.append(" not found. City, District and taluka set for Pune.");
				}

				// set distributor and route
				Query<DistributorDetails> queryDistributor = session
						.createQuery("From DistributorDetails where upper(distributor_name)='"
								+ sfdcCustomerData.getDistributor_name().toUpperCase() + "' and company_id="
								+ company_id, DistributorDetails.class);
				List<DistributorDetails> fieldMIDistributorList = queryDistributor.list();
				if (fieldMIDistributorList != null && fieldMIDistributorList.size() > 0) {

					customerDetails.setDistributor_id(fieldMIDistributorList.get(0).getDistributor_id());

					// set Route
					Query<Route> queryRoute = session.createQuery("From Route where upper(route_name)='"
							+ sfdcCustomerData.getRoute_name().toUpperCase() + "' and company_id=" + company_id
							+ " and distributor_id=" + customerDetails.getDistributor_id(), Route.class);
					List<Route> fieldMIRouteList = queryRoute.list();
					if (fieldMIRouteList != null && fieldMIRouteList.size() > 0) {

						customerDetails.setRoute_id(fieldMIRouteList.get(0).getRoute_id());
					} else {

						customerDetails.setDistributor_id((Integer) defaultValueMap.get("DISTRIBUTOR"));
						customerDetails.setRoute_id((Integer) defaultValueMap.get("ROUTE"));
						importMessage.append("Route with name ").append(sfdcCustomerData.getRoute_name())
								.append(" not found. Distributor and ROUTE set to DEFAULT.");

						customerDetails.setRoute_id(0);
					}
				} else {

					customerDetails.setDistributor_id((Integer) defaultValueMap.get("DISTRIBUTOR"));
					customerDetails.setRoute_id((Integer) defaultValueMap.get("ROUTE"));
					importMessage.append("Distributor with name ").append(sfdcCustomerData.getDistributor_name())
							.append(" not found. Distributor and ROUTE set to DEFAULT");

				}

				addLogToImport(session, importMessagesList, sfdcCustomerData, importMessage, company_id, u_id);
				if (customerExists)
					session.update(customerDetails);
				else {

					customerDetails.setCreated_date(sfdcCustomerData.getCreated_date());
					customerDetails.setUpdated_date(sfdcCustomerData.getCreated_date());
					session.save(customerDetails);
				}

				customerList.add(customerDetails);
				FieldMILogger.info(this.getClass().getName(),
						"Updated customer:" + customerDetails.getCustomer_id() + " Code:" + customerDetails.getCode());

			}

		} catch (Exception ex) {

			throw ex;
		}

	}

	private HashMap<String, Object> getDefaultValues(Session session, int company_id) {

		try {

			HashMap<String, Object> defaultValues = new HashMap<String, Object>();
			Query<Country> queryCountry = session.createQuery("From Country where upper(country_name)='INDIA'",
					Country.class);
			Query<States> queryState = session.createQuery("From States where upper(state_name)='MAHARASHTRA'",
					States.class);
			Query<Citys> queryCity = session.createQuery("From Citys where upper(city_name)='PUNE'", Citys.class);			
			
			
			
			Country country = queryCountry.uniqueResult();
			States state = queryState.uniqueResult();
			Citys city = queryCity.uniqueResult();

			Query<DistributorDetails> queryDistributor = session.createQuery(
					"From DistributorDetails where upper(distributor_name)='DEFAULT' and company_id=" + company_id,
					DistributorDetails.class);
			DistributorDetails distributor = queryDistributor.uniqueResult();
			
			Query<Route> queryRoute = session.createQuery("From Route where upper(route_name)='DEFAULT' and company_id=" + company_id
					+ " and distributor_id=" + distributor.getDistributor_id(), Route.class);

			Route route = queryRoute.uniqueResult();

			defaultValues.put("COUNTRY", country.getCountry_id());
			defaultValues.put("STATE", state.getState_id());
			defaultValues.put("CITY", city.getCity_id());
			defaultValues.put("DISTRICT", "PUNE");
			defaultValues.put("TALUKA", "HAVELI");
			defaultValues.put("DISTRIBUTOR", distributor.getDistributor_id());
			defaultValues.put("ROUTE", route.getRoute_id());

			return defaultValues;
		} catch (Exception ex) {

			throw ex;
		}
	}

	private void addLogToImport(Session session, List<CustomerImportRecords> importMessagesList,
			WsCustomerDetails sfdcCustomerData, StringBuilder importMessage, int company_id, int u_id) {

		String msg = importMessage.toString();

		if (sfdcCustomerData != null) {
			CustomerImportRecords cRecords = new CustomerImportRecords();
			cRecords.setState(sfdcCustomerData.getStateId());
			cRecords.setCity(sfdcCustomerData.getCityId());
			cRecords.setCustomer_name(sfdcCustomerData.getCustomer_name());
			cRecords.setCompany_id(company_id);
			cRecords.setUser_id(u_id);
			cRecords.setGst_no(sfdcCustomerData.getGst_no());
			cRecords.setAddress(sfdcCustomerData.getAddress());
			cRecords.setPincode(sfdcCustomerData.getPincode());
			cRecords.setMobile(sfdcCustomerData.getMobile());
			cRecords.setStatus(0);
			cRecords.setCode(sfdcCustomerData.getCode());
			cRecords.setDistributor_name(sfdcCustomerData.getDistributor_name());
			cRecords.setRoute_name(sfdcCustomerData.getRoute_name());
			if (msg.equals("")) {
				cRecords.setComment("Imported Successfully!!");
			} else {
				cRecords.setComment(importMessage.toString());
			}
			cRecords.setBilling_address(sfdcCustomerData.getAddress());
			cRecords.setCustomerType("Confirmed");
			Long importId = System.currentTimeMillis();
			cRecords.setImport_id(importId.intValue());
			session.save(cRecords);
			importMessagesList.add(cRecords);
		}
	}

	public void importCustomersTarget(Session session, int c_id, int u_id,
			List<CustomerTargetRecords> customerTargetListFromSAP) throws Exception {
		// TODO Auto-generated method stub
		try {

			FieldMILogger.info(this.getClass().getName(),
					"Customer List Size from SAP:" + customerTargetListFromSAP.size());
			for (CustomerTargetRecords sfdcCustomerTargetData : customerTargetListFromSAP) {

				CustomerTargetRecords customerTargets = null;

				boolean customerExists = false;
				Query<CustomerTargetRecords> queryCustomerTarget = session
						.createQuery(
								"From CustomerTargetRecords where customerCode='"
										+ sfdcCustomerTargetData.getCustomerCode() + "' and company_id=" + c_id,
								CustomerTargetRecords.class);
				List<CustomerTargetRecords> fieldMICustomerList = queryCustomerTarget.list();
				if (fieldMICustomerList != null && fieldMICustomerList.size() > 0) {

					customerExists = true;
					customerTargets = fieldMICustomerList.get(0);
				} else {

					customerTargets = new CustomerTargetRecords();
				}
				String query = "from CustomerDetails where code='" + sfdcCustomerTargetData.getCustomerCode()
						+ "' and company_id=" + c_id;
				Query<CustomerDetails> queryCustomer = session.createQuery(query, CustomerDetails.class);
				List<CustomerDetails> fieldMICustomer = queryCustomer.list();
				if (fieldMICustomer != null && fieldMICustomer.size() > 0) {

					customerTargets.setCustomerName(fieldMICustomer.get(0).getCustomer_name());
					customerTargets.setCustomer_id(fieldMICustomer.get(0).getCustomer_id());
				} else {

					customerTargets.setCustomerName("");
					customerTargets.setCustomer_id(0);

				}
				customerTargets.setCustomerCode(sfdcCustomerTargetData.getCustomerCode());
				customerTargets.setCompany_id(c_id);
				customerTargets.setUser_id(u_id);
				customerTargets.setStatus(1);
				customerTargets.setVisits(0);
				customerTargets.setAchievedTarget(0);
				customerTargets.setCollection(0);
				customerTargets.setOrders(0);
				customerTargets.setFinanacialYear(sfdcCustomerTargetData.getFinanacialYear());
				customerTargets.setMonth(sfdcCustomerTargetData.getMonth());
				customerTargets.setVolume(sfdcCustomerTargetData.getVolume());

				if (sfdcCustomerTargetData.getCategoryName() != null) {

					Query<CategoryMaster> queryCategoryMaster = session
							.createQuery(
									"From CategoryMaster where category_name='"
											+ sfdcCustomerTargetData.getCategoryName() + "' and company_id=" + c_id,
									CategoryMaster.class);
					List<CategoryMaster> fieldMICategoryMasterList = queryCategoryMaster.list();

					if (fieldMICategoryMasterList != null && fieldMICategoryMasterList.size() > 0) {

						customerTargets.setCategoryId(fieldMICategoryMasterList.get(0).getCategory_id());
						customerTargets.setCategoryName(fieldMICategoryMasterList.get(0).getCategory_name());
					}
				} else {
					customerTargets.setCategoryId(0);
					customerTargets.setCategoryName("");

				}
				if (customerExists)
					session.update(customerTargets);
				else {

					customerTargets.setCreated_date(new Date());
					customerTargets.setUpdated_date(new Date());
					session.save(customerTargets);
				}
				CustomerTargetRecordsDetails ctd = new CustomerTargetRecordsDetails();
				ctd.setCustomer_target_id(customerTargets.getCustomer_target_id());
				ctd.setCustomerName(customerTargets.getCustomerName());
				ctd.setCustomerCode(customerTargets.getCustomerCode());
				ctd.setCustomer_id(customerTargets.getCustomer_id());
				ctd.setMonth(customerTargets.getMonth());
				ctd.setVisits(customerTargets.getVisits());
				ctd.setOrders(customerTargets.getOrders());
				ctd.setCollection(customerTargets.getCollection());
				ctd.setAmount(customerTargets.getAmount());
				ctd.setVolume(customerTargets.getVolume());
				ctd.setCategoryId(customerTargets.getCategoryId());
				ctd.setCategoryName(customerTargets.getCategoryName());
				session.save(ctd);

			}

		}

		catch (Exception ex) {

		}
	}

	public void importSalesMasters(Session session, int c_id, int u_id, List<SalesMaster> salesListFromSFDC)
			throws Exception {
		// TODO Auto-generated method stub
		try {

			FieldMILogger.info(this.getClass().getName(), "Sales List Size from SAP:" + salesListFromSFDC.size());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String importDate = sdf.format(Calendar.getInstance().getTime());
			for (SalesMaster sfdcSalesData : salesListFromSFDC) {

				SalesMaster salesMaster = new SalesMaster();

				String customrQuery = "from CustomerDetails where UPPER(code)='"
						+ sfdcSalesData.getCust_name().toUpperCase() + "' and company_id=" + c_id + " and status=1";
				Query<CustomerDetails> queryCustomer = session.createQuery(customrQuery, CustomerDetails.class);
				List<CustomerDetails> fieldMICustomerList = queryCustomer.list();

				String productQuery = "from ProductDetails where UPPER(product_code)='"
						+ sfdcSalesData.getApproval_status().toUpperCase() + "' and company_id=" + c_id
						+ " and status=1";
				Query<ProductDetails> queryProduct = session.createQuery(productQuery, ProductDetails.class);
				List<ProductDetails> fieldMIProductList = queryProduct.list();

				String priceQuery = "from NewPriceMaster where UPPER(product_code)='"
						+ sfdcSalesData.getApproval_status().toUpperCase() + "' and company_id=" + c_id
						+ " and activestatus=1";
				Query<NewPriceMaster> queryPriceMaster = session.createQuery(priceQuery, NewPriceMaster.class);
				List<NewPriceMaster> fieldMIPriceMasterList = queryPriceMaster.list();

				if (fieldMICustomerList.size() > 0 && fieldMIProductList.size() > 0
						&& fieldMIPriceMasterList.size() > 0) {
					String salesQuery = "from SalesMaster where customer_id="
							+ fieldMICustomerList.get(0).getCustomer_id() + " and company_id=" + c_id
							+ " and salesOrderNumber='" + sfdcSalesData.getSalesOrderNumber() + "'";
					Query<SalesMaster> saleMaster = session.createQuery(salesQuery, SalesMaster.class);
					List<SalesMaster> sm1 = saleMaster.list();
					if (sm1.size() == 0) {

						salesMaster.setCustomer_id(fieldMICustomerList.get(0).getCustomer_id());
						salesMaster.setCreated_by(u_id);
						salesMaster.setCompany_id(c_id);
						salesMaster.setCreated_date(sfdcSalesData.getCreated_date());
						salesMaster.setUser_id(u_id);
						salesMaster.setStatus(1);
						salesMaster.setSalesOrderNumber(sfdcSalesData.getSalesOrderNumber());
						salesMaster.setTotalamount(sfdcSalesData.getTotalamount());
						salesMaster.setTotal_weight_in_ton(sfdcSalesData.getTotal_weight_in_ton());
						salesMaster.setDiscount_flag("0");
						salesMaster.setUpdated_date(new Date());
						salesMaster.setOrderImportDate(importDate);
						session.save(salesMaster);

						SalesDetails salesDetails = new SalesDetails();
						salesDetails.setProduct_id(fieldMIProductList.get(0).getProduct_id());
						salesDetails.setPricemaster_id(fieldMIPriceMasterList.get(0).getPricemaster_id());
						salesDetails.setSales_master_id(salesMaster.getSales_master_id());
						salesDetails.setPack_rate(sfdcSalesData.getDiscounted_price());
						salesDetails.setProduct_rate(sfdcSalesData.getDiscounted_price());
						salesDetails.setPack_qty("1.0");
						salesDetails.setProduct_rate(1.0);
						salesDetails.setPack(fieldMIPriceMasterList.get(0).getDisplaypackname());
						session.save(salesDetails);

					} else {
						salesMaster = sm1.get(0);
						SalesDetails salesDetails = new SalesDetails();
						salesDetails.setProduct_id(fieldMIProductList.get(0).getProduct_id());
						salesDetails.setPricemaster_id(fieldMIPriceMasterList.get(0).getPricemaster_id());
						salesDetails.setSales_master_id(salesMaster.getSales_master_id());
						salesDetails.setPack_rate(sfdcSalesData.getDiscounted_price());
						salesDetails.setProduct_rate(sfdcSalesData.getDiscounted_price());
						salesDetails.setPack_qty("1.0");
						salesDetails.setProduct_rate(1.0);
						salesDetails.setPack(fieldMIPriceMasterList.get(0).getDisplaypackname());
						session.save(salesDetails);

					}
				} else {

					StringBuffer importMessage = new StringBuffer();
					if (fieldMICustomerList.size() == 0)
						importMessage.append("Customer not found.");
					if (fieldMIProductList.size() == 0)
						importMessage.append("Product not found.");
					if (fieldMIPriceMasterList.size() == 0)
						importMessage.append("Price Master not found.");

					addSalesOrderLogToImport(session, sfdcSalesData, importMessage.toString(), c_id, u_id);
				}
			}

		}

		catch (Exception ex) {
			throw ex;
		}

	}

	private void addSalesOrderLogToImport(Session session, SalesMaster salesMasterLog, String importMessage, int c_id,
			int u_id) {

		if (salesMasterLog != null) {
			SalesOrderImportRecords salesMaster = new SalesOrderImportRecords();
			salesMaster.setCreated_by(u_id);
			salesMaster.setCustomerCode(salesMasterLog.getCust_name());
			salesMaster.setProductCode(salesMasterLog.getApproval_status());
			salesMaster.setCompany_id(c_id);
			salesMaster.setCreated_date(salesMasterLog.getCreated_date());
			salesMaster.setUser_id(u_id);
			salesMaster.setStatus(1);
			salesMaster.setSalesOrderNumber(salesMasterLog.getSalesOrderNumber());
			salesMaster.setTotalAmount(salesMasterLog.getTotalamount());
			salesMaster.setTotalWeightTon(salesMasterLog.getTotal_weight_in_ton());
			salesMaster.setUpdated_date(new Date());
			salesMaster.setComment(importMessage);
			Long importId = System.currentTimeMillis();
			salesMaster.setImport_id(importId.intValue());
			session.save(salesMaster);
		}
	}

	public void importInfluencersCustomers(Session session, int company_id, int u_id,
			List<WsInfluencerDetails> infListFromSFDC, List<CustomerDetails> customerList) {

		try {

			FieldMILogger.info(this.getClass().getName(), "Customer List Size from SAP:" + infListFromSFDC.size());
			for (WsInfluencerDetails sfdcCustomerData : infListFromSFDC) {

				CustomerDetails customerDetails = null;

				boolean customerExists = false;
				Query<CustomerDetails> queryCustomer = session.createQuery("From CustomerDetails where code='"
						+ sfdcCustomerData.getCode() + "' and company_id=" + company_id, CustomerDetails.class);
				List<CustomerDetails> fieldMICustomerList = queryCustomer.list();
				if (fieldMICustomerList != null && fieldMICustomerList.size() > 0) {

					customerExists = true;
					// customerDetails.setCustomer_id(Integer.parseInt(""+fieldMICustomerList.get(0)));
					customerDetails = fieldMICustomerList.get(0);
				} else {

					customerDetails = new CustomerDetails();
				}

				customerDetails.setCode(sfdcCustomerData.getCode());
				customerDetails.setCustomer_name(sfdcCustomerData.getCustomer_name());
				customerDetails.setCustomerAccountGroup(sfdcCustomerData.getCustomerAccountGroup());
				customerDetails.setCompany_id(company_id);
				customerDetails.setCreated_by(u_id);
				customerDetails.setUpdated_by(u_id);
				customerDetails.setStatus(1);
				customerDetails.setGst_no(sfdcCustomerData.getGst_no());
				customerDetails.setPan_card(sfdcCustomerData.getPan_card());
				customerDetails.setAddress(sfdcCustomerData.getAddress());
				customerDetails.setPincode(sfdcCustomerData.getPincode());
				customerDetails.setMobile(sfdcCustomerData.getMobile());
				customerDetails.setCredit_limit(0);
				customerDetails.setCredit_limit(0);
				customerDetails.setClassType(0);
				customerDetails.setStoreType(0);
				customerDetails.setLocation_no(0);
				customerDetails.setExportedToSAP(1);
				customerDetails.setSendToSAP(2);
				customerDetails.setCustomer_type("Influencer");
				customerDetails.setNew_city(0);
				customerDetails.setNew_state(0);
				customerDetails.setDistributor_id(0);
				customerDetails.setRoute_id(0);
				boolean mandatoryDataFound = true;
				// set Business Segment
				if (sfdcCustomerData.getBsegment() != null) {

					Query<BusinessSegment> queryBsegment = session.createQuery(
							"From BusinessSegment where upper(bsegment_name)='"
									+ sfdcCustomerData.getBsegment().toUpperCase() + "' and company_id=" + company_id,
							BusinessSegment.class);
					List<BusinessSegment> fieldMIBsegmentList = queryBsegment.list();

					if (fieldMIBsegmentList != null && fieldMIBsegmentList.size() > 0) {

						customerDetails.setbSegmentId(fieldMIBsegmentList.get(0).getBsegment_id());
					}
				} else {
					customerDetails.setbSegmentId(0);
				}

				if (sfdcCustomerData.getCountry_name() == null || sfdcCustomerData.getCountry_name().length() == 0
						|| sfdcCustomerData.getStateId() == null || sfdcCustomerData.getStateId().length() == 0
						|| sfdcCustomerData.getCityId() == null || sfdcCustomerData.getCityId().length() == 0) {

					// mandatoryDataFound = false;

				}

				if (sfdcCustomerData.getStateId() == null || sfdcCustomerData.getStateId().length() == 0
						|| sfdcCustomerData.getCityId() == null || sfdcCustomerData.getCityId().length() == 0) {

					// mandatoryDataFound = false;

				}

				if (sfdcCustomerData.getCityId() == null || sfdcCustomerData.getCityId().length() == 0) {

					// mandatoryDataFound = false;
				}

				if (mandatoryDataFound) {

					// set Country
					Query<Country> queryCountry = session.createQuery("From Country where upper(country_name)='"
							+ sfdcCustomerData.getCountry_name().toUpperCase() + "'", Country.class);
					List<Country> fieldMICountryList = queryCountry.list();

					if (fieldMICountryList != null && fieldMICountryList.size() > 0) {

						customerDetails.setCountry_id(fieldMICountryList.get(0).getCountry_id());
					} else {

						// mandatoryDataFound = false;

					}

					// set state
//					Query<States> queryState = session.createQuery(
//							"From States where upper(state_name)='" + sfdcCustomerData.getStateId().toUpperCase()
//									+ "' and country_id=" + customerDetails.getCountry_id(),
//							States.class);
//					List<States> fieldMIStateList = queryState.list();
//					if (fieldMIStateList != null && fieldMIStateList.size() > 0) {
//
//						customerDetails.setNew_state(fieldMIStateList.get(0).getState_id());
//					} else {
//
//						customerDetails.setNew_state(0);
//						
//					}
					// set zone

					// set city, district code and taluka code
//					Query<Citys> queryCitys = session.createQuery(
//							"From Citys where upper(city_name)='" + sfdcCustomerData.getCityId().toUpperCase()
//									+ "' ",
//							Citys.class);
//					List<Citys> fieldMICitysList = queryCitys.list();
//					if (fieldMICitysList != null && fieldMICitysList.size() > 0) {
//
//						customerDetails.setNew_city(fieldMICitysList.get(0).getCity_id());
//						customerDetails.setNew_state(fieldMICitysList.get(0).getState_id());
//						Query<District> queryDistrict = session.createQuery(
//								"From District where district_id=" + fieldMICitysList.get(0).getDistrict_id(),
//								District.class);
//						District fieldMIDistrict = queryDistrict.uniqueResult();
//						if (fieldMIDistrict != null) {
//
//							customerDetails.setDistrict(fieldMIDistrict.getDistrictCode());
//						}
//
//						Query<Taluka> queryTaluka = session.createQuery(
//								"From Taluka where taluka_id=" + fieldMICitysList.get(0).getTaluka_id(), Taluka.class);
//						Taluka fieldMITaluka = queryTaluka.uniqueResult();
//						if (fieldMITaluka != null) {
//
//							customerDetails.setTown(fieldMITaluka.getTalukaCode());
//						}

//					} else {
//
//						mandatoryDataFound = false;
//						
//					}
					Query<ZoneWiseStates> queryZone = session.createQuery("From ZoneWiseStates where stateId = "
							+ customerDetails.getNew_state() + " and company_id=" + company_id, ZoneWiseStates.class);
					List<ZoneWiseStates> fieldMIZoneList = queryZone.list();
					if (fieldMIZoneList != null && fieldMIZoneList.size() > 0) {

						customerDetails.setZone_id(fieldMIZoneList.get(0).getZoneId());

					} else {

//						mandatoryDataFound = false;
//						importMessage.append("Zone with name ").append(sfdcCustomerData.getZoneName())
//								.append(" not found. ");
						customerDetails.setZone_id(0);
					}

					// set distributor and route
//					Query<DistributorDetails> queryDistributor = session
//							.createQuery("From DistributorDetails where upper(distributor_name)='"
//									+ sfdcCustomerData.getDistributor_name().toUpperCase() + "' and company_id="
//									+ company_id, DistributorDetails.class);
//					List<DistributorDetails> fieldMIDistributorList = queryDistributor.list();
//					if (fieldMIDistributorList != null && fieldMIDistributorList.size() > 0) {
//
//						customerDetails.setDistributor_id(fieldMIDistributorList.get(0).getDistributor_id());
//
//						// set Route
//						Query<Route> queryRoute = session.createQuery("From Route where upper(route_name)='"
//								+ sfdcCustomerData.getRoute_name().toUpperCase() + "' and company_id=" + company_id
//								+ " and distributor_id=" + customerDetails.getDistributor_id(), Route.class);
//						List<Route> fieldMIRouteList = queryRoute.list();
//						if (fieldMIRouteList != null && fieldMIRouteList.size() > 0) {
//
//							customerDetails.setRoute_id(fieldMIRouteList.get(0).getRoute_id());
//						} else {
//
//							mandatoryDataFound = false;
//							importMessage.append("Route with name ").append(sfdcCustomerData.getRoute_name())
//									.append(" not found. ");
//
//							customerDetails.setRoute_id(0);
//
//						}
//					} 
				}

				if (customerExists && mandatoryDataFound)
					session.update(customerDetails);
				else if (mandatoryDataFound) {

					customerDetails.setCreated_date(sfdcCustomerData.getCreated_date());
					customerDetails.setUpdated_date(sfdcCustomerData.getCreated_date());
					session.save(customerDetails);
				}

				if (mandatoryDataFound) {
					customerList.add(customerDetails);
					// customerList.add(customerDetails);
					FieldMILogger.info(this.getClass().getName(), "Updated customer:" + customerDetails.getCustomer_id()
							+ " Code:" + customerDetails.getCode());
				} else {

				}

			}

		} catch (Exception ex) {

			throw ex;
		}

	}
	private String createSearchConstraint(String[] itemList) {
	    StringBuilder formula = new StringBuilder();
	    for (String item : itemList) {
	        formula.append("IF(SEARCH($A$1, \"").append(item).append("\") > 0, INDIRECT(\"$A$1\"), \"\"),");
	    }
	    formula.deleteCharAt(formula.length() - 1); // Remove the trailing comma
	    return formula.toString();
	}


}
