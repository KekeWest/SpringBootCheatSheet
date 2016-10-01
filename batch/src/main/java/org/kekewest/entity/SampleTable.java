package org.kekewest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SampleTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String staffCode;

    private String name;

}
