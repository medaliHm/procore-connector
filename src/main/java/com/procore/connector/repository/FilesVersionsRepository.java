package com.procore.connector.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.procore.connector.models.File_versions;


public interface FilesVersionsRepository extends JpaRepository<File_versions, String> {

	@Query("Select v from File_versions v where v.file_id=:idFile")
	public List<File_versions> getListVersionByFile(@Param("idFile") String idFile);
}
