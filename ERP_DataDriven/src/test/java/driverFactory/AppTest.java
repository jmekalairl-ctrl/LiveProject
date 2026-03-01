package driverFactory;

import org.testng.annotations.Test;
import ERP.Pages.SuppliersPage;
import utils.BaseClass;
import utils.ExcelFileUtil;
public class AppTest extends BaseClass{
	String inputpath 	=	"./DataTables/ERPData.xlsx";
	String supplierpath =	"./DataTables/SupplierResults.xlsx";
	String customerpath =	"./DataTables/CustomerResults.xlsx";
	@Test(priority = 0,enabled = false)
	public void startSupplier() throws Throwable
	{
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("suppliers");
		test.info("No of rows in supplier sheet   "+rc);
		System.out.println("No of rows in supplier sheet  "+rc);
		for(int i=1;i<=rc;i++)
		{
			test.assignAuthor("Jagan Mohan Reddy Mekala ");
			test.assignCategory("Functional Testing");
			String sname = 		xl.getCellData("suppliers", i, 0);
			String address = 	xl.getCellData("suppliers", i, 1);
			String city = 		xl.getCellData("suppliers", i, 2);
			String country = 	xl.getCellData("suppliers", i, 3);
			String cperson = 	xl.getCellData("suppliers", i, 4);
			String pnumber = 	xl.getCellData("suppliers", i, 5);
			String email = 		xl.getCellData("suppliers", i, 6);
			String mnumber = 	xl.getCellData("suppliers", i, 7);
			String notes = 		xl.getCellData("suppliers", i, 8);
			test.info(sname+"   "+address+"   "+city+"   "+country+"    "+cperson+"   "+pnumber+"   "+email+"    "+mnumber);
			SuppliersPage sup = new SuppliersPage(driver);
			boolean res = sup.Add_Supplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
			if(res)
			{
				xl.setCellData("suppliers", i, 9, "Pass", supplierpath);
				test.pass("Suppler Added Success");
			}
			else
			{
				xl.setCellData("suppliers", i, 9, "Fail", supplierpath);
				test.fail("Suppler Added UnSuccess");
			}
		}
	}
}
