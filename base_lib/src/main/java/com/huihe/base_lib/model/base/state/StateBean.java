package com.huihe.base_lib.model.base.state;

public class StateBean implements IStateBean {

    private static int mCurState = STATE_LOADING;
    public static void setState(int curState){
        mCurState = curState;
    }
}
