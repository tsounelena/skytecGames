package com.tsoun.repository;

import com.tsoun.model.Clan;

public interface ClanRepository {

    Clan getClan(long id);

    boolean updateClan(Clan clan);

}
