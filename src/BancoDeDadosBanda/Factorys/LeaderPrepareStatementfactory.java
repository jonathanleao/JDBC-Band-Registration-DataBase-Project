package BancoDeDadosBanda.Factorys;

import BancoDeDadosBanda.Domain.Leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LeaderPrepareStatementfactory {

    public static PreparedStatement createPrepareStatementForFindLeaderByName(Connection connection, String sql, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%"+name+"%");
        return preparedStatement;
    }

    public static PreparedStatement createPrepareStatementForCreate(Connection connection, String sql, Leader leader) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, leader.getLeaderName());
        preparedStatement.setString(2, leader.getSurname());
        return preparedStatement;
    }

    public static PreparedStatement createPrepareStatementForDeleteLeaderById(Connection connection, String sql, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    public static PreparedStatement createPrepareStatementForUpdateLeader(Connection connection, String sql, Leader leader) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, leader.getLeaderName());
        preparedStatement.setString(2, leader.getSurname());
        preparedStatement.setInt(3, leader.getId());

        return preparedStatement;
    }

    public static void saveManyLeadersTransactions(Connection connection, String sql, List<Leader> leaders) throws SQLException {
        for (Leader leader : leaders) {
            try ( PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                preparedStatement.setString(1, leader.getLeaderName());
                preparedStatement.setString(2, leader.getSurname());
                preparedStatement.execute();

            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
