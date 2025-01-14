package com.cmb.beprepared.repository;

import com.cmb.beprepared.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository <Alert, Long> {
}
