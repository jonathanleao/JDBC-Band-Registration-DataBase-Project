package BancoDeDadosBanda.Factorys;

import BancoDeDadosBanda.Domain.Band;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BandPrepareStatementFactory {
    public static PreparedStatement createPrepareStatementFindBandByName(Connection connection, String sql, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%"+name+"%");
        return preparedStatement;
    }

    public static PreparedStatement createPrepareStatementForCreateBand(Connection connection, String sql, Band band) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, band.getFk_leader());
        preparedStatement.setString(2, band.getBandName());
        preparedStatement.setInt(3, band.getNumMembers());

        return preparedStatement;
    }

    public static PreparedStatement createPrepareStatementForDeleteBandById(Connection connection, String sql, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    public static PreparedStatement createPrepareStatementForUpdateBand(Connection connection, String sql, Band band) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, band.getBandName());
        preparedStatement.setInt(2, band.getNumMembers());
        preparedStatement.setInt(3, band.getId());

        return preparedStatement;
    }

    public static void saveManyBandsTransaction(Connection connection, String sql, List<Band> bands) throws SQLException {
        for (Band band : bands) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, band.getFk_leader());
                preparedStatement.setString(2, band.getBandName());
                preparedStatement.setInt(3, band.getNumMembers());
                preparedStatement.execute();

            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        }
    }

}
