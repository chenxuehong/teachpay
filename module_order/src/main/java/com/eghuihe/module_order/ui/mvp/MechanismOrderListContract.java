package com.eghuihe.module_order.ui.mvp;

import com.huihe.base_lib.model.LongOrderPayInfoEntity;
import com.huihe.base_lib.model.LongOrderPayInfoModel;
import com.huihe.base_lib.model.MechanismOrderEntity;
import com.huihe.base_lib.model.MechanismOrderModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.model.personal.WxPayModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MechanismOrderListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryMechanismOrderList(
                String type,
                String rcharge_type,
                String user_id,
                String mechanism_id,
                Boolean finished,
                Boolean is_one_time_payment,
                Integer pageSize,
                Integer currentPage,
                String study_type,
                String course_num,
                String status,
                DisposableObserver<MechanismOrderModel> subscriber);

        DisposableObserver getLongOrder(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String master_id,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String user_study_card_id,
                String paying_for_identity,
                String pay_type,
                String mechanism_id,
                DisposableObserver<InsertInfoResultModel> subscriber);

        DisposableObserver getWxLongOrder(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String master_id,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String user_study_card_id,
                String paying_for_identity,
                String pay_type,
                String mechanism_id,
                DisposableObserver<WxPayModel> subscriber);

        DisposableObserver queryLongOrderPayInfo(
                String id,
                DisposableObserver<LongOrderPayInfoModel> subscriber);
    }

    public interface View extends IStateView {
        void MechanismOrderList(List<MechanismOrderEntity> mechanismOrderEntities);

        void onGetAliOrder(String orderInfo);
        void onGetWxOrder(WxPayModel.WxPayEntity wxPayEntity);
        void onGetLongOrderInfoDetail(LongOrderPayInfoEntity longOrderPayInfoEntity);
    }

    public interface Presenter {
        void queryMechanismOrderList(
                String type,
                String rcharge_type,
                String user_id,
                String mechanism_id,
                Boolean finished,
                Boolean is_one_time_payment,
                Integer pageSize,
                Integer currentPage,
                String study_type,
                String course_num,
                String status);

        void getLongOrder(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String master_id,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String user_study_card_id,
                String paying_for_identity,
                String pay_type,
                String mechanism_id);

        void getWxLongOrder(
                String user_id,
                String rcharge_type,
                String source,
                String course_num,
                String master_id,
                String rcharge_abstract,
                String studycard_id,
                String study_type,
                Boolean is_one_time_payment,
                String user_study_card_id,
                String paying_for_identity,
                String pay_type,
                String mechanism_id);

        void queryLongOrderPayInfo(
                String id);

    }
}
