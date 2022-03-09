package com.airlines.dao;

import com.airlines.entity.Airplane;
import com.airlines.entity.CrewMember;
import com.airlines.entity.Model;
import com.airlines.exception.DaoOperationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDaoImpl implements AirplaneDao {

    private static final String INSERT_AIRPLANE_SQL = "INSERT INTO airplanes(code_name, model, manufacture, capacity, flight, crew_id) VALUES (?, ?, ?, ?, ?, ?);";
    private final static String SELECT_AIRPLANE_BY_ID_SQL = "SELECT * FROM airplanes WHERE airplanes.code_name = ?;";
    private final static String SELECT_ALL_AIRPLANE_SQL = "SELECT * FROM airplanes;";
    private static final String DELETE_AIRPLANE_SQL = "delete from airplanes where id = ?;";
    private static final String SELECT_CREW_MEMBERS_BY_CREW_NAME = "SELECT * FROM airplanes LEFT JOIN crews " +
            "ON airplanes.crews_id = crews.id WHERE crews.name = ?";
    private static final String UPDATE_AIRPLANE_CREWS_SQL = "UPDATE airplanes SET crew_id = ? WHERE id = ?;";

    private final DataSource dataSource;

    public AirplaneDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Airplane airplane) {
        try (Connection connection = dataSource.getConnection()) {
            saveAirplane(airplane, connection);
        } catch (SQLException e) {
            throw new DaoOperationException(e.getMessage(), e);
        }
    }

    private void saveAirplane(Airplane airplane, Connection connection) throws SQLException {
        PreparedStatement insertStatement = prepareInsertStatement(connection, airplane);
        executeUpdate(insertStatement, "Airplane was not created");
        int id = fetchGeneratedId(insertStatement);
        Airplane.builder().withId(id).build();
    }

    private PreparedStatement prepareInsertStatement(Connection connection, Airplane airplane) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_AIRPLANE_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            return fillStatementWithAccountData(insertStatement, airplane);
        } catch (SQLException e) {
            throw new DaoOperationException("Cannot prepare statement to insert Airplane", e);
        }
    }

    private PreparedStatement fillStatementWithAccountData(PreparedStatement insertStatement, Airplane airplane)
            throws SQLException {
        insertStatement.setString(1, airplane.getCodeName());
        insertStatement.setString(2, airplane.getCodeName());
        insertStatement.setString(3, String.valueOf(airplane.getModel()));
        insertStatement.setDate(4, Date.valueOf(airplane.getManufacture()));
        insertStatement.setInt(5, airplane.getCapacity());
        insertStatement.setInt(6, airplane.getFlightRange());
        insertStatement.setInt(7, airplane.getCrews_id());
        return insertStatement;
    }

    private void executeUpdate(PreparedStatement insertStatement, String errorMessage) throws SQLException {
        int rowsAffected = insertStatement.executeUpdate();
        if (rowsAffected == 0) {
            throw new DaoOperationException(errorMessage);
        }
    }

    private int fetchGeneratedId(PreparedStatement insertStatement) throws SQLException {
        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new DaoOperationException("Can not obtain an Airplane id");
        }
    }

    @Override
    public Airplane findOne(String codeName) {
        try (Connection connection = dataSource.getConnection()) {
            return findAirplaneByCodeName(codeName, connection);
        } catch (SQLException e) {
            throw new DaoOperationException(String.format("Cannot find Airplane by codeName = %s", codeName), e);
        }
    }

    private Airplane findAirplaneByCodeName(String codeName, Connection connection) throws SQLException {
        PreparedStatement selectByIdStatement = prepareSelectByCodeNameStatement(codeName, connection);
        ResultSet resultSet = selectByIdStatement.executeQuery();
        resultSet.next();
        return parseRow(resultSet);
    }

    private PreparedStatement prepareSelectByCodeNameStatement(String codeName, Connection connection) {
        try {
            PreparedStatement selectByIdStatement = connection.prepareStatement(SELECT_AIRPLANE_BY_ID_SQL);
            selectByIdStatement.setString(1, codeName);
            return selectByIdStatement;
        } catch (SQLException e) {
            throw new DaoOperationException("Cannot prepare statement to select airplane by codeName", e);
        }
    }

    private Airplane parseRow(ResultSet resultSet) throws SQLException {
        return Airplane.builder()
                .withId(resultSet.getInt("id"))
                .withCodeName(resultSet.getString("code_name"))
                .withModel(Model.valueOf(resultSet.getString("model")))
                .withManufacture(resultSet.getDate("manufactureDate").toLocalDate())
                .withCapacity(resultSet.getInt("capacity"))
                .withFlightRange(resultSet.getInt("flightRange"))
                .withCrewId(resultSet.getInt("crewId"))
                .build();
    }

    @Override
    public List<Airplane> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_AIRPLANE_SQL);
            return collectToList(resultSet);
        } catch (SQLException e) {
            throw new DaoOperationException("Can not perform find all operation", e);
        }
    }

    private List<Airplane> collectToList(ResultSet resultSet) throws SQLException {
        List<Airplane> accountList = new ArrayList<>();
        while (resultSet.next()) {
            Airplane airplane = parseRow(resultSet);
            accountList.add(airplane);
        }
        return accountList;
    }

    public boolean deleteAirplane(int id) {
        boolean rowDeleted;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_AIRPLANE_SQL);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(e.getMessage());
        }
    }

    @Override
    public List<Airplane> searchAirplanes(String crewName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CREW_MEMBERS_BY_CREW_NAME)) {
            List<Airplane> list = new ArrayList<>();
            preparedStatement.setString(1, crewName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Airplane airplane = parseRow(resultSet);
                list.add(airplane);
            }
            return list;
        } catch (SQLException e) {
            throw new DaoOperationException("Can not perform select Airplane by id query", e);
        }
    }

    public void updateAirplane(Airplane airplane, int crewId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement updateStatement = prepareUpdateStatement(airplane, crewId, connection);
            executeUpdate(updateStatement, "Airplane was not updated");
        } catch (SQLException e) {
            throw new DaoOperationException(String.format("Can not update airplane with id = %d", crewId), e);
        }
    }

    private PreparedStatement prepareUpdateStatement(Airplane airplane, int id, Connection connection) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement(UPDATE_AIRPLANE_CREWS_SQL);
            fillStatementWithAccountData(updateStatement, airplane);
            updateStatement.setInt(1, id);
            return updateStatement;
        } catch (SQLException e) {
            throw new DaoOperationException("Cannot prepare statement to update Airplane", e);
        }
    }
}
