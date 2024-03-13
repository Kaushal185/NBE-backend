package com.grs.angproject.msgtrace;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MSG_TRACE")
public class MsgTrace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SWIFT_MSG_ID")
    private Long swiftMsgId;

    @Column(name = "MSG_STATUS")
    private String msgStatus;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CREATED_ON")
    private Timestamp createdOn;
}
