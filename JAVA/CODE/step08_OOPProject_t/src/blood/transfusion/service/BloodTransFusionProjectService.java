package blood.transfusion.service;

import java.util.ArrayList;

import blood.transfusion.dto.BloodTransfusionProject;
import blood.transfusion.dto.Donor;
import blood.transfusion.dto.People;
import blood.transfusion.dto.Recipient;
import blood.transfusion.exception.NotExistException;
import blood.transfusion.model.BloodTransfusionVirtualDB;

public class BloodTransFusionProjectService {
	private static BloodTransFusionProjectService instance = new BloodTransFusionProjectService();
	private BloodTransfusionVirtualDB projectVirtualData = BloodTransfusionVirtualDB.getInstance();

	private BloodTransFusionProjectService(){}
	public static BloodTransFusionProjectService getInstance(){
		return instance;
	}
	
	// 모든 프로젝트 반환
	public ArrayList<BloodTransfusionProject> getAllProjects(){
		return projectVirtualData.getProjectList();
	}
	
	// 프로젝트 검색_1. 모든 데이터를 가지고 와서 2. 이름으로 비교(조건) -> 해당 조건에 맞는 프로젝트를 반환
	public BloodTransfusionProject getProject(String projectName) {
		BloodTransfusionProject project = null;
		
		ArrayList<BloodTransfusionProject> projects = projectVirtualData.getProjectList();
		for(int i = 0; i < projects.size(); i++) {
			if (projects.get(i).getBtProjectName().equals(projectName)) {
				project = projects.get(i);
			}
		}
		
		return project;
	}

	// 새로운 프로젝트 추가
	public void projectInsert(BloodTransfusionProject project) {
		projectVirtualData.insertProject(project);
	}
	
	// 프로젝트 수정 - 프로젝트 명으로 현혈자 혹은 수혈자 수정_1. 프로젝트 존재여부 확인, 2. 수정하고자 하는 대상이 누구인지를 판단! 3. 수정 형식이 맞지 않을 때 수정 불가능 하도록 지정
	public void projectUpdate(String projectName, People people) throws NotExistException{
		BloodTransfusionProject project = getProject(projectName);
		
		if(project != null) {
			if (people instanceof Recipient) {
				project.setRecipient((Recipient) people);
			} else if (people instanceof Donor) {
				project.setDonor((Donor) people);
			} else {
				throw new NotExistException("입력하신 데이터의 형태를 확인해주세요.");
			}
		} else {
			throw new NotExistException("수정하고자 하는 Project가 존재하지 않습니다");
		}
	}
	
	
	// 프로젝트 삭제_1. 프로젝트 존재 여부 확인, 2. 삭제
	public void projectDelete(String projectName) throws NotExistException{
		BloodTransfusionProject project = getProject(projectName);
		
		if(project != null) {
			projectVirtualData.getProjectList().remove(project);
		} else {
			throw new NotExistException("삭제하고자 하는 Project가 존재하지 않습니다");
		}
	}
}
