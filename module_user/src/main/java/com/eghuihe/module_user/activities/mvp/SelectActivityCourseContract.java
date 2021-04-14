package com.eghuihe.module_user.activities.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.CommodityModel;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class SelectActivityCourseContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize,
                NetworkSubscriber<CommodityModel> subscriber);

        DisposableObserver insertBusinessSinglePaymentActivity(
                Boolean is_new_customers,
                String master_set_price_ids,
                String mechanism_id,
                String start_time,
                String end_time,
                String type,
                String tags,
                String price,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver insertBusinessSalesCourseActivity(
                Boolean is_new_customers,
                String master_set_price_ids,
                String mechanism_id,
                String start_time,
                String end_time,
                String type,
                String tags,
                String coupon_time,
                String discount_amount,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver insertBusinessGroupingActivity(
                Boolean is_new_customers,
                String master_set_price_ids,
                String mechanism_id,
                String start_time,
                String end_time,
                String type,
                String tags,
                String number_of_people,
                String each_time_percentage,
                String each_time_percentage_max,
                NetworkSubscriber<InsertInfoResultModel> subscriber);

        DisposableObserver insertBusinessExperienceSpecialsActivity(
                Boolean is_new_customers,
                String master_set_price_ids,
                String mechanism_id,
                String start_time,
                String end_time,
                String type,
                String tags,
                String coupon_time,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {
        void showCourseList(List<MasterSetPriceEntity> exclusiveCoursesEntities);

        void onInsertBusinessActivitySuccess();
    }

    public interface Presenter {
        void queryCourseList(
                String mechanism_id,
                String type,
                String status,
                String appointment_type,
                Integer currentPage,
                Integer pageSize);

        void insertBusinessSinglePaymentActivity(
                Boolean is_new_customers,
                String master_set_price_ids,
                String mechanism_id,
                String start_time,
                String end_time,
                String type,
                String tags,
                String price);

        void insertBusinessSalesCourseActivity(
                Boolean is_new_customers,
                String master_set_price_ids,
                String mechanism_id,
                String start_time,
                String end_time,
                String type,
                String tags,
                String coupon_time,
                String discount_amount);

        void insertBusinessGroupingActivity(
                Boolean is_new_customers,
                String master_set_price_ids,
                String mechanism_id,
                String start_time,
                String end_time,
                String type,
                String tags,
                String number_of_people,
                String each_time_percentage,
                String each_time_percentage_max);

        void insertBusinessExperienceSpecialsActivity(
                Boolean is_new_customers,
                String master_set_price_ids,
                String mechanism_id,
                String start_time,
                String end_time,
                String type,
                String tags,
                String coupon_time);
    }
}
