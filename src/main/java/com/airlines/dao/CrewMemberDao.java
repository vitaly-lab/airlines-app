package com.airlines.dao;

import com.airlines.entity.CrewMember;

public interface CrewMemberDao {

    void save(CrewMember crewMember);

    CrewMember findOne(int id);

    void update(CrewMember crewMember);
}
