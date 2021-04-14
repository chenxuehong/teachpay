package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.model.ClassStudentBean;
import com.huihe.base_lib.model.ClassStudentModel;
import com.huihe.base_lib.model.personal.InsertInfoResultModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class StudentListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryStudentInfoList(
                String type,
                String mechanism_id,
                String studycard_id,
                Integer currentPage,
                Integer pageSize,
                DisposableObserver<ClassStudentModel> subscriber);
    }

    public interface View extends IStateView {
        void onStudentInfoList(List<ClassStudentBean> classStudentBeans);
    }

    public interface Presenter {
        void queryStudentInfoList(
                String type,
                String mechanism_id,
                String studycard_id,
                Integer currentPage,
                Integer pageSize);
    }
}
