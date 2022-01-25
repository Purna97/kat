// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.controller;

import java.io.FileNotFoundException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import com.knowledgeassessment.entity.Users;
import com.knowledgeassessment.DTO.NotAttendedReportDto;
import java.util.Iterator;
import com.knowledgeassessment.entity.TestDetails;
import com.knowledgeassessment.entity.ApplicationDept;
import com.knowledgeassessment.DTO.ApplicationMasterDto;
import com.knowledgeassessment.DTO.ScoreDto;
import com.knowledgeassessment.entity.ScoreDetails;
import com.knowledgeassessment.DTO.ReportDto;
import java.util.ArrayList;
import com.knowledgeassessment.entity.ApplicationMaster;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import com.knowledgeassessment.DAO.ReportsDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.knowledgeassessment.DAO.AssessmentDao;
import org.springframework.stereotype.Controller;

@Controller
public class ReportsController
{
    @Autowired
    private AssessmentDao assessmentDao;
    @Autowired
    private ReportsDao reportDao;
    
    @RequestMapping(value = { "/generateReports" }, method = { RequestMethod.GET })
    public String generateReports(HttpServletRequest req, Model model) {
        return "generateReport";
    }
    
    @RequestMapping(value = { "/test_attendedReport" }, method = { RequestMethod.GET })
    public String testAttendedReportpage(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        List<ApplicationMaster> appList = (List<ApplicationMaster>)assessmentDao.getAllApplicationName();
        model.addAttribute("appList", appList);
        return "TestAttentendedReport";
    }
    
    @RequestMapping(value = { "/test_not_attendedReport" }, method = { RequestMethod.GET })
    public String testNotAttendedReportpage(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        List<ApplicationMaster> appList = (List<ApplicationMaster>)assessmentDao.getAllApplicationName();
        model.addAttribute("appList", appList);
        return "TestNotAttendedReport";
    }
    
    @RequestMapping(value = { "/getReportData" }, method = { RequestMethod.GET })
    public String getReportDetailsOfTestAttended(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        List<ReportDto> reportlist = new ArrayList<ReportDto>();
        Integer applicationId = Integer.parseInt(req.getParameter("applicationid"));
        Integer testId = Integer.parseInt(req.getParameter("testid"));
        ApplicationMaster application = assessmentDao.applicationDetails((int)applicationId);
        String applicationName = application.getApplicationname();
        TestDetails test = reportDao.getTestDetaisByTestId(testId);
        String testName = test.getTestname();
        List<ScoreDetails> scoreDetails = (List<ScoreDetails>)reportDao.getScoredetailsList(applicationId, testId);
        for (ScoreDetails scoreDetails2 : scoreDetails) {
            String userName = reportDao.getEmployeeName(scoreDetails2.getEmpnumber());
            ReportDto reportDTO = new ReportDto();
            reportDTO.setApplicationId((int)applicationId);
            reportDTO.setTestId((int)testId);
            reportDTO.setApplicationName(applicationName);
            reportDTO.setTestName(testName);
            reportDTO.setEmpNumber(scoreDetails2.getEmpnumber());
            reportDTO.setScore(scoreDetails2.getScore());
            reportDTO.setStatus(scoreDetails2.getTeststatus());
            reportDTO.setEmpName(userName);
            reportlist.add(reportDTO);
        }
        int passCount = reportDao.getPassCount(applicationId, testId);
        int failCount = reportDao.getFailCount(applicationId, testId);
        ScoreDto scoreCountObj = new ScoreDto();
        List scoreCountList = new ArrayList();
        scoreCountList.add(failCount);
        scoreCountList.add(passCount);
        scoreCountObj.setReportDtoList((List)reportlist);
        scoreCountObj.setScoreCountList(scoreCountList);
        model.addAttribute("reportlist", reportlist);
        model.addAttribute("passcount", passCount);
        model.addAttribute("failcount", failCount);
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("testId", testId);
        model.addAttribute("listSize", reportlist.size());
        List<ApplicationMaster> appList = assessmentDao.getAllApplicationName();
        model.addAttribute("appList", appList);
        String deptid = (String)req.getSession().getAttribute("department");
        Integer departmentId = Integer.parseInt(deptid);
        List<ApplicationDept> applicationList = assessmentDao.getApplicationlist(departmentId);
        List<ApplicationMasterDto> applicationDtoList = new ArrayList<ApplicationMasterDto>();
        for (ApplicationDept applicationDept : applicationList) {
            ApplicationMaster applications = assessmentDao.applicationDetails(applicationDept.getApplicationMaster().getApplicationid());
            ApplicationMasterDto applicationDto = new ApplicationMasterDto();
            applicationDto.setApplicationid(Integer.valueOf(applications.getApplicationid()));
            applicationDto.setApplicationName(applications.getApplicationname());
            applicationDtoList.add(applicationDto);
        }
        model.addAttribute("applicationDtoList", applicationDtoList);
        session.setAttribute("downloadAttTestReport", reportlist);
        return "TestAttentendedReport";
    }
    
