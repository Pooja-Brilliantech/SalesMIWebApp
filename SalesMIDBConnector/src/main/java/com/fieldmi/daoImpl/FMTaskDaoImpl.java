package com.fieldmi.daoImpl;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMTaskDao;
import com.fieldmi.stubs.WSTaskDetails;
import com.fieldmi.utils.AjaxResponse;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.S3Operations;
import com.fieldmi.utils.SalesMIDBUtils;
import com.softtantra.salesapp.pojo.TaskDetails;



public class FMTaskDaoImpl implements FMTaskDao{

	static Properties fileProperties = new Properties();

	static {

		try {

			fileProperties.load(FMTaskDaoImpl.class.getClassLoader().getResourceAsStream("/application.properties"));
		} catch (Exception e) {

			FieldMILogger.error(FMTaskDaoImpl.class.getName(), e);
		}
	}
	
	public static Properties getPropertiesFile(String path) {
		try {
			fileProperties
					.load(FMTaskDaoImpl.class.getClassLoader().getResourceAsStream("/application.properties"));
		} catch (Exception e) {

			FieldMILogger.error(FMTaskDaoImpl.class.getName(), e);
		}
		return fileProperties;
	}
	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session initiateSession() {
		return sessionFactory.openSession();
	}
	@SuppressWarnings("unused")
	private void destroySession(Session session) {
		if (session != null) {

			if (session.isDirty())
				session.flush();
			session.close();
		}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public AjaxResponse updateTaskStatus(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		AjaxResponse ajax = new AjaxResponse();
		Session session=initiateSession();
		try{
			
			
			if(wsTaskDetails.getTask_id()!=0){
				String sql1="update task_details set task_status="+wsTaskDetails.getTask_status()+", updated_date='"+wsTaskDetails.getCurrent_date()+"'  where company_id="+wsTaskDetails.getCompany_id()+" and task_id="+wsTaskDetails.getTask_id();
				Query query1=session.createSQLQuery(sql1);
				query1.executeUpdate();
				
				ajax.setData(wsTaskDetails);
				ajax.setStatus(1);
				ajax.setMessage("Task Updated Successfully.");
			}else{
				ajax.setData(null);
				ajax.setStatus(0);
				ajax.setMessage("Task id not present.");
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(),e);
			ajax.setData(null);
			ajax.setStatus(0);
			ajax.setMessage("Error occured while updating task status.");
		}finally {
			destroySession(session);
		}

		return ajax;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public AjaxResponse getTaskListUserAndStatusWise(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		AjaxResponse ajax = new AjaxResponse();
		Session session=initiateSession();
		List<TaskDetails> details = new ArrayList<TaskDetails> ();
		
		List list = null;
		try{
			String sql="select login_id from login_details where user_id="+wsTaskDetails.getUser_id()+" and company_id="+wsTaskDetails.getCompany_id()+" and is_active=1";
			Query query1=session.createSQLQuery(sql);
			BigInteger user_id=(BigInteger)query1.uniqueResult();
			String sql1 = "";
			int pageSize = 500;
			int pageNumber=wsTaskDetails.getPage_no();
			if(user_id.intValueExact() !=0){
				if (wsTaskDetails.getTask_status()==0 || wsTaskDetails.getTask_status()==1 || wsTaskDetails.getTask_status()==3 || wsTaskDetails.getTask_status()==4 || wsTaskDetails.getTask_status()==2) {
					sql1 = "select t.task_id,t.from_date,t.to_date,t.task_type,t.type_details,"
							+ " t.close_dateTime,t.closing_comment,t.closing_img_url,t.task_status,"
							+ " concat(u.first_name,' ',u.last_name)  as assign_user_name "
							+ ",concat(u1.first_name,' ',u1.last_name)  as assign_by," + " t.user_id,t.created_by, t.main_task,t.sub_task,t.tat,t.completed_tat "
							+ " from task_details t" + " left outer join sm_user_details u on u.user_id=t.user_id"
							+ " left outer join sm_user_details u1 on u1.user_id=t.created_by" + " where  (t.user_id="
							+ wsTaskDetails.getUser_id() + " or t.created_by=" + wsTaskDetails.getUser_id()+ ") and t.task_status="+wsTaskDetails.getTask_status()+" and t.company_id=" 
							+ wsTaskDetails.getCompany_id() + " and t.status<>0";
							
					
							Query q = session.createSQLQuery(sql1);
							q = q.setFirstResult(pageSize * (pageNumber - 1));
							list = q.setMaxResults(pageSize).list();
							
					
						} else{
							sql1 = "select t.task_id,t.from_date,t.to_date,t.task_type,t.type_details,"
								+ " t.close_dateTime,t.closing_comment,t.closing_img_url,t.task_status,"
								+ " concat(u.first_name,' ',u.last_name)  as assign_user_name "
								+ ",concat(u1.first_name,' ',u1.last_name)  as assign_by," + " t.user_id,t.created_by, t.main_task,t.sub_task,t.tat,t.completed_tat"
								+ " from task_details t" + " left outer join sm_user_details u on u.user_id=t.user_id"
								+ " left outer join sm_user_details u1 on u1.user_id=t.created_by" + " where  (t.user_id="
								+ wsTaskDetails.getUser_id() + " or t.created_by=" + wsTaskDetails.getUser_id()
								+ ") and t.company_id=" + wsTaskDetails.getCompany_id() + " and t.status<>0";
							
							Query q = session.createSQLQuery(sql1);
							q = q.setFirstResult(pageSize * (pageNumber - 1));
							list = q.setMaxResults(pageSize).list();
							} 
				
				
				
		
				for (Object object : list) {
					
					Object[] obj = (Object[]) object;
					TaskDetails taskdetails = new TaskDetails();
					
					taskdetails.setTask_id((int)obj[0]);
					taskdetails.setFrom_date((Date)obj[1]);
					taskdetails.setTo_date((Date)obj[2]);
					taskdetails.setTask_type((String)obj[3]);
					taskdetails.setType_details((String)obj[4]);
					taskdetails.setClose_dateTime((Date)obj[5]);
					taskdetails.setClosing_comment((String)obj[6]);
					taskdetails.setClosing_img_url((String)obj[7]);
					taskdetails.setTask_status((int)obj[8]);
					taskdetails.setUsername((String)obj[9]);
					taskdetails.setAssign_by((String)obj[10]);
					taskdetails.setUser_id((int)obj[11]);
					taskdetails.setUpdated_by((int)obj[12]);
					taskdetails.setMain_task((String)obj[13]);
					taskdetails.setSub_task((String)obj[14]);
					taskdetails.setTAT((String)obj[15]);
					taskdetails.setCompleted_tat((String)obj[16]);
					
					details.add(taskdetails);
				
				}
				ajax.setData(details);
				ajax.setStatus(1);
				ajax.setMessage("TaskList Send Successfully.");
				
			}else{
				ajax.setData(null);
				ajax.setStatus(0);
				ajax.setMessage("Invalid User Id.");
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(),e);
			ajax.setData(null);
			ajax.setStatus(0);
			ajax.setMessage("Error occured while getting the task list.");
		}finally {
			destroySession(session);
		}

		return ajax;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public AjaxResponse uploadFiles(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		AjaxResponse ajax = new AjaxResponse();
		String files="";
		Session session=initiateSession();
		try{
			
			
			String path1="";
			String path2="";
			String path3="";

			if(wsTaskDetails.getFile1() != null && wsTaskDetails.getFile2() != null && wsTaskDetails.getFile3() != null){
				
				 path1=uploadFiles(wsTaskDetails.getCompany_id(),wsTaskDetails.getFile1(),S3Operations.TASK_FILES_FOLDER,wsTaskDetails.getTask_id(),1,wsTaskDetails.getExtension1(),wsTaskDetails.getFilename1());
				 path2=uploadFiles(wsTaskDetails.getCompany_id(),wsTaskDetails.getFile2(),S3Operations.TASK_FILES_FOLDER,wsTaskDetails.getTask_id(),1,wsTaskDetails.getExtension2(),wsTaskDetails.getFilename2());
				 path3=uploadFiles(wsTaskDetails.getCompany_id(),wsTaskDetails.getFile3(),S3Operations.TASK_FILES_FOLDER,wsTaskDetails.getTask_id(),1,wsTaskDetails.getExtension3(),wsTaskDetails.getFilename3());
				
			}else if(wsTaskDetails.getFile1() != null && wsTaskDetails.getFile2() != null){
				
					path1=uploadFiles(wsTaskDetails.getCompany_id(),wsTaskDetails.getFile1(),S3Operations.TASK_FILES_FOLDER,wsTaskDetails.getTask_id(),1,wsTaskDetails.getExtension1(),wsTaskDetails.getFilename1());
					 path2=uploadFiles(wsTaskDetails.getCompany_id(),wsTaskDetails.getFile2(),S3Operations.TASK_FILES_FOLDER,wsTaskDetails.getTask_id(),1,wsTaskDetails.getExtension2(),wsTaskDetails.getFilename2());	
					 
				}else{
					path1=uploadFiles(wsTaskDetails.getCompany_id(),wsTaskDetails.getFile1(),S3Operations.TASK_FILES_FOLDER,wsTaskDetails.getTask_id(),1,wsTaskDetails.getExtension1(),wsTaskDetails.getFilename1());
				}
			
			if(path1 != null && path1 != "" && path2 != null && path2 != "" && path3 != null && path3 != ""){
				
				files = path1+","+path2+","+path3;
				
			}else if(path1 != null && path1 != "" && path2 != null && path2 != ""){
				
				files = path1+","+path2;
			}else{
				
				files = path1;
			}
			
			String sql="update task_details set task_files='"+files+"' where company_id="+wsTaskDetails.getCompany_id()+" and task_id="+wsTaskDetails.getTask_id()+" and task_type='"+wsTaskDetails.getTask_type()+"'";
			Query query1=session.createSQLQuery(sql);
			int rowCount = query1.executeUpdate();
			if(rowCount==1){
			ajax.setStatus(1);
			ajax.setMessage("uploaded files Successfully.");
			}else{
				ajax.setStatus(0);
				ajax.setMessage("failed to uploaded files.");
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(),e);
			ajax.setData(null);
			ajax.setStatus(0);
			ajax.setMessage("Error occured while uploading files.");
		}finally {
			destroySession(session);
		}

		return ajax;
	}
	
	public String uploadFiles(int company_id, byte[] file, String prefix, int id, int flag,String extension,String filename) {
		String objectKey="";
		Session session=initiateSession();
		try {

			if (flag == 1) {

				if (file.length != 0) {

					String bucketName = fileProperties.getProperty("bucketName");
			
					objectKey = company_id + prefix + id + "/" + filename;
					
					S3Operations s3Operatons = CommonMethods.getS3OperationClient();
					s3Operatons.addFilesToS3(bucketName, objectKey, new ByteArrayInputStream(file), file.length);
				return objectKey;
					
				} else {
					return "";
				}
			} else {
				return "";
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		}finally {
			destroySession(session);
		}
	}

	@Override
	public AjaxResponse getSubTaskList(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		AjaxResponse ajax = new AjaxResponse();
		Session session= initiateSession();
		try{
			String sql= "select id,task_name,days,hours,minutes from sub_task_details where status =1 and sub_task_id=0 and company_id=" +wsTaskDetails.getCompany_id() + " and task_type='"+wsTaskDetails.getTask_type()+"' and task_type_id="+wsTaskDetails.getTask_id();
			Query query = session.createSQLQuery(sql);
			List list= query.list();
			List<WSTaskDetails> wSTaskDetails = new ArrayList<WSTaskDetails>();
			
			if(list != null && list.size()!= 0){
				for(Object object :list){
					Object[] obj = (Object[]) object;	
					WSTaskDetails subTaskDetails = new WSTaskDetails();
					subTaskDetails.setTask_id((int) obj[0]);
					subTaskDetails.setTask_name((String) obj[1]);
					String days=(String)obj[2];
					String hours=(String)obj[3];
					String mins=(String)obj[4];
					subTaskDetails.setTAT(days+":"+hours+":"+mins);
					wSTaskDetails.add(subTaskDetails);
				
				}
				ajax.setData(wSTaskDetails);
				ajax.setStatus(1);
				ajax.setMessage("Sub Task List");
			}else{
				ajax.setData(null);
				ajax.setStatus(0);
				ajax.setMessage("Data not Available");
			}
		}catch (Exception e) {
				FieldMILogger.error(this.getClass().getName(), e);
				return null;
			}finally{
				destroySession(session);
			}
		return ajax;
	}

	@Override
	public AjaxResponse getSubTaskList1(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		AjaxResponse ajax = new AjaxResponse();
		Session session= initiateSession();
		try{
			String sql= "select id,task_name,days,hours,minutes from sub_task_details where status =1 and sub_task_id!=0 and company_id=" +wsTaskDetails.getCompany_id() + " and task_type='"+wsTaskDetails.getTask_type()+"' and sub_task_id="+wsTaskDetails.getTask_id();
			Query query = session.createSQLQuery(sql);
			List list= query.list();
			
			List<WSTaskDetails> wSTaskDetails = new ArrayList<WSTaskDetails>();
			if(list != null && list.size()!= 0){
				for(Object object :list){
					Object[] obj = (Object[]) object;	
					WSTaskDetails subTaskDetails = new WSTaskDetails();
					subTaskDetails.setTask_id((int) obj[0]);
					subTaskDetails.setTask_name((String) obj[1]);
					String days=(String)obj[2];
					String hours=(String)obj[3];
					String mins=(String)obj[4];
					subTaskDetails.setTAT(days+":"+hours+":"+mins);
					wSTaskDetails.add(subTaskDetails);
				}
				ajax.setData(wSTaskDetails);
				ajax.setStatus(1);
				ajax.setMessage("Sub Task List");
			}else{
				ajax.setData(null);
				ajax.setStatus(0);
				ajax.setMessage("Data not Available");
			}
		}catch (Exception e) {
				FieldMILogger.error(this.getClass().getName(), e);
				return null;
			}finally{
				destroySession(session);
			}
		return ajax;
	}

	@Override
	public AjaxResponse getTaskImageLinks(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		AjaxResponse ajax = new AjaxResponse();
		Session session= initiateSession();
		try{
			Query query=session.createQuery("select task_files from TaskDetails where id=:task_id and company_id=:company_id");
					query.setParameter("task_id", wsTaskDetails.getTask_id());
					query.setParameter("company_id", wsTaskDetails.getCompany_id());
					String path=(String)query.uniqueResult();
					WSTaskDetails taskDetails = new WSTaskDetails();
					if (path != null) { 
						String []files=path.split(",");
						
						if(files.length==3){
						taskDetails.setFilename1(files[0]);
						taskDetails.setFilename2(files[1]);
						taskDetails.setFilename3(files[2]);
						}
						else if(files.length==2) {
							taskDetails.setFilename1(files[0]);
							taskDetails.setFilename2(files[1]);
						}else{
							taskDetails.setFilename1(files[0]);
						}
						ajax.setData(taskDetails);
						ajax.setStatus(1);
						ajax.setMessage("Successfully get file links");
						
					} else {
						taskDetails.setFilename1("");
						taskDetails.setFilename2("");
						taskDetails.setFilename3("");
						
						ajax.setData(null);
						ajax.setStatus(0);
						ajax.setMessage("No Files Available");
					}
					
		
			
		}catch(Exception e){
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		}finally{
			destroySession(session);
		}
		return ajax;
	}
	
	
}
