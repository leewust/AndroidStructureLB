package com.app.liliuhuan.mylibrary.base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: liliuhuan
 * date：2018/7/6
 * version:1.0.0
 * description: CommonFragmentPagerAdapter${DES}
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titleArray;
    private List<Fragment> fragments;

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments == null) return null;
        if (position < fragments.size())
            return fragments.get(position);
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0) {
            return fragments.size();
        }
        return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (titleArray != null && titleArray.length > 0 && position < titleArray.length) {
            return titleArray[position];
        }
        return null;
    }

    public void setTitles(List<String> titleList) {
        if (titleList == null && titleList.size() == 0) return;
        titleArray = titleList.toArray(new String[]{});
        notifyDataSetChanged();
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}
