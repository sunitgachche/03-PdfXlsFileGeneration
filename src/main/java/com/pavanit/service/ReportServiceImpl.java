package com.pavanit.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.pavanit.entity.EligibilityDtlsEntity;
import com.pavanit.repository.EligibilityDtlsRepository;
import com.pavanit.request.SearchRequest;
import com.pavanit.response.SearchResponse;

@Service
public class ReportServiceImpl implements ReportService {
	/*
	 * @Autowired private EligibilityDtlsRepository eligibilityDtlsRepository;
	 * 
	 * @Override public List<String> getPlanNames() { return
	 * eligibilityDtlsRepository.getUniquePlanNames(); }
	 * 
	 * @Override public List<String> getPlanStatuses() { return
	 * eligibilityDtlsRepository.getUniquePlanStatus(); }
	 * 
	 * @Override public List<SearchResponse> searchPlans(SearchRequest request) {
	 * 
	 * List<EligibilityDtlsEntity> eligRecords = null; if(request == null) {
	 * List<EligibilityDtlsEntity> allRecords = eligibilityDtlsRepository.findAll();
	 * }else { user can select only plan-name and click on search button user can
	 * select only plan-status and click on search button user can select only
	 * plan-name and plan-status and click on search button user can select
	 * start-date and end-date and click on search button user can select plan-name,
	 * start-date and end-date and click on search button user can select
	 * plan-status, start-date and end-date and click on search button user can
	 * select plan-name, plan-status, start-date and end-date and click on search
	 * button
	 * 
	 * String planName = request.getPlanName(); String planStatus =
	 * request.getPlanStatus(); LocalDate startDate = request.getStartDate();
	 * LocalDate endDate = request.getEndDate();
	 * 
	 * 
	 * EligibilityDtlsEntity entity = new EligibilityDtlsEntity();
	 * 
	 * if(planName != null && !planName.equals("")) { //add plan-name to where
	 * clause entity.setPlanName(planName); }
	 * 
	 * if (planStatus != null && !planStatus.equals("")) { //add plan-status to
	 * where clause entity.setPlanStatus(planStatus); }
	 * 
	 * if (startDate != null & endDate != null) { //add start-date and end-date to
	 * where clause entity.setStartDate(startDate); entity.setEndDate(endDate); }
	 * 
	 * Example<EligibilityDtlsEntity> of = Example.of(entity);
	 * List<EligibilityDtlsEntity> findAll = eligibilityDtlsRepository.findAll(of);
	 * 
	 * } List<SearchResponse> response = new ArrayList<>(); for
	 * (EligibilityDtlsEntity eligRecord : eligRecords) { SearchResponse sr = new
	 * SearchResponse(); BeanUtils.copyProperties(eligRecord, sr); response.add(sr);
	 * } return response; }
	 * 
	 * public boolean isSearchReqEmpty(SearchRequest request) {
	 * 
	 * if (request == null) { return true; } if (request.getPlanName() != null &&
	 * !request.getPlanName().equals("")) { return false; } if
	 * (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
	 * return false; } if (request.getStartDate() != null &&
	 * !request.getStartDate().equals("")) { return false; } if
	 * (request.getEndDate() != null && !request.getEndDate().equals("")) { return
	 * false; } return true; }
	 * 
	 * 
	 */ 
	@Autowired
	private EligibilityDtlsRepository repository;

	@Override
	public List<String> getPlanNames() {
		return repository.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return repository.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {

		// user can select only plan_name and click on search button
		// user can select only plan_status and click on search button
		// user can select only plan_name and plan_status click on search button
		// user can select start_date and end_date click on search button
		// user can select paln_name, start_date,end_date click on search button
		// user can select paln_status, start_date,end_date click on search button
		// user can select paln_name,paln_status,start_date,end_date click on search
		// search button
		List<EligibilityDtlsEntity> eligRecords = null;

		if (isSearchReqEmpty(request)) {
			eligRecords = repository.findAll();
		} else {

			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();

			EligibilityDtlsEntity entity = new EligibilityDtlsEntity();

			if (planName != null && !planName.equals("")) {
				// add plan name where to clause
				entity.setPlanName(planName);
			}
			if (planStatus != null && !planStatus.equals("")) {
				// add plan name where to clause
				entity.setPlanStatus(planStatus);
			}
			if (startDate != null & endDate != null) {
				// add start date and end date where to clause
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}
			Example<EligibilityDtlsEntity> of = Example.of(entity);
			eligRecords = repository.findAll(of);
		}

		List<SearchResponse> response = new ArrayList<>();

		for (EligibilityDtlsEntity eligRecord : eligRecords) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(eligRecord, sr);
			response.add(sr);
		}
		return response;
	}

	public boolean isSearchReqEmpty(SearchRequest request) {

		if (request == null) {
			return true;
		}
		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			return false;
		}
		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			return false;
		}
		if (request.getStartDate() != null && !request.getStartDate().equals("")) {
			return false;
		}
		if (request.getEndDate() != null && !request.getEndDate().equals("")) {
			return false;
		}
		return true;
	}

}
