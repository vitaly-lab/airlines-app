package com.airlines.dao;

import com.airlines.entity.Crew;
import com.airlines.entity.CrewMember;

import java.util.List;

public interface CrewDao {

    void addMemberToCrew(CrewMember crewMember, Crew crew);

    List<CrewMember> findCrewMembersById(int idCrew);

    List<CrewMember> findCrewMembersByName(String nameCrew);
}
