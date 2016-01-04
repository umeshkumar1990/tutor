package com.app.studio.tutor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.app.studio.tutor.R;
import com.app.studio.tutor.adapter.TutorAdapter;
import com.app.studio.tutor.interfaces.OnWebServiceListener;
import com.app.studio.tutor.model.TutorBean;
import com.app.studio.tutor.model.WebServiceModel;
import com.app.studio.tutor.webservice.WebAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

public class TutorListFragment extends Fragment implements OnWebServiceListener {

	private View 		 rootView;
	private ListView     tutorListView;
	private TutorAdapter tutorAdapter;
	private Context      mContext;
	private ArrayList<TutorBean> tutorArrayList;
	private LinearLayout acceptLayout;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		rootView		=	inflater.inflate(R.layout.activity_tutor_list, container, false);

		mContext		=	getActivity();
		tutorListView	=	(ListView)rootView.findViewById(R.id.tutor_list);
		acceptLayout	=	(LinearLayout)rootView.findViewById(R.id.accept_layout);
		tutorArrayList	=	new ArrayList<TutorBean>();

        int num         =   getArguments().getInt("menu_position");

		for(int i=0; i<num; i++) {

			TutorBean tutorBean	=	new TutorBean();
			tutorBean.setDistance(i+" km");
			tutorBean.setLocation("jaipur");
			tutorBean.setSkills("Class VI-VIII,Bcom Tution");
			tutorBean.setTutor("Teacher");
			tutorArrayList.add(tutorBean);
		}

		tutorAdapter	=	new TutorAdapter(getActivity(), tutorArrayList);
		tutorListView.setAdapter(tutorAdapter);

		tutorListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Toast.makeText(mContext, "Distance : " + tutorArrayList.get(position).getDistance(), Toast.LENGTH_SHORT).show();
			}
		});

		acceptLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Fragment fragment = new AcceptedFragment();

				FragmentManager fragmentManager = getFragmentManager();//getSupportFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}
		});

		/*HashMap<String, String> params	=	new HashMap<>();
		params.put("user_id", "10");
		params.put("name", "umesh");

		WebServiceModel webServiceModel	=	new WebServiceModel();
		webServiceModel.setContext(getActivity());
		webServiceModel.setProgressMessage("Loading...");
		webServiceModel.setProgressVisibility(true);
		webServiceModel.setRequestTag("TUTOR_LIST");
		webServiceModel.setUrl("http://www.google.com");
		webServiceModel.setWebServiceListener(this);
		webServiceModel.setParams(params);

		new WebAsyncTask(webServiceModel).execute();*/

		return rootView;
	}

	@Override
	public void onWebServiceSuccess(int statusCode, String response, String tag) {

	}

	@Override
	public void onWebServiceFailure(int statusCode, String errorMsg, String tag) {

	}
}
