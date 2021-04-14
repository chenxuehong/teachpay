package com.huihe.base_lib.model;

import java.util.List;

public class MyMemberLevelBean {
    public String id;
    public String memberName;
    public int member_level;
    public boolean isPayed;
    public String endTime;
    public List<MemberTimeCardBean> memberTimeCardBeans;
    public static class MemberTimeCardBean {

        public String id;
        public String cardName;
        public float discount_amout;
        public String amount;
        public Integer duration;
        public Integer member_level;
    }

    public List<String> memberEquityList;
}
