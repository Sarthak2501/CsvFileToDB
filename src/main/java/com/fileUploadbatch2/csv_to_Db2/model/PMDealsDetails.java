package com.fileUploadbatch2.csv_to_Db2.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "PM_Deals_Details")
public class PMDealsDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
            @Parameter(name = "UUID_gen_strategy_class", value = "org.hibernate.id.UUID.CustomVersionOneStrategy") })
    private UUID id;

    @Column(name = "PM_Deal_Id")
    private UUID pMDealId;

    @Column(name = "Game_ID")
    private UUID gameId;

    @Column(name = "Area_ID")
    private UUID  areaId;

    @Column(name = "PMS_Purchased")
    private int pMSPurchased;

    public PMDealsDetails() {
    }

    public PMDealsDetails(UUID pMDealId, UUID gameId, UUID areaId, int pMSPurchased) {
        this.pMDealId = pMDealId;
        this.gameId = gameId;
        this.areaId = areaId;
        this.pMSPurchased = pMSPurchased;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getpMDealId() {
        return pMDealId;
    }

    public void setpMDealId(UUID pMDealId) {
        this.pMDealId = pMDealId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID  areaId) {
        this.areaId = areaId;
    }

    public int getpMSPurchased() {
        return pMSPurchased;
    }

    public void setpMSPurchased(int pMSPurchased) {
        this.pMSPurchased = pMSPurchased;
    }
}
