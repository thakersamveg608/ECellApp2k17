package com.bquiz.raipur.ecellapp2k17.bmodel.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bquiz.raipur.ecellapp2k17.R;
import com.bquiz.raipur.ecellapp2k17.bmodel.model.RetrofitBModelProvider;
import com.bquiz.raipur.ecellapp2k17.bmodel.model.data.MentorData;
import com.bquiz.raipur.ecellapp2k17.bmodel.presenter.BModelPresenter;
import com.bquiz.raipur.ecellapp2k17.bmodel.presenter.BModelPresenterImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BModelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BModelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BModelFragment extends Fragment implements BModelView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    private MentorsAdapter recyclerAdapter;
    private CardView card_default_esummit;
    private LinearLayoutManager layoutManager;
    private ProgressBar progressBar;

    BModelPresenter bModelPresenter;

    private OnFragmentInteractionListener mListener;

    public BModelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BModelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BModelFragment newInstance(String param1, String param2) {
        BModelFragment fragment = new BModelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        View view = inflater.inflate(R.layout.fragment_bmodel,container,false);

        card_default_esummit = (CardView) view.findViewById(R.id.card_coming_soon_esummit);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_view_speakers);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new MentorsAdapter(getContext());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        bModelPresenter=new BModelPresenterImpl(this,new RetrofitBModelProvider());
        bModelPresenter.requestData();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setData(List<MentorData> mentorDataList) {
        recyclerAdapter.setData(mentorDataList);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressBar(boolean show) {

        if(show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showDefault(boolean show) {

        if(show){
            card_default_esummit.setVisibility(View.VISIBLE);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
