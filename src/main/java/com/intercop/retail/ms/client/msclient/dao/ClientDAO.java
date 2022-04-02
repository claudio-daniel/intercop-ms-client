package com.intercop.retail.ms.client.msclient.dao;

import com.intercop.retail.ms.client.msclient.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, String> {
}
