package fr.free.nrw.commons.review;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by nes on 19.05.2018.
 */

public class ReviewPagerAdapter extends FragmentStatePagerAdapter {
    private int currentPosition;
    ReviewOutOfContextFragment reviewOutOfContextFragment;
    ReviewLicenceViolationFragment reviewLicenceViolationFragment;
    ReviewCategoryMissuseFragment reviewCategoryMissuseFragment;


    public ReviewPagerAdapter(FragmentManager fm) {
        super(fm);
        reviewOutOfContextFragment = new ReviewOutOfContextFragment();
        reviewLicenceViolationFragment = new ReviewLicenceViolationFragment();
        reviewCategoryMissuseFragment = new ReviewCategoryMissuseFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show image
                currentPosition = 0;
                reviewOutOfContextFragment.update(currentPosition, ReviewController.fileName);
                return reviewOutOfContextFragment;
            case 1: // Fragment # 1 - This will show image
                currentPosition = 1;
                reviewLicenceViolationFragment.update(currentPosition, ReviewController.fileName);
                return reviewLicenceViolationFragment;
            default:// Fragment # 2-9 - Will show list
                currentPosition = 2;
                reviewCategoryMissuseFragment.update(currentPosition, ReviewController.fileName);
                return reviewCategoryMissuseFragment;
        }
    }

}
