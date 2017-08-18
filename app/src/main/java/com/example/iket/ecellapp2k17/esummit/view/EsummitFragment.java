package com.example.iket.ecellapp2k17.esummit.view;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iket.ecellapp2k17.R;
import com.example.iket.ecellapp2k17.esummit.model.RetrofitProviderSpeakers;
import com.example.iket.ecellapp2k17.esummit.view.SpeakerAdapter;
import com.example.iket.ecellapp2k17.esummit.model.MockSpeakers;
import com.example.iket.ecellapp2k17.esummit.model.data.SpeakerData;
import com.example.iket.ecellapp2k17.esummit.presenter.EsummitPresenter;
import com.example.iket.ecellapp2k17.esummit.presenter.EsummitPresenterImpl;
import com.example.iket.ecellapp2k17.helper.TypewriterView;
import com.example.iket.ecellapp2k17.home.Home;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EsummitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EsummitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EsummitFragment extends Fragment implements ViewInterface{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

//    @BindView(R.id.esummit_bg)
//    ImageView esummit_bg_img;

    @BindView(R.id.esummit_img)
    ImageView esummit_logo;

    @BindView(R.id.esummit_title)
    TextView esummit_title;
    @BindView(R.id.esummit_desc)
    TextView esummit_desc;

    RecyclerView recyclerView;
    private SpeakerAdapter recyclerAdapter;
    private LinearLayoutManager layoutManager;

    EsummitPresenter esummitPresenter;

    TypewriterView typewriterView;

    public EsummitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EsummitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EsummitFragment newInstance(String param1, String param2) {
        EsummitFragment fragment = new EsummitFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_esummit, container, false);
        ButterKnife.bind(this,view);
//        Glide.with(this).load(R.drawable.e_summit_bg).into(esummit_bg_img);
        Glide.with(this).load(R.drawable.esummit).into(esummit_logo);

        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_view_speakers);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new SpeakerAdapter(getContext());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setNestedScrollingEnabled(false);


//        esummitPresenter=new EsummitPresenterImpl(new RetrofitProviderSpeakers(),this);
        esummitPresenter=new EsummitPresenterImpl(new MockSpeakers(),this);
        esummitPresenter.requestData();
        esummit_title.setEnabled(false);
        esummit_desc.setEnabled(false);
        typewriterView = (TypewriterView) view.findViewById(R.id.type_writer_text);
        setAnim();


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

    void setAnim()
    {

        typewriterView.setText("");
        typewriterView.setEnabled(false);
        typewriterView
                .type("A trek to the Zenith of Glory").pause()
                .delete("A trek to the Zenith of Glory").pause(500)
                .type("NIT Raipur!").pause(500)
                .delete("NIT Raipur!")
                .type("9th-10th September,2017").pause(1000)
                .delete("9th-10th September,2017")
                .run(new Runnable() {
                    @Override
                    public void run() {
                        setAnim();

                    }
                });

    }

    @Override
    public void setData(List<SpeakerData> speakerDataList) {

        recyclerAdapter.setData(speakerDataList);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void ShowProgressBar(boolean show) {

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
