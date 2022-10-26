package com.ashu.service;

import java.util.List;

import com.ashu.binding.request.SearchRequest;
import com.ashu.binding.responce.PlanResponce;

public interface InsurancePlanService {

	public List<PlanResponce> searchPlans(SearchRequest request);
	
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
}