    @RequestMapping(value = { "/generateAssessmentReport" }, method = { RequestMethod.GET })
    public String generateAssessmentReport(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("username") == null) {
            model.addAttribute("session_msg", "Your Session has been expired,please login again!");
            return "redirect:Login";
        }
        List<NotAttendedReportDto> reportlist = new ArrayList<NotAttendedReportDto>();
        ReportDto report = new ReportDto();
        int userCount = 0;
        int count = 0;
        Integer appid = Integer.parseInt(request.getParameter("applicationid"));
        Integer testid = Integer.parseInt(request.getParameter("testid"));
        List<Users> users = reportDao.getAllUsers();
        ApplicationMaster application = assessmentDao.applicationDetails((int)appid);
        String applicationName = application.getApplicationname();
        TestDetails test = reportDao.getTestDetaisByTestId(testid);
        String testName = test.getTestname();
        for (Users users2 : users) {
            boolean isUserExists = reportDao.getScoreDetailsByEmpNo(users2.getEmp_number(), appid, testid);
            if (!isUserExists) {
                NotAttendedReportDto reportDTO = new NotAttendedReportDto();
                reportDTO.setApplicationName(applicationName);
                reportDTO.setTestName(testName);
                reportDTO.setEmpNumber(users2.getEmp_number());
                reportDTO.setStatus("Not Attempted");
                reportDTO.setEmpName(users2.getFirstName()+""+users2.getLastName());
                reportlist.add(reportDTO);
                ++count;
            }
        }
        System.out.println("count----" + count);
        ScoreDto scoreCountObj = new ScoreDto();
        scoreCountObj.setNotAttendedDto((List)reportlist);
        scoreCountObj.setUserCount(count);
        model.addAttribute("reportlist", reportlist);
        model.addAttribute("listSize", reportlist.size());
        model.addAttribute("totalCount", count);
        session.setAttribute("nonAttTestReport", reportlist);
        List<ApplicationMaster> appList = assessmentDao.getAllApplicationName();
        model.addAttribute("appList", appList);
        String deptid = (String)request.getSession().getAttribute("department");
        Integer departmentId = Integer.parseInt(deptid);
        List<ApplicationDept> applicationList = assessmentDao.getApplicationlist(departmentId);
        List<ApplicationMasterDto> applicationDtoList = new ArrayList<ApplicationMasterDto>();
        for (ApplicationDept applicationDept : applicationList) {
            ApplicationMaster applications = assessmentDao.applicationDetails(applicationDept.getApplicationMaster().getApplicationid());
            ApplicationMasterDto applicationDto = new ApplicationMasterDto();
            applicationDto.setApplicationid(Integer.valueOf(applications.getApplicationid()));
            applicationDto.setApplicationName(applications.getApplicationname());
            applicationDtoList.add(applicationDto);
        }
        model.addAttribute("applicationDtoList", applicationDtoList);
        return "TestNotAttendedReport";
    }
    
    @RequestMapping(value = { "/downloadTestReport" }, method = { RequestMethod.GET })
    public void testReports(HttpServletRequest req, Model model, HttpServletResponse response) throws FileNotFoundException {
        HttpSession sess = req.getSession();
        try {
            List<ReportDto> reportList = (List<ReportDto>)sess.getAttribute("downloadAttTestReport");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=Test_AttendedReport.xls");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("TestAttendedReport");
            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Employee Number");
            cell = row.createCell(1);
            cell.setCellValue("Employee Name");
            cell = row.createCell(2);
            cell.setCellValue("Application Name");
            cell = row.createCell(3);
            cell.setCellValue("Test Name");
            cell = row.createCell(4);
            cell.setCellValue("Score");
            cell = row.createCell(5);
            cell.setCellValue("Result");
            int i = 1;
            for (ReportDto list : reportList) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue((double)list.getEmpNumber());
                cell = row.createCell(1);
                cell.setCellValue(list.getEmpName());
                cell = row.createCell(2);
                cell.setCellValue(list.getApplicationName());
                cell = row.createCell(3);
                cell.setCellValue(list.getTestName());
                cell = row.createCell(4);
                cell.setCellValue((double)list.getScore());
                cell = row.createCell(5);
                cell.setCellValue(list.getStatus());
                ++i;
            }
            FileOutputStream out = new FileOutputStream(new File("Test_AttendedReport.xlsx"));
            workbook.write((OutputStream)response.getOutputStream());
            out.close();
            System.out.println("Test Attended Report generated successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = { "/downloadNonAttTestReport" }, method = { RequestMethod.GET })
    public void nonAttTestReports(HttpServletRequest req, Model model, HttpServletResponse response) throws FileNotFoundException {
        HttpSession sess = req.getSession();
        try {
            List<NotAttendedReportDto> reportlist = (List<NotAttendedReportDto>)sess.getAttribute("nonAttTestReport");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=Test_Not_AttendedReport.xls");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("TestNotAttendedReport");
            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Employee Number");
            cell = row.createCell(1);
            cell.setCellValue("Employee Name");
            cell = row.createCell(2);
            cell.setCellValue("Application Name");
            cell = row.createCell(3);
            cell.setCellValue("Test Name");
            cell = row.createCell(4);
            cell.setCellValue("Result");
            int i = 1;
            for (NotAttendedReportDto list : reportlist) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue((double)list.getEmpNumber());
                cell = row.createCell(1);
                cell.setCellValue(list.getEmpName());
                cell = row.createCell(2);
                cell.setCellValue(list.getApplicationName());
                cell = row.createCell(3);
                cell.setCellValue(list.getTestName());
                cell = row.createCell(4);
                cell.setCellValue(list.getStatus());
                ++i;
            }
            FileOutputStream out = new FileOutputStream(new File("Test_Not_AttendedReport.xlsx"));
            workbook.write((OutputStream)response.getOutputStream());
            out.close();
            System.out.println("Test Not Attended Report generated successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
