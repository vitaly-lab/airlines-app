package com.airlines.dao;

import com.airlines.entity.Airplane;

import java.util.List;

public interface AirplaneDao {

    void save(Airplane airplane);

    Airplane findOne(String codeName);

    List<Airplane> findAll();

    boolean deleteAirplane(int crewId);

    List<Airplane> searchAirplanes(String  crewName);

    void updateAirplane(Airplane airplane, int crews_id);

}
