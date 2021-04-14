package com.huihe.base_lib.utils.manager;

public class LoadPagerManager {

    private int mFirstPageIndex = 1;
    private int mPageSize = 10;
    private int mCurrentPage = 1;

    public void currentPageUp() {
        this.mCurrentPage ++;
    }

    public void scrollToFirst(){

        this.mCurrentPage = getFirstPage();
    }
    public int getCurrentPage() {
        return mCurrentPage;
    }

    public int getFirstPage(){
        return mFirstPageIndex;
    }
    public int getPageSize(){
        return mPageSize;
    }
}
