package com.tsoun.manager;

import com.tsoun.connection.DataSource;
import com.tsoun.repository.ClanRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrackerManager {
    private static final Logger logger = LoggerFactory.getLogger(ClanRepositoryImpl.class);

    public static void trackClanGold(long user_id, long clan_id, int gold) {

        String sqlQuery = "INSERT INTO gold_track (user_id, clan_id, gold_amount) VALUES (?, ?, ?)";

        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlQuery)
        ) {
            statement.setLong(1, user_id);
            statement.setLong(2, clan_id);
            statement.setInt(3, gold);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.error(String.format("SQLException in method updateClan: %s", e.getMessage()));
        }
    }
}
