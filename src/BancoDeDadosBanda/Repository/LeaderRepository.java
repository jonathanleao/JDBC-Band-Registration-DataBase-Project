package BancoDeDadosBanda.Repository;

import BancoDeDadosBanda.Domain.ConnectionFactory;
import BancoDeDadosBanda.Domain.Leader;
import BancoDeDadosBanda.Factorys.LeaderPrepareStatementfactory;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class LeaderRepository {
    public static void createLeader(Leader leader) {
        String sql = "INSERT INTO `cadastro_banda`.`leader` (`leaderName`, `surname`) VALUES (?, ?);";
        log.info("Insert leader into Database '{}'", leader.getLeaderName());
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = LeaderPrepareStatementfactory.createPrepareStatementForCreate(connection, sql, leader)) {
            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Create producer database rows affected{}", rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteLeader(int id) {
        String sql = "DELETE FROM `cadastro_banda`.`leader` WHERE (`id` = ?);";
        log.info("Deleting leader where id = '{}'", id);
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = LeaderPrepareStatementfactory.createPrepareStatementForDeleteLeaderById(connection, sql, id)) {
            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Delete leader database rows affected{}", rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLeader(Leader leader) {
        String sql = "UPDATE `cadastro_banda`.`leader` SET `leaderName` = ?, `surname` = ? WHERE (`id` = ?);";
        log.info("Updating Leader ind database '{}'", leader.getLeaderName());
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = LeaderPrepareStatementfactory.createPrepareStatementForUpdateLeader(connection, sql, leader)) {
            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Update leaders database rows affected{}", rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveManyLeaderTransactions(List<Leader> leaders) {
        String sql = "INSERT INTO `cadastro_banda`.`leader` (`leaderName`, `surname`) VALUES (?, ?);";
        log.info("saving Leaders in database ");
        try (Connection connection = ConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);
            LeaderPrepareStatementfactory.saveManyLeadersTransactions(connection, sql, leaders);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Leader> findByName(String leaderName) {
        List<Leader> leaders = new ArrayList<>();
        String sql = "select * from cadastro_banda.leader where leaderName like ?;";
        log.info("Leaders found by name'{}'", leaderName);
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = LeaderPrepareStatementfactory.createPrepareStatementForFindLeaderByName(connection, sql, leaderName)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("leaderName");
                String surname = rs.getString("surname");

                Leader leader = Leader.LeaderBuilder.builder()
                        .id(id)
                        .leaderName(name)
                        .surname(surname)
                        .build();

                leaders.add(leader);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return leaders;
    }

    public static void showLeadersDataMeta() {
        String sql = "select * from cadastro_banda.leader";
        log.info("show Leaders MetaData");
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("table name " + metaData.getTableName(i));
                System.out.println("column name " + metaData.getColumnName(i));
                System.out.println("column type " + metaData.getColumnTypeName(i));
                System.out.println("column size " + metaData.getColumnDisplaySize(i));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
