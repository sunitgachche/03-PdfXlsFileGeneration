package com.pavanit.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavanit.config.ExcelGenerator;
import com.pavanit.config.PdfGenerator;
import com.pavanit.request.SearchRequest;
import com.pavanit.response.SearchResponse;
import com.pavanit.service.ReportService;

@RestController
public class ReportRestController {
	
	
	@Autowired
	private ReportService reportService;

	@GetMapping("plan-name")
	public List<String> getPlanNames() {
		return reportService.getPlanNames();
	}

	@GetMapping("plan-status")
	public List<String> getPlanStatus() {
		return reportService.getPlanStatuses();
	}

	@PostMapping("/serch")
	public List<SearchResponse> serch(@RequestBody SearchRequest request) {
		return reportService.searchPlans(request);
	}

	@GetMapping("/excel")
	public void generatorExcel(HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=Plans.xls";
		response.setHeader(headerKey, headerValue);

		List<SearchResponse> plan = reportService.searchPlans(null);
		ExcelGenerator excelGenerator = new ExcelGenerator();
		excelGenerator.generateExcel(plan, response);
	}

	@GetMapping("/pdf")
	public void generatorPdf( HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=Plans.pdf";
		response.setHeader(headerKey, headerValue);

		List<SearchResponse> plan = reportService.searchPlans(null);
		PdfGenerator pdfGen = new PdfGenerator();
		pdfGen.generatePdf(plan, response);
	}


}
