package com.cmpe451.group9.infograppo.common.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cmpe451.group9.infograppo.R;
import com.cmpe451.group9.infograppo.activities.NavigationDrawerActivity;
import com.cmpe451.group9.infograppo.network.models.User;
import com.cmpe451.group9.infograppo.network.services.MySingleton;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private FragmentTabHost mTabHost;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
       /* setContentView(R.layout.activity_main);

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Tab1"),
                Tab1Fragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Tab2"),
                Tab2Fragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Tab3"),
                Tab3Fragment.class, null);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

/*        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_profile);

        TabHost host = (TabHost) getActivity().findViewById(R.id.tabHost);
//        host.setup();

        //Tab 1
        TabHost.TabSpec spec = mTabHost.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Contributions");
        mTabHost.addTab(spec);

        //Tab 2
        spec = mTabHost.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Favorites");
        mTabHost.addTab(spec);

        //Tab 3
        spec = mTabHost.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Followings");
        mTabHost.addTab(spec);

        //Tab 3
        spec = mTabHost.newTabSpec("Tab Four");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Followers");
        mTabHost.addTab(spec);*/

        final TextView userName = (TextView) getActivity().findViewById(R.id.text_profile_username);
        final TextView userInfo = (TextView) getActivity().findViewById(R.id.text_profile_userinfo);
        final TextView userFollow = (TextView) getActivity().findViewById(R.id.text_profile_userfollow);

        String baseURL = NavigationDrawerActivity.baseURL + "users/2";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, baseURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        User user = new Gson().fromJson(String.valueOf(response), User.class);
                        String utmp = user.getName() + " " + user.getSurname()
                                + " (" + user.getUsername() + ")";
                        userName.setText(utmp);
                        utmp= user.getAge() + " , " + user.getEmail();
                        userInfo.setText(utmp);
                        int followers = 123, followings = 36;
                        utmp= followers+" followers, "+ followings +" followings";
                        userFollow.setText(utmp);


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });

        // Access the RequestQueue through your singleton class.

        MySingleton.getInstance(getContext()).addToRequestQueue(jsObjRequest);

        return inflater.inflate(R.layout.fragment_profile, container, false);
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}
