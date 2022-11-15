package com.fileUploadbatch2.csv_to_Db2.repository;

import com.fileUploadbatch2.csv_to_Db2.model.PMDealsDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CMSRepo extends JpaRepository<PMDealsDetails, UUID> {

}
