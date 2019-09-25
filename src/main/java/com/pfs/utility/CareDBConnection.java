package com.pfs.utility;

import java.util.ArrayList;
import java.util.List;

/*
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;*/

public class CareDBConnection {/*

	static String serverIp = "96.116.161.129";//"10.5.3.133";
	static String keyspace = "care_dev";
	static Cluster cluster;
	static Session session;
	
	static {
		if(TestBase.appURL.contains("qa")) {
			keyspace = "care_qa";
		}
		 cluster = Cluster.builder().addContactPoints(serverIp).withCredentials("dev", "dev").build();
		 session = cluster.connect(keyspace);
		 ExecutionLog.log("Connected with: " + serverIp);
	}


	public static void main(String[] args) {
		CareDBConnection db = new CareDBConnection();
		System.out.println("--------------Group Name----------------------" + db.getGroupOfUser("akazmi755"));
		System.out.println("session" + session);
		System.out.println("cluster" + cluster);
		
		session.close();
		cluster.close();
	
		System.out.println("session" + session);
		System.out.println("cluster" + cluster);
		if(session!=null) {
			session=null;
			cluster=null;
		}
		System.out.println("session" + session);
		System.out.println("cluster" + cluster);
		System.out.println("------------------------------------");
		//db.setGroupName("akazmi755", "grp_XfinityMobile");
		//System.out.println("-------Group Name After Changing the Group----" + db.getGroupName("akazmi755"));*/
	//}
	
/*	public static String getGroupOfUser(String nt_id) {
		String Group= nt_id + " user does not exist";
		String cqlStatement = "select * from user;";
		 ExecutionLog.log("Connected with db having serverIP: " + serverIp);

		for (Row row : session.execute(cqlStatement)) {

			String userID = row.getString("nt_id");
			Group = row.getString("group");

			if(userID.contains(nt_id)) {
				ExecutionLog.log(userID + "--" + Group);
				break;
			}
		}
		return Group;
	}
	
	public static void setGroupOfUser(String nt_id, String groupToSet) {
		String cqlStatement = "Update user  set group= '" + groupToSet + "'where org='comcast' and nt_id='" + nt_id + "';";
		session.execute(cqlStatement);		
		Assert.assertTrue(getGroupOfUser(nt_id).equals(groupToSet));
		ExecutionLog.log("Verified that group of user '" + nt_id + "' has been changed to '" + groupToSet + "'");
	}
	
	public static void closeSessions() {
		if(session!=null) {
			session.close();
			cluster.close();
		}
		
	}
	public List<String> getAllValuesOfColumn(String columnName, String cqlStatement) {
		List<String> columnData = new ArrayList<String>();
		for (Row row : session.execute(cqlStatement)) {
			String userID = row.getString(columnName);
			columnData.add(userID);			
		}
		session.close();
		cluster.close();
		return columnData;
	}

*/}
