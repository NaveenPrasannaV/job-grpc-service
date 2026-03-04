package org.nit.jobgrpcservice.repo;


import org.nit.jobgrpcservice.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

    List<JobPost> findByPostProfileContainingOrPostDescContainingIgnoreCase(String postProfile, String postDesc);

}
