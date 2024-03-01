package com.track.entity.issue;

import com.track.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Action {

    private Long id;
    private LocalDateTime createDate;
    private String describe;
    private User createBy;

}
