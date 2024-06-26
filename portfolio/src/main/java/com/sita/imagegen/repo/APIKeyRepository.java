package com.sita.imagegen.repo;

import com.sita.imagegen.model.APIKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APIKeyRepository extends JpaRepository<APIKey, Long> {
    APIKey findByServiceName(String serviceName);
}