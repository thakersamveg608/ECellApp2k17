package com.bquiz.raipur.ecellapp2k17.events.view;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.bquiz.raipur.ecellapp2k17.R;
import com.bquiz.raipur.ecellapp2k17.events.model.data.EventsData;
import com.bquiz.raipur.ecellapp2k17.events.presenter.EventsPresenter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventDetailsFragment extends android.support.v4.app.DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EventsData eventsData;

    private AVLoadingIndicatorView progressBar;

    private ImageView event_details_bg,event_details_location_img,down_arrow_image;
    private TextView event_details_desc,event_details_loc,event_details_name,event_details_date,event_details_time;
    private EventsPresenter eventsPresenter;
    private List<EventsData> data = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public EventDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventDetailsFragment newInstance(String param1, String param2) {
        EventDetailsFragment fragment = new EventDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1 );
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
            d.getWindow().setWindowAnimations(R.style.Dialog_anim);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog_theme);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view =  inflater.inflate(R.layout.fragment_event_details, container, false);
        event_details_bg = (ImageView) view.findViewById(R.id.eventImage);
        down_arrow_image = (ImageView) view.findViewById(R.id.down_arrow_img);
        event_details_location_img = (ImageView) view.findViewById(R.id.locImage);
        event_details_desc = (TextView) view.findViewById(R.id.eventBody);
        event_details_loc = (TextView) view.findViewById(R.id.eventLocation);
        event_details_name = (TextView) view.findViewById(R.id.eventTitle);
        event_details_date = (TextView) view.findViewById(R.id.eventDate);
        event_details_time = (TextView) view.findViewById(R.id.eventTime);
        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.progressBar_event_details);

        YoYo.with(Techniques.SlideInDown)
                .duration(1400)
                .repeat(0)
                .playOn(view.findViewById(R.id.eventBody));
        YoYo.with(Techniques.SlideInDown)
                .duration(1200)
                .repeat(0)
                .playOn(view.findViewById(R.id.eventDate));
        YoYo.with(Techniques.SlideInDown)
                .duration(1200)
                .repeat(0)
                .playOn(view.findViewById(R.id.eventLocation));
        YoYo.with(Techniques.SlideInDown)
                .duration(1200)
                .repeat(0)
                .playOn(view.findViewById(R.id.eventTime));
        YoYo.with(Techniques.SlideInDown)
                .duration(1200)
                .repeat(0)
                .playOn(view.findViewById(R.id.locImage));
        YoYo.with(Techniques.SlideInDown)
                .duration(1000)
                .repeat(0)
                .playOn(view.findViewById(R.id.eventTitle));

        event_details_name.setText(eventsData.getEventName());
        event_details_desc.setText(Html.fromHtml(Html.fromHtml(eventsData.getDescription()).toString()));
        event_details_loc.setText(eventsData.getVenue());
        event_details_date.setText(eventsData.getDate());
        event_details_time.setText(eventsData.getTime());
        Glide.with(getContext()).load(eventsData.getMeta()).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(event_details_bg);

        down_arrow_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventDetailsFragment.this.dismiss();
            }
        });

        //eventsPresenter.requestEvents();

        Glide.with(getContext()).load(R.drawable.ic_location_grey).into(event_details_location_img);
//        Glide.with(getContext()).load(eventsData.getImage()).into(event_details_bg);

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

    void setData(EventsData data)
    {
        eventsData=data;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
