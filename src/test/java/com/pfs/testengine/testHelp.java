package com.pfs.testengine;

import java.sql.SQLException;
import org.testng.annotations.Test;

import com.pfs.utility.DBConnection;

public class testHelp {

	

	@Test
	public static void testing() throws SQLException {
		
		
		String insertQuery= "INSERT INTO paytax.payment (payment_id,account_number,userid_number,payment_type_code,transaction_date,transaction_time,status_code,payment_date,amount_1,amount_2,amount_3,amount_4,amount_5,amount_6,amount_7,amount_8,amount_9,amount_10,amount_11,amount_12,clear_fi_number,confirmation_extension,confirmation_number,credit_debit_index,debit_fi_account_number,debit_fi_branch_number,debit_fi_number,due_date,filing_userid,input_method_code,member_fi_number,operator_id,payment_on_account,recipient_id,revenue_expense_transit_number,report_thru_date,fi_settlement_date,recipient_settlement_date,payment_extension_number,otc_flag,otc_company_name,otc_ref_info,created_by,created_date,updated_by,updated_date) VALUES (99999999,'12312312310000',600001,301,20191223,9464348,'P',20191223,5575.25,0,0,0,0,0,0,0,0,0,0,5575.25,0,0,100023,'D','123456789033',3333,6,20191223,600001,'W',6,'G_N','N',300,550,20191223,20191223,0,0,'N','Askida','Askida','QA1','2019-12-17 10:00:00','QA1','2019-12-17 10:00:00');";
		String selectQuery= "select * from paytax.payment where payment_id = 99999999;";
		String deleteQuery= "delete from paytax.payment where payment_id = 99999999;";
		
//		DBConnection.executeInsertDeleteQuery(insertQuery);
		DBConnection.executeQuery(selectQuery);
//		DBConnection.executeInsertDeleteQuery(deleteQuery);

	}

}
