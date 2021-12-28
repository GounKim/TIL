package blood.transfusion.controller;

import java.util.ArrayList;

import blood.transfusion.dto.BloodTransfusionProject;
import blood.transfusion.dto.People;
import blood.transfusion.exception.NotExistException;
import blood.transfusion.service.BloodTransFusionProjectService;
import blood.transfusion.view.EndView;
import blood.transfusion.view.FailView;

public class BloodTransfusionController {
	private static BloodTransfusionController instance = new BloodTransfusionController();
	private BloodTransFusionProjectService service = BloodTransFusionProjectService.getInstance();

	private BloodTransfusionController() {
	}

	public static BloodTransfusionController getInstance() {
		return instance;
	}

	// 모든 프로젝트 검색
	public void projectListView() {
		ArrayList<BloodTransfusionProject> projectList = service.getAllProjects();
		EndView.projectListView(projectList);
	}
	
	// 특정 프로젝트 검색_
	public void projectView(String projectName) {
		BloodTransfusionProject project = service.getProject(projectName);
		if (project != null) {
			EndView.projectView(project);
		} else {
			EndView.messageView("검색 요청하신 프로젝트는 존재하지 않습니다");
		}
	}

	// 새로운 프로젝트 저장
	public void insertProject(BloodTransfusionProject newProject) {
		service.projectInsert(newProject);
	}
	
	// 존재하는 프로젝트 수정_
	public void updateProject(String projectName, People people) {
		try {
			service.projectUpdate(projectName, people);
		} catch (NotExistException e){
			FailView.failMessageView(e.getMessage());
		}
	}

	// 프로젝트 삭제_
	public void deleteProject(String projectName) {
		try {
			service.projectDelete(projectName);
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}
}




























