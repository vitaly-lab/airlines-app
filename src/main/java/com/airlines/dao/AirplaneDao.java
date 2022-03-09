package com.airlines.dao;

import com.airlines.entity.Airplane;

import java.util.List;

public interface AirplaneDao {

    void save(Airplane airplane);

    Airplane findOne(String codeName);

    List<Airplane> findAll();

    boolean deleteAirplane(int crewName);

    List<Airplane> searchAirplanes(String crewId);

    void updateAirplane(Airplane airplane, int crews_id);

}
