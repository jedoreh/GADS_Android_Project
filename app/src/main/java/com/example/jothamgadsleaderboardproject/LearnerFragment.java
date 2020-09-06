package com.example.jothamgadsleaderboardproject;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearnerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnerFragment extends Fragment {

    View view;
    private ProgressBar mLoadingProgress;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LearnerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearnerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnerFragment newInstance(String param1, String param2) {
        LearnerFragment fragment = new LearnerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rvLearners;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_learner, container, false);

        mLoadingProgress = (ProgressBar) view.findViewById(R.id.pb_learner_loading);

        rvLearners = (RecyclerView) view.findViewById(R.id.rvLearners);

        LinearLayoutManager learnerLayoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);

        rvLearners.setLayoutManager(learnerLayoutManager);

        try {
            URL learnerUrl = ApiUtil.buildUrl("hours");
            new LearnersQueryTask().execute(learnerUrl);
        }
        catch (Exception e) {
            Log.d("Error", e.getMessage());
        }

        ArrayList<Learner> learners = ApiUtil.getLearners("");
        //tvLearner.setText(result);
        //String resultString = "";


        LearnerAdapter adapter = new LearnerAdapter(learners);
        rvLearners.setAdapter(adapter);

        return view;
    }

    public class LearnersQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(searchURL);
            }
            catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView tvLearner = (TextView) view.findViewById(R.id.tvLearner);
            TextView tvError = (TextView) view.findViewById(R.id.tv_learner_error);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            if (result == null) {
                rvLearners.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            } else {
                rvLearners.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);
            }

            ArrayList<Learner> learners = ApiUtil.getLearners(result);
            //tvLearner.setText(result);
            String resultString = "";

            //tvLearner.setText(resultString);
            //tvLearner.setText(result);

            LearnerAdapter adapter = new LearnerAdapter(learners);
            rvLearners.setAdapter(adapter);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
    }
}