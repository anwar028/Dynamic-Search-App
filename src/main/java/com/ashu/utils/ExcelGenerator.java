package com.ashu.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ashu.binding.responce.PlanResponce;

public class ExcelGenerator {

	public void report(List<PlanResponce> plans, HttpServletResponse response) throws IOException {

		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("plans");
		XSSFRow createRow = sheet.createRow(0);

		createRow.createCell(0).setCellValue("PlanId");
		createRow.createCell(1).setCellValue("PlanName");
		createRow.createCell(2).setCellValue("PlanaHolderName");
		createRow.createCell(3).setCellValue("PlanStatus");
		createRow.createCell(4).setCellValue("PlanSsn");

		for (int i = 0; i < plans.size(); i++) {

			PlanResponce plan = plans.get(i);

			XSSFRow dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(plan.getPlanId());
			dataRow.createCell(0).setCellValue(plan.getPlanName());
			dataRow.createCell(0).setCellValue(plan.getPlanaHolderName());
			dataRow.createCell(0).setCellValue(plan.getPlanStatus());
			dataRow.createCell(0).setCellValue(plan.getPlanSsn());

		}

		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();

	}
}
