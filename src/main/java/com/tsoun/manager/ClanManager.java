package com.tsoun.manager;

import com.tsoun.model.Clan;
import com.tsoun.repository.ClanRepository;
import com.tsoun.repository.ClanRepositoryImpl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ClanManager {

    private static final ClanRepository clanRepository = new ClanRepositoryImpl();

    @Nullable
    public static Clan getClan(long clanId){
        return clanRepository.getClan(clanId);
    }

    public static boolean saveClan(@Nonnull Clan clan) {
        return clanRepository.updateClan(clan);
    }
}
