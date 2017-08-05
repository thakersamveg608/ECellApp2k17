package com.example.iket.ecellapp2k17.about_us.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.iket.ecellapp2k17.R;
import com.example.iket.ecellapp2k17.about_us.model.MockAboutUs;
import com.example.iket.ecellapp2k17.about_us.model.data.AboutUsData;
import com.example.iket.ecellapp2k17.about_us.presenter.AboutUsPresenter;
import com.example.iket.ecellapp2k17.about_us.presenter.AboutUsPresenterImpl;
import com.example.iket.ecellapp2k17.helper.VerticalViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AboutUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AboutUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutUsFragment extends Fragment implements AboutUsInterface{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.vision_img)
    ImageView vision_chip;

    @BindView(R.id.contact_us_img)
    ImageView contact_us_chip;

    @BindView(R.id.team_img)
    ImageView team_chip;

    @BindView(R.id.past_work_img)
    ImageView past_work_chip;

    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    AboutUsPresenter aboutUsPresenter;
    private OnFragmentInteractionListener mListener;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutUsFragment newInstance(String param1, String param2) {
        AboutUsFragment fragment = new AboutUsFragment();
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
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_view_team);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter=new RecyclerAdapter(getContext());
        recyclerView.setAdapter(recyclerAdapter);

        ButterKnife.bind(this,view);
        Glide.with(getContext()).load(R.drawable.vision).bitmapTransform(new CropCircleTransformation(getContext())).into(vision_chip);
        Glide.with(getContext()).load(R.drawable.contact_us).bitmapTransform(new CropCircleTransformation(getContext())).into(contact_us_chip);
        Glide.with(getContext()).load(R.drawable.past_work).bitmapTransform(new CropCircleTransformation(getContext())).into(past_work_chip);
        Glide.with(getContext()).load(R.drawable.team).bitmapTransform(new CropCircleTransformation(getContext())).into(team_chip);
        // blogsPresenter=new BlogsPresenterImpl(new RetrofitBlogsProvider(),this);

        aboutUsPresenter =new AboutUsPresenterImpl(new MockAboutUs(),this);
        aboutUsPresenter.requestData();
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
    public void setData(List<AboutUsData> aboutUsDataList) {
        recyclerAdapter.setData(aboutUsDataList);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void ShowProgressBar(boolean show) {


    }

    public void go_to_vision(){

    }
    public void go_to_contact_us(){

    }
    public void go_to_past_work(){

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
