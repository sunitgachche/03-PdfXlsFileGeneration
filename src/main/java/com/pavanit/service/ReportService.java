package com.pavanit.service;

import java.util.List;

import com.pavanit.request.SearchRequest;
import com.pavanit.response.SearchResponse;

public interface ReportService {

	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<SearchResponse> searchPlans(SearchRequest request);
	
	//public void exportExcel(List<SearchResponse>records);
	
	//public void exportPdf(List<SearchResponse>records);
	
	
	
}
