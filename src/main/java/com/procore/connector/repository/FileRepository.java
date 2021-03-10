package com.procore.connector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.procore.connector.models.Files;


@Repository
public interface FileRepository extends JpaRepository<Files, String> {

	@Query("Select r from Files r where r.localPath= :localPath")
	public Files getFilesByLocalPath(@Param("localPath") String localPath);
	
	@Query("select case when count(r)> 0 then true else false end from Files r where r.localPath= :localPath")
	public boolean fileExistsByLocalPath(@Param("localPath") String localPath);
}
