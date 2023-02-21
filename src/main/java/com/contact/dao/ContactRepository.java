package com.contact.dao;

import com.contact.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,String> {

    @Query("FROM ContactEntity WHERE idContact IN :ids")
    List<ContactEntity> getContactByIds(List<String> ids);
}
