package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.ProjectFile;

public interface FileManagerDAO {

	public int projectFileUpLoad(ProjectFile projectFile);

	public List<ProjectFile> getFileList(String projectFile_location);

	public int getFileNum();

	public ProjectFile searchFile(int projectFile_num);

	public int fileDelete(int projectFile_num);

}
