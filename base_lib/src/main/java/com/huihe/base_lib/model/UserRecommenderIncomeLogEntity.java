package com.huihe.base_lib.model;

import com.huihe.base_lib.model.personal.MasterAppointmentEntity;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import java.math.BigDecimal;

public class UserRecommenderIncomeLogEntity {
    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 1
     * recommender_id : 5
     * type : mechanism
     * is_settlement : true
     * create_time : 2020-09-08 22:07:56
     * update_time : 2020-09-12 14:44:55
     * mechanism_id : 4
     * master_id : 0
     * appointment_id : 0
     * mastersetprice_id : 28
     * cash : 19999
     * cash_describe :
     * recommender_type :
     * map : null
     */

    public Integer pageSize;
    public Integer currentPage;
    public Integer totalItem;
    public Integer startRow;
    public Integer totalPage;
    public String id;
    public String recommender_id;
    public String type;
    public Boolean is_settlement;
    public String create_time;
    public String update_time;
    public String mechanism_id;
    public String master_id;
    public String appointment_id;
    public String mastersetprice_id;
    public BigDecimal cash;
    public String cash_describe;
    public String recommender_type;
    public String user_id;
    public String invitation_id;
    public String recharge_record_id;
    public String role_id;
    public Map map;
    public static class Map{
        public MasterMechanismModel.MasterMechanismEntity mechanismEntity;
        public MasterSetPriceEntity masterSetPriceEntity;
        public MasterinfoBean masterInfoEntity;
        public MasterAppointmentEntity masterAppointmentEntity;
    }
}
