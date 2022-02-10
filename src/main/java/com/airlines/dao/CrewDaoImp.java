package com.airlines.dao;

import com.airlines.entity.Citizenship;
import com.airlines.entity.Crew;
import com.airlines.entity.CrewMember;
import com.airlines.entity.Position;
import com.airlines.exception.DaoOperationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrewDaoImp implements CrewDao {
    private static final String INSERT_CREWS_CREW_MEMBERS = "INSERT INTO crews_crew_members (crews_id, crew_members_id) VALUES(?, ?);";
    private static final String SELECT_CREW_MEMBERS_BY_CREW__ID =
    "SELECT * FROM crew_members  " +
            "LEFT JOIN crews_crew_members " +
            "ON crew_members.id = crews_crew_members.crews_members_id " +
            "WHERE crews_crew_members.crews_id=?;";

    private static final String SELECT_CREW_MEMBERS_BY_CREW__NAME =
               "SELECT *" +
               "FROM crew_members " +
               "LEFT JOIN (crews_crew_members JOIN crew USING (crew_name))" +
               "USING (crew_members_id) WHERE crew_name=?;";

    private final DataSource dataSource;

    public CrewDaoImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addMemberToCrew(CrewMember crewMember, Crew crew) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CREWS_CREW_MEMBERS)) {
            preparedStatement.setInt(1, crew.getId());
            preparedStatement.setInt(2, crewMember.getId());
        } catch (SQLException e) {
            throw new DaoOperationException("Couldn't save  CrewMember " + crewMember, e);
        }
    }

    public List<CrewMember> findCrewMembersById(int idCrew) {
        List<CrewMember> list;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CREW_MEMBERS_BY_CREW__ID)) {
            list = new ArrayList<>();
            preparedStatement.setInt(1, idCrew);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CrewMember crewMember = parseRow(resultSet);
                list.add(crewMember);
            }
            return list;
        } catch (SQLException e) {
            throw new DaoOperationException("Can not perform select CrewMembers by id query", e);
        }

    }

    public List<CrewMember> findCrewMembersByName(String nameCrew) {
        List<CrewMember> list;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CREW_MEMBERS_BY_CREW__NAME)) {
            list = new ArrayList<>();
            preparedStatement.setString(1, nameCrew);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CrewMember crewMember = parseRow(resultSet);
                list.add(crewMember);
            }
            return list;
        } catch (SQLException e) {
            throw new DaoOperationException("Can not perform select CrewMembers by id query", e);
        }

    }

    private CrewMember parseRow(ResultSet resultSet) {
        try {
            return createFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoOperationException("Couldn't parse row to create CrewMembers instance", e);
        }
    }

    private CrewMember createFromResultSet(ResultSet resultSet) throws SQLException {
        return CrewMember.builder()
                .withId(resultSet.getInt("id"))
                .withFirsName(resultSet.getString("first_name"))
                .withLastName(resultSet.getString("last_name"))
                .withBirthday(resultSet.getDate("birthday").toLocalDate())
                .withPosition(Position.valueOf(resultSet.getString("position")))
                .withCitizenship(Citizenship.valueOf(resultSet.getString("citizenship")))
                .build();
    }
}
