package com.track.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Board {

    private Long id;
    private String statesValues;
    private String nameBoard;
    private List<String> users;
    private List<String> sprints;
    private List<String> projects;



}
