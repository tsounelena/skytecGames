package com.tsoun.repository;

import com.tsoun.connection.DataSource;
import com.tsoun.model.Clan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClanRepositoryImpl implements ClanRepository {

    private static Logger logger = LoggerFactory.getLogger(ClanRepositoryImpl.class);

    @Nullable
    public Clan getClan(long id) {
        Clan clan = null;

        String sqlQuery = "SELECT * FROM clan WHERE id = ?";
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int gold = resultSet.getInt("gold");
                clan = new Clan(id, name, gold);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(String.format("SQLException in method getClan: %s", e.getMessage()));
        }
        return clan;
    }

    public boolean updateClan(@Nonnull Clan clan) {
        String sqlQuery = "UPDATE clan SET name = ?, gold = ? WHERE id = ?";
        boolean rowUpdated = false;

        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlQuery)
        ) {
            statement.setString(1, clan.getName());
            statement.setInt(2, clan.getGold());
            statement.setLong(3, clan.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(String.format("SQLException in method updateClan: %s", e.getMessage()));
        }
        return rowUpdated;
    }
}
