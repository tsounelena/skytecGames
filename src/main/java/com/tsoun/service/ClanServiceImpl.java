package com.tsoun.service;

import com.google.common.util.concurrent.Striped;
import com.tsoun.exception.ClanNotFoundException;
import com.tsoun.manager.ClanManager;
import com.tsoun.manager.TrackerManager;
import com.tsoun.model.Clan;

import java.util.concurrent.locks.Lock;

public class ClanServiceImpl implements ClanService{
    @SuppressWarnings("UnstableApiUsage")
    private final Striped<Lock> lockStripes = Striped.lock(10);

    @SuppressWarnings("UnstableApiUsage")
    public void incrementGold(long userId, long clanId, int gold) {
        Lock lock = lockStripes.get(clanId);
        try {
            lock.lock();
            Clan clan = ClanManager.getClan(clanId);
            if (clan == null) {
                throw new ClanNotFoundException(String.format("Clan with id: %d not found", clanId));
            }
            clan.incrementGold(gold);
            ClanManager.saveClan(clan);
        } finally {
            lock.unlock();
        }
    }
}
