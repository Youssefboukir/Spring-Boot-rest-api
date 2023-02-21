package com.contact.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="contacts")
public class ContactEntity {

    @Id
    @Column(name = "id_contact")
    private String idContact;

    private String name;

    private String email;

    private String phone;

    @CreationTimestamp
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updateAt;
}
