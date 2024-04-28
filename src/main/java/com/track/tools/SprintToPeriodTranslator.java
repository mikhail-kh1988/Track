package com.track.tools;

import com.track.dto.output.Period;
import com.track.entity.Sprint;
import com.track.entity.SprintIssue;
import com.track.dto.output.Track;


import java.util.ArrayList;
import java.util.List;

public class SprintToPeriodTranslator {

    public static Period getPeriodFromSprint(Sprint sprint){

        Period period = new Period();
        List<Track> trackList = new ArrayList<>();

        period.setId(sprint.getId());
        period.setName(sprint.getName());
        period.setStartDate(sprint.getStartDate());
        period.setEndDate(sprint.getEndDate());

        for (SprintIssue si: sprint.getSprintsIssue())
            trackList.add(IssueToTrackTranslator.getTrack(si.getIssues()));

        period.setTrackList(trackList);

        return period;
    }
}
