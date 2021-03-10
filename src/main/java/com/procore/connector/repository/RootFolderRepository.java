package com.procore.connector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.procore.connector.models.RootFolder;


@Repository
public interface RootFolderRepository extends JpaRepository<RootFolder, String> {

	
	@Query("Select r from RootFolder r where r.localPath= :localPath")
	public RootFolder getRootFolderByLocalPath(@Param("localPath") String localPath);
	
	@Query("select case when count(r)> 0 then true else false end from RootFolder r where r.localPath= :localPath")
	public boolean folderExistsByLocalPath(@Param("localPath") String localPath);
}
