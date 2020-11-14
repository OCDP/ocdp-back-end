package br.ufg.api.ocd.model;

import lombok.Data;

import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
@Data
public class CustomSequences {
    @Id
    private String id;
    private int seq;

}