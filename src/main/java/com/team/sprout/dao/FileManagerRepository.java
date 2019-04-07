package com.team.sprout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.team.sprout.vo.ProjectFile;

@Repository
public class FileManagerRepository {

	@Autowired
	SqlSession session;

	public int projectFileUpLoad(ProjectFile ProjectFile) {
		FileManagerDAO dao=session.getMapper(FileManagerDAO.class);
		int result=dao.projectFileUpLoad(ProjectFile);
		return result;
	}

	public List<ProjectFile> getFileList(String projectFile_location) {
		FileManagerDAO dao=session.getMapper(FileManagerDAO.class);
		List<ProjectFile> list=dao.getFileList(projectFile_location);
		return list;
	}

	public int getFileNum() {
		FileManagerDAO dao=session.getMapper(FileManagerDAO.class);
		int result=dao.getFileNum();
		return result;
	}

	public ProjectFile searchFile(int projectFile_num) {
		FileManagerDAO dao=session.getMapper(FileManagerDAO.class);
		ProjectFile projectFile=dao.searchFile(projectFile_num);
		return projectFile;
	}

	public int fileDelete(int projectFile_num) {
		FileManagerDAO dao=session.getMapper(FileManagerDAO.class);
		int result=dao.fileDelete(projectFile_num);
		return result;
	};
}
