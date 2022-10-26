package com.ashu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashu.binding.request.SearchRequest;
import com.ashu.binding.responce.PlanResponce;
import com.ashu.entity.InsurancePlan;
import com.ashu.repository.InsurancePlanRepository;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

	@Autowired
	private InsurancePlanRepository repo;

	@Override
	public List<PlanResponce> searchPlans(SearchRequest request) {

		InsurancePlan entity = new InsurancePlan();

		if (request!=null && request.getPlanName() != null && !request.getPlanName().equals("")) {

			entity.setPlanName(request.getPlanName());
		}
		if (request!=null && request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {

			entity.setPlanStatus(request.getPlanStatus());

		}

		Example<InsurancePlan> of = Example.of(entity);

		List<InsurancePlan> findAll = repo.findAll(of);

		List<PlanResponce> plans = new ArrayList<>();

		for (InsurancePlan plan : findAll) {

			PlanResponce presp = new PlanResponce();

			BeanUtils.copyProperties(plan, presp);
		}
		return plans;
	}

	@Override
	public List<String> getUniquePlanNames() {

		return repo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		
		return repo.getPlanStatus();
	}
}
