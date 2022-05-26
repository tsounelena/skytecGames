package com.tsoun.controller;

import com.tsoun.manager.TrackerManager;
import com.tsoun.service.ClanService;
import com.tsoun.service.ClanServiceImpl;

public class ClanController {

    private static final ClanService clanService = new ClanServiceImpl();

    public void incrementGold(long clanId, int gold, long userId) {
        if (clanId < 0) {
            throw new UnsupportedOperationException("Clan id must be more or equals 0");
        }
        clanService.incrementGold(userId, clanId, gold);
        TrackerManager.trackClanGold(userId, clanId, gold);
    }
}
