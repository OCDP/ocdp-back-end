package br.ufg.api.ocd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class CustomSequences {
    @Id
    private String id;
    private int seq;

}