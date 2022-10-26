package com.ashu.rest;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashu.binding.request.SearchRequest;
import com.ashu.binding.responce.PlanResponce;
import com.ashu.service.InsurancePlanService;
import com.ashu.utils.ExcelGenerator;
import com.ashu.utils.PdfReportGenerator;

@RestController
public class InsurancePlanRestController {

	@Autowired
	private InsurancePlanService service;
	
	
	@GetMapping("/pdff")
	public void exportIntoPdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans.pdf";
		response.setHeader(headerKey, headerValue);
 
		List<PlanResponce> searchPlans = service.searchPlans(null);

		PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
		pdfReportGenerator.exportPdf(searchPlans, response);

	}


	@GetMapping("/excel")
	public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans.xlsx";
		response.setHeader(headerKey, headerValue);

		List<PlanResponce> searchPlans = service.searchPlans(null);

		ExcelGenerator excelGenerator = new ExcelGenerator();

		excelGenerator.report(searchPlans, response);
	}

	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponce>> SearchPlans(@RequestBody SearchRequest request) {

		List<PlanResponce> searchPlans = service.searchPlans(request);

		return new ResponseEntity<>(searchPlans, HttpStatus.OK);
	}

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> getPlanNames() {

		List<String> planNames = service.getUniquePlanNames();

		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}

	@GetMapping("/planStatus")
	public ResponseEntity<List<String>> getPlanStatus() {

		List<String> planStatus = service.getUniquePlanStatus();

		return new ResponseEntity<>(planStatus, HttpStatus.OK);
	}

}
