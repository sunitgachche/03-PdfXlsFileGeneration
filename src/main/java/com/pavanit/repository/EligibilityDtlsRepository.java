package com.pavanit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pavanit.entity.EligibilityDtlsEntity;

public interface EligibilityDtlsRepository extends JpaRepository<EligibilityDtlsEntity, Integer>{
	
	@Query("select distinct(planName) from EligibilityDtlsEntity")
	public List<String> getUniquePlanNames();
	
	@Query("select distinct(planStatus) from EligibilityDtlsEntity")
	public List<String> getUniquePlanStatus();


	public List<EligibilityDtlsEntity> findByPlanName(String planName);

	public List<EligibilityDtlsEntity> findByPlanStatus(String planStatus);

	public List<EligibilityDtlsEntity> findByPlanNameAndPlanStatus(String planName, String planStatus);
}
