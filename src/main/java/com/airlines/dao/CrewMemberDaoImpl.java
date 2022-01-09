package com.airlines.dao;

import com.airlines.entity.Citizenship;
import com.airlines.entity.CrewMember;
import com.airlines.entity.Position;
import com.airlines.exception.DaoOperationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrewMemberDaoImpl implements CrewMemberDao {

    private final static String INSERT_CREWMEMBER_SQL = "INSERT INTO crew_members(first_name, last_name, position, birthday, citizenship) VALUES (?, ?, ?, ?, ?);";
    private final static String SELECT_CREWMEMBER_BY_ID_SQL = "SELECT * FROM crew_members WHERE id = ?;";
    private final static String UPDATE_CREWMEMBER_SQL = "UPDATE crew_members SET first_name =?, last_name = ?, position = ?, birthday = ?, citizenship = ? WHERE id = ?;";
    private DataSource dataSource;

    public CrewMemberDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(CrewMember crewMember) {
        try (Connection connection = dataSource.getConnection()) {
            saveCrewMember(crewMember, connection);
        } catch (SQLException e) {
            throw new DaoOperationException(e.getMessage(), e);
        }
    }

    private void saveCrewMember(CrewMember crewMember, Connection connection) throws SQLException {
        PreparedStatement insertStatement = prepareInsertStatement(connection, crewMember);
        executeUpdate(insertStatement, "Account was not created");
        int id = fetchGeneratedId(insertStatement);
        CrewMember.builder().withId(id).build();

    }

    private PreparedStatement prepareInsertStatement(Connection connection, CrewMember crewMember) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_CREWMEMBER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            return fillStatementWithAccountData(insertStatement, crewMember);
        } catch (SQLException e) {
            throw new DaoOperationException("Cannot prepare statement to insert account", e);
        }
    }

    private PreparedStatement fillStatementWithAccountData(PreparedStatement insertStatement, CrewMember crewMember)
            throws SQLException {
        insertStatement.setString(1, crewMember.getFirsName());
        insertStatement.setString(2, crewMember.getLastName());
        insertStatement.setString(3, String.valueOf(crewMember.getPosition()));
        insertStatement.setDate(4, Date.valueOf(crewMember.getBirthday()));
        insertStatement.setString(5, String.valueOf(crewMember.getCitizenship()));
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
            throw new DaoOperationException("Can not obtain an account ID");
        }
    }

    public CrewMember findOne(int id) {
        try (Connection connection = dataSource.getConnection()) {
            return findCrewMemberById(id, connection);
        } catch (SQLException e) {
            throw new DaoOperationException(String.format("Cannot find Account by id = %d", id), e);
        }
    }

    private CrewMember findCrewMemberById(int id, Connection connection) throws SQLException {
        PreparedStatement selectByIdStatement = prepareSelectByIdStatement(id, connection);
        ResultSet resultSet = selectByIdStatement.executeQuery();
        resultSet.next();
        return parseRow(resultSet);
    }

    private PreparedStatement prepareSelectByIdStatement(int id, Connection connection) {
        try {
            PreparedStatement selectByIdStatement = connection.prepareStatement(SELECT_CREWMEMBER_BY_ID_SQL);
            selectByIdStatement.setLong(1, id);
            return selectByIdStatement;
        } catch (SQLException e) {
            throw new DaoOperationException("Cannot prepare statement to select account by id", e);
        }
    }

    private CrewMember parseRow(ResultSet resultSet) throws SQLException {
        CrewMember crewMember = CrewMember.builder()
                .withId(resultSet.getInt("id"))
                .withFirsName(resultSet.getString("first_name"))
                .withLastName(resultSet.getString("last_name"))
                .withBirthday(resultSet.getDate("birthday").toLocalDate())
                .withPosition(Position.BOARD_CONDUCTOR)
                .withPosition(Position.FIRST_PILOT)
                .withPosition(Position.STEWARDESS)
                .withPosition(Position.FIRST_PILOT)
                .withCitizenship(Citizenship.AUSTRIA)
                .withCitizenship(Citizenship.AUSTRALIA)
                .withCitizenship(Citizenship.GERMANY)
                .withCitizenship(Citizenship.NEW_ZEALAND)
                .withCitizenship(Citizenship.UK)
                .withCitizenship(Citizenship.USA)
                .build();

        return crewMember;
    }

    @Override
    public void update(CrewMember crewMember) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement updateStatement = prepareUpdateStatement(crewMember, connection);
            executeUpdate(updateStatement, "Account was not updated");
        } catch (SQLException e) {
            throw new DaoOperationException(String.format("Cannot update Account with id = %d", crewMember.getId()), e);
        }
    }

    private PreparedStatement prepareUpdateStatement(CrewMember crewMember, Connection connection) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement(UPDATE_CREWMEMBER_SQL);
            fillStatementWithAccountData(updateStatement, crewMember);
            updateStatement.setLong(7, crewMember.getId());
            return updateStatement;
        } catch (SQLException e) {
            throw new DaoOperationException(String.format("Cannot prepare update statement for account id = %d", crewMember.getId()), e);
        }
    }
}
