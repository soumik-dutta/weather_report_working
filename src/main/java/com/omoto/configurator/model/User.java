package com.omoto.configurator.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by omoto on 16/12/16.
 */
@Data
@Entity
@Table(name = "CUSTOMERSTABLE")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @NotNull
    @Size(min=2, max=40)
    @Column(name = "USERNAME")
    private String userName;

    @NotNull
    @Size(min=2, max=20)
    @Column(name = "PASSWORD")
    private String password;

}
