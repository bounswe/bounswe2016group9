package com.cmpe451.group9.infograppo.common.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cmpe451.group9.infograppo.R;
import com.cmpe451.group9.infograppo.activities.GrappoActivity;
import com.cmpe451.group9.infograppo.activities.NavigationDrawerActivity;
import com.cmpe451.group9.infograppo.network.models.Topic;
import com.cmpe451.group9.infograppo.network.services.MySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GrappiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GrappiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GrappiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> topics;
    ArrayList<Integer> topicsIds;
    ArrayAdapter<String> adapter;
    String baseURL = NavigationDrawerActivity.baseURL;

    private OnFragmentInteractionListener mListener;

    public GrappiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GrappiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GrappiFragment newInstance(String param1, String param2) {
        GrappiFragment fragment = new GrappiFragment();
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
        View view = inflater.inflate(R.layout.fragment_grappi, container, false);


        final ListView listView = (ListView) view.findViewById(R.id.list_grappi);
        final String url = baseURL + "topics"; //all topics

        MySingleton.getInstance(getContext()).addToRequestQueue(new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        topics = new ArrayList<>();
                        topicsIds = new ArrayList<>();
                        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, topics);
                        listView.setAdapter(adapter);
                        Topic tmp;
                        JSONObject obj = new JSONObject();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                obj = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tmp = new Gson().fromJson(String.valueOf(obj), Topic.class);
                            try {
                                topics.add(tmp.getName());
                                topicsIds.add(tmp.getEntityId());
                            }catch (Exception ignored){}
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }));

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(),GrappoActivity.class)
                        .putExtra("topicId", topicsIds.get(i)).putExtra("topicName", topics.get(i)));

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
