package com.example.jothamgadsleaderboardproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentCollectionAdapter extends FragmentStatePagerAdapter {
    public FragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentCollectionAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        LearnerBoardFragment learnerBoardFragment = new LearnerBoardFragment();
        Bundle bundle = new Bundle();
        position = position + 1;
        bundle.putString("message", "Clicked " + position );
        learnerBoardFragment.setArguments(bundle);
        return learnerBoardFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
