package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFuntions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
String inputpath ="C:\\MyProject1\\Hybrid_FrameWork\\TestInput\\HybridFrameWorks.xlsx";
String outputpath ="C:\\MyProject1\\Hybrid_FrameWork\\TestOutput\\HybridResults.xlsx";
String TCSheet ="TestCases";
String TSSheet ="TestSteps";

@Test
public void startTest()throws Throwable
{
	boolean res=false;
	String tcres="";
	ExcelFileUtil xl =new ExcelFileUtil(inputpath);
	//count no of rows in TCSHeet, TSSheet
	int TCCount =xl.rowcount(TCSheet);
	int TSCount =xl.rowcount(TSSheet);
	//count no of cells in TCSHeet, TSSheet
	int TCCell=xl.cellcount(TCSheet);
	int TSCell=xl.cellcount(TSSheet);
	Reporter.log(TCCount+"  "+TSCount+" "+TCCell+"   "+TSCell,true);
	//itearte all rows in TCSheet
	for(int i=1;i<=TCCount;i++)
	{
		String ExecutionMode=xl.getCellData(TCSheet, i, 2);
		if(ExecutionMode.equalsIgnoreCase("Y"))
		{
			String tcid =xl.getCellData(TCSheet, i, 0);
			String ModuleName =xl.getCellData(TCSheet, i, 1);
			//iterate all rows in TSSheet
			for(int j=1;j<TSCount;j++)
			{
				String tsid =xl.getCellData(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					String keyword =xl.getCellData(TSSheet, j, 3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						res= FunctionLibrary.verifyLogin(para1, para2);
					}
					else if(keyword.equalsIgnoreCase("NewBranch"))
					{
						String para1=xl.getCellData(TSSheet, j, 5);
						String para2=xl.getCellData(TSSheet, j, 6);
						String para3=xl.getCellData(TSSheet, j, 7);
						String para4=xl.getCellData(TSSheet, j, 8);
						String para5=xl.getCellData(TSSheet, j, 9);
						String para6=xl.getCellData(TSSheet, j, 10);
						String para7=xl.getCellData(TSSheet, j, 11);
						String para8=xl.getCellData(TSSheet, j, 12);
						String para9=xl.getCellData(TSSheet, j, 13);
						FunctionLibrary.clickBranches();
						res= FunctionLibrary.verifyBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
																			
					}
					else if(keyword.equalsIgnoreCase("BranchUpdate"))
					{
						String para1=xl.getCellData(TSSheet, j, 5);
						String para2=xl.getCellData(TSSheet, j, 6);
						String para5=xl.getCellData(TSSheet, j, 9);
						String para6=xl.getCellData(TSSheet, j, 10);
						FunctionLibrary.clickBranches();
						res= FunctionLibrary.verifyBranchUpdate(para1, para2, para5, para6);
					}
					else if(keyword.equalsIgnoreCase("NewRole"))
					{
						String para1=xl.getCellData(TSSheet, j, 5);
						String para2=xl.getCellData(TSSheet, j, 6);
						String para3=xl.getCellData(TSSheet, j, 7);
						FunctionLibrary.clickRoles();
						res= FunctionLibrary.verifyRoleCreation(para1, para2, para3);
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res=FunctionLibrary.verifyLogout();
					}
					
					String tsres="";
					if(res)
					{
						tsres="Pass";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					else
					{
						tsres="Fail";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					tcres=tsres;
										
				}
			}
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		else
		{
			//write as blocked which test case flag to N
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
	
	
	
}
}
