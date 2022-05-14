package reporterService.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reporterService.entity.MyStoredProcedure;
import reporterService.entity.Report;
import reporterService.entity.ReportResult;
import reporterService.service.ReportService;
import reporterService.service.ServiceFactory;

@Path("/")
public class ReportResources {
	private static final Logger logger = LoggerFactory.getLogger(ReportResources.class);

	@Context
	private ServiceFactory reportTypeService;

	@POST
	@Path("addReport")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public int addReport(Report report) {
		logger.info("add report was called");
		int generatedId = -1;
		if (null != reportTypeService) {
			logger.info("checkPost_1");
			ReportService reportService = reportTypeService.getReportService();
			generatedId = reportService.createReport(report);

		}

		return generatedId;
	}

	@GET
	@Path("reportTypes")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Report> reportTypeList() {
		List<Report> reports = null;
		if (null != reportTypeService) {
			ReportService reportService = reportTypeService.getReportService();
			reports = reportService.getReport();
		}

		return reports;
	}

	@GET
	@Path("storedProcedure")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ReportResult> storedProcedure() {
		List<MyStoredProcedure> myStoredProcedures = null;
		MyStoredProcedure resultProcedure = null;
		List<ReportResult> reportResults = null;
		if (null != reportTypeService) {
			ReportService reportService = reportTypeService.getReportService();
			HashMap<String, Object> inputParams = new HashMap<String, Object>();
			inputParams.put("reportId", 1);
			// inputParams.put("reportId",3);
			String sqlCall = Util.buildStoredProcedureCall(inputParams, "GetMyStoredProcedure");
			logger.info("SQL Call:" + sqlCall);
			logger.info("inputParams:" + inputParams.toString());
			myStoredProcedures = reportService.getMyStoredProcedures(sqlCall, inputParams);
			logger.info("Result:" + myStoredProcedures);
			resultProcedure = Util.buildPrettyObject(myStoredProcedures);
			logger.info("resultProcedure:" + resultProcedure);
			Set<String> inputSet = resultProcedure.getInputParams();
			logger.info("inputSet:" + inputSet);

			sqlCall = Util.buildStoredProcedureCall(inputSet, resultProcedure.getName());
			logger.info("SQL Call:" + sqlCall);
			// HashMap<String,Object> spParams = null;
			HashMap<String, Object> spParams = new HashMap();
			spParams.put("fromDate", 1530819855280L);
			spParams.put("toDate", 1629626623612L);
			reportResults = reportService.getResult(sqlCall, spParams);
		}

		return reportResults;
	}
}
