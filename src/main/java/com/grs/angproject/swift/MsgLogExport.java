package com.grs.angproject.swift;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MSG_LOG")
public class MsgLogExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MESSAGE_TYPE")
    private String messageType;
//    private String direction;
//    private String filename;

    @Column(name = "IDENTIFIER")
    private String identifier;
//    private String receiver;
//    private Long translationRuleId;

    @Column(name = "STATUS")
    private String status;
    //    private String createdBy;
    @Column(name = "CREATED_ON")
    private Timestamp createdOn;
//    private String updatedBy;

    public Long getId() {
        return this.id;
    }
//    private String source;

}