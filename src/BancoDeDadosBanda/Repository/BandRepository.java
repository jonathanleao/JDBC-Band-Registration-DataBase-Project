package BancoDeDadosBanda.Repository;

import BancoDeDadosBanda.Domain.Band;
import BancoDeDadosBanda.Domain.ConnectionFactory;
import BancoDeDadosBanda.Factorys.BandPrepareStatementFactory;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BandRepository {
    public static void createBand(Band band) {
        String sql = "INSERT INTO `cadastro_banda`.`band` (`fk_leader`, `bandName`, `numMembers`) VALUES (?, ?, ?);";
        log.info("Create band in the database '{}'", band.getBandName());
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = BandPrepareStatementFactory.createPrepareStatementForCreateBand(connection, sql, band)) {

            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Create band database rows affected{}", rowsAffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteBand(int id) {
        String sql = "DELETE FROM `cadastro_banda`.`band` WHERE (`id` = ?);";
        log.info("Deleting band from database whre id = '{}'", id);
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = BandPrepareStatementFactory.createPrepareStatementForDeleteBandById(connection, sql, id)) {

            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Delete band database rows affected{}", rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateBand(Band band) {
        String sql = "UPDATE `cadastro_banda`.`band` SET `bandName` = ?, `numMembers` = ? WHERE (`id` = ?);";
        log.info("Updating band in database '{}'", band.getBandName());
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = BandPrepareStatementFactory.createPrepareStatementForUpdateBand(connection, sql, band)) {

            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Update band database rows affected{}", rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveManyBands(List<Band> bands) {
        String sql = "INSERT INTO `cadastro_banda`.`band` (`fk_leader`, `bandName`, `numMembers`) VALUES (?, ?, ?);";
        log.info("Insert bands into database");
        try (Connection connection = ConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);
            BandPrepareStatementFactory.saveManyBandsTransaction(connection, sql, bands);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Band> findByName(String bandName) {
        List<Band> bands = new ArrayList<>();
        String sql = "select * from cadastro_banda.band where bandName like ?";
        log.info("bands found by name'{}'", bandName);
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = BandPrepareStatementFactory.createPrepareStatementFindBandByName(connection, sql, bandName)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("bandName");
                int numMembers = rs.getInt("numMembers");
                int leaderID = rs.getInt("fk_leader");
                Band band = Band.BandBuilder.builder()
                        .id(id)
                        .bandName(name)
                        .numMembers(numMembers)
                        .fk_leader(leaderID)
                        .build();

                bands.add(band);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bands;
    }


    public static void showBandDataMeta() {
        String sql = "select * from cadastro_banda.band;";
        log.info("Show bands MetaDat");
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
