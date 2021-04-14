package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonResult;

import java.util.List;

public class GiftCenterModel extends JsonResult<GiftCenterModel.ReceiveGiftEntity> {
    public static class ReceiveGiftEntity {

        public int consumeCount;
        public int getCount;
        public List<UserOrderEntity> userOrderEntities;

        public List<UserOrderEntity> getUserOrderEntities() {
            return userOrderEntities;
        }

        public int getConsumeCount() {
            return consumeCount;
        }

        public int getGetCount() {
            return getCount;
        }

    }
}
