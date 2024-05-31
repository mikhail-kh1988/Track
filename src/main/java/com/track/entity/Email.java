package com.track.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Email {

    private Long id;
    private List<String> to;

}
