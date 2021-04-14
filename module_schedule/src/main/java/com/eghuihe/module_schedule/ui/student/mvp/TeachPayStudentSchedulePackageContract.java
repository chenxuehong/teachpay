package com.eghuihe.module_schedule.ui.student.mvp;

import com.huihe.base_lib.api.NetworkSubscriber;
import com.huihe.base_lib.model.StudentCoursePackageEntity;
import com.huihe.base_lib.model.StudentCoursePackageModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TeachPayStudentSchedulePackageContract {
    public interface Model extends IBaseModel {
        DisposableObserver queryStudentExclusiveCoursesList(
                String type,
                String user_id,
                Integer currentPage,
                Integer pageSize,
                DisposableObserver<StudentCoursePackageModel> subscriber);
        DisposableObserver insertUserGrouping(
                String user_id,
                String master_set_price_id,
                String study_card_id,
                NetworkSubscriber<InsertInfoResultModel> subscriber);
    }

    public interface View extends IStateView {

        void onStudentCoursePackageEntityList(List<StudentCoursePackageEntity> studentCoursePackageEntities);
        void onPingGroupSuccess(String content);
    }

    public interface Presenter {
        void queryStudentExclusiveCoursesList(
                String type,
                String user_id,
                Integer currentPage,
                Integer pageSize);
        void insertUserGrouping(
                String user_id,
                String master_set_price_id,
                String study_card_id);
    }
}
